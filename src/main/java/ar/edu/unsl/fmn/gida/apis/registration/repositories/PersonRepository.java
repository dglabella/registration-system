package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<List<Person>> findByName(String name);
    Optional<List<Person>> findByLastName(String lastName);
    Optional<List<Person>> findByDni(String dni);
}
