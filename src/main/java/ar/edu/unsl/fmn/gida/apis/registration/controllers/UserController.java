package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;
import javax.validation.Valid;
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
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.services.UserService;

@RestController
@RequestMapping(value = Endpoint.users)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable int id) {
        return this.userService.getOne(id);
    }

    @PostMapping
    public User postUser(@Valid @RequestBody User user) {
        return this.userService.insert(user);
    }

    @PutMapping(value = "{id}")
    public User updateUser(@PathVariable int id, @Valid @RequestBody User user) {
        return this.userService.update(id, user);
    }

    @DeleteMapping
    public User deleteUser() {
        throw new ErrorResponse("delete user operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
