package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;

@Repository
public interface WeeklyRepository extends JpaRepository<Weekly, Integer> {

	long countByPersonIdAndActiveTrue(Integer personId);

	Optional<Weekly> findByIdAndActiveTrue(Integer id);

	Optional<Weekly> findByPersonIdAndEndIsNullAndActiveTrue(Integer personId);

	Optional<Weekly> findTopByPersonIdOrderByIdDesc(Integer personId);

	Optional<Weekly> findByPersonIdAndActiveTrueAndStartLessThanEqualAndEndGreaterThanEqual(
			Integer personId, LocalDate date1, LocalDate date2);

	Page<Weekly> findAllByActiveTrue(Pageable pageable);

	List<Weekly> findAllByPersonIdAndActiveTrue(Integer personId);

	Page<Weekly> findAllByPersonIdAndActiveTrue(Integer personId, PageRequest pageble);
}
