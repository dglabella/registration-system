package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;
import ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation.EntityValidationMessages;

public class CredentialValidator extends Validator<Credential> {

    public CredentialValidator(ExpressionValidator expressionValidator,
            EntityValidationMessages entityValidationMessages) {
        super(expressionValidator, entityValidationMessages);
    }

    @Override
    public void validate(Credential entity) {
        /**
         * check nullability
         */

        if (!(entity.getPersonFk() != null || Constraints.Credential.PERSON_FK_NULLABLE))
            this.sendError("credential person id is required");

        /**
         * check size
         */

        /**
         * check pattern
         */
    }
}
