package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.DependencyRepository;

@Service
public class DependencyService {

    @Autowired
    private DependencyRepository dependencyRepository;

    public Dependency getOne(int id) {
        Dependency d = null;
        Optional<Dependency> optional = dependencyRepository.findById(id);

        if (optional.isPresent()) {
            d = optional.get();
        }

        return d;
    }

    public List<Dependency> getAll() {
        return dependencyRepository.findAll();
    }

    public Dependency insert(Dependency dependency) {
        Dependency d = dependencyRepository.save(dependency);
        return d;
    }

    public Dependency update(Dependency dependency) {
        Dependency d = dependencyRepository.save(dependency);
        return d;
    }

    public Dependency delete(int id) {
        return null;
    }
}
