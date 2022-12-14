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

    Optional<Person> findByIdAndActiveTrue(Integer id);

    Optional<Person> findByDniAndActiveTrue(String dni);

    List<Person> findByDniContainingAndActiveTrue(String dniPattern);

    List<Person> findAllByPersonNameAndActiveTrue(String name);

    List<Person> findAllByPersonLastNameAndActiveTrue(String lastName);

    List<Person> findAllByPersonNameContainingAndActiveTrue(String namePattern);

    List<Person> findAllByPersonLastNameContainingAndActiveTrue(String lastNamePattern);

}
