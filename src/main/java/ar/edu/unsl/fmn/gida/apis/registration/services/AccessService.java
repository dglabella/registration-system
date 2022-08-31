package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.AccessRepository;

@Service
public class AccessService {

    @Autowired
    private AccessRepository accessRepository;

    public Access getOne(int id) {
        Access a = null;
        Optional<Access> optional = accessRepository.findById(id);

        if (optional.isPresent()) {
            a = optional.get();
        }

        return a;
    }

    public List<Access> getAll() {
        return accessRepository.findAll();
    }

    public Access insert(Access access) {
        Access a = accessRepository.save(access);
        return a;
    }

    public Access update(Access access) {
        Access a = accessRepository.save(access);
        return a;
    }

    public Access delete(int id) {
        return null;
    }
}
