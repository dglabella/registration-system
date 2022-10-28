package ar.edu.unsl.fmn.gida.apis.registration.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class WeeklyValidator extends Validator<Weekly> {

    private PersonValidator personValidator;

    public WeeklyValidator(ExpressionValidator expresionValidator,
            PersonValidator personValidator) {
        super(expresionValidator);
        this.personValidator = personValidator;
    }

    @Override
    public void fieldsValidation(Weekly entity) {
        /**
         * there is no check needed.
         */
    }

    @Override
    public void associationValidation(Weekly entity) {
        /**
         * there is no check needed.
         */
    }

    @Override
    public void close() {
        this.closeFieldsValidation();
        this.personValidator.closeFieldsValidation();
    }
}
