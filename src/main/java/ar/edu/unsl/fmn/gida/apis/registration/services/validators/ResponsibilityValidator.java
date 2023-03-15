package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.Responsibility;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class ResponsibilityValidator extends Validator<Responsibility> {

    public ResponsibilityValidator(ExpressionValidator expressionValidator,
            ValidationMessenger entityValidationMessenger) {
        super(expressionValidator, entityValidationMessenger);
    }

    @Override
    public void validateInsert(Responsibility entity) {
        /**
         * check nullability
         */
        if (entity.getId() != null)
            this.sendError(this.getEntityValidationMessenger().idNotRequired());

        if (!(entity.getWeeklyId() != null) || Constraints.Responsibility.WEEKLY_ID_NULLABLE)
            this.sendError(this.getEntityValidationMessenger()
                    .attributeRequired(Responsibility.class.getSimpleName(), "weekly id"));

        if (!(entity.getDay() != null) || Constraints.Responsibility.DAY_NULLABLE)
            this.sendError(this.getEntityValidationMessenger()
                    .attributeRequired(Responsibility.class.getSimpleName(), "day"));

        if (!(entity.getEntranceTime() != null)
                || Constraints.Responsibility.ENTRANCE_TIME_NULLABLE)
            this.sendError(this.getEntityValidationMessenger()
                    .attributeRequired(Responsibility.class.getSimpleName(), "entrance time"));

        if (!(entity.getDepartureTime() != null)
                || Constraints.Responsibility.DEPARTURE_TIME_NULLABLE)
            this.sendError(this.getEntityValidationMessenger()
                    .attributeRequired(Responsibility.class.getSimpleName(), "departure time"));

        /**
         * check size
         */


        /**
         * check pattern
         */
    }

    @Override
    public void validateUpdate(Responsibility entity) {
        this.validateInsert(entity);
    }
}
