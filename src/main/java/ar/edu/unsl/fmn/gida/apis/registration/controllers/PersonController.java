package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.io.Console;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;

@RestController
@RequestMapping(value = RegistrationSystemApplication.Endpoints.persons)
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        Person p = this.personService.getOne(id);
        // System.out.println("persona tiene " + p.getDependency().getId());
            //System.in.read();

        ResponseEntity<Person> response = p != null ? ResponseEntity.ok().body(p)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        return response;
    }

    @GetMapping("/getName/{name}")
    public ResponseEntity<List<Person>> getPersonName(@PathVariable(name="name") String name) {
        List<Person> p = this.personService.getName(name);

        // ResponseEntity<Person> response = p != null ? ResponseEntity.ok().body(p)
        //         : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ResponseEntity<List<Person>> response =
        p.size() > 0 ? ResponseEntity.ok().body(p)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/getLastName/{lastName}")
    public ResponseEntity<List<Person>> getPersonLastName(@PathVariable(name="lastName") String lastName) {
        List<Person> p = this.personService.getLastName(lastName);

        ResponseEntity<List<Person>> response =
        p.size() > 0 ? ResponseEntity.ok().body(p)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping("/getDNI/{dni}")
    public ResponseEntity<List<Person>> getPersonDNI(@PathVariable(name="dni") String dni) {
        List<Person> p = this.personService.getDNI(dni);

        ResponseEntity<List<Person>> response =
        p.size() > 0 ? ResponseEntity.ok().body(p)
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
                p != null ? new ResponseEntity<Person>(p, HttpStatus.CREATED)
                        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

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
