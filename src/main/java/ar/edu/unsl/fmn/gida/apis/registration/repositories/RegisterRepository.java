package ar.edu.unsl.fmn.gida.apis.registration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unsl.fmn.gida.apis.registration.model.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    List<Register> findAllByPersonIdAndActiveTrue(int id);

    Optional<Register> findByIdAndActiveIsTrue(int id);

    Optional<Register> findByPersonFkAndCheckOutIsNullAndActiveIsTrue(int personFk);
}
