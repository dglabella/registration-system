package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.persons)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class PersonController {

    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_QUANTITY_PER_PAGE = 100;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable int id) {
        Person person = this.personService.getOne(id);
        return person;
    }

    @GetMapping(value = "/paged")
    public Page<Person> getAll(@RequestParam Map<String, String> map) {
        Page<Person> page = null;

        if (!map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.personService.getAll(this.DEFAULT_PAGE_NUMBER,
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.personService.getAll(Integer.parseInt(map.get("page")),
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (!map.containsKey("page") && map.containsKey("quantity")) {
            page = this.personService.getAll(this.DEFAULT_PAGE_NUMBER,
                    Integer.parseInt(map.get("quantity")));
        } else {
            page = this.personService.getAll(Integer.parseInt(map.get("page")),
                    Integer.parseInt(map.get("quantity")));
        }

        return page;
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

    @DeleteMapping(value = "/{id}")
    public Person deletePerson() {
        throw new ErrorResponse("delete person operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
