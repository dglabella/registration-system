package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.enums.WorkAttendanceState;
import ar.edu.unsl.fmn.gida.apis.registration.model.WorkAttendance;

@Repository
public interface WorkAttendanceRepository extends JpaRepository<WorkAttendance, Integer> {

	Optional<WorkAttendance> findByIdAndActiveTrue(int id);

	Optional<WorkAttendance> findByWeeklyIdAndDateAndActiveTrue(Integer weeklyId, LocalDate date);

	List<WorkAttendance> findAllByWeeklyIdAndActiveTrue(int weeklyId);

	Page<WorkAttendance> findAllByWeeklyIdAndActiveTrue(int weeklyId, Pageable pageable);

	List<WorkAttendance> findAllByDateBetweenAndActiveTrue(LocalDate from, LocalDate to);

	Page<WorkAttendance> findAllByDateBetweenAndActiveTrue(LocalDate from, LocalDate to,
			Pageable pageable);

	List<WorkAttendance> findAllByStateAndDateBetweenAndActiveTrue(WorkAttendanceState state,
			LocalDate from, LocalDate to);

	Page<WorkAttendance> findAllByStateAndDateBetweenAndActiveTrue(WorkAttendanceState state,
			LocalDate from, LocalDate to, Pageable pageable);

	List<WorkAttendance> findAllByWeeklyIdAndActiveTrueAndDateBetween(Integer weeklyId,
			LocalDate from, LocalDate to);

	Page<WorkAttendance> findAllByWeeklyIdAndActiveTrueAndDateBetween(Integer weeklyId,
			LocalDate from, LocalDate to, Pageable pageable);

	List<WorkAttendance> findAllByWeeklyIdAndStateAndActiveTrueAndDateBetween(Integer weeklyId,
			WorkAttendanceState state, LocalDate from, LocalDate to);
}
