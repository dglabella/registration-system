package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.endpoints.Endpoint;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;

@RestController
@RequestMapping(value = Endpoint.persons)
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable int id) {
        Person person = this.personService.getOne(id);
        return person;

    }

    @GetMapping("/getName/{name}")
    public List<Person> getPersonName(@PathVariable(name="name") String name) {
        List<Person> p = this.personService.getName(name);
        return p;
    }

    @GetMapping("/getLastName/{lastName}")
    public List<Person> getPersonLastName(@PathVariable(name="lastName") String lastName) {
        List<Person> p = this.personService.getLastName(lastName);

        return p;
    }

    @GetMapping("/getDNI/{dni}")
    public Person getPersonDNI(@PathVariable(name="dni") String dni) {
        Person p = this.personService.getDNI(dni);
        return p;
    }
    
    @GetMapping
    public List<Person> getAllPersons() {
        List<Person> persons = personService.getAll();
        return persons;
    }

    @PostMapping
    public Person postPerson(@Valid @RequestBody Person person) {
        Person p = personService.insert(person);
        return p;
    }

    @PutMapping(value = "/{id}")
    public Person updatePerson(@PathVariable int id, @Valid @RequestBody Person person) {
        Person p = personService.update(id, person);
        return p;
    }

    @DeleteMapping
    public Person deletePerson() {
        throw new ErrorResponse("delete person operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}

