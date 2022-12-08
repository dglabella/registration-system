package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import ar.edu.unsl.fmn.gida.apis.registration.security.CustomUserDetails;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.UserValidator;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final UserValidator validator = new UserValidator(new CustomExpressionValidator());
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getOne(int id) {
        User u = null;
        Optional<User> optional = userRepository.findByIdAndActiveTrue(id);
        if (optional.isPresent()) {
            u = optional.get();
        } else {
            throw new ErrorResponse(RegistrationSystemApplication.MESSAGES.getUserMessages()
                    .notFoundErrorMessage(User.class.getSimpleName(), id), HttpStatus.NOT_FOUND);
        }

        return u;
    }

    public List<User> getAll() {
        return userRepository.findAllByActiveTrue();
    }

    public User insert(User user) {
        this.validator.validate(user);
        User u = null;
        if (this.userRepository.existsByDniOrEmailOrAccountAndActiveTrue(user.getDni(),
                user.getEmail(), user.getAccount())) {
            throw new ErrorResponse(RegistrationSystemApplication.MESSAGES.getUserMessages()
                    .constraintsErrorMessage(User.class.getSimpleName(), "or or or", "raasdmas"),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            u = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return u;
    }

    public User update(int id, User user) {
        User u = null;
        Optional<User> optional = this.userRepository.findByIdAndActiveTrue(id);

        if (optional.isPresent()) {
            try {
                user.setId(id);
                u = userRepository.save(user);
            } catch (DataIntegrityViolationException exception) {
                exception.printStackTrace();
                throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } else {
            // this error should not happen in a typical situation
            throw new ErrorResponse(
                    RegistrationSystemApplication.MESSAGES.getUserMessages()
                            .updateNonExistentEntityErrorMessage(User.class.getSimpleName(), id),
                    HttpStatus.NOT_FOUND);
        }
        return u;
    }

    public User delete(int id) {
        throw new ErrorResponse("delete user operation not implemented yet...",
                HttpStatus.NOT_FOUND);
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByAccountAndActiveTrue(account).orElseThrow(
                () -> new UsernameNotFoundException(RegistrationSystemApplication.MESSAGES
                        .getUserMessages().notFoundByAccountErrorMessage(account)));
        System.out.println("user is:\n" + user.getAccount() + " " + user.getPassword());

        return new CustomUserDetails(user);
    }
}
