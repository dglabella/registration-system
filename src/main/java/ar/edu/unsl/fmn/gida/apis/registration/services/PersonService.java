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
        Person p = null;
        Optional<Person> optional = personRepository.findByIdAndActiveIsTrue(id);

        if (optional.isPresent()) {
            p = optional.get();
        } else {
            throw new ErrorResponse("there is no person with id: " + id, HttpStatus.NOT_FOUND);
        }

        return p;
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
        Optional<Person> optional = this.personRepository.findById(person.getId());
        if (optional.isPresent()) {

        }

        if (person.getId() != null) {
            p = personRepository.save(person);
        } else {
            p = null;
        }
        return p;
    }

    public Person delete(int id) {
        return null;
    }
}
