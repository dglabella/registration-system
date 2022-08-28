package ar.edu.unsl.fmn.gida.apis.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        return r;
    }

    public List<Register> getAll() {
        return registerRepository.findAll();
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
