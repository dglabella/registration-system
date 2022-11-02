package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;

@Repository
public interface WeeklyRepository extends JpaRepository<Weekly, Integer> {

    Optional<Weekly> findByIdAndActiveIsTrue(Integer id);

    Optional<Weekly> findByPersonFkAndEndIsNullAndActiveIsTrue(Integer personFk);

    List<Weekly> findAllByActiveTrue();
}
