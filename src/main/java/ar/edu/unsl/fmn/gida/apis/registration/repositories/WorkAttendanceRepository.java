package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.WorkAttendance;

@Repository
public interface WorkAttendanceRepository extends JpaRepository<WorkAttendance, Integer> {

    Optional<WorkAttendance> findByIdAndActiveTrue(int id);

    Page<WorkAttendance> findAllByWeeklyIdAndActiveTrue(int weeklyId, Pageable pageable);
}
