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
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;
import ar.edu.unsl.fmn.gida.apis.registration.services.DependencyService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.dependencies)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class DependencyController {

    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_QUANTITY_PER_PAGE = 20;

    @Autowired
    private DependencyService dependencyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Dependency> getDependency(@PathVariable int id) {
        Dependency d = this.dependencyService.getOne(id);

        ResponseEntity<Dependency> response = d != null ? ResponseEntity.ok().body(d)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping
    public Page<Dependency> getAllDependencies(@RequestParam Map<String, String> map) {
        Page<Dependency> page = null;
        if (!map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.dependencyService.getAll(this.DEFAULT_PAGE_NUMBER,
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.dependencyService.getAll(Integer.parseInt(map.get("page")),
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (!map.containsKey("page") && map.containsKey("quantity")) {
            page = this.dependencyService.getAll(this.DEFAULT_PAGE_NUMBER,
                    Integer.parseInt(map.get("quantity")));
        } else {
            page = this.dependencyService.getAll(Integer.parseInt(map.get("page")),
                    Integer.parseInt(map.get("quantity")));
        }

        return page;
    }

    @PostMapping
    public ResponseEntity<Dependency> postDependcncy(@RequestBody Dependency dependency) {
        Dependency d = dependencyService.insert(dependency);

        ResponseEntity<Dependency> response =
                d != null ? new ResponseEntity<Dependency>(d, HttpStatus.CREATED)
                        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return response;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Dependency> updateDependency() {
        throw new ErrorResponse("update dependency operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Dependency> deleteDependency() {
        throw new ErrorResponse("delete dependency operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
