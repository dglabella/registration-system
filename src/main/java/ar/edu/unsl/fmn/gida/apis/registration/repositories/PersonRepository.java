package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Optional<Person> findByIdAndActiveTrue(Integer id);

	Optional<Person> findByDniAndActiveTrue(String dni);

	Optional<Person> findByDniAndIdIsNot(String dni, int id);

	Page<Person> findAllByActiveTrue(Pageable pageable);

	Page<Person> findAllByDniContainingAndActiveTrue(String dniPattern, Pageable pageable);

	Page<Person> findAllByDniContainingAndActiveTrueOrderByIdAsc(String dniPattern,
			Pageable pageable);

	Page<Person> findAllByPersonNameContainingAndActiveTrue(String name, PageRequest of);

	Page<Person> findAllByPersonLastNameContainingAndActiveTrue(String lastNamePattern,
			Pageable pageable);
}
