package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.UserRepository;
import ar.edu.unsl.fmn.gida.apis.registration.utils.validators.UserValidator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserValidator userValidator;

    public List<User> getAll() {
        return userRepository.findByActiveTrue();
    }

    public User getOne(int id) {
        User u = null;
        Optional<User> optional = userRepository.findByIdAndActiveTrue(id);

        if (optional.isPresent()) {
            u = optional.get();
        } else {
            throw new ErrorResponse("there is no user with id: " + id, HttpStatus.NOT_FOUND);
        }

        return u;
    }

    public User insert(User user) {
        User u = null;

        // if (this.userValidator.validate(user)) {
        // // save
        // } else {
        // // throw error and get message
        // }

        // try {
        u = userRepository.save(user);
        // } catch (DataIntegrityViolationException exception) {
        // exception.printStackTrace();
        // throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
        // HttpStatus.BAD_REQUEST);
        // }

        return u;
    }

    public User update(User user) {
        User u = null;
        if (user.getId() != null) {
            // has to have id if it is going to be updated, otherwise there is no distiction between
            // insert and update
            u = userRepository.save(user);
        }
        // return null means error: "you can't update user if you don't tell me which one..."
        return u;
    }

    public User delete(int id) {
        return null;
    }
}
