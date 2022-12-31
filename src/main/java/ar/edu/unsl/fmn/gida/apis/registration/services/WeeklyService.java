package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WeeklyRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.WeeklyValidator;

@Service
@Transactional
public class WeeklyService {

    @Autowired
    private WeeklyRepository weeklyRepository;

    private WeeklyValidator weeklyValidator = new WeeklyValidator(new CustomExpressionValidator());

    public Weekly getOne(int id) {
        Weekly w = null;
        Optional<Weekly> optional = weeklyRepository.findByIdAndActiveTrue(id);

        if (optional.isPresent()) {
            w = optional.get();
        } else {
            throw new ErrorResponse(RegistrationSystemApplication.MESSAGES.getWeeklyMessages()
                    .notFound(Weekly.class.getSimpleName(), id), HttpStatus.NOT_FOUND);
        }

        return w;
    }

    public Weekly getCurrentWeeklyFromPerson(Integer personId) {
        return this.weeklyRepository.findByPersonFkAndEndIsNullAndActiveTrue(personId)
                .orElseThrow(() -> new ErrorResponse(
                        RegistrationSystemApplication.MESSAGES.getWeeklyMessages()
                                .getCurrentWeeklyError(personId),
                        HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public List<Weekly> getAll() {
        return this.weeklyRepository.findAllByActiveTrue();
    }

    public Weekly insert(Weekly weekly) {
        this.weeklyValidator.validate(weekly);
        try {
            if (weekly.getStart() == null) {
                weekly.setStart(new Date());
            } else if (weekly.getStart().compareTo(new Date()) < 0) {
                // check if start date is ok for the new weekly
                throw new ErrorResponse(RegistrationSystemApplication.MESSAGES.getWeeklyMessages()
                        .wrongWeeklyDatetime(), HttpStatus.UNPROCESSABLE_ENTITY);
            }
            this.weeklyRepository.save(weekly);

        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return weekly;
    }

    public Weekly update(Integer personId, Weekly weekly) {
        this.weeklyValidator.validate(weekly);
        Weekly ret = null;
        Weekly w = this.getCurrentWeeklyFromPerson(personId);
        try {
            if (!weekly.equals(w)) {
                // updates in database
                w.setEnd(new Date());
                // then save the new weekly
                if (weekly.getStart() == null) {
                    weekly.setStart(new Date());
                } else if (weekly.getStart().compareTo(new Date()) < 0) {
                    // check if start date is ok for the new weekly
                    throw new ErrorResponse(RegistrationSystemApplication.MESSAGES
                            .getWeeklyMessages().wrongWeeklyDatetime(),
                            HttpStatus.UNPROCESSABLE_ENTITY);
                }
                ret = this.weeklyRepository.save(weekly);
            }
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ret;
    }

    public Weekly delete(int id) {
        throw new ErrorResponse("delete weekly operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
