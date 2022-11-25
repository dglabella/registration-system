package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.AccessRepository;
import ar.edu.unsl.fmn.gida.apis.registration.validators.AccessValidator;
import ar.edu.unsl.fmn.gida.apis.registration.validators.CustomExpressionValidator;

@Service
@Transactional
public class AccessService {

    @Autowired
    private AccessRepository accessRepository;

    public Access getOne(int id) {
        Access a = null;
        Optional<Access> optional = accessRepository.findByIdAndActiveTrue(id);

        if (optional.isPresent()) {
            a = optional.get();
        } else {
            throw new ErrorResponse(RegistrationSystemApplication.MESSAGES.getAccessMessages()
                    .notFoundErrorMessage(id), HttpStatus.NOT_FOUND);
        }

        return a;
    }

    public List<Access> getAll() {
        return accessRepository.findAllByActiveTrue();
    }

    public Access insert(Access access) {
        new AccessValidator(new CustomExpressionValidator()).validate(access);
        Access a = null;
        try {
            a = accessRepository.save(access);
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return a;
    }

    public Access update(int id, Access access) {
        Access a = null;
        Optional<Access> optional = accessRepository.findByIdAndActiveTrue(id);
        if (optional.isPresent()) {
            try {
                access.setId(id);
                accessRepository.save(access);

            } catch (DataIntegrityViolationException exception) {
                exception.printStackTrace();
                throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }

        } else {
            // this error should not happen in a typical situation
            throw new ErrorResponse(RegistrationSystemApplication.MESSAGES.getAccessMessages()
                    .updateErrorMessage(id), HttpStatus.NOT_FOUND);
        }
        return a;
    }

    public Access delete(int id) {
        return null;
    }
}
