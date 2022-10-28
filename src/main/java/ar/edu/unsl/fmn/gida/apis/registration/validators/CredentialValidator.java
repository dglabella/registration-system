package ar.edu.unsl.fmn.gida.apis.registration.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;

public class CredentialValidator extends Validator<Credential> {

    public CredentialValidator(ExpressionValidator expressionValidator) {
        super(expressionValidator);
    }

    @Override
    public void fieldsValidation(Credential entity) {
        // TODO Auto-generated method stub

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
