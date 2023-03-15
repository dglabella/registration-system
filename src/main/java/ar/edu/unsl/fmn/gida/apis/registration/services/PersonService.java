package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
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
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.PersonRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.PersonValidator;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.Cypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.QrCypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.qr.CustomQRGenerator;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private WeeklyService weeklyService;

	@Autowired
	private CredentialService credentialService;

	@Autowired
	private RegisterService registerService;

	private final PersonValidator validator = new PersonValidator(new CustomExpressionValidator(),
			RegistrationSystemApplication.MESSENGER.getPersonValidationMessenger());

	private final Cypher cypher = new QrCypher();

	public Person getOne(int id) {
		Person person = null;
		person = this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(
						() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
										.notFound(Person.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));

		person.setCredential(this.credentialService.getOneByPersonId(id));

		Weekly currentWeekly = this.weeklyService.getCurrentWeeklyFromPerson(person.getId());
		person.setCurrentWeekly(currentWeekly);

		return person;
	}

	public Page<Person> getAll(int page, int quantityPerPage) {
		Page<Person> persons =
				this.repository.findAllByActiveTrue(PageRequest.of(page, quantityPerPage));

		for (Person person : persons) {
			person.setCurrentWeekly(this.weeklyService
					.getCurrentWeeklyFromPersonWithResponsibilities(person.getId()));
			person.setCredential(this.credentialService.getOneByPersonId(person.getId()));
		}

		return persons;
	}

	public Person getOneByDni(String dni) {
		Person person = this.repository.findByDniAndActiveTrue(dni)
				.orElseThrow(() -> new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getPersonServiceMessenger().notFoundByDniErrorMessage(dni),
						HttpStatus.NOT_FOUND));

		person.setCurrentWeekly(this.weeklyService.getCurrentWeeklyFromPerson(person.getId()));

		return person;
	}

	public List<Person> getAllByDniApproachOrderByIdAsc(String dni) {
		return this.repository.findByDniContainingAndActiveTrueOrderByIdAsc(dni);
	}

	public Page<Person> getAllByDniApproach(String dni, int page, int size) {
		return this.repository.findByDniContainingAndActiveTrue(dni, PageRequest.of(page, size));
	}

	public Page<Person> getAllByNameApproach(String string, int page, int size) {
		return this.repository.findAllByPersonNameContainingAndActiveTrue(string,
				PageRequest.of(page, size));
	}

	public Page<Person> getAllByLastNameApproach(String string, int page, int size) {
		return this.repository.findAllByPersonLastNameContainingAndActiveTrue(string,
				PageRequest.of(page, size));
	}

	public void insert(Person requestBody) {
		this.validator.validateInsert(requestBody);

		this.repository.findByDniAndActiveTrue(requestBody.getDni()).ifPresent(u -> {
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
							.alreadyExistConstraint(Person.class.getSimpleName(), "dni",
									requestBody.getDni()),
					HttpStatus.CONFLICT);
		});

		// save person
		Person person = this.repository.save(requestBody);

		// QR generation code
		Credential credential = null;
		credential = new Credential();
		credential.setPersonId(person.getId());
		credential.setData(this.cypher.encrypt("" + person.getId()));
		credential.setImg(new CustomQRGenerator().generate(credential.getData(), 350, 350));

		// save qr
		this.credentialService.insert(credential);

		// save weekly if exist
		if (requestBody.getCurrentWeekly() != null) {
			this.weeklyService.insert(person.getId(), requestBody.getCurrentWeekly());
		}
	}

	public void update(int id, Person requestBody) {
		this.validator.validateUpdate(requestBody);

		this.repository.findOneByDniAndIdIsNot(requestBody.getDni(), id).ifPresent(person -> {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getPersonServiceMessenger()
					.alreadyExistConstraint(Person.class.getSimpleName(), "dni", person.getDni()),
					HttpStatus.CONFLICT);
		});

		// save person
		requestBody.setId(id);
		this.repository.save(requestBody);
	}

	public Person delete(int id) {

		Person person =
				this.repository.findByIdAndActiveTrue(id)
						.orElseThrow(() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
										.notFound(Person.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));

		this.credentialService.delete(person.getId());
		this.weeklyService.delete(person.getId());
		this.registerService.delete(person.getId());

		person.setActive(false);

		return person;
	}
}
