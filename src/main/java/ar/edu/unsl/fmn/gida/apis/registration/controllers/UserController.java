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
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.services.UserService;

@RestController
@RequestMapping(value = RegistrationSystemApplication.Endpoints.users)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = this.userService.getOne(id);

        ResponseEntity<User> response = user != null ? ResponseEntity.ok().body(user)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return response;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();

        ResponseEntity<List<User>> response = users.size() > 0 ? ResponseEntity.ok().body(users)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user) {
        User u = userService.insert(user);

        ResponseEntity<User> response = u != null ? new ResponseEntity<User>(u, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return response;
    }

    @PutMapping
    public ResponseEntity<User> updateUser() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser() {
        return null;
    }
}
