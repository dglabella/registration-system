package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.PersonRepository;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getOne(int id) {
        Person person = null;
        Optional<Person> optional = personRepository.findByIdAndActiveIsTrue(id);

        if (optional.isPresent()) {
            person = optional.get();
        } else {
            throw new ErrorResponse("there is no person with id: " + id, HttpStatus.NOT_FOUND);
        }

        return person;
    }

    public List<Person> getAllWithName(String name) {
        return this.personRepository.findAllByNameAndActiveTrue(name);
    }

    public List<Person> getAllWithLastName(String lastName) {
        return this.personRepository.findAllByLastNameAndActiveTrue(lastName);
    }

    public Person getOneByDni(int dni) {
        Person person = null;
        Optional<Person> optional = this.personRepository.findByDniAndActiveTrue(dni);

        if (optional.isPresent()) {
            person = optional.get();
        } else {
            throw new ErrorResponse("there is no person with dni: " + dni, HttpStatus.NOT_FOUND);
        }

        return person;
    }

    public List<Person> getAll() {
        return personRepository.findAllByActiveTrue();
    }

    public Person insert(Person person) {
        Person p = null;
        try {
            p = personRepository.save(person);
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return p;
    }

    public Person update(int id, Person person) {
        Person p = null;

        Optional<Person> optional = personRepository.findByIdAndActiveIsTrue(id);
        if (optional.isPresent()) {
            try {
                person.setId(id);
                personRepository.save(person);
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

        return p;
    }

    public Person delete(int id) {
        return null;
    }
}
