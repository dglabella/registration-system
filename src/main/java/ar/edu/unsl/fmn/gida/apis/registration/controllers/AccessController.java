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
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.services.AccessService;

@RestController
@RequestMapping(value = RegistrationSystemApplication.Endpoints.accesses)
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
    public ResponseEntity<List<Access>> getAllAccesses() {
        List<Access> accesses = accessService.getAll();

        ResponseEntity<List<Access>> response = accesses.size() > 0 ? ResponseEntity.ok().body(accesses)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @PostMapping
    public ResponseEntity<Access> postAccess(@RequestBody Access access) {
        Access a = accessService.insert(access);

        ResponseEntity<Access> response = a != null ? new ResponseEntity<Access>(a, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return response;
    }

    @PutMapping
    public ResponseEntity<Access> updateAccess() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Access> deleteAccess() {
        return null;
    }
}
