package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
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

    @GetMapping(value = "/search")
    public List<Person> search(@RequestParam Map<String, String> map) {
        List<Person> persons = new ArrayList<>();

        if (map.containsKey("dni")) {
            persons.add(this.personService.getOneByDni(map.get("dni")));
        } else if (map.containsKey("name")) {
            persons = this.personService.getAllWithName(map.get("name"));
        } else if (map.containsKey("lastName")) {
            persons = this.personService.getAllWithLastName(map.get("lastName"));
        }

        return persons;
    }

    @GetMapping("/paged")
    public Page <Person> getAllPersons(Pageable pageable) {
  

        return this.personService.getAll(pageable);
    }

    // public List<Person> getAllPersons(@RequestParam Map<String, String> map) {
    //     if (!map.containsKey("page") || !map.containsKey("quantity")) {
    //         throw new ErrorResponse(
    //                 "when request for paginated resources, must be specified page number, and quantity per page.",
    //                 HttpStatus.BAD_REQUEST);
    //     }

    //     String page = map.get("page");
    //     String quantityPerPage = map.get("quantity");

    //     return this.personService.getAll(Integer.parseInt(page), Integer.parseInt(quantityPerPage));
    // }

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
