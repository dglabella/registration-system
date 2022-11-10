package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {

    List<Credential> findAllByActiveTrue();

    Optional<Credential> findByIdAndActiveTrue(Integer id);

    Optional<Credential> findByPersonIdAndActiveTrue(Integer personId);
}
