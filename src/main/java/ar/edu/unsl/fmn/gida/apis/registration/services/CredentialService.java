package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.RegistrationSystemApplication;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.CredentialRepository;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CredentialValidator;
import ar.edu.unsl.fmn.gida.apis.registration.services.validators.CustomExpressionValidator;

@Service
@Transactional
public class CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    private CredentialValidator credentialValidator =
            new CredentialValidator(new CustomExpressionValidator(),
                    RegistrationSystemApplication.MESSAGES.getCredentialValidationMessages());

    public Credential getOne(Integer id) {
        Credential credential = null;
        Optional<Credential> optional = this.credentialRepository.findByIdAndActiveTrue(id);
        if (optional.isPresent()) {
            credential = optional.get();
        } else {
            throw new ErrorResponse(
                    RegistrationSystemApplication.MESSAGES.getCredentialBusinessLogicMessages()
                            .notFound(Credential.class.getSimpleName(), id),
                    HttpStatus.NOT_FOUND);
        }
        return credential;
    }

    public Credential getOneByPersonId(Integer personId) {
        Credential credential = null;
        Optional<Credential> optional =
                this.credentialRepository.findByPersonIdAndActiveTrue(personId);
        if (optional.isPresent()) {
            credential = optional.get();
        } else {
            throw new ErrorResponse(RegistrationSystemApplication.MESSAGES
                    .getCredentialBusinessLogicMessages().notFoundByPersonIdErrorMessage(personId),
                    HttpStatus.NOT_FOUND);
        }
        return credential;
    }

    public Page<Credential> getAll(int page, int quantity) {
        return this.credentialRepository.findAllByActiveTrue(PageRequest.of(page, quantity));
    }

    public Credential insert(Credential credential) {
        this.credentialValidator.validate(credential);
        return this.credentialRepository.save(credential);
    }

    public Credential update(int id, Credential credential) {
        throw new ErrorResponse("update credential operation not implemented yet...",
                HttpStatus.NOT_IMPLEMENTED);
    }
}
