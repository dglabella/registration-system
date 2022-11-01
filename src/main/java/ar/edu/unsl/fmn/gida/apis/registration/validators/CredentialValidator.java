package ar.edu.unsl.fmn.gida.apis.registration.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class CredentialValidator extends Validator<Credential> {

    public CredentialValidator(ExpressionValidator expressionValidator) {
        super(expressionValidator);
    }

    @Override
    public void fieldsValidation(Credential entity) {
        // TODO Auto-generated method stub
        /**
         * check nullability
         */

        if (!(entity.getPersonFk() != null || Constraints.Credential.PERSON_FK_NULLABLE))
            this.sendError("credential person id is required");

    }

    @Override
    public void associationValidation(Credential entity) {
        /**
         * there is no check needed.
         */
    }

    @Override
    public void close() {
        this.closeFieldsValidation();
    }
}
