package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.WorkAttendance;

public class WorkAttendanceValidator extends Validator<WorkAttendance> {

    public WorkAttendanceValidator(ExpressionValidator expressionValidator,
            ValidationMessenger entityValidationMessenger) {
        super(expressionValidator, entityValidationMessenger);
    }

    @Override
    public void validateInsert(WorkAttendance entity) {
        /**
         * check nullability
         */

        // if (entity.getId() != null)
        // this.sendError(this.getEntityValidationMessenger().idNotRequired());

        // if (!(this.getExpressionValidator().isPresent(entity.getAccessName())
        // || Constraints.Access.NAME_NULLABLE))
        // this.sendError(this.getEntityValidationMessenger()
        // .attributeRequired(Access.class.getSimpleName(), "name"));

        // if (!(this.getExpressionValidator().isPresent(entity.getDescription())
        // || Constraints.Access.DESCRIPTION_NULLABLE))
        // this.sendError(this.getEntityValidationMessenger()
        // .attributeRequired(Access.class.getSimpleName(), "description"));

        // /**
        // * check size
        // */

        // if (!(Constraints.Access.NAME_MIN_LENGHT < entity.getAccessName().length()
        // && entity.getAccessName().length() < Constraints.Access.NAME_MAX_LENGHT))
        // this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
        // Access.class.getSimpleName(), "name", Constraints.Access.NAME_MIN_LENGHT,
        // Constraints.Access.NAME_MAX_LENGHT));

        /**
         * check pattern
         */
    }

    @Override
    public void validateUpdate(WorkAttendance entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateUpdate'");
    }
}
