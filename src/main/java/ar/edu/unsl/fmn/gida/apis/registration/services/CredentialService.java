package ar.edu.unsl.fmn.gida.apis.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.CredentialRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CredentialValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;

@Service
@Transactional
public class CredentialService {

	@Autowired
	private CredentialRepository repository;

	private final CredentialValidator validator =
			new CredentialValidator(new CustomExpressionValidator(),
					RegistrationSystemApplication.MESSENGER.getCredentialValidationMessenger());

	public Credential get(Integer id) {
		return this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getCredentialServiceMessenger().notFound(Person.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));
	}

	public Credential getByPersonId(Integer personId) {
		return this.repository.findByPersonIdAndActiveTrue(personId)
				.orElseThrow(() -> new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getCredentialServiceMessenger().notFoundByPersonIdErrorMessage(personId),
						HttpStatus.NOT_FOUND));
	}

	public Page<Credential> getAll(int page, int quantity) {
		return this.repository.findAllByActiveTrue(PageRequest.of(page, quantity));
	}

	public Credential insert(Credential body) {
		if (body == null)
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getCredentialServiceMessenger()
							.unspecifiedEntity(Credential.class.getSimpleName()),
					HttpStatus.UNPROCESSABLE_ENTITY);

		this.validator.validateInsert(body);

		return this.repository.save(body);
	}

	public Credential update(int id, Credential body) {
		throw new ErrorResponse("update credential operation not implemented yet...",
				HttpStatus.NOT_IMPLEMENTED);
	}

	public void delete(int personId) {

		Credential credential = this.repository.findByPersonIdAndActiveTrue(personId)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getCredentialServiceMessenger()
								.deleteNonExistentEntityCorruptDB(Person.class.getSimpleName(),
										Credential.class.getSimpleName(), personId),
						HttpStatus.NOT_FOUND));

		credential.setActive(false);
	}
}
