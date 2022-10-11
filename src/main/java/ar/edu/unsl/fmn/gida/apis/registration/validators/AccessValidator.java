package ar.edu.unsl.fmn.gida.apis.registration.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class AccessValidator extends Validator<Access> {

    public AccessValidator(ExpressionValidator expresionValidator) {
        super(expresionValidator);
    }

    @Override
    public void fieldsValidation(Access entity) {
        /**
         * check nullability
         */
        if (entity.getId() == null)
            this.sendError("access id must not be null");

        if (!(this.getExpressionValidator().isPresent(entity.getAccesseName())
                || Constraints.Access.ACCESSNAME_NULLABLE))
            this.sendError("access name is required");

        /**
         * check size
         */

        if (!(Constraints.Access.ACCESSNAME_MIN_LENGHT < entity.getAccesseName().length()
                && entity.getAccesseName().length() < Constraints.Access.ACCESSNAME_MAX_LENGHT))
            this.sendError("invalid access name: must contain between "
                    + Constraints.Access.ACCESSNAME_MIN_LENGHT + " and "
                    + Constraints.Access.ACCESSNAME_MAX_LENGHT + " characters");

        if (!(entity.getAccesseName().length() < Constraints.Access.DESCRIPTION_MAX_LENGHT))
            this.sendError("invalid access description: must contain "
                    + Constraints.Access.DESCRIPTION_MAX_LENGHT + " characters");
    }

    @Override
    public void associationValidation(Access entity) {}

    @Override
    public void close() {
        this.closeFieldsValidation();
    }
}
