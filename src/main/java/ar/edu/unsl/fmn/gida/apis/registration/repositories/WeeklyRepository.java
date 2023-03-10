package ar.edu.unsl.fmn.gida.apis.registration.repositories;

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

	Optional<Weekly> findByIdAndActiveTrue(Integer id);

	Optional<Weekly> findByPersonIdAndEndIsNullAndActiveTrue(Integer personId);

	Page<Weekly> findAllByActiveTrue(Pageable pageable);

	Page<Weekly> findAllByPersonIdAndActiveTrue(Integer personId, PageRequest pageble);

	List<Weekly> findAllByPersonIdAndActiveTrue(Integer personId);
}
