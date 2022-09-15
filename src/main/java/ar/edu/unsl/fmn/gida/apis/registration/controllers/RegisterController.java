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
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.services.RegisterService;

@RestController
@RequestMapping(value = Endpoint.registers)
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Register> getRegister(@PathVariable int id) {
        Register r = this.registerService.getOne(id);

        ResponseEntity<Register> response = r != null ? ResponseEntity.ok().body(r)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Register>> getAllRegister() {
        List<Register> registers = registerService.getAll();

        ResponseEntity<List<Register>> response =
                registers.size() > 0 ? ResponseEntity.ok().body(registers)
                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @PostMapping
    public ResponseEntity<Register> postRegister(@RequestBody Register register) {
        Register r = registerService.insert(register);

        ResponseEntity<Register> response =
                r != null ? new ResponseEntity<Register>(r, HttpStatus.CREATED)
                        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return response;
    }

    @PutMapping
    public ResponseEntity<Register> updateRegister() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Register> deleteRegister() {
        return null;
    }
}
