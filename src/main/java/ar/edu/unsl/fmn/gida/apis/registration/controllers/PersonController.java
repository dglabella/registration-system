package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.endpoints.Endpoint;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.responses.PersonsPage;
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

    @GetMapping
    public List<Person> getAllPersons() {
        List<Person> ret = this.personService.getAll();
        return ret;
    }

    @GetMapping(value = "/paged")
    public PersonsPage getAll(@RequestParam Map<String, String> map) {
        PersonsPage personsPage = new PersonsPage();
        if (!map.containsKey("page")) {
            throw new ErrorResponse("must specify at least a page number", HttpStatus.BAD_REQUEST);
        } else if (!map.containsKey("quantity")) {
            if (Integer.parseInt(map.get("page")) < 0) {
                throw new ErrorResponse("page number must not be less than zero",
                        HttpStatus.BAD_REQUEST);
            } else {
                personsPage.setPageNumber(Integer.parseInt(map.get("page")));
                personsPage.setQuantityPerPage(Integer.parseInt(map.get("quantity")));
                personsPage.setResouces(
                        this.personService.getAll(Integer.parseInt(map.get("page")), 20));
            }
        } else {
            if (Integer.parseInt(map.get("quantity")) < 0) {
                throw new ErrorResponse("quantity number must not be less than zero",
                        HttpStatus.BAD_REQUEST);
            }
            personsPage.setPageNumber(Integer.parseInt(map.get("page")));
            personsPage.setQuantityPerPage(Integer.parseInt(map.get("quantity")));
            personsPage.setResouces(this.personService.getAll(Integer.parseInt(map.get("page")),
                    Integer.parseInt(map.get("quantity"))));
        }

        return personsPage;
    }

    @GetMapping(value = "/dni/{dni}")
    public Person getPersonByDni(@PathVariable String dni) {
        return this.personService.getOneByDni(dni);
    }

    @GetMapping(value = "/name/{name}")
    public List<Person> getPersonByName(@PathVariable String name) {
        return this.personService.getAllWithName(name);
    }

    @GetMapping(value = "/lastName/{lastName}")
    public List<Person> getPersonByLastName(@PathVariable String lastName) {
        return this.personService.getAllWithLastName(lastName);
    }

    @GetMapping(value = "/search")
    public List<Person> search(@RequestParam Map<String, String> map) {
        List<Person> persons = new ArrayList<>();

        if (map.containsKey("dni")) {
            persons = this.personService.getOneByDniApproach(map.get("dni"));
        } else if (map.containsKey("name")) {
            persons = this.personService.getAllWithNameApproach(map.get("name"));
        } else if (map.containsKey("lastName")) {
            persons = this.personService.getAllWithLastNameApproach(map.get("lastName"));
        }

        return persons;
    }

    @PostMapping
    public Person postPerson(@RequestBody Person person) {
        Person p = personService.insert(person);
        return p;
    }

    @PutMapping(value = "/{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody Person person) {
        Person p = personService.update(id, person);
        return p;
    }

    @DeleteMapping
    public Person deletePerson() {
        throw new ErrorResponse("delete person operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
