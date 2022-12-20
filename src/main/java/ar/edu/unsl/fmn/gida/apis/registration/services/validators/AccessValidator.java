package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class AccessValidator extends Validator<Access> {

    public AccessValidator(ExpressionValidator expresionValidator) {
        super(expresionValidator);
    }

    @Override
    public void validate(Access entity) {
        /**
         * check nullability
         */

        if (!(this.getExpressionValidator().isPresent(entity.getAccessName())
                || Constraints.Access.NAME_NULLABLE))
            this.sendError("access name is required");

        if (!(this.getExpressionValidator().isPresent(entity.getDescription())
                || Constraints.Access.DESCRIPTION_NULLABLE))
            this.sendError("access description is required");

        /**
         * check size
         */
        if (!(Constraints.Access.NAME_MIN_LENGHT < entity.getAccessName().length()
                && entity.getAccessName().length() < Constraints.Access.NAME_MAX_LENGHT))
            this.sendError("invalid access name: must contain between "
                    + Constraints.Access.NAME_MIN_LENGHT + " and "
                    + Constraints.Access.NAME_MAX_LENGHT + " characters");
    }
}
