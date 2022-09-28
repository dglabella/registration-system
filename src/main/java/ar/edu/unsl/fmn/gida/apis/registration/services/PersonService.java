package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.PersonRepository;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WeeklyRepository;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WeeklyRepository weeklyRepository;

    public Person getOne(int id) {
        Person person = null;
        Optional<Person> optional1 = this.personRepository.findByIdAndActiveIsTrue(id);

        if (optional1.isPresent()) {
            person = optional1.get();
            Optional<Weekly> optional2 =
                    this.weeklyRepository.findByPersonFkAndEndIsNullAndActiveIsTrue(person.getId());
            if (optional2.isPresent()) {
                person.setCurrentWeekly(optional2.get());
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

    public Person getOneByDni(int dni) {
        Person person = null;
        Optional<Person> optional = this.personRepository.findByDniAndActiveTrue(dni);

        if (optional.isPresent()) {
            person = optional.get();
            Optional<Weekly> optional2 =
                    this.weeklyRepository.findByPersonFkAndEndIsNullAndActiveIsTrue(person.getId());
            if (optional2.isPresent()) {
                person.setCurrentWeekly(optional2.get());
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

        for (Person person : persons)
            person.setDependency(null);

        return persons;
    }

    @Transactional
    public Person insert(Person person) {
        Person p = null;

        try {
            p = this.personRepository.save(person);
            p.getCurrentWeekly().setPersonFk(p.getId());
            this.weeklyRepository.save(p.getCurrentWeekly());
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return p;
    }

    @Transactional
    public Person update(int id, Person person) {
        Weekly w = null;

        Optional<Person> optional1 = this.personRepository.findByIdAndActiveIsTrue(id);
        if (optional1.isPresent()) {
            try {
                person.setId(id);
                Optional<Weekly> optional2 = this.weeklyRepository
                        .findByPersonFkAndEndIsNullAndActiveIsTrue(person.getId());

                if (optional2.isPresent() && !optional2.get().equals(person.getCurrentWeekly())) {

                    w = optional2.get();
                    w.setEnd(new Date());
                    w = this.weeklyRepository.save(w);

                    this.personRepository.save(person);
                    person.getCurrentWeekly().setPersonFk(person.getId());
                    this.weeklyRepository.save(person.getCurrentWeekly());
                } else {
                    person.getCurrentWeekly().setPersonFk(person.getId());
                    this.personRepository.save(person);
                    this.weeklyRepository.save(person.getCurrentWeekly());
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
