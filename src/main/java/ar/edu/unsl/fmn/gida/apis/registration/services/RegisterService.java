package ar.edu.unsl.fmn.gida.apis.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.RegisterRepository;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    public Register getOne(int id) {
        Register r = null;
        Optional<Register> optional = registerRepository.findById(id);

        if (optional.isPresent()) {
            r = optional.get();
        }
        else{
            throw new ErrorResponse("there is no register with id: " + id, HttpStatus.NOT_FOUND);
        }

        return r;
    }

    public List<Register> getAll() {
        return registerRepository.findAll();
    }

    public List<Register> getRegistersFromPerson(int id){
        List<Register> r = registerRepository.findAllByPersonIdAndActiveTrue(id);

        return r;
    }

    public Register insert(Register register) {
        Register r = registerRepository.save(register);
        return r;
    }

    public Register update(Register register) {
        Register r = registerRepository.save(register);
        return r;
    }

    public Register delete(int id) {
        return null;
    }
}
