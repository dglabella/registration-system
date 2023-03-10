package ar.edu.unsl.fmn.gida.apis.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.AccessRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.AccessValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;

@Service
@Transactional
public class AccessService {

	@Autowired
	private AccessRepository repository;

	private final AccessValidator validator = new AccessValidator(new CustomExpressionValidator(),
			RegistrationSystemApplication.MESSENGER.getAccessValidationMessenger());

	public Access getOne(int id) {
		Access ret =
				this.repository.findByIdAndActiveTrue(id)
						.orElseThrow(() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getAccessServiceMessenger()
										.notFound(Access.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));

		return ret;
	}

	public Page<Access> getAll(int page, int quantity) {
		return this.repository.findAllByActiveTrue(PageRequest.of(page, quantity));
	}

	public Access insert(Access requestBody) {
		this.validator.validateInsert(requestBody);
		return this.repository.save(requestBody);
	}

	public Access update(int id, Access requestBody) {
		this.validator.validateUpdate(requestBody);

		this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getAccessServiceMessenger()
								.updateNonExistentEntity(Access.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));

		requestBody.setId(id);
		return this.repository.save(requestBody);
	}

	public Access delete(int id) {
		Access ret = this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getAccessServiceMessenger()
								.updateNonExistentEntity(Access.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));

		ret.setActive(false);

		return ret;
	}
}
