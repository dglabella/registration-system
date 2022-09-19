package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.DependencyRepository;

@Service
public class DependencyService {

    @Autowired
    private DependencyRepository dependencyRepository;

    public Dependency getOne(int id) {
        Dependency d = null;
        Optional<Dependency> optional = dependencyRepository.findByIdAndActiveIsTrue(id);

        if (optional.isPresent()) {
            d = optional.get();
        }else{
            throw new ErrorResponse("there is no dependency with id: " + id, HttpStatus.NOT_FOUND);
        }

        return d;
    }

    public List<Dependency> getAll() {
        return dependencyRepository.findAllByActiveTrue();
    }

    public Dependency insert(Dependency dependency) {
        Dependency d = null;
        try {
            d = dependencyRepository.save(dependency);
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return d;
    }

    public Dependency update(int id, Dependency dependency) {
        Dependency d = null;
        
        Optional<Dependency> optional = dependencyRepository.findByIdAndActiveIsTrue(id);
        if (optional.isPresent()) {
            try{
                dependency.setId(id);
                dependencyRepository.save(dependency);
            
            } catch (DataIntegrityViolationException exception) {
                exception.printStackTrace();
                throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
        
        } else {
            // this error should not happen in a typical situation
            throw new ErrorResponse(
                    "cannot update dependency with id " + id + " because it doesn't exist",
                    HttpStatus.NOT_FOUND);
        }
        return d;
    }

    public Dependency delete(int id) {
        return null;
    }
}
