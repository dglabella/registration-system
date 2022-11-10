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

    Page<Register> findAllByCheckInBetweenAndActiveTrue(Date from, Date to, Pageable pageable);

    List<Register> findAllByPersonIdAndActiveTrue(Integer id);

    Optional<Register> findByIdAndActiveTrue(Integer id);

    Optional<Register> findByPersonFkAndCheckOutIsNullAndActiveTrue(int personFk);

    Page<Register> findAllByPersonFkAndActiveTrueAndCheckInBetween(Integer personId, Date from,
            Date to, Pageable pageable);
}
