package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {

	Page<Register> findAll(Pageable pageable);

	List<Register> findAllByCheckInBetweenAndActiveTrue(Date from, Date to);

	List<Register> findAllByCheckInBetweenAndActiveTrueOrderByPersonFkAsc(Date from, Date to);

	Page<Register> findAllByCheckInBetweenAndActiveTrue(Date from, Date to, Pageable pageable);

	List<Register> findAllByPersonIdAndActiveTrue(Integer id);

	Optional<Register> findByIdAndActiveTrue(Integer id);

	Optional<Register> findByPersonFkAndCheckOutIsNullAndActiveTrue(int personFk);

	List<Register> findAllByPersonFkAndActiveTrueAndCheckInBetween(Integer personId, Date from,
			Date to);

	Page<Register> findAllByPersonFkAndActiveTrueAndCheckInBetween(Integer personId, Date from,
			Date to, Pageable pageable);

	// @Query(value = "SELECT * " + "FROM registers "
	// + "INNER JOIN persons ON registers.person_fk = persons.id "
	// + "WHERE persons.dni LIKE'%33%' AND "
	// + "(registers.check_in BETWEEN '2020-08-01' AND '2020-09-01') AND "
	// + "persons.active = 1 AND registers.active = 1 \n#pageable\n",
	// countQuery = "SELECT COUNT(*) " + "FROM registers "
	// + "INNER JOIN persons ON registers.person_fk = persons.id "
	// + "WHERE persons.dni LIKE '%33%' AND "
	// + "(registers.check_in BETWEEN '2020-08-01' AND '2020-09-01') AND "
	// + "persons.active = 1 AND registers.active = 1",
	// nativeQuery = true)
	// Page<Register> findAllByPersonDniContainingAndActiveTrue(@Param("dni") String dni,
	// @Param("from") Date from, @Param("to") Date to, Pageable pageable);
}
