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
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.CustomCypher;
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

    private PersonValidator personValidator = new PersonValidator(new CustomExpressionValidator());

    public Person getOne(int id) {
        Person person = null;
        Optional<Person> personOptional = this.personRepository.findByIdAndActiveTrue(id);
        if (personOptional.isPresent()) {
            person = personOptional.get();
            person.setCurrentWeekly(this.weeklyService.getCurrentWeeklyFromPerson(person.getId()));
            person.setCredential(this.credentialService.getOneByPersonId(id));
        } else {
            throw new ErrorResponse(RegistrationSystemApplication.MESSAGES.getPersonMessages()
                    .notFoundErrorMessage(Person.class.getSimpleName(), id), HttpStatus.NOT_FOUND);
        }

        return person;
    }

    public Person getOneByDni(String dni) {
        Person person = null;
        Optional<Person> personOptional = this.personRepository.findByDniAndActiveTrue(dni);

        if (personOptional.isPresent()) {
            person = personOptional.get();
            person.setCurrentWeekly(this.weeklyService.getCurrentWeeklyFromPerson(person.getId()));
        } else {
            throw new ErrorResponse(RegistrationSystemApplication.MESSAGES.getPersonMessages()
                    .notFoundByDniErrorMessage(dni), HttpStatus.NOT_FOUND);
        }

        return person;
    }

    public List<Person> getAllWithName(String name) {
        return this.personRepository.findAllByPersonNameAndActiveTrue(name);
    }

    public List<Person> getAllWithLastName(String lastName) {
        return this.personRepository.findAllByPersonLastNameAndActiveTrue(lastName);
    }

    public List<Person> getOneByDniApproach(String string) {
        return this.personRepository.findByDniContainingAndActiveTrue(string);
    }

    public List<Person> getAllWithNameApproach(String string) {
        return this.personRepository.findAllByPersonNameContainingAndActiveTrue(string);
    }

    public List<Person> getAllWithLastNameApproach(String string) {
        return this.personRepository.findAllByPersonLastNameContainingAndActiveTrue(string);
    }

    public List<Person> getAll() {
        List<Person> persons = this.personRepository.findAllByActiveTrue();
        return persons;
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

    public Person insert(Person person) {
        this.personValidator.validate(person);

        Credential credential = null;
        try {

            person.setId(this.personRepository.save(person).getId());

            // QR generation code
            credential = new Credential();
            credential.setPersonFk(person.getId());
            credential.setData(new CustomCypher().encrypt(new PersonConverter().stringify(person)));
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
                    RegistrationSystemApplication.MESSAGES.getPersonMessages()
                            .updateNonExistentEntityErrorMessage(Person.class.getSimpleName(), id),
                    HttpStatus.NOT_FOUND);
        }
        return ret;
    }

    public Person delete(int id) {
        throw new ErrorResponse("delete person operation not implemented yet...",
                HttpStatus.NOT_FOUND);
    }
}
