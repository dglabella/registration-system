package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.CredentialRepository;
import ar.edu.unsl.fmn.gida.apis.registration.validators.CredentialValidator;
import ar.edu.unsl.fmn.gida.apis.registration.validators.CustomExpressionValidator;

@Service
@Transactional
public class CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    private CredentialValidator credentialValidator =
            new CredentialValidator(new CustomExpressionValidator());

    public Credential getOne(int id) {
        Credential credential = null;
        Optional<Credential> optional = this.credentialRepository.findByIdAndActiveIsTrue(id);
        if (optional.isPresent()) {
            credential = optional.get();
        } else {
            throw new ErrorResponse("there is no credential with id: " + id, HttpStatus.NOT_FOUND);
        }
        return credential;
    }

    public List<Credential> getAll() {
        return this.credentialRepository.findAllByActiveIsTrue();
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
