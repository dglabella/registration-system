package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;
import ar.edu.unsl.fmn.gida.apis.registration.services.RegisterService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class RegisterController {

	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 100;

	private final String SEARCH_OP = "/search";

	private final String SEARCH_OP_BY_DNI_EXACT = "/dniexact=";

	@Autowired
	private RegisterService service;

	@Autowired
	private PersonService personService;

	@GetMapping(value = Urls.Privileges.responsible + Urls.registers)
	public Page<Register> getRegistersBetweenDates(@RequestParam Map<String, String> map) {
		Page<Register> page = null;

		String from = map.get("from");
		String to = map.get("to");

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAll(from, to, this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAll(from, to, Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAll(from, to, this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAll(from, to, Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.registers + "/person/{personId}")
	public Page<Register> getRegistersFromPersonBetweenDates(@PathVariable int personId,
			@RequestParam Map<String, String> map) {
		Page<Register> page = null;

		String from = map.get("from");
		String to = map.get("to");

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllFromPersonBetweenDates(personId, from, to,
					this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllFromPersonBetweenDates(personId, from, to,
					Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllFromPersonBetweenDates(personId, from, to,
					this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllFromPersonBetweenDates(personId, from, to,
					Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = Urls.Privileges.responsible + Urls.registers + "/person" + SEARCH_OP
			+ SEARCH_OP_BY_DNI_EXACT + "{value}")
	public Page<Person> getRegistersFromPersonBetweenDatesByDni(@PathVariable String value,
			@RequestParam Map<String, String> map) {
		Page<Person> page = null;
		List<Person> content = new ArrayList<>();

		String from = map.get("from");
		String to = map.get("to");

		if (!map.containsKey("page") && !map.containsKey("size")) {
			content.add(this.personService.getByDniWithRegistersBetweenDates(value, from, to,
					this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE));
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			content.add(this.personService.getByDniWithRegistersBetweenDates(value, from, to,
					Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE));
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			content.add(this.personService.getByDniWithRegistersBetweenDates(value, from, to,
					this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size"))));
		} else {
			content.add(this.personService.getByDniWithRegistersBetweenDates(value, from, to,
					Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size"))));
		}

		page = new PageImpl<>(content, PageRequest.of(0, Integer.MAX_VALUE), Integer.MAX_VALUE);

		return page;
	}
}
