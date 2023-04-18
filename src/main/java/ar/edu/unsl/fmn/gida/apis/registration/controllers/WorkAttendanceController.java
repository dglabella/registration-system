package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.enums.WorkAttendanceState;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.workAttendances)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class WorkAttendanceController {

	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 100;

	private final String SEARCH_OP = "/search";

	private final String SEARCH_OP_BY_DNI_EXACT = "/dniexact=";

	@Autowired
	private PersonService personService;

	@GetMapping
	public Page<Person> getAllWorkAttendancesBetweenDates(@RequestParam Map<String, String> map) {
		Page<Person> page = null;
		String from = map.get("from");
		String to = map.get("to");

		if (map.containsKey("state") && map.get("state").equals("ALL")) {
			if (!map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getAllEachWithWorkAttendancesBetweenDates(from, to,
						this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getAllEachWithWorkAttendancesBetweenDates(from, to,
						Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				page = this.personService.getAllEachWithWorkAttendancesBetweenDates(from, to,
						this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
			} else {
				page = this.personService.getAllEachWithWorkAttendancesBetweenDates(from, to,
						Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
			}

		} else if (map.containsKey("state") && !map.get("state").equals("ALL")) {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getAllEachWithWorkAttendancesByStateBetweenDates(
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getAllEachWithWorkAttendancesByStateBetweenDates(
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				page = this.personService.getAllEachWithWorkAttendancesByStateBetweenDates(
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
			} else {
				page = this.personService.getAllEachWithWorkAttendancesByStateBetweenDates(
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
			}

		} else {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getAllEachWithWorkAttendancesBetweenDates(from, to,
						this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getAllEachWithWorkAttendancesBetweenDates(from, to,
						Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				page = this.personService.getAllEachWithWorkAttendancesBetweenDates(from, to,
						this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
			} else {
				page = this.personService.getAllEachWithWorkAttendancesBetweenDates(from, to,
						Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
			}
		}

		return page;
	}

	@GetMapping(value = SEARCH_OP + SEARCH_OP_BY_DNI_EXACT + "{value}")
	public Page<Person> getAllWorkAttendancesByDniAndStateBetweenDates(@PathVariable String value,
			@RequestParam Map<String, String> map) {
		Page<Person> page;
		String from = map.get("from");
		String to = map.get("to");

		if (map.containsKey("state") && map.get("state").equals("ALL")) {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getByDniWithWorkAttendancesBetweenDates(value, from, to,
						this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getByDniWithWorkAttendancesBetweenDates(value, from, to,
						Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				page = this.personService.getByDniWithWorkAttendancesBetweenDates(value, from, to,
						this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
			} else {
				page = this.personService.getByDniWithWorkAttendancesBetweenDates(value, from, to,
						Integer.parseInt(map.get("PAGE")), Integer.parseInt(map.get("size")));
			}

		} else if (map.containsKey("state") && !map.get("state").equals("ALL")) {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getByDniWithWorkAttendanceByStateBetweenDates(value,
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getByDniWithWorkAttendanceByStateBetweenDates(value,
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				page = this.personService.getByDniWithWorkAttendanceByStateBetweenDates(value,
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
			} else {
				page = this.personService.getByDniWithWorkAttendanceByStateBetweenDates(value,
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						Integer.parseInt(map.get("PAGE")), Integer.parseInt(map.get("size")));
			}

		} else {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getByDniWithWorkAttendancesBetweenDates(value, from, to,
						this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				page = this.personService.getByDniWithWorkAttendancesBetweenDates(value, from, to,
						Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				page = this.personService.getByDniWithWorkAttendancesBetweenDates(value, from, to,
						this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
			} else {
				page = this.personService.getByDniWithWorkAttendancesBetweenDates(value, from, to,
						Integer.parseInt(map.get("PAGE")), Integer.parseInt(map.get("size")));
			}
		}

		return page;
	}
}
