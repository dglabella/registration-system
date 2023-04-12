package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.enums.WorkAttendanceState;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.WorkAttendance;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;
import ar.edu.unsl.fmn.gida.apis.registration.services.WorkAttendanceService;
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
	private WorkAttendanceService service;

	@Autowired
	private PersonService personService;

	@GetMapping
	public Page<WorkAttendance> getAllWorkAttendancesBetweenDates(
			@RequestParam Map<String, String> map) {
		Page<WorkAttendance> page = null;
		String from = map.get("from");
		String to = map.get("to");

		if (map.containsKey("state") && map.get("state").equals("ALL")) {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				List<WorkAttendance> workAttendances = this.service.getAllBetweenDates(from, to);
				page = new PageImpl<>(workAttendances, PageRequest.of(0, Integer.MAX_VALUE),
						workAttendances.size());
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				page = this.service.getAllBetweenDates(from, to, Integer.parseInt(map.get("page")),
						this.DEFAULT_PAGE_SIZE);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				page = this.service.getAllBetweenDates(from, to, this.DEFAULT_PAGE_NUMBER,
						Integer.parseInt(map.get("size")));
			} else {
				page = this.service.getAllBetweenDates(from, to, Integer.parseInt(map.get("page")),
						Integer.parseInt(map.get("size")));
			}

		} else if (map.containsKey("state") && !map.get("state").equals("ALL")) {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				List<WorkAttendance> workAttendances = this.service.getAllByStateBetweenDates(
						WorkAttendanceState.valueOf(map.get("state")), from, to);
				page = new PageImpl<>(workAttendances, PageRequest.of(0, Integer.MAX_VALUE),
						workAttendances.size());
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				page = this.service.getAllByStateBetweenDates(
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				page = this.service.getAllByStateBetweenDates(
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
			} else {
				page = this.service.getAllByStateBetweenDates(
						WorkAttendanceState.valueOf(map.get("state")), from, to,
						Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
			}

		} else {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				List<WorkAttendance> workAttendances = this.service.getAllBetweenDates(from, to);
				page = new PageImpl<>(workAttendances, PageRequest.of(0, Integer.MAX_VALUE),
						workAttendances.size());
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				page = this.service.getAllBetweenDates(from, to, Integer.parseInt(map.get("page")),
						this.DEFAULT_PAGE_SIZE);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				page = this.service.getAllBetweenDates(from, to, this.DEFAULT_PAGE_NUMBER,
						Integer.parseInt(map.get("size")));
			} else {
				page = this.service.getAllBetweenDates(from, to, Integer.parseInt(map.get("page")),
						Integer.parseInt(map.get("size")));
			}
		}

		return page;
	}

	@GetMapping(value = SEARCH_OP + SEARCH_OP_BY_DNI_EXACT + "{value}")
	public Person getAllWorkAttendancesByDniAndStateBetweenDates(@PathVariable String value,
			@RequestParam Map<String, String> map) {
		Person person;
		String from = map.get("from");
		String to = map.get("to");

		if (map.containsKey("state") && map.get("state").equals("ALL")) {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				person = this.personService.getWithAllWorkAttendancesByDniBetweenDates(value, from,
						to);
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			} else {
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			}

		} else if (map.containsKey("state") && !map.get("state").equals("ALL")) {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				person = this.personService.getAllWorkAttendanceByDniAndStateBetweenDates(value,
						WorkAttendanceState.valueOf(map.get("state")), from, to);
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			} else {
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			}

		} else {

			if (!map.containsKey("page") && !map.containsKey("size")) {
				person = this.personService.getWithAllWorkAttendancesByDniBetweenDates(value, from,
						to);
			} else if (map.containsKey("page") && !map.containsKey("size")) {
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			} else if (!map.containsKey("page") && map.containsKey("size")) {
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			} else {
				throw new ErrorResponse("paged query not implemented yet",
						HttpStatus.NOT_IMPLEMENTED);
			}
		}

		return person;
	}
}
