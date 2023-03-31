package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;

@Repository
public interface DependencyRepository extends JpaRepository<Dependency, Integer> {

	Optional<Dependency> findByIdAndActiveTrue(Integer id);

	Page<Dependency> findAllByActiveTrue(Pageable pageable);

	Optional<Dependency> findByDependencyNameAndActiveTrue(String dependencyName);
}
