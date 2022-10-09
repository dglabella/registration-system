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
        if (entity.getId() == null)
            this.sendError("weekly id must not be null");

        if (entity.getPersonFk() == null)/// PREGUNTAR YA QUE PERSONFK ES PARTE DE UNA ASOCIACION
            this.sendError("weekly personfk must not be null");

        if (!(this.getExpressionValidator()
                .isPresent(String.format("%1$tY-%1$tm-%1$td", entity.getStart()))
                || Constraints.Weekly.START_NULLABLE))
            this.sendError("weekly start date is required");
    }

    @Override
    public void associationValidation(Weekly entity) {
        this.personValidator.validate(entity.getPerson());
    }
}
