package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	private DependencyRepository dependencyRepository;

	private DependencyValidator dependencyValidator =
			new DependencyValidator(new CustomExpressionValidator(),
					RegistrationSystemApplication.MESSENGER.getAccessValidationMessenger());

	public Dependency getOne(int id) {
		Dependency d = null;
		Optional<Dependency> optional = dependencyRepository.findByIdAndActiveTrue(id);

		if (optional.isPresent()) {
			d = optional.get();
		} else {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getDependencyServiceMessenger().notFound(Dependency.class.getSimpleName(), id),
					HttpStatus.NOT_FOUND);
		}

		return d;
	}

	public Page<Dependency> getAll(int page, int quantity) {
		return dependencyRepository.findAllByActiveTrue(PageRequest.of(page, quantity));
	}

	public Dependency insert(Dependency dependency) {
		this.dependencyValidator.validateInsert(dependency);

		Dependency d = null;
		try {
			d = dependencyRepository.save(dependency);
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return d;
	}

	public Dependency update(int id, Dependency dependency) {
		this.dependencyValidator.validateUpdate(dependency);

		Dependency d = null;

		Optional<Dependency> optional = dependencyRepository.findByIdAndActiveTrue(id);
		if (optional.isPresent()) {
			try {
				dependency.setId(id);
				dependencyRepository.save(dependency);

			} catch (DataIntegrityViolationException exception) {
				exception.printStackTrace();
				throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}

		} else {
			// this error should not happen in a typical situation
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getDependencyServiceMessenger()
							.updateNonExistentEntity(Dependency.class.getSimpleName(), id),
					HttpStatus.NOT_FOUND);
		}
		return d;
	}

	public Dependency delete(int id) {
		throw new ErrorResponse("delete dependency operation not implemented yet...",
				HttpStatus.NOT_IMPLEMENTED);
	}
}
