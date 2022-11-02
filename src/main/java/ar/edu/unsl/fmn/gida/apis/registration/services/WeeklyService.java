package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WeeklyRepository;
import ar.edu.unsl.fmn.gida.apis.registration.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.validators.WeeklyValidator;

@Service
@Transactional
public class WeeklyService {

    @Autowired
    private WeeklyRepository weeklyRepository;

    private WeeklyValidator weeklyValidator = new WeeklyValidator(new CustomExpressionValidator());

    public Weekly getOne(int id) {
        Weekly w = null;
        Optional<Weekly> optional = weeklyRepository.findByIdAndActiveIsTrue(id);

        if (optional.isPresent()) {
            w = optional.get();
        } else {
            throw new ErrorResponse("there is no weekly with id: " + id, HttpStatus.NOT_FOUND);
        }

        return w;
    }

    public Weekly getCurrentWeeklyFromPerson(Integer personId) {
        Weekly ret = null;
        Optional<Weekly> optional =
                this.weeklyRepository.findByPersonFkAndEndIsNullAndActiveIsTrue(personId);
        if (optional.isPresent()) {
            ret = optional.get();
        } else {
            throw new ErrorResponse("FATAL ERROR: database integrity may be corrupted; person "
                    + personId + " has not a current weekly", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ret;
    }

    public List<Weekly> getAll() {
        return weeklyRepository.findAllByActiveTrue();
    }

    public Weekly insert(Weekly weekly) {
        this.weeklyValidator.validate(weekly);
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

    public Weekly update(Integer personId, Weekly weekly) {
        // if (weekly != null) {
        this.weeklyValidator.validate(weekly);
        Weekly w = this.getCurrentWeeklyFromPerson(personId);
        try {
            if (!weekly.equals(w)) {
                // updates in database
                w.setEnd(new Date());
                // then save the new weekly
                if (weekly.getStart() == null) {
                    weekly.setStart(new Date());
                }
                this.weeklyRepository.save(weekly);
            }
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        // }
        return weekly;
    }

    public Weekly delete(int id) {
        return null;
    }
}
