package ar.edu.unsl.fmn.gida.apis.registration.validators;

import org.springframework.http.HttpStatus;
import ar.edu.unsl.fmn.gida.apis.registration.enums.Role;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class PersonValidator implements Validator<Person> {

    private boolean fieldsValidated = false;

    private CustomExpressionValidator expresionValidator;

    private WeeklyValidator weeklyValidator;

    public PersonValidator(CustomExpressionValidator expresionValidator) {
        this.expresionValidator = expresionValidator;
        this.weeklyValidator = new WeeklyValidator(expresionValidator);
    }

    private void validateClassFields(Person person) {
        /**
         * check nullability
         */
        if (person.getDependencyFk() == null)
            throw new ErrorResponse("person dependency id is required", HttpStatus.BAD_REQUEST);

        if (!(this.expresionValidator.isPresent(person.getPersonName())
                || Constraints.Person.NAME_NULLABLE))
            throw new ErrorResponse("person name is required", HttpStatus.BAD_REQUEST);

        if (!(this.expresionValidator.isPresent(person.getPersonLastName())
                || Constraints.Person.LAST_NAME_NULLABLE))
            throw new ErrorResponse("person last name is required", HttpStatus.BAD_REQUEST);


        if (!(this.expresionValidator.isPresent(person.getDni())
                || Constraints.Person.DNI_NULLABLE))
            throw new ErrorResponse("person dni is required", HttpStatus.BAD_REQUEST);

        if (person.getCurrentWeekly() == null)
            throw new ErrorResponse("person current weekly is required", HttpStatus.BAD_REQUEST);

        if (person.getRoles().size() == 0)
            throw new ErrorResponse("person requires at least one role", HttpStatus.BAD_REQUEST);

        /**
         * check size
         */
        if (!(Constraints.Person.NAME_MIN_LENGHT < person.getPersonName().length()
                && person.getPersonName().length() < Constraints.Person.NAME_MAX_LENGHT))
            throw new ErrorResponse(
                    "invalid person name: must contain between "
                            + Constraints.Person.NAME_MIN_LENGHT + " and "
                            + Constraints.Person.NAME_MAX_LENGHT + " characters",
                    HttpStatus.BAD_REQUEST);

        if (!(Constraints.Person.LAST_NAME_MIN_LENGHT < person.getPersonLastName().length()
                && person.getPersonLastName().length() < Constraints.Person.LAST_NAME_MAX_LENGHT))
            throw new ErrorResponse(
                    "invalid person last name: must contain between "
                            + Constraints.Person.LAST_NAME_MIN_LENGHT + " and "
                            + Constraints.Person.LAST_NAME_MAX_LENGHT + " characters",
                    HttpStatus.BAD_REQUEST);

        if (!(Constraints.Person.DNI_MIN_LENGHT < person.getDni().length()
                && person.getDni().length() < Constraints.Person.DNI_MAX_LENGHT))
            throw new ErrorResponse(
                    "invalid person dni: must contain between " + Constraints.Person.DNI_MIN_LENGHT
                            + " and " + Constraints.Person.DNI_MAX_LENGHT + " characters",
                    HttpStatus.BAD_REQUEST);

        /**
         * check pattern
         */
        if (!this.expresionValidator.composedName(person.getPersonName()))
            throw new ErrorResponse("invalid person name: must have only valids chararcters",
                    HttpStatus.BAD_REQUEST);

        if (!this.expresionValidator.composedName(person.getPersonLastName()))
            throw new ErrorResponse("invalid person last name: must have only valids chararcters",
                    HttpStatus.BAD_REQUEST);

        if (!this.expresionValidator.onlyNumbers(person.getDni()))
            throw new ErrorResponse("invalid person dni: only numbers allowed",
                    HttpStatus.BAD_REQUEST);

        for (Role role : person.getRoles()) {
            if (!(0 <= role.ordinal() && role.ordinal() <= Constraints.Person.ROLE_LAST_ORDINAL))
                throw new ErrorResponse("invalid person role: there is no role defined for ordinal "
                        + role.ordinal(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validateAssociations(Person person) {
        this.weeklyValidator.validate(person.getCurrentWeekly());
    }

    @Override
    public void validate(Person entity) {
        if (!this.fieldsValidated) {
            this.validateClassFields(entity);
            this.fieldsValidated = true;
            this.validateAssociations(entity);
        }
    }
}
