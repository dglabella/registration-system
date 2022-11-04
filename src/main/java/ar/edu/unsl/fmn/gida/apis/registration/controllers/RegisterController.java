package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.endpoints.Endpoint;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.responses.RegistersPage;
import ar.edu.unsl.fmn.gida.apis.registration.services.RegisterService;

@RestController
@RequestMapping(value = Endpoint.registers)
public class RegisterController {

    private final int DEFAULT_QUANTITY_PER_PAGE = 100;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/{id}")
    public Register getRegister(@PathVariable int id) {
        Register r = this.registerService.getOne(id);
        return r;
    }

    @GetMapping(value = "/paged")
    public RegistersPage getRegistersBetweenDates(@RequestParam Map<String, String> map) {
        if (!map.containsKey("from") && !map.containsKey("to")) {
            throw new ErrorResponse(
                    "request registers between dates must be at least specify a \"from\" date, a \"to\" date, or both",
                    HttpStatus.BAD_REQUEST);
        }
        RegistersPage page = new RegistersPage();

        String from = map.get("from");
        String to = map.get("to");

        this.registerService.getAll(from, to);

        return page;
    }

    @GetMapping(value = "person/{id}/bewtween")
    public RegistersPage getRegistersFromPersonBetweenDates(@PathVariable int id,
            @RequestParam Map<String, String> map) {
        RegistersPage registersPage = new RegistersPage();
        String from = map.get("from");
        String to = map.get("to");

        if (!map.containsKey("from") && !map.containsKey("to")) {
            throw new ErrorResponse(
                    "request registers between dates must be at least specify a \"from\" date, a \"to\" date, or both",
                    HttpStatus.BAD_REQUEST);
        } else if (!map.containsKey("page") && !map.containsKey("quantity")) {
            registersPage.setPageNumber(-1);
            registersPage.setQuantityPerPage(-1);
            registersPage.setResouces(this.registerService.getAll(from, to));
        } else if (map.containsKey("page") && !map.containsKey("quantity")) {
            registersPage.setPageNumber(Integer.parseInt(map.get("page")));
            registersPage.setQuantityPerPage(this.DEFAULT_QUANTITY_PER_PAGE);
            registersPage.setResouces(this.registerService.getAll(from, to));
        } else if (map.containsKey("page")) {

        }

        this.registerService.getAll(from, to);

        return registersPage;
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
