package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.Map;
// import java.util.Optional;
// import java.util.Set;
// import java.util.stream.Collectors;
// import ar.edu.unsl.fmn.gida.apis.registration.enums.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.services.UserService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class UserController {
	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_PAGE_SIZE = 20;

	@Autowired
	private UserService service;

	@GetMapping(value = Urls.Privileges.admin + Urls.users)
	public Page<User> getAllUsers(@RequestParam Map<String, String> map) {
		Page<User> page = null;
		if (!map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAll(this.DEFAULT_PAGE_NUMBER, this.DEFAULT_PAGE_SIZE);
		} else if (map.containsKey("page") && !map.containsKey("size")) {
			page = this.service.getAll(Integer.parseInt(map.get("page")), this.DEFAULT_PAGE_SIZE);
		} else if (!map.containsKey("page") && map.containsKey("size")) {
			page = this.service.getAll(this.DEFAULT_PAGE_NUMBER, Integer.parseInt(map.get("size")));
		} else {
			page = this.service.getAll(Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("size")));
		}

		return page;
	}

	@GetMapping(value = Urls.Privileges.user + Urls.users + "/{id}")
	public User getUser(@PathVariable int id) {
		return this.service.getOne(id);
	}

	@GetMapping(value = Urls.Privileges.user + Urls.users + "/account/{account}")
	public User getUser(@PathVariable String account) {
		return (User) this.service.loadUserByUsername(account);
	}

	@PostMapping(Urls.Privileges.admin + Urls.signup)
	public void postUser(@RequestBody User user) {
		this.service.insert(user);
	}

	@PutMapping(value = Urls.Privileges.user + Urls.users + "/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedAccount = (String) authentication.getPrincipal();

		// Set<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority())
		// .collect(Collectors.toSet());

		// Optional<String> optional = roles.stream().findAny(); // only one privilege exist
		// String priv =
		// optional.orElseThrow(() -> new ErrorResponse(
		// RegistrationSystemApplication.MESSENGER.getUserControllerMessenger()
		// .userPrivilegeIntegrityCorruption(loggedAccount),
		// HttpStatus.FORBIDDEN));

		return this.service.updateUser(id, user, loggedAccount);
	}

	@PutMapping(value = Urls.Privileges.admin + Urls.users + "/{id}")
	public User updateAdmin(@PathVariable int id, @RequestBody User user) {
		return this.service.updateAdmin(id, user);
	}

	@DeleteMapping(Urls.Privileges.admin + Urls.users + "/{id}")
	public User deleteUser(@PathVariable int id) {
		return this.service.delete(id);
	}
}
