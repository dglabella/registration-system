package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.EntityValidationMessages;

public class AccessValidator extends Validator<Access> {

    public AccessValidator(ExpressionValidator expressionValidator,
            EntityValidationMessages entityValidationMessages) {
        super(expressionValidator, entityValidationMessages);
    }

    @Override
    public void validate(Access entity) {
        /**
         * check nullability
         */
        if (entity.getId() != null)
            this.sendError(this.getEntityValidationMessages().idNotRequired());

        if (!(this.getExpressionValidator().isPresent(entity.getAccessName())
                || Constraints.Access.NAME_NULLABLE))
            this.sendError(this.getEntityValidationMessages()
                    .attributeRequired(Access.class.getSimpleName(), "name"));

        if (!(this.getExpressionValidator().isPresent(entity.getDescription())
                || Constraints.Access.DESCRIPTION_NULLABLE))
            this.sendError(this.getEntityValidationMessages()
                    .attributeRequired(Access.class.getSimpleName(), "description"));

        /**
         * check size
         */
        if (!(Constraints.Access.NAME_MIN_LENGHT < entity.getAccessName().length()
                && entity.getAccessName().length() < Constraints.Access.NAME_MAX_LENGHT))
            this.sendError(this.getEntityValidationMessages().invalidAttributeSize(
                    Access.class.getSimpleName(), "name", Constraints.Access.NAME_MIN_LENGHT,
                    Constraints.Access.NAME_MAX_LENGHT));
    }
}
