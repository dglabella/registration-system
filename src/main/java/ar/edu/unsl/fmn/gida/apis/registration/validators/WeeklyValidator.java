package ar.edu.unsl.fmn.gida.apis.registration.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;

public class WeeklyValidator implements Validator<Weekly> {

    private CustomExpressionValidator expresionValidator;

    public WeeklyValidator(CustomExpressionValidator expresionValidator) {
        this.expresionValidator = expresionValidator;
    }


    @Override
    public void validate(Weekly entity) {}
}
