package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findAllByActiveTrue();

    Page<Person> findAllByActiveTrue(Pageable pageable);

    Optional<Person> findByIdAndActiveIsTrue(Integer id);

    Optional<Person> findByDniAndActiveIsTrue(String dni);

    List<Person> findByDniContainingAndActiveIsTrue(String dniPattern);

    List<Person> findAllByPersonNameAndActiveIsTrue(String name);

    List<Person> findAllByPersonLastNameAndActiveIsTrue(String lastName);

    List<Person> findAllByPersonNameContainingAndActiveIsTrue(String namePattern);

    List<Person> findAllByPersonLastNameContainingAndActiveIsTrue(String lastNamePattern);
}
