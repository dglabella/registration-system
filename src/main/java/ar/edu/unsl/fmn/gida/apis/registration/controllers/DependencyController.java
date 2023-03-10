package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;
import ar.edu.unsl.fmn.gida.apis.registration.services.DependencyService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.dependencies)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class DependencyController {

	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 20;

	@Autowired
	private DependencyService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Dependency> getDependency(@PathVariable int id) {
		Dependency d = this.service.getOne(id);

		ResponseEntity<Dependency> response = d != null ? ResponseEntity.ok().body(d)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return response;
	}

	@GetMapping
	public Page<Dependency> getAllDependencies(@RequestParam Map<String, String> map) {
		Page<Dependency> page = null;
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

	@PostMapping
	public Dependency postDependency(@RequestBody Dependency dependency) {
		return this.service.insert(dependency);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Dependency> updateDependency() {
		throw new ErrorResponse(
				RegistrationSystemApplication.MESSENGER.getDependencyControllerMessenger()
						.operationNotImplementedYet("update", Dependency.class.getSimpleName()),
				HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping(value = "/{id}")
	public Dependency deleteDependency(@PathVariable int id) {
		return this.service.delete(id);
	}
}
