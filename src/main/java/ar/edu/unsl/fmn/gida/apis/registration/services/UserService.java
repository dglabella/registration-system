package ar.edu.unsl.fmn.gida.apis.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.UserRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.UserValidator;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	private final UserValidator validator = new UserValidator(new CustomExpressionValidator(),
			RegistrationSystemApplication.MESSENGER.getUserValidationMessenger());

	private final PasswordEncoder encoder = new BCryptPasswordEncoder();

	public User get(int id) {
		return this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(
						() -> new ErrorResponse(
								RegistrationSystemApplication.MESSENGER.getUserServiceMessenger()
										.notFound(User.class.getSimpleName(), id),
								HttpStatus.NOT_FOUND));
	}

	public Page<User> getAll(int page, int quantity) {
		return this.repository.findAllByActiveTrue(PageRequest.of(page, quantity));
	}

	public User insert(User requestBody) {
		this.validator.validateInsert(requestBody);

		this.repository.findOneByDniAndActiveTrue(requestBody.getDni()).ifPresent(u -> {
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getUserServiceMessenger()
							.alreadyExistConstraint(User.class.getSimpleName(), "dni", u.getDni()),
					HttpStatus.CONFLICT);
		});

		this.repository.findOneByEmailAndActiveTrue(requestBody.getEmail()).ifPresent(u -> {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserServiceMessenger().alreadyExistConstraint(User.class.getSimpleName(),
							"email", u.getEmail()),
					HttpStatus.CONFLICT);
		});

		this.repository.findOneByAccountAndActiveTrue(requestBody.getAccount()).ifPresent(u -> {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserServiceMessenger().alreadyExistConstraint(User.class.getSimpleName(),
							"account", u.getAccount()),
					HttpStatus.CONFLICT);
		});

		User ret = null;

		requestBody.setPassword(this.encoder.encode(requestBody.getPassword()));
		ret = this.repository.save(requestBody);

		return ret;
	}

	public User updateUser(int id, User requestBody, String loggedAccount) {
		this.validator.validateUpdate(requestBody);
		User ret = null;

		User loggedUser = this.repository.findOneByAccountAndActiveTrue(loggedAccount)
				.orElseThrow(() -> new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getUserServiceMessenger().accesssViolation(loggedAccount),
						HttpStatus.FORBIDDEN));

		if (loggedUser.getId() != id) {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserServiceMessenger().updateAccessViolation(loggedAccount),
					HttpStatus.UNAUTHORIZED);
		}

		this.repository.findOneByDniAndIdIsNot(requestBody.getDni(), id).ifPresent(u -> {
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getUserServiceMessenger()
							.alreadyExistConstraint(User.class.getSimpleName(), "dni", u.getDni()),
					HttpStatus.CONFLICT);
		});

		this.repository.findOneByEmailAndIdIsNot(requestBody.getEmail(), id).ifPresent(u -> {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserServiceMessenger().alreadyExistConstraint(User.class.getSimpleName(),
							"email", u.getEmail()),
					HttpStatus.CONFLICT);
		});

		this.repository.findOneByAccountAndIdIsNot(requestBody.getAccount(), id).ifPresent(u -> {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserServiceMessenger().alreadyExistConstraint(User.class.getSimpleName(),
							"account", u.getAccount()),
					HttpStatus.CONFLICT);
		});

		if (requestBody.getOldPassword() != null && requestBody.getPassword() != null) {
			if (!this.encoder.matches(requestBody.getOldPassword(), loggedUser.getPassword())) {
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getUserServiceMessenger().oldPasswordNotMatching(),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
			requestBody.setPassword(this.encoder.encode(requestBody.getPassword()));
		} else {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserServiceMessenger().oldPasswordIsNull(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		requestBody.setId(id);
		requestBody.setEnabled(true); // because user is active = false by default
		requestBody.setPrivilege(loggedUser.getPrivilege());

		ret = this.repository.save(requestBody);

		return ret;
	}

	public User updateAdmin(int id, User requestBody) {
		this.validator.validateUpdate(requestBody);

		User ret = null;

		// this error should not happen in a typical situation
		User user = this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getUserServiceMessenger()
								.updateNonExistentEntity(User.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));

		this.repository.findOneByDniAndIdIsNot(requestBody.getDni(), id).ifPresent(u -> {
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getUserServiceMessenger()
							.alreadyExistConstraint(User.class.getSimpleName(), "dni", u.getDni()),
					HttpStatus.CONFLICT);
		});

		this.repository.findOneByEmailAndIdIsNot(requestBody.getEmail(), id).ifPresent(u -> {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserServiceMessenger().alreadyExistConstraint(User.class.getSimpleName(),
							"email", u.getEmail()),
					HttpStatus.CONFLICT);
		});

		this.repository.findOneByAccountAndIdIsNot(requestBody.getAccount(), id).ifPresent(u -> {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserServiceMessenger().alreadyExistConstraint(User.class.getSimpleName(),
							"account", u.getAccount()),
					HttpStatus.CONFLICT);
		});

		requestBody.setId(id);
		requestBody.setPassword(this.encoder.encode(requestBody.getPassword()));
		requestBody.setEnabled(true); // because user is active = false by default
		requestBody.setPrivilege(user.getPrivilege());
		ret = this.repository.save(requestBody);

		return ret;
	}

	public User delete(int id) {
		User ret = this.repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getUserServiceMessenger()
								.deleteNonExistentEntity(User.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));

		ret.setEnabled(false);

		return ret;
	}

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		// System.out.println("user is:\n" + user.getAccount() + " " + user.getPassword());
		User user = this.repository.findOneByAccountAndActiveTrue(account).orElseThrow(
				() -> new UsernameNotFoundException(RegistrationSystemApplication.MESSENGER
						.getUserServiceMessenger().notFoundByAccount(account)));
		return user;
	}

	// ---------------------------------- INICIO CRISTIAN  --------------------------------------------------
	
	public Page<User> getAllByDni(String dni, int page, int size) {
		Page<User> usersPage = this.repository.findAllByDniAndActiveTrue(dni,
				PageRequest.of(page, size));
		return usersPage;
	}
	
	public Page<User> getAllByUserNameApproach(String name, int page, int size) {
		Page<User> usersPage = this.repository.findAllByUserNameContainingAndActiveTrue(name,
				PageRequest.of(page, size));
		return usersPage;
	}
	
	
	public Page<User> getAllByUserLastNameApproach(String lastName, int page, int size) {
		Page<User> usersPage = this.repository.findAllByUserLastNameContainingAndActiveTrue(lastName,
				PageRequest.of(page, size));
		return usersPage;
	}
	// ---------------------------------- FIN CRISTIAN -----------------------------------------------------
}
