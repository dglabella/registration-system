package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WeeklyRepository;

@Service
public class WeeklyService {

    @Autowired
    private WeeklyRepository weeklyRepository;

    public Weekly getOne(int id) {
        Weekly w = null;
        Optional<Weekly> optional = weeklyRepository.findByIdAndActiveIsTrue(id);

        if (optional.isPresent()) {
            w = optional.get();
        }else {
            throw new ErrorResponse("there is no weekly with id: " + id, HttpStatus.NOT_FOUND);
        }

        return w;
    }

    public List<Weekly> getAll() {
        return weeklyRepository.findAllByActiveTrue();
    }

    public Weekly insert(Weekly weekly) {
        Weekly w = null;
        try {
            w = weeklyRepository.save(weekly);
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return w;
    }

    public Weekly update(int id, Weekly weekly) {
        Weekly w = null;
        Optional<Weekly> optional = weeklyRepository.findByIdAndActiveIsTrue(id);
        if (optional.isPresent()) {
            try{
                weekly.setId(id);
                weeklyRepository.save(weekly);
            
            } catch (DataIntegrityViolationException exception) {
                exception.printStackTrace();
                throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
        
        } else {
            // this error should not happen in a typical situation
            throw new ErrorResponse(
                    "cannot update weekly with id " + id + " because it doesn't exist",
                    HttpStatus.NOT_FOUND);
        }
        return w;
    }

    public Weekly delete(int id) {
        return null;
    }
}
