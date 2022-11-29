package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByDniOrEmailOrAccountAndActiveTrue(String dni, String email, String account);

    Optional<User> findByIdAndActiveTrue(Integer id);

    List<User> findAllByActiveTrue();
}
