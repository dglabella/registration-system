package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.endpoints.Endpoint;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.services.RegisterService;

@RestController
@RequestMapping(value = Endpoint.registers)
public class RegisterController {

    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_QUANTITY_PER_PAGE = 100;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/{id}")
    public Register getRegister(@PathVariable int id) {
        Register r = this.registerService.getOne(id);
        return r;
    }

    @GetMapping(value = "/paged")
    public Page<Register> getRegistersBetweenDates(@RequestParam Map<String, String> map) {
        Page<Register> page = null;

        String from = map.get("from");
        String to = map.get("to");

        if (!map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.registerService.getAll(from, to, this.DEFAULT_PAGE_NUMBER,
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.registerService.getAll(from, to, Integer.parseInt(map.get("page")),
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (!map.containsKey("page") && map.containsKey("quantity")) {
            page = this.registerService.getAll(from, to, this.DEFAULT_PAGE_NUMBER,
                    Integer.parseInt(map.get("quantity")));
        } else {
            page = this.registerService.getAll(from, to, Integer.parseInt(map.get("page")),
                    Integer.parseInt(map.get("quantity")));
        }

        return page;
    }

    @GetMapping(value = "person/{id}/paged")
    public Page<Register> getRegistersFromPersonBetweenDates(@PathVariable int id,
            @RequestParam Map<String, String> map) {
        Page<Register> page = null;

        String from = map.get("from");
        String to = map.get("to");

        if (!map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.registerService.getAllFromPerson(id, from, to, this.DEFAULT_PAGE_NUMBER,
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.registerService.getAllFromPerson(id, from, to, Integer.parseInt(map.get("page")),
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (!map.containsKey("page") && map.containsKey("quantity")) {
            page = this.registerService.getAllFromPerson(id, from, to, this.DEFAULT_PAGE_NUMBER,
                    Integer.parseInt(map.get("quantity")));
        } else {
            page = this.registerService.getAllFromPerson(id,from, to, Integer.parseInt(map.get("page")),
                    Integer.parseInt(map.get("quantity")));
        }

        return page;
    }

    @PostMapping
    public Register postRegister(@RequestBody Register register) {
        Register r = registerService.insert(register);
        return r;
    }

    @PutMapping(value = "/{id}")
    public Register updateRegister(@PathVariable int id, @RequestBody Register register) {
        throw new ErrorResponse("cannot update a register, illegal operation ",
                HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping
    public ResponseEntity<Register> deleteRegister() {
        throw new ErrorResponse("delete register operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}

