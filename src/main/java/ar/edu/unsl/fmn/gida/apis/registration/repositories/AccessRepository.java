package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unsl.fmn.gida.apis.registration.model.Access;

@Repository
public interface AccessRepository extends JpaRepository<Access, Integer> {
    Optional<Access> findByIdAndActiveIsTrue(int id);
    List<Access> findAllByActiveTrue();
}
