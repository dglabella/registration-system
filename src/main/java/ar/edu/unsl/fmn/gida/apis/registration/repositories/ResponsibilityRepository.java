package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Responsibility;

@Repository
public interface ResponsibilityRepository extends JpaRepository<Responsibility, Integer> {

    Optional<Responsibility> findByIdAndActiveTrue(int id);

    List<Responsibility> findAllByWeeklyIdAndActiveTrue(Integer weeklyId);

    List<Responsibility> findAllByWeeklyIdAndDayAndActiveTrue(Integer weeklyId, DayOfWeek day);

}
