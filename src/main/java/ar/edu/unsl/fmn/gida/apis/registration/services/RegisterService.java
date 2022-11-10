package ar.edu.unsl.fmn.gida.apis.registration.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private RegisterValidator registerValidator =
            new RegisterValidator(new CustomExpressionValidator());

    private Cypher cypher = new CustomCypher();
    private PersonConverter personConverter = new PersonConverter();

    private SimpleDateFormat dateFormatter =
            new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.ENGLISH);

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

    public Page<Register> getAll(String from, String to, int page, int quantityPerPage) {
        Date fromDate = null;
        Date toDate = null;

        try {
            fromDate = from != null ? this.dateFormatter.parse(from) : new Date(Long.MIN_VALUE);
            toDate = to != null ? this.dateFormatter.parse(to) : new Date();

            if (fromDate.compareTo(toDate) > 0) {
                throw new ErrorResponse(" \"from\" date cannot have a later date than \"to\" date",
                        HttpStatus.BAD_REQUEST);
            }

        } catch (ParseException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(
                    "date format is wrong, make sure that date format follows the right specification",
                    HttpStatus.BAD_REQUEST);
        }
        return this.registerRepository.findAllByCheckInBetweenAndActiveTrue(fromDate, toDate,
                PageRequest.of(page, quantityPerPage));
    }

    public Page<Register> getAllFromPerson(Integer personId, String from, String to, int page,
            int quantityPerPage) {
        Date fromDate = null;
        Date toDate = null;

        try {
            fromDate = from != null ? this.dateFormatter.parse(from) : new Date(Long.MIN_VALUE);
            toDate = to != null ? this.dateFormatter.parse(to) : new Date();

            if (fromDate.compareTo(toDate) > 0) {
                throw new ErrorResponse(" \"from\" date cannot have a later date than \"to\" date",
                        HttpStatus.BAD_REQUEST);
            }

        } catch (ParseException exception) {
            exception.printStackTrace();
            throw new ErrorResponse(
                    "date format is wrong, make sure that date format follows the right specification",
                    HttpStatus.BAD_REQUEST);
        }
        return this.registerRepository.findAllByPersonFkAndActiveTrueAndCheckInBetween(personId,
                fromDate, toDate, PageRequest.of(page, quantityPerPage));
    }

    public Register insert(Register register) {
        this.registerValidator.validate(register);
        Person person = null;
        Register r1 = new Register();
        Register r2 = null;
        Optional<Register> optional;

        try {
            person = this.personConverter
                    .objectify(this.cypher.decrypt(register.getEncryptedData()));

            optional = this.registerRepository
                    .findByPersonFkAndCheckOutIsNullAndActiveTrue(person.getId());

            if (optional.isPresent()) {
                r1.setId(optional.get().getId());
                r1.setPersonFk(optional.get().getPersonFk());
                r1.setAccessFk(optional.get().getAccessFk());
                r1.setCheckIn(optional.get().getCheckIn());
                r1.setCheckOut(new Date());
                r1 = this.registerRepository.save(r1);

                if (register.getAccessFk() != optional.get().getAccessFk()) {
                    r2 = new Register();
                    r2.setPersonFk(person.getId());
                    r2.setAccessFk(register.getAccessFk());
                    r2.setCheckIn(new Date());
                    r2 = this.registerRepository.save(r2);
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
