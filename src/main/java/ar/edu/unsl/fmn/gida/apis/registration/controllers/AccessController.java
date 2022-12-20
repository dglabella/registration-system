package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.services.AccessService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.accesses)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class AccessController {

    @Autowired
    private AccessService accessService;

    @GetMapping(value = "/{id}")
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
    public Access postAccess(@RequestBody Access access) {
        Access a = accessService.insert(access);
        return a;
    }

    @PutMapping(value = "/{id}")
    public Access updateAccess(@PathVariable int id, @RequestBody Access access) {
        Access a = accessService.update(id, access);
        return a;
    }

    @DeleteMapping(value = "/{id}")
    public Access deleteAccess() {
        throw new ErrorResponse("delete access operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
