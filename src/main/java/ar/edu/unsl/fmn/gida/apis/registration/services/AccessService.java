package ar.edu.unsl.fmn.gida.apis.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	private AccessRepository accessRepository;

	private AccessValidator accessValidator = new AccessValidator(new CustomExpressionValidator(),
			RegistrationSystemApplication.MESSENGER.getAccessValidationMessenger());

	public Access getOne(int id) {
		Access ret =
				accessRepository.findByIdAndActiveTrue(id)
						.orElseThrow(() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getAccessServiceMessenger()
										.notFound(Access.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));

		return ret;
	}

	public Page<Access> getAll(int page, int quantity) {
		return accessRepository.findAllByActiveTrue(PageRequest.of(page, quantity));
	}

	public Access insert(Access access) {
		this.accessValidator.validate(access);
		Access ret;
		try {
			ret = accessRepository.save(access);
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return ret;
	}

	public Access update(int id, Access access) {
		this.accessValidator.validate(access);
		Access ret = this.accessRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getAccessServiceMessenger()
								.updateNonExistentEntity(Access.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));

		try {
			access.setId(id);
			accessRepository.save(access);
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return ret;
	}

	public Access delete(int id) {
		return null;
	}
}
