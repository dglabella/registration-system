package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
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
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.PersonRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.PersonValidator;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.PersonDetailsCypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters.PersonConverter;
import ar.edu.unsl.fmn.gida.apis.registration.utils.qr.CustomQRGenerator;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private WeeklyService weeklyService;

	@Autowired
	private CredentialService credentialService;

	@Autowired
	private RegisterService registerService;

	private PersonValidator personValidator = new PersonValidator(new CustomExpressionValidator(),
			RegistrationSystemApplication.MESSENGER.getPersonValidationMessenger());

	public Person getOne(int id) {
		Person person = null;
		person = this.personRepository.findByIdAndActiveTrue(id)
				.orElseThrow(
						() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
										.notFound(Person.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));

		person.setCurrentWeekly(this.weeklyService.getCurrentWeeklyFromPerson(person.getId()));
		person.setCredential(this.credentialService.getOneByPersonId(id));

		return person;
	}

	public Page<Person> getAll(int page, int quantityPerPage) {
		Page<Person> persons =
				this.personRepository.findAllByActiveTrue(PageRequest.of(page, quantityPerPage));

		for (Person person : persons) {
			person.setCurrentWeekly(this.weeklyService.getCurrentWeeklyFromPerson(person.getId()));
			person.setCredential(this.credentialService.getOneByPersonId(person.getId()));
		}

		return persons;
	}

	public Person getOneByDni(String dni) {
		Person person = this.personRepository.findByDniAndActiveTrue(dni)
				.orElseThrow(() -> new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getPersonServiceMessenger().notFoundByDniErrorMessage(dni),
						HttpStatus.NOT_FOUND));

		person.setCurrentWeekly(this.weeklyService.getCurrentWeeklyFromPerson(person.getId()));

		return person;
	}

	public List<Person> getAllByDniApproachOrderByIdAsc(String dni) {
		return this.personRepository.findByDniContainingAndActiveTrueOrderByIdAsc(dni);
	}

	public Page<Person> getAllByDniApproach(String dni, int page, int size) {
		return this.personRepository.findByDniContainingAndActiveTrue(dni,
				PageRequest.of(page, size));
	}

	public Page<Person> getAllByNameApproach(String string, int page, int size) {
		return this.personRepository.findAllByPersonNameContainingAndActiveTrue(string,
				PageRequest.of(page, size));
	}

	public Page<Person> getAllByLastNameApproach(String string, int page, int size) {
		return this.personRepository.findAllByPersonLastNameContainingAndActiveTrue(string,
				PageRequest.of(page, size));
	}

	public Person insert(Person person) {
		this.personValidator.validate(person);

		Credential credential = null;
		try {

			person.setId(this.personRepository.save(person).getId());

			// QR generation code
			credential = new Credential();
			credential.setPersonFk(person.getId());
			credential.setData(
					new PersonDetailsCypher().encrypt(new PersonConverter().stringify(person)));
			credential.setImg(new CustomQRGenerator().generate(credential.getData(), 350, 350));
			// save qr
			person.setCredential(this.credentialService.insert(credential));
			if (person.getCurrentWeekly() == null) {
				// setting default weekly
				person.setCurrentWeekly(new Weekly());
			}
			person.getCurrentWeekly().setPersonFk(person.getId());
			person.setCurrentWeekly(this.weeklyService.insert(person.getCurrentWeekly()));
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return person;
	}

	public Person update(int id, Person person) {
		this.personValidator.validate(person);
		Person ret = null;
		Optional<Person> personOptional = this.personRepository.findByIdAndActiveTrue(id);

		if (personOptional.isPresent()) {
			try {
				person.setId(id);
				if (person.getCurrentWeekly() != null) {
					person.getCurrentWeekly().setPersonFk(id);
					person.setCurrentWeekly(
							this.weeklyService.update(id, person.getCurrentWeekly()));
				}
				ret = this.personRepository.save(person);

				ret.setCurrentWeekly(this.weeklyService.getCurrentWeeklyFromPerson(person.getId()));
				ret.setCredential(this.credentialService.getOneByPersonId(id));

			} catch (DataIntegrityViolationException exception) {
				exception.printStackTrace();
				throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} else {
			// this error should not happen in a typical situation
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
							.updateNonExistentEntity(Person.class.getSimpleName(), id),
					HttpStatus.NOT_FOUND);
		}
		return ret;
	}

	public Person delete(int id) {

		Person person = new Person();

		person = this.personRepository.findByIdAndActiveTrue(id)
				.orElseThrow(
						() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getPersonServiceMessenger()
										.notFound(Person.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));
		try {

			this.credentialService.delete(person.getId());
			this.weeklyService.delete(person.getId());
			this.registerService.delete(person.getId());
			person.setActive(false);

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			throw new ErrorResponse(e.getMostSpecificCause().getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return person;
	}
}
