package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByDniAndActiveTrue(String dni);
    Optional<Person> findByIdAndActiveIsTrue(int id);

    List<Person> findByNameAndActiveTrue(String name);
    List<Person> findByLastNameAndActiveTrue(String lastName);

    List<Person> findAllByActiveTrue();
}
