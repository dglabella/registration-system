package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getOne(int id) {
        User u = null;
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            u = optional.get();
        }

        return u;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User insert(User user) {
        User u = userRepository.save(user);
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
