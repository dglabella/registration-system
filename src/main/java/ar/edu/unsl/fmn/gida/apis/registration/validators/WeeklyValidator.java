package ar.edu.unsl.fmn.gida.apis.registration.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;


public class WeeklyValidator extends Validator<Weekly> {

    public WeeklyValidator(ExpressionValidator expresionValidator) {
        super(expresionValidator);
    }

    @Override
    public void validate(Weekly entity) {
        /**
         * check nullability
         */
        if (!(entity.getPersonFk() != null || Constraints.Weekly.PERSONFK_NULLABLE))
            this.sendError("weekly person id is required");

        /**
         * check size
         */

        /**
         * check pattern
         */
    }
}
