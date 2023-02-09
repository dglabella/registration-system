package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import ar.edu.unsl.fmn.gida.apis.registration.enums.Privilege;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.UserRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.UserValidator;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private final UserValidator validator = new UserValidator(new CustomExpressionValidator(),
			RegistrationSystemApplication.MESSENGER.getUserValidationMessenger());
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User getOne(int id) {
		User u = null;
		Optional<User> optional = userRepository.findByIdAndActiveTrue(id);
		if (optional.isPresent()) {
			u = optional.get();
		} else {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserBusinessLogicMessenger().notFound(User.class.getSimpleName(), id),
					HttpStatus.NOT_FOUND);
		}

		return u;
	}

	public Page<User> getAll(int page, int quantity) {
		return userRepository.findAllByActiveTrue(PageRequest.of(page, quantity));
	}

	public User insert(User user) {
		this.validator.validate(user);
		this.userRepository.findOneByDniAndActiveTrue(user.getDni()).ifPresent(u -> {
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getUserBusinessLogicMessenger()
							.constraintsError(User.class.getSimpleName(), "dni", u.getDni()),
					HttpStatus.CONFLICT);
		});

		this.userRepository.findOneByEmailAndActiveTrue(user.getEmail()).ifPresent(u -> {
			throw new ErrorResponse(
					RegistrationSystemApplication.MESSENGER.getUserBusinessLogicMessenger()
							.constraintsError(User.class.getSimpleName(), "email", u.getEmail()),
					HttpStatus.CONFLICT);
		});

		this.userRepository.findOneByAccountAndActiveTrue(user.getAccount()).ifPresent(u -> {
			throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
					.getUserBusinessLogicMessenger().constraintsError(User.class.getSimpleName(),
							"account", u.getAccount()),
					HttpStatus.CONFLICT);
		});

		User ret = null;

		try {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			ret = userRepository.save(user);
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return ret;
	}

	public User update(int id, User user, String loggedAccount, Privilege privilege) {
		this.validator.validate(user);

		User ret = null;

		Optional<User> loggedUserOpt =
				this.userRepository.findOneByAccountAndActiveTrue(loggedAccount);

		User loggedUser = loggedUserOpt
				.orElseThrow(() -> new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getUserBusinessLogicMessenger().accesssViolation(loggedAccount),
						HttpStatus.FORBIDDEN));

		// this error should not happen in a typical situation
		User resourceUser = this.userRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getUserBusinessLogicMessenger()
								.updateNonExistentEntity(User.class.getSimpleName(), id),
						HttpStatus.NOT_FOUND));

		try {
			if (loggedUser.getPrivilege() != Privilege.ROLE_ADMIN && loggedUser.getId() != id) {
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getUserBusinessLogicMessenger().updateAccessViolation(loggedAccount),
						HttpStatus.UNAUTHORIZED);
			}

			this.userRepository.findOneByDniAndIdIsNot(user.getDni(), id).ifPresent(u -> {
				throw new ErrorResponse(
						RegistrationSystemApplication.MESSENGER.getUserBusinessLogicMessenger()
								.constraintsError(User.class.getSimpleName(), "dni", u.getDni()),
						HttpStatus.CONFLICT);
			});

			this.userRepository.findOneByEmailAndIdIsNot(user.getEmail(), id).ifPresent(u -> {
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getUserBusinessLogicMessenger()
						.constraintsError(User.class.getSimpleName(), "email", u.getEmail()),
						HttpStatus.CONFLICT);
			});

			this.userRepository.findOneByAccountAndIdIsNot(user.getAccount(), id).ifPresent(u -> {
				throw new ErrorResponse(RegistrationSystemApplication.MESSENGER
						.getUserBusinessLogicMessenger()
						.constraintsError(User.class.getSimpleName(), "account", u.getAccount()),
						HttpStatus.CONFLICT);
			});

			user.setId(id);
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			user.setEnabled(true); // because user is active = false by default
			user.setPrivilege(resourceUser.getPrivilege());
			ret = userRepository.save(user);
		} catch (DataIntegrityViolationException exception) {
			exception.printStackTrace();
			throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return ret;
	}

	public User delete(int id) {
		throw new ErrorResponse("delete user operation not implemented yet...",
				HttpStatus.NOT_FOUND);
	}

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		// System.out.println("user is:\n" + user.getAccount() + " " + user.getPassword());
		User user = this.userRepository.findOneByAccountAndActiveTrue(account).orElseThrow(
				() -> new UsernameNotFoundException(RegistrationSystemApplication.MESSENGER
						.getUserBusinessLogicMessenger().notFoundByAccount(account)));
		return user;
	}
}
