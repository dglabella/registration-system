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
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.services.RegisterService;

@RestController
@RequestMapping(value = Endpoint.registers)
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/{id}")
    public Register getRegister(@PathVariable int id) {
        Register r = this.registerService.getOne(id);
        return r;
    }

    @GetMapping
    public List<Register> getAllRegister() {
        List<Register> registers = registerService.getAll();
        return registers;
    }

    @PostMapping
    public Register postRegister(@Valid @RequestBody String encryptedData) {
        ///qr 
        Register r = registerService.insert(encryptedData);
        return r;
    }

    @PutMapping(value = "/{id}")
    public Register updateRegister(@PathVariable int id, @Valid @RequestBody Register register) {
        Register r = registerService.update(id, register);
        return r;
    }

    @DeleteMapping
    public ResponseEntity<Register> deleteRegister() {
        throw new ErrorResponse("delete register operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
