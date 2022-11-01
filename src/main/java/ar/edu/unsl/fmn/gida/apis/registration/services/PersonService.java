package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
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
import ar.edu.unsl.fmn.gida.apis.registration.repositories.CredentialRepository;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.PersonRepository;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WeeklyRepository;
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
    private WeeklyRepository weeklyRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    public Person getOne(int id) {
        Person person = null;
        Optional<Person> personOptional = this.personRepository.findByIdAndActiveIsTrue(id);

        if (personOptional.isPresent()) {
            person = personOptional.get();
            Optional<Weekly> weeklyOptional =
                    this.weeklyRepository.findByPersonFkAndEndIsNullAndActiveIsTrue(person.getId());
            if (weeklyOptional.isPresent()) {
                person.setCurrentWeekly(weeklyOptional.get());
            } else {
                throw new ErrorResponse("FATAL ERROR, Corrupt Database Integrity",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new ErrorResponse("there is no person with id: " + id, HttpStatus.NOT_FOUND);
        }

        return person;
    }

    public List<Person> getAllWithName(String name) {
        List<Person> persons = this.personRepository.findAllByPersonNameAndActiveTrue(name);

        for (Person person : persons)
            person.setDependency(null);

        return persons;
    }

    public List<Person> getAllWithLastName(String lastName) {
        List<Person> persons = this.personRepository.findAllByPersonNameAndActiveTrue(lastName);

        for (Person person : persons)
            person.setDependency(null);

        return persons;
    }

    public Person getOneByDni(String dni) {
        Person person = null;
        Optional<Person> personOptional = this.personRepository.findByDniAndActiveTrue(dni);

        if (personOptional.isPresent()) {
            person = personOptional.get();
            Optional<Weekly> weeklyOptional =
                    this.weeklyRepository.findByPersonFkAndEndIsNullAndActiveIsTrue(person.getId());
            if (weeklyOptional.isPresent()) {
                person.setCurrentWeekly(weeklyOptional.get());
            } else {
                throw new ErrorResponse("FATAL ERROR, Corrupt Database Integrity",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new ErrorResponse("there is no person with dni: " + dni, HttpStatus.NOT_FOUND);
        }

        return person;
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
        new PersonValidator(new CustomExpressionValidator()).validate(person);

        Credential credential = null;

        if (person.getCurrentWeekly() == null) {
            // setting default weekly
            person.setCurrentWeekly(new Weekly());
        }

        try {
            // QR generation code
            person.setId(this.personRepository.save(person).getId());

            credential = new Credential();
            credential.setPersonFk(person.getId());
            credential.setData(new CustomCypher().encrypt(new PersonConverter().stringify(person)));
            credential.setImg(new SerialBlob(
                    new CustomQRGenerator().generate(credential.getData(), 350, 350)));
            // save qr
            credential = this.credentialRepository.save(credential);
            person.setCredential(credential);

            person.getCurrentWeekly().setPersonFk(person.getId());
            person.getCurrentWeekly()
                    .setId(this.weeklyRepository.save(person.getCurrentWeekly()).getId());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new ErrorResponse(e.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (SerialException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public Person update(int id, Person person) {
        new PersonValidator(new CustomExpressionValidator()).validate(person);
        Weekly w = null;

        Optional<Person> personOptional = this.personRepository.findByIdAndActiveIsTrue(id);
        if (personOptional.isPresent()) {
            try {
                person.setId(id);
                // get the current weekly
                Optional<Weekly> weeklyOptional = this.weeklyRepository
                        .findByPersonFkAndEndIsNullAndActiveIsTrue(person.getId());

                if (weeklyOptional.isPresent()) {
                    if (!weeklyOptional.get().equals(person.getCurrentWeekly())) {

                        w = weeklyOptional.get();
                        w.setEnd(new Date());
                        // close the old weekly
                        w = this.weeklyRepository.save(w);

                        // now it's okey to update the weekly and the person
                        person.getCurrentWeekly().setPersonFk(person.getId());
                        this.weeklyRepository.save(person.getCurrentWeekly());// this is already
                                                                              // detached
                        this.personRepository.save(person);
                    } else {
                        // save only the person if weekly
                        person.getCurrentWeekly().setPersonFk(person.getId());
                        this.personRepository.save(person);
                    }
                } else {
                    throw new ErrorResponse("FATAL ERROR, Corrupt Database Integrity",
                            HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (DataIntegrityViolationException exception) {
                exception.printStackTrace();
                throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } else {
            // this error should not happen in a typical situation
            throw new ErrorResponse(
                    "cannot update person with id " + id + " because it doesn't exist",
                    HttpStatus.NOT_FOUND);
        }

        return person;
    }

    public Person delete(int id) {
        return null;
    }
}
