package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person postPerson(Person person) {
        System.out.println("persona en service tiene " + person.getDni());
        Person p = personRepository.save(person);
        System.out.println("despues de save");
        return p;
    }
}
