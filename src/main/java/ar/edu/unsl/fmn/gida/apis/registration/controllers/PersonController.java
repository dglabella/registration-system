package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
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
		Person person = this.service.get(id);
		return person;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.persons)
	public Page<Person> getPersons(@RequestParam Map<String, String> map) {
		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllEachWithCredential(this.DEFAULT_PAGE_NUMBER,
					this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllEachWithCredential(Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllEachWithCredential(this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllEachWithCredential(Integer.parseInt(map.get("page")),
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
			page = this.service.getAllByDniApproachEachWithCredential(value,
					this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByDniApproachEachWithCredential(value,
					Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllByDniApproachEachWithCredential(value,
					this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllByDniApproachEachWithCredential(value,
					Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.persons + SEARCH_OP + SEARCH_OP_BY_NAME
			+ "{value}")
	public Page<Person> getPersonsByNameApproach(@PathVariable String value,
			@RequestParam Map<String, String> map) {

		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByPersonNameApproachEachWithCredential(value,
					this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByPersonNameApproachEachWithCredential(value,
					Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllByPersonNameApproachEachWithCredential(value,
					this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllByPersonNameApproachEachWithCredential(value,
					Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.persons + SEARCH_OP
			+ SEARCH_OP_BY_LASTNAME + "{value}")
	public Page<Person> getPersonsByLastnameApproach(@PathVariable String value,
			@RequestParam Map<String, String> map) {

		Page<Person> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByPersonLastNameApproachEachWithCredential(value,
					this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllByPersonLastNameApproachEachWithCredential(value,
					Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllByPersonLastNameApproachEachWithCredential(value,
					this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllByPersonLastNameApproachEachWithCredential(value,
					Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.persons + SEARCH_OP
			+ SEARCH_OP_BY_DNI_EXACT + "{value}")
	public Page<Person> getPersonByDni(@PathVariable String value) {
		Page<Person> page;
		List<Person> content = new ArrayList<>();
		content.add(this.service.getByDniWithCredential(value));
		page = new PageImpl<>(content, PageRequest.of(0, Integer.MAX_VALUE), Integer.MAX_VALUE);
		return page;
	}

	@PostMapping(value = Urls.Privileges.responsible + Urls.persons)
	public void postPerson(@RequestBody Person person) {
		this.service.insert(person);
	}

	@PostMapping(value = Urls.Privileges.user + Urls.persons + "/check")
	public ResponseEntity<Person> checkPersonQr(@RequestBody Check check) {
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
