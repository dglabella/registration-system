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

    Optional<User> findOneByDniAndActiveTrue(String dni);

    Optional<User> findOneByEmailAndActiveTrue(String email);

    Optional<User> findOneByAccountAndActiveTrue(String account);

    List<User> findAllByActiveTrue();

    Optional<User> findOneByDni(String dni);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByAccount(String account);

    Optional<User> findOneByDniAndIdIsNot(String dni, int id);

    Optional<User> findOneByEmailAndIdIsNot(String email, int id);

    Optional<User> findOneByAccountAndIdIsNot(String account, int id);
}
