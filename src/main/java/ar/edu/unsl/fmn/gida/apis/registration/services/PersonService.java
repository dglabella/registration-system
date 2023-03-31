package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.time.LocalDate;
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
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.model.auxiliaries.Check;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.PersonRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CheckValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.PersonValidator;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.QrCypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters.QrDataConverter;
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

	private final PersonValidator personValidator =
			new PersonValidator(new CustomExpressionValidator(),
					RegistrationSystemApplication.MESSENGER.getPersonValidationMessenger());

	private final CheckValidator checkValidator =
			new CheckValidator(new CustomExpressionValidator(),
					RegistrationSystemApplication.MESSENGER.getRegisterValidationMessenger());

	private final QrDataConverter converter = new QrDataConverter(new QrCypher());

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

	public Page<Person> getAll(int page, int size) {
		Page<Person> persons = this.repository.findAllByActiveTrue(PageRequest.of(page, size));

		for (Person person : persons) {
			person.setCurrentWeekly(this.weeklyService
					.getCurrentWeeklyFromPersonWithResponsibilities(person.getId()));
			person.setCredential(this.credentialService.getOneByPersonId(person.getId()));
		}

		return persons;
	}

	public Person getOneByDni(String dni) {
		Person person =
				this.repository.findByDniAndActiveTrue(dni)
						.orElseThrow(
								() -> new ErrorResponse(
										RegistrationSystemApplication.MESSENGER
												.getPersonServiceMessenger().notFoundByDni(dni),
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

	public Person getOneByDniWithRegistersBetweenDates(String dni, String from, String to, int page,
			int size) {
		Page<Register> registers;
		Person person =
				this.repository.findByDniAndActiveTrue(dni)
						.orElseThrow(
								() -> new ErrorResponse(
										RegistrationSystemApplication.MESSENGER
												.getPersonServiceMessenger().notFoundByDni(dni),
										HttpStatus.NOT_FOUND));

		registers = this.registerService.getAllFromPerson(person.getId(), from, to, page, size);

		person.setRegisters(registers.getContent());

		return person;
	}

	public void insert(Person requestBody) {
		this.personValidator.validateInsert(requestBody);

		this.repository.findByDniAndActiveTrue(requestBody.getDni()).ifPresent(u -> {
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
							.alreadyExistConstraint(Person.class.getSimpleName(), "dni",
									requestBody.getDni()),
					HttpStatus.CONFLICT);
		});

		// save person
		Person person = this.repository.save(requestBody);


		try {
			// QR generation code
			Credential credential = null;
			credential = new Credential();
			credential.setPersonId(person.getId());
			credential.setData(this.converter.stringify(person));
			credential.setImg(new CustomQRGenerator().generate(credential.getData(), 350, 350));

			// save qr
			this.credentialService.insert(credential);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getPersonServiceMessenger().qrGenerationFailed(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// save weekly if exist
		if (requestBody.getCurrentWeekly() != null) {
			this.weeklyService.insert(person.getId(), requestBody.getCurrentWeekly());
		}
	}

	public void update(int id, Person requestBody) {
		this.personValidator.validateUpdate(requestBody);

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
		this.weeklyService.deleteAll(id);
		this.registerService.deleteAll(person.getId());

		person.setActive(false);

		return person;
	}

	public Person checkQr(Check requestBody) {
		this.checkValidator.validateInsert(requestBody);

		int personId;
		try {
			// decode QR
			personId = this.converter.objectify(requestBody.getEncryptedData()).getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getPersonServiceMessenger().qrDecodeFailed(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Person person = this.repository.findByIdAndActiveTrue(personId)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
								.notFound(Person.class.getSimpleName(), personId),
						HttpStatus.NOT_FOUND));

		// save register
		LocalDate checkingDate = this.registerService
				.insert(person.getId(), requestBody.getAccessId()).getTime().toLocalDate();

		Weekly weekly = this.weeklyService
				.getWeeklyWithResponsibilitiesFromPersonContainingDate(personId, checkingDate);

		List<Register> dateRegisters =
				this.registerService.getAllFromPersonWithDate(personId, checkingDate);

		// this.registerService.getAllFromPerson(personId, from, to);

		if (weekly != null) {
			// do calculation for work attendance
			this.weeklyService.workAttendanceCalculation(weekly, checkingDate, dateRegisters);
		}
		// throw new ErrorResponse(
		// RegistrationSystemApplication.MESSENGER.getWeeklyServiceMessenger()
		// .notFoundByPersonIdAndContainingDate(personId, checkingDate.toString()),
		// HttpStatus.NOT_FOUND);

		return person;
	}
}
