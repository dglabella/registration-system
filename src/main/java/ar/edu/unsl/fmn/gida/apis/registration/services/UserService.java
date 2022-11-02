package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.UserRepository;
import ar.edu.unsl.fmn.gida.apis.registration.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.validators.UserValidator;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserValidator validator = new UserValidator(new CustomExpressionValidator());

    public User getOne(int id) {
        User u = null;
        Optional<User> optional = userRepository.findByIdAndActiveIsTrue(id);

        if (optional.isPresent()) {
            u = optional.get();
        } else {
            throw new ErrorResponse("there is no user with id: " + id, HttpStatus.NOT_FOUND);
        }

        return u;
    }

    public List<User> getAll() {
        return userRepository.findAllByActiveTrue();
    }

    public User insert(User user) {
        this.validator.validate(user);

        User u = null;
        try {
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
        Optional<User> optional = this.userRepository.findByIdAndActiveIsTrue(id);

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
                    "cannot update user with id " + id + " because it doesn't exist",
                    HttpStatus.NOT_FOUND);
        }
        return u;
    }

    public User delete(int id) {
        return null;
    }
}
