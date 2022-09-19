package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;

import javax.validation.Valid;

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
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.services.AccessService;

@RestController
@RequestMapping(value = Endpoint.accesses)
public class AccessController {

    @Autowired
    private AccessService accessService;

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Access> getAccess(@PathVariable int id) {
        Access a = this.accessService.getOne(id);

        ResponseEntity<Access> response = a != null ? ResponseEntity.ok().body(a)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping
    public List<Access> getAllAccesses() {
        List<Access> accesses = accessService.getAll();
        return accesses;
    }

    @PostMapping
    public Access postAccess(@Valid @RequestBody Access access) {
        Access a = accessService.insert(access);
        return a;
    }

    @PutMapping(value = "/{id}")
    public Access updateAccess(@PathVariable int id, @Valid @RequestBody Access access) {
        Access a = accessService.update(id, access);
        return a;
    }

    @DeleteMapping
    public Access deleteAccess() {
        throw new ErrorResponse("delete access operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
