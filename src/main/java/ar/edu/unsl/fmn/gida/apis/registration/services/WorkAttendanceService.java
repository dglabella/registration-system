package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.WorkAttendance;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.WorkAttendanceRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.WorkAttendanceValidator;

@Service
@Transactional
public class WorkAttendanceService {

    @Autowired
    private WorkAttendanceRepository repository;

    private final WorkAttendanceValidator validator =
            new WorkAttendanceValidator(new CustomExpressionValidator(),
                    RegistrationSystemApplication.MESSENGER.getPersonValidationMessenger());


    public WorkAttendance getOne(int id) {
        return this.repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ErrorResponse(
                        RegistrationSystemApplication.MESSENGER.getWorkAttendanceServiceMessenger()
                                .notFound(WorkAttendance.class.getSimpleName(), id),
                        HttpStatus.NOT_FOUND));
    }

    public Page<WorkAttendance> getAllFromPerson(int personId, int page, int size) {
        return this.repository.findAllByPersonIdAndActiveTrue(personId, PageRequest.of(page, size));
    }

    public WorkAttendance insert(WorkAttendance requestBody) {
        throw new ErrorResponse("work attendance insert operation not implemented",
                HttpStatus.NOT_IMPLEMENTED);
    }

    public void createWorkAttendancesForPersonBetweenDates(int personId, LocalDate from,
            LocalDate to) {
        throw new ErrorResponse("work attendances creation not implemented",
                HttpStatus.NOT_IMPLEMENTED);
    }

    public WorkAttendance update(int id) {
        throw new ErrorResponse("work attendances update not implemented",
                HttpStatus.NOT_IMPLEMENTED);
    }

    public void delete(int id) {
        throw new ErrorResponse("work attendances delete not implemented",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
