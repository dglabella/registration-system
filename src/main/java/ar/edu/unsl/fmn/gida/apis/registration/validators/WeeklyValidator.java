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
         * check nullability
         */
        if (entity.getStart() == null && !Constraints.Weekly.START_NULLABLE)
            this.sendError("weekly start date is required");
    }

    @Override
    public void associationValidation(Weekly entity) {
        this.personValidator.validate(entity.getPerson());
    }

    @Override
    public void close() {
        this.closeFieldsValidation();
        this.personValidator.closeFieldsValidation();
    }
}
