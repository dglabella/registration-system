package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;

@RestController
@RequestMapping(value = RegistrationSystemApplication.Endpoints.persons)
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        Person p = this.personService.getOne(id);

        ResponseEntity<Person> response = p != null ? ResponseEntity.ok().body(p)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAll();

        ResponseEntity<List<Person>> response =
                persons.size() > 0 ? ResponseEntity.ok().body(persons)
                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @PostMapping
    public ResponseEntity<Person> postPerson(@RequestBody Person person) {
        Person p = personService.insert(person);

        ResponseEntity<Person> response =
                p != null ? ResponseEntity.ok().body(p) : new ResponseEntity<>(HttpStatus.CREATED);

        return response;
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Person> deletePerson() {
        return null;
    }
}
