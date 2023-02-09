package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Access;

@Repository
public interface AccessRepository extends JpaRepository<Access, Integer> {

	Optional<Access> findByIdAndActiveTrue(Integer id);

	Page<Access> findAllByActiveTrue(Pageable pageable);
}
