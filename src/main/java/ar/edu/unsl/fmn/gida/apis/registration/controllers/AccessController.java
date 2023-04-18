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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.services.AccessService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class AccessController {
	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 20;

	@Autowired
	private AccessService service;

	@GetMapping(value = Urls.Privileges.responsible + Urls.accesses + "/{id}")
	public Access getAccess(@PathVariable int id) {
		return this.service.get(id);
	}

	@GetMapping(value = Urls.Privileges.pub + Urls.accesses)
	public Page<Access> getAllAccesses(@RequestParam Map<String, String> map) {
		Page<Access> page = null;

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

	@PostMapping(value = Urls.Privileges.responsible + Urls.accesses)
	public Access postAccess(@RequestBody Access access) {
		return this.service.insert(access);
	}

	@PutMapping(value = Urls.Privileges.responsible + Urls.accesses + "/{id}")
	public Access updateAccess(@PathVariable int id, @RequestBody Access access) {
		return this.service.update(id, access);
	}

	@DeleteMapping(value = Urls.Privileges.responsible + Urls.accesses + "/{id}")
	public Access deleteAccess(@PathVariable int id) {
		return this.service.delete(id);
	}
}
