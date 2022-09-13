package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getOne(int id) {
        Person p = null;
        Optional<Person> optional = personRepository.findById(id);

        if (optional.isPresent()) {
            p = optional.get();
        }

        return p;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person insert(Person person) {
        Person p = null;
        Optional<Person> optional = this.personRepository.findById(person.getId());

        if (!optional.isPresent()) {
            p = personRepository.save(person);
        }

        return p;
    }

    public Person update(Person person) {
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
