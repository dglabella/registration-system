package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.enums.WorkAttendanceState;
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
		return this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(
						() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
										.notFound(Person.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));
	}

	public Person getOneWithCredential(int id) {
		Person person =
				this.repository.findByIdAndActiveTrue(id)
						.orElseThrow(() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
										.notFound(Person.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));

		person.setCredential(this.credentialService.getOneByPersonId(id));

		Weekly currentWeekly =
				this.weeklyService.getFromPersonContainingDate(person.getId(), LocalDate.now());
		person.setCurrentWeekly(currentWeekly);

		return person;
	}

	public Person getOneByDniWithCredential(String dni) {
		Person person =
				this.repository.findByDniAndActiveTrue(dni)
						.orElseThrow(
								() -> new ErrorResponse(
										RegistrationSystemApplication.MESSENGER
												.getPersonServiceMessenger().notFoundByDni(dni),
										HttpStatus.NOT_FOUND));

		person.setCredential(this.credentialService.getOneByPersonId(person.getId()));

		// person.setCurrentWeekly(
		// this.weeklyService.getCurrentWeeklyFromPersonWithResponsibilities(person.getId()));

		return person;
	}

	public Person getOneByDniWithRegistersBetweenDates(String dni, String from, String to, int page,
			int size) {
		List<Register> registers;
		Person person =
				this.repository.findByDniAndActiveTrue(dni)
						.orElseThrow(
								() -> new ErrorResponse(
										RegistrationSystemApplication.MESSENGER
												.getPersonServiceMessenger().notFoundByDni(dni),
										HttpStatus.NOT_FOUND));

		// registers = this.registerService.getAllFromPerson(person.getId(), from, to, page, size);
		registers = this.registerService.getAllFromPerson(person.getId(), from, to);

		person.setRegisters(registers);

		return person;
	}

	public Page<Person> getAllEachWithCredential(int page, int size) {
		Page<Person> personsPage = this.repository.findAllByActiveTrue(PageRequest.of(page, size));

		for (Person person : personsPage.getContent()) {
			person.setCredential(this.credentialService.getOneByPersonId(person.getId()));
			// person.setCurrentWeekly(this.weeklyService
			// .getCurrentWeeklyFromPersonWithResponsibilities(person.getId()));
		}

		return personsPage;
	}

	public Page<Person> getAllByDniApproachEachWithCredential(String dni, int page, int size) {
		Page<Person> personPage = this.repository.findAllByDniContainingAndActiveTrue(dni,
				PageRequest.of(page, size));

		for (Person person : personPage.getContent()) {
			person.setCredential(this.credentialService.getOneByPersonId(person.getId()));
			// person.setCurrentWeekly(this.weeklyService
			// .getCurrentWeeklyFromPersonWithResponsibilities(person.getId()));
		}

		return personPage;
	}

	public Page<Person> getAllByDniApproachOrderByIdAscEachWithCredential(String dni, int page,
			int size) {
		Page<Person> personsPage = this.repository
				.findAllByDniContainingAndActiveTrueOrderByIdAsc(dni, PageRequest.of(page, size));

		for (Person person : personsPage.getContent()) {
			person.setCredential(this.credentialService.getOneByPersonId(person.getId()));
			// person.setCurrentWeekly(this.weeklyService
			// .getCurrentWeeklyFromPersonWithResponsibilities(person.getId()));
		}

		return personsPage;
	}

	public Page<Person> getAllByPersonNameApproachEachWithCredential(String name, int page,
			int size) {
		Page<Person> personsPage = this.repository.findAllByPersonNameContainingAndActiveTrue(name,
				PageRequest.of(page, size));

		for (Person person : personsPage.getContent()) {
			person.setCredential(this.credentialService.getOneByPersonId(person.getId()));
			// person.setCurrentWeekly(this.weeklyService
			// .getCurrentWeeklyFromPersonWithResponsibilities(person.getId()));
		}
		return personsPage;
	}

	public Page<Person> getAllByPersonLastNameApproachEachWithCredential(String name, int page,
			int size) {
		Page<Person> personsPage = this.repository
				.findAllByPersonLastNameContainingAndActiveTrue(name, PageRequest.of(page, size));

		for (Person person : personsPage.getContent()) {
			person.setCredential(this.credentialService.getOneByPersonId(person.getId()));
			// person.setCurrentWeekly(this.weeklyService
			// .getCurrentWeeklyFromPersonWithResponsibilities(person.getId()));
		}
		return personsPage;
	}

	public Page<Person> getAllEachWithWorkAttendancesBetweenDates(String from, String to, int page,
			int size) {
		Page<Person> personsPage = this.repository.findAllByActiveTrue(PageRequest.of(page, size));

		LocalDate fromDate;
		LocalDate toDate;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? LocalDate.parse(from)
					: LocalDate.of(1973, 5, 10);

			toDate = (to != null && to.trim().length() != 0) ? LocalDate.parse(to)
					: LocalDate.now();
			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().crossDates(), HttpStatus.BAD_REQUEST);


		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		for (Person person : personsPage.getContent()) {
			List<Weekly> weeklies =
					this.weeklyService.getAllFromPersonEachWithWorkAttendancesBetweenDates(
							person.getId(), fromDate, toDate);
			person.setWeeklies(weeklies);
		}

		return personsPage;
	}

	public Page<Person> getAllEachWithWorkAttendancesByStateBetweenDates(WorkAttendanceState state,
			String from, String to, int page, int size) {
		Page<Person> personsPage = this.repository.findAllByActiveTrue(PageRequest.of(page, size));

		LocalDate fromDate;
		LocalDate toDate;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? LocalDate.parse(from)
					: LocalDate.of(1973, 5, 10);

			toDate = (to != null && to.trim().length() != 0) ? LocalDate.parse(to)
					: LocalDate.now();
			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().crossDates(), HttpStatus.BAD_REQUEST);


		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		for (Person person : personsPage.getContent()) {
			List<Weekly> weeklies =
					this.weeklyService.getAllFromPersonEachWithWorkAttendancesByStateBetweenDates(
							person.getId(), state, fromDate, toDate);
			person.setWeeklies(weeklies);
		}

		return personsPage;
	}

	public Page<Person> getByDniWithWorkAttendancesBetweenDates(String dni, String from, String to,
			int page, int size) {
		Optional<Person> optional = this.repository.findByDniAndActiveTrue(dni);
		Person person =
				optional.orElseThrow(() -> new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getPersonServiceMessenger().notFoundByDni(dni), HttpStatus.NOT_FOUND));

		LocalDate fromDate;
		LocalDate toDate;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? LocalDate.parse(from)
					: LocalDate.of(1973, 5, 10);

			toDate = (to != null && to.trim().length() != 0) ? LocalDate.parse(to)
					: LocalDate.now();
			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().crossDates(), HttpStatus.BAD_REQUEST);


		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		List<Weekly> weeklies =
				this.weeklyService.getAllFromPersonEachWithWorkAttendancesBetweenDates(
						person.getId(), fromDate, toDate);

		person.setWeeklies(weeklies);

		List<Person> content = new ArrayList<>(1);
		content.add(person);
		return new PageImpl<Person>(content, PageRequest.of(0, 1), 1);
	}

	public Page<Person> getByDniWithWorkAttendanceByStateBetweenDates(String dni,
			WorkAttendanceState state, String from, String to, int page, int size) {
		Optional<Person> optional = this.repository.findByDniAndActiveTrue(dni);
		Person person =
				optional.orElseThrow(() -> new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getPersonServiceMessenger().notFoundByDni(dni), HttpStatus.NOT_FOUND));

		LocalDate fromDate;
		LocalDate toDate;

		try {
			fromDate = (from != null && from.trim().length() != 0) ? LocalDate.parse(from)
					: LocalDate.of(1973, 5, 10);

			toDate = (to != null && to.trim().length() != 0) ? LocalDate.parse(to)
					: LocalDate.now();
			if (fromDate.compareTo(toDate) > 0)
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getRegisterServiceMessenger().crossDates(), HttpStatus.BAD_REQUEST);

		} catch (DateTimeParseException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getRegisterServiceMessenger().dateFormatSpecificationError(),
					HttpStatus.BAD_REQUEST);
		}

		List<Weekly> weeklies =
				this.weeklyService.getAllFromPersonEachWithWorkAttendancesByStateBetweenDates(
						person.getId(), state, fromDate, toDate);

		person.setWeeklies(weeklies);

		List<Person> content = new ArrayList<>(1);
		content.add(person);
		return new PageImpl<Person>(content, PageRequest.of(0, 1), 1);
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

		this.repository.findByDniAndIdIsNot(requestBody.getDni(), id).ifPresent(person -> {
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
				.insert(person.getId(), requestBody.getAccessId(), requestBody.getTime()).getTime()
				.toLocalDate();

		// Weekly weekly =
		// this.weeklyService.getFromPersonWithResponsibilitiesContainingDate(personId,
		// checkingDate);
		//
		// List<Register> dateRegisters =
		// this.registerService.getAllFromPersonWithDate(personId, checkingDate);
		//
		// if (weekly != null) {
		// // do calculation for work attendance
		// this.weeklyService.workAttendanceCalculation(weekly, checkingDate, dateRegisters);
		// }

		Weekly weekly = this.weeklyService.getFromPersonWithResponsibilitiesContainingDate(personId,
				requestBody.getTime().toLocalDate());

		List<Register> dateRegisters = this.registerService.getAllFromPersonWithDate(personId,
				requestBody.getTime().toLocalDate());

		// this.registerService.getAllFromPerson(personId, from, to);
		if (weekly != null) {
			// do calculation for work attendance
			this.weeklyService.workAttendanceCalculation(weekly,
					requestBody.getTime().toLocalDate(), dateRegisters);
		}

		return person;
	}
}
