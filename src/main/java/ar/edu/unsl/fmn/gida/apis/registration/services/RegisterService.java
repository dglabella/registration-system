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
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;
import ar.edu.unsl.fmn.gida.apis.registration.repositories.RegisterRepository;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.CustomCypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.Cypher;
import ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters.PersonConverter;
import ar.edu.unsl.fmn.gida.apis.registration.validators.CustomExpressionValidator;
import ar.edu.unsl.fmn.gida.apis.registration.validators.RegisterValidator;

@Service
@Transactional
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    private Cypher cypher = new CustomCypher();
    private PersonConverter personConverter = new PersonConverter();

    public Register getOne(int id) {
        Register r = null;
        Optional<Register> optional = registerRepository.findById(id);

        if (optional.isPresent()) {
            r = optional.get();
        } else {
            throw new ErrorResponse("there is no register with id: " + id, HttpStatus.NOT_FOUND);
        }

        return r;
    }

    public List<Register> getAll(String from, String to) {
        return registerRepository.findAll();
    }

    public List<Register> getRegistersFromPerson(int id) {
        List<Register> r = registerRepository.findAllByPersonIdAndActiveTrue(id);

        return r;
    }

    public Register insert(Register register) {
        new RegisterValidator(new CustomExpressionValidator()).validate(register);
        Person person = null;
        Register r1 = new Register();
        Register r2 = null;
        Optional<Register> optional;

        try {
            person = this.personConverter
                    .objectify(this.cypher.decrypt(register.getEncryptedData()));

            optional = this.registerRepository
                    .findByPersonFkAndCheckOutIsNullAndActiveIsTrue(person.getId());

            if (optional.isPresent()) {
                r1.setId(optional.get().getId());
                r1.setPersonFk(optional.get().getPersonFk());
                r1.setAccessFk(optional.get().getAccessFk());
                r1.setCheckIn(optional.get().getCheckIn());
                r1.setCheckOut(new Date());
                r1 = registerRepository.save(r1);

                if (register.getAccessFk() != optional.get().getAccessFk()) {
                    r2 = new Register();
                    r2.setPersonFk(person.getId());
                    r2.setAccessFk(register.getAccessFk());
                    r2.setCheckIn(new Date());
                    r2 = registerRepository.save(r2);
                }
            } else {
                r1.setPersonFk(person.getId());
                r1.setAccessFk(register.getAccessFk());
                r1.setCheckIn(new Date());
                r1 = registerRepository.save(r1);
            }
        } catch (DataIntegrityViolationException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(exception.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return r2 == null ? r1 : r2;
    }

    public Register update(int id, Register register) {
        throw new ErrorResponse("cannot update a register, illegal operation ",
                HttpStatus.NOT_IMPLEMENTED);
    }

    public Register delete(int id) {
        return null;
    }
}
