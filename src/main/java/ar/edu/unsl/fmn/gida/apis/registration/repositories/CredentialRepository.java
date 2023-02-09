package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {

	Page<Credential> findAllByActiveTrue(Pageable pageable);

	Optional<Credential> findByIdAndActiveTrue(Integer id);

	Optional<Credential> findByPersonIdAndActiveTrue(Integer personId);
}
