package ar.edu.unsl.fmn.gida.apis.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.DependencyRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.DependencyValidator;

@Service
@Transactional
public class DependencyService {

	@Autowired
	private DependencyRepository repository;

	private final DependencyValidator validator =
			new DependencyValidator(new CustomExpressionValidator(),
					RegistrationSystemApplication.MESSENGER.getAccessValidationMessenger());

	public Dependency getOne(int id) {
		return this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getDependencyServiceMessenger()
								.notFound(Dependency.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));
	}

	public Page<Dependency> getAll(int page, int quantity) {
		return this.repository.findAllByActiveTrue(PageRequest.of(page, quantity));
	}

	public Dependency insert(Dependency requestBody) {
		this.validator.validateInsert(requestBody);

		this.repository.findByDependencyNameAndActiveTrue(requestBody.getDependencyName())
				.ifPresent((dependency) -> {
					throw new ErrorResponse(
							RegistrationSystemApplication.MESSENGER.getDependencyServiceMessenger()
									.alreadyExistConstraint(Dependency.class.getSimpleName(),
											"dependency name", dependency.getDependencyName()),
							HttpStatus.CONFLICT);
				});

		return this.repository.save(requestBody);
	}

	public Dependency update(int id, Dependency requestBody) {
		this.validator.validateUpdate(requestBody);

		this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getDependencyServiceMessenger()
								.updateNonExistentEntity(Dependency.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));

		requestBody.setId(id);

		return this.repository.save(requestBody);
	}

	public Dependency delete(int id) {
		Dependency ret = this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getDependencyServiceMessenger()
								.updateNonExistentEntity(Dependency.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));

		ret.setActive(false);

		return ret;
	}
}
