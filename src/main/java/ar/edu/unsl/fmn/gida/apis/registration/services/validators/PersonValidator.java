package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.EntityValidationMessages;

public class PersonValidator extends Validator<Person> {

    public PersonValidator(ExpressionValidator expressionValidator,
            EntityValidationMessages entityValidationMessages) {
        super(expressionValidator, entityValidationMessages);
    }

    @Override
    public void validate(Person entity) {
        /**
         * check nullability
         */
        if (!(entity.getDependencyFk() != null || Constraints.Person.DEPENDENCY_FK_NULLABLE))
            this.sendError("person dependency id is required");

        if (!(this.getExpressionValidator().isPresent(entity.getPersonName())
                || Constraints.Person.NAME_NULLABLE))
            this.sendError("person name is required");

        if (!(this.getExpressionValidator().isPresent(entity.getPersonLastName())
                || Constraints.Person.LAST_NAME_NULLABLE))
            this.sendError("person last name is required");

        if (!(this.getExpressionValidator().isPresent(entity.getDni())
                || Constraints.Person.DNI_NULLABLE))
            this.sendError("person dni is required");

        if (entity.getRoles() == null || entity.getRoles().size() == 0)
            this.sendError("person requires at least one role");

        /**
         * check size
         */
        if (!(Constraints.Person.NAME_MIN_LENGHT < entity.getPersonName().length()
                && entity.getPersonName().length() < Constraints.Person.NAME_MAX_LENGHT))
            this.sendError("invalid person name: must contain between "
                    + Constraints.Person.NAME_MIN_LENGHT + " and "
                    + Constraints.Person.NAME_MAX_LENGHT + " characters");

        if (!(Constraints.Person.LAST_NAME_MIN_LENGHT < entity.getPersonLastName().length()
                && entity.getPersonLastName().length() < Constraints.Person.LAST_NAME_MAX_LENGHT))
            this.sendError("invalid person last name: must contain between "
                    + Constraints.Person.LAST_NAME_MIN_LENGHT + " and "
                    + Constraints.Person.LAST_NAME_MAX_LENGHT + " characters");

        if (!(Constraints.Person.DNI_MIN_LENGHT < entity.getDni().length()
                && entity.getDni().length() < Constraints.Person.DNI_MAX_LENGHT))
            this.sendError(
                    "invalid person dni: must contain between " + Constraints.Person.DNI_MIN_LENGHT
                            + " and " + Constraints.Person.DNI_MAX_LENGHT + " characters");

        /**
         * check pattern
         */
        if (!this.getExpressionValidator().composedName(entity.getPersonName()))
            this.sendError("invalid person name: must have only valids characters");

        if (!this.getExpressionValidator().composedName(entity.getPersonLastName()))
            this.sendError("invalid person last name: must have only valids characters");

        if (!this.getExpressionValidator().onlyNumbers(entity.getDni()))
            this.sendError("invalid person dni: only numbers allowed");
    }
}
