package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.services.CredentialService;

@RestController
@RequestMapping(value = Endpoint.credentials)
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping(value = "/{id}")
    public Credential getCredential(@PathVariable int id) {
        Credential credential = this.credentialService.getOne(id);
        return credential;
    }

    @GetMapping
    public List<Credential> getAllCredentials() {
        List<Credential> ret = this.credentialService.getAll();
        return ret;
    }

    @PostMapping
    public Credential postCredential(@RequestBody Credential credential) {
        throw new ErrorResponse("insert credential operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(value = "/{id}")
    public Credential updateCredential(@PathVariable int id, @RequestBody Credential credential) {
        Credential c = this.credentialService.update(id, credential);
        return c;
    }

    @DeleteMapping
    public Credential deleteCredential() {
        throw new ErrorResponse("delete credential operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}