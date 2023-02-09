package ar.edu.unsl.fmn.gida.apis.registration.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.services.CredentialService;
import ar.edu.unsl.fmn.gida.apis.registration.urls.Urls;

@RestController
@RequestMapping(value = Urls.Privileges.responsible + Urls.credentials)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"})
public class CredentialController {
	private final int DEFAULT_PAGE_NUMBER = 0;
	private final int DEFAULT_QUANTITY_PER_PAGE = 20;

	@Autowired
	private CredentialService credentialService;

	@GetMapping(value = "/{id}")
	public Credential getCredential(@PathVariable int id) {
		Credential credential = this.credentialService.getOne(id);
		return credential;
	}

	@GetMapping
	public Page<Credential> getAllCredentials(@RequestParam Map<String, String> map) {
		Page<Credential> page = null;
		if (!map.containsKey("page") && !map.containsKey("quantity")) {
			page = this.credentialService.getAll(this.DEFAULT_PAGE_NUMBER,
					this.DEFAULT_QUANTITY_PER_PAGE);
		} else if (map.containsKey("page") && !map.containsKey("quantity")) {
			page = this.credentialService.getAll(Integer.parseInt(map.get("page")),
					this.DEFAULT_QUANTITY_PER_PAGE);
		} else if (!map.containsKey("page") && map.containsKey("quantity")) {
			page = this.credentialService.getAll(this.DEFAULT_PAGE_NUMBER,
					Integer.parseInt(map.get("quantity")));
		} else {
			page = this.credentialService.getAll(Integer.parseInt(map.get("page")),
					Integer.parseInt(map.get("quantity")));
		}

		return page;
	}

	@PutMapping(value = "/{id}")
	public Credential updateCredential(@PathVariable int id, @RequestBody Credential credential) {
		Credential c = this.credentialService.update(id, credential);
		return c;
	}

	@DeleteMapping(value = "/{id}")
	public Credential deleteCredential() {
		throw new ErrorResponse(
				RegistrationSystemApplication.MESSENGER.getCredentialControllerMessenger()
						.operationNotImplementedYet("delete", Credential.class.getSimpleName()),
				HttpStatus.NOT_IMPLEMENTED);
	}
}
