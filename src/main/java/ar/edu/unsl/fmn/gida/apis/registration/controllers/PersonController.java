package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;

@RestController
@RequestMapping(value = "person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/{id}")
    public Person getPerson(@PathVariable String id) {
        return null;
    }

    @GetMapping
    public ArrayList<Person> getAllPerson() {
        return personService.getPerson();
    }

    @PostMapping
    public Person postPerson(@RequestBody Person person) {
        return personService.postPerson(person);
    }

}
