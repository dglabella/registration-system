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
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.model.auxiliaries.Check;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
// @RequestMapping(value = Urls.Privileges.responsible + Urls.persons)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class PersonController {

	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 100;

	private final String SEARCH_OP = "/search";

	private final String SEARCH_OP_BY_DNI = "/dni=";
	private final String SEARCH_OP_BY_NAME = "/name=";
	private final String SEARCH_OP_BY_LASTNAME = "/lastname=";
	private final String SEARCH_OP_BY_DNI_EXACT = "/dniexact=";

	@Autowired
	private PersonService service;

	@GetMapping(value = "/{id}")
	public Person getPerson(@PathVariable int id) {
		Person person = this.service.getOne(id);
		return person;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.persons)
	public Page<Person> getAllPersons(@RequestParam Map<String, String> map) {
		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAll(this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAll(Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAll(this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAll(Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.persons + SEARCH_OP + SEARCH_OP_BY_DNI
			+ "{value}")
	public Page<Person> getPersonsByDniApproach(@PathVariable String value,
			@RequestParam Map<String, String> map) {

		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByDniApproach(value, this.DEFAULT_PAGE_NUMBER,
					this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByDniApproach(value, Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllByDniApproach(value, this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllByDniApproach(value, Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.persons + SEARCH_OP + SEARCH_OP_BY_NAME
			+ "{value}")
	public Page<Person> getPersonsByNameApproach(@PathVariable String value,
			@RequestParam Map<String, String> map) {

		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByNameApproach(value, this.DEFAULT_PAGE_NUMBER,
					this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByNameApproach(value, Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllByNameApproach(value, this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllByNameApproach(value, Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.persons + SEARCH_OP
			+ SEARCH_OP_BY_LASTNAME + "{value}")
	public Page<Person> getPersonsByLastnameApproach(@PathVariable String value,
			@RequestParam Map<String, String> map) {

		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByLastNameApproach(value, this.DEFAULT_PAGE_NUMBER,
					this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByLastNameApproach(value, Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllByLastNameApproach(value, this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllByLastNameApproach(value, Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = SEARCH_OP + SEARCH_OP_BY_DNI_EXACT + "{value}")
	public Person getOneByDniWithRegistersBetweenDates(@PathVariable String value,
			@RequestParam Map<String, String> map) {
		Person page = null;

		String from = map.get("from");
		String to = map.get("to");

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getOneByDniWithRegistersBetweenDates(value, from, to,
					this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getOneByDniWithRegistersBetweenDates(value, from, to,
					Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getOneByDniWithRegistersBetweenDates(value, from, to,
					this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getOneByDniWithRegistersBetweenDates(value, from, to,
					Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@PostMapping(value = Urls.Privileges.responsible + Urls.persons)
	public void postPerson(@RequestBody Person person) {
		this.service.insert(person);
	}

	@PostMapping(value = Urls.Privileges.user + Urls.persons + "/check")
	public Person checkPersonQr(@RequestBody Check check) {
		return this.service.checkQr(check);
	}

	@PutMapping(value = Urls.Privileges.responsible + Urls.persons + "/{id}")
	public void updatePerson(@PathVariable int id, @RequestBody Person person) {
		this.service.update(id, person);
	}

	@DeleteMapping(value = Urls.Privileges.responsible + Urls.persons + "/{id}")
	public void deletePerson(@PathVariable int id) {
		this.service.delete(id);
	}
}
