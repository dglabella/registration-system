package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Privilege;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.services.UserService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = Urls.Privileges.admin + Urls.users)
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = Urls.Privileges.user + Urls.users + "/{id}")
    public User getUser(@PathVariable int id) {
        return this.userService.getOne(id);
    }

    @GetMapping(value = Urls.Privileges.user + Urls.users + "/account/{account}")
    public User getUser(@PathVariable String account) {
        return (User) this.userService.loadUserByUsername(account);
    }

    @PostMapping(Urls.Privileges.pub + Urls.signin)
    public User postUser(@RequestBody User user) {
        return this.userService.insert(user);
    }

    @PutMapping(value = Urls.Privileges.user + Urls.users + "/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String loggedAccount = (String) authentication.getPrincipal();
        Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority())
                .collect(Collectors.toSet());

        Optional<String> optional = roles.stream().findAny(); // only one privilege exist
        String priv =
                optional.orElseThrow(() -> new ErrorResponse(
                        RegistrationSystemApplication.MESSAGES.getUserMessages()
                                .userPrivilegeIntegrityCorruption(loggedAccount),
                        HttpStatus.FORBIDDEN));

        return this.userService.update(id, user, loggedAccount, Privilege.valueOf(priv));
    }

    @DeleteMapping(Urls.Privileges.admin + Urls.users + "/{id}")
    public User deleteUser() {
        throw new ErrorResponse("delete user operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
