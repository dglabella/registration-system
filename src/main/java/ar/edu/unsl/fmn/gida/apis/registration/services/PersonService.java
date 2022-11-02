package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.PersonRepository;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.CustomCypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters.PersonConverter;
import ar.edu.unsl.fmn.gida.apis.registration.utils.qr.CustomQRGenerator;
import ar.edu.unsl.fmn.gida.apis.registration.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.validators.PersonValidator;

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
        Optional<Person> personOptional = this.personRepository.findByIdAndActiveIsTrue(id);

        if (personOptional.isPresent()) {
            person = personOptional.get();
            person.setCurrentWeekly(this.weeklyService.getCurrentWeeklyFromPerson(person.getId()));
        } else {
            throw new ErrorResponse("there is no person with id: " + id, HttpStatus.NOT_FOUND);
        }

        return person;
    }

    public Person getOneByDni(String dni) {
        Person person = null;
        Optional<Person> personOptional = this.personRepository.findByDniAndActiveIsTrue(dni);

        if (personOptional.isPresent()) {
            person = personOptional.get();
            person.setCurrentWeekly(this.weeklyService.getCurrentWeeklyFromPerson(person.getId()));
        } else {
            throw new ErrorResponse("there is no person with dni: " + dni, HttpStatus.NOT_FOUND);
        }

        return person;
    }

    public List<Person> getAllWithName(String name) {
        return this.personRepository.findAllByPersonNameAndActiveIsTrue(name);
    }

    public List<Person> getAllWithLastName(String lastName) {
        return this.personRepository.findAllByPersonLastNameAndActiveIsTrue(lastName);
    }

    public List<Person> getOneByDniApproach(String string) {
        return this.personRepository.findByDniContainingAndActiveIsTrue(string);
    }

    public List<Person> getAllWithNameApproach(String string) {
        return this.personRepository.findAllByPersonNameContainingAndActiveIsTrue(string);
    }

    public List<Person> getAllWithLastNameApproach(String string) {
        return this.personRepository.findAllByPersonLastNameContainingAndActiveIsTrue(string);
    }

    public List<Person> getAll() {
        List<Person> persons = this.personRepository.findAllByActiveTrue();
        return persons;
    }

    public List<Person> getAll(int page, int quantityPerPage) {
        // Pageable pageable = PageRequest.of(page, quantityPerPage);
        // List<Person> persons = this.personRepository.findAllByActiveTrue(pageable);
        return this.personRepository.findAllByActiveTrue(PageRequest.of(page, quantityPerPage));
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
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new ErrorResponse(e.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return person;
    }

    public Person update(int personId, Person person) {
        this.personValidator.validate(person);

        Optional<Person> personOptional = this.personRepository.findByIdAndActiveIsTrue(personId);

        if (personOptional.isPresent()) {
            try {
                person.setId(personId);
                if (person.getCurrentWeekly() != null) {
                    person.getCurrentWeekly().setPersonFk(personId);
                    this.weeklyService.update(personId, person.getCurrentWeekly());
                }

                this.personRepository.save(person);

            } catch (DataIntegrityViolationException exception) {
                exception.printStackTrace();
                throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } else {
            // this error should not happen in a typical situation
            throw new ErrorResponse(
                    "cannot update person with id " + personId + " because it doesn't exist",
                    HttpStatus.NOT_FOUND);
        }
        return person;
    }

    public Person delete(int id) {
        return null;
    }
}
