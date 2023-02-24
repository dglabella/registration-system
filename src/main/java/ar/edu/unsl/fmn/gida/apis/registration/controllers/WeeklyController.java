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
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.services.WeeklyService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.weeklies)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class WeeklyController {

	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 100;

	@Autowired
	private WeeklyService weeklyService;

	@GetMapping
	public Page<Weekly> getAllWeeklies(@RequestParam Map<String, String> map) {
		Page<Weekly> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.weeklyService.getAll(this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.weeklyService.getAll(Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.weeklyService.getAll(this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.weeklyService.getAll(Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = "/person/{id}/paged")
	public Page<Weekly> getAllWeekliesFromPerson(@PathVariable int id,
			@RequestParam Map<String, String> map) {
		Page<Weekly> page = null;

		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.weeklyService.getAllFromPerson(id, this.DEFAULT_PAGE_NUMBER,
					this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.weeklyService.getAllFromPerson(id, Integer.parseInt(map.get("page")),
					this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.weeklyService.getAllFromPerson(id, this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("size")));
		} else {
			page = this.weeklyService.getAllFromPerson(id, Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}
}
