package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByIdAndActiveIsTrue(int id);

    Optional<Person> findByDniAndActiveTrue(String dni);

    List<Person> findAllByPersonNameAndActiveTrue(String name);

    List<Person> findAllByPersonLastNameAndActiveTrue(String lastName);

    List<Person> findAllByActiveTrue();

    List<Person> findAllByPAGINATEDDDDDDActiveTrue(int page, int quantityPerPage);
}
