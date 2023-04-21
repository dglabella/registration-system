package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.services.PersonService;
import ar.edu.unsl.fmn.gida.apis.registration.services.RegisterService;
import ar.edu.unsl.fmn.gida.apis.registration.services.WeeklyService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.weeklies)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class WeeklyController {

	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 100;

	@Autowired
	private WeeklyService service;

	@Autowired
	private PersonService personService;

	@Autowired
	private RegisterService registerService;

	@GetMapping(value = "/person/{id}")
	public Page<Weekly> getAllWeekliesFromPerson(@PathVariable int id,
			@RequestParam Map<String, String> map) {
		Page<Weekly> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllFromPersonEachWithResponsibilities(id,
					this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAllFromPersonEachWithResponsibilities(id,
					Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAllFromPersonEachWithResponsibilities(id,
					this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAllFromPersonEachWithResponsibilities(id,
					Integer.parseInt(map.get("page")), Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@PostMapping(value = "/person/{id}")
	public void postWeekly(@PathVariable int id, @RequestBody Weekly requestBody) {
		this.service.insert(this.personService.get(id).getId(), requestBody);

		LocalDate today = LocalDate.now();
		// inserting a weekly from the past?
		if (requestBody.getStart().compareTo(today) < 0) {

			List<Register> registers = this.registerService.getAllFromPersonBetweenDates(id,
					requestBody.getStart(), today);
			this.service.calculateWorkAttendanceStatePerWeekly(requestBody, registers);
		}
	}

	@DeleteMapping(value = "/{id}")
	public void deleteWeekly(@PathVariable int id) {
		this.service.delete(id);
	}
}
