package ar.edu.unsl.fmn.gida.apis.registration.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Register;

public class RegisterValidator extends Validator<Register> {

    public RegisterValidator(ExpressionValidator expressionValidator) {
        super(expressionValidator);
    }

    @Override
    public void validate(Register entity) {
        /**
         * check nullability
         */
        if (entity.getAccessFk() == null)
            this.sendError("register access id is required");

        if (entity.getEncryptedData() == null)
            this.sendError("register encrypted data is required");
    }
}
