package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.persons)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class PersonController {

	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 100;

	private final String SEARCH_OP = "/search";

	private final String SEARCH_OP_BY_DNI = "/dni=";
	private final String SEARCH_OP_BY_NAME = "/name=";
	private final String SEARCH_OP_BY_LASTNAME = "/lastname=";

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/{id}")
	public Person getPerson(@PathVariable int id) {
		Person person = this.personService.getOne(id);
		return person;
	}

	@GetMapping
	public Page<Person> getAll(@RequestParam Map<String, String> map) {
		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.personService.getAll(this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.personService.getAll(Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.personService.getAll(this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.personService.getAll(Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = SEARCH_OP + SEARCH_OP_BY_DNI + "{value}")
	public Page<Person> getPersonsByDniApproach(@PathVariable String value,
			@RequestParam Map<String, String> map) {

		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.personService.getAllByDniApproach(value, this.DEFAULT_PAGE_NUMBER,
					this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.personService.getAllByDniApproach(value, Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.personService.getAllByDniApproach(value, this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.personService.getAllByDniApproach(value, Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = SEARCH_OP + SEARCH_OP_BY_NAME + "{value}")
	public Page<Person> getPersonsByNameApproach(@PathVariable String value,
			@RequestParam Map<String, String> map) {

		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.personService.getAllByNameApproach(value, this.DEFAULT_PAGE_NUMBER,
					this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.personService.getAllByNameApproach(value, Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.personService.getAllByNameApproach(value, this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.personService.getAllByNameApproach(value, Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = SEARCH_OP + SEARCH_OP_BY_LASTNAME + "{value}")
	public Page<Person> getPersonsByLastnameApproach(@PathVariable String value,
			@RequestParam Map<String, String> map) {

		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.personService.getAllByLastNameApproach(value, this.DEFAULT_PAGE_NUMBER,
					this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.personService.getAllByLastNameApproach(value,
					Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.personService.getAllByLastNameApproach(value, this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.personService.getAllByLastNameApproach(value,
					Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
		}

		return page;
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
	public Person deletePerson(@PathVariable int id) {
		Person p = personService.delete(id);
		return p;
	}
}
