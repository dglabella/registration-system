package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.endpoints.Endpoint;
import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;
import ar.edu.unsl.fmn.gida.apis.registration.services.DependencyService;

@RestController
@RequestMapping(value = Endpoint.dependencies)
public class DependencyController {

    @Autowired
    private DependencyService dependencyService;

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Dependency> getDependency(@PathVariable int id) {
        Dependency d = this.dependencyService.getOne(id);

        ResponseEntity<Dependency> response = d != null ? ResponseEntity.ok().body(d)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Dependency>> getAllDependencies() {
        List<Dependency> dependencies = dependencyService.getAll();

        ResponseEntity<List<Dependency>> response =
                dependencies.size() > 0 ? ResponseEntity.ok().body(dependencies)
                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @PostMapping
    public ResponseEntity<Dependency> postDependcncy(@RequestBody Dependency dependency) {
        Dependency d = dependencyService.insert(dependency);

        ResponseEntity<Dependency> response =
                d != null ? new ResponseEntity<Dependency>(d, HttpStatus.CREATED)
                        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return response;
    }

    @PutMapping
    public ResponseEntity<Dependency> updateDependency() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Dependency> deleteDependency() {
        return null;
    }
}
