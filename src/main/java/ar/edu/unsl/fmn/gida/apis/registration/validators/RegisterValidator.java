package ar.edu.unsl.fmn.gida.apis.registration.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Register;

public class RegisterValidator extends Validator<Register> {

    private PersonValidator personValidator;
    private AccessValidator accessValidator;

    public RegisterValidator(ExpressionValidator expressionValidator) {
        super(expressionValidator);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void fieldsValidation(Register entity) {
        /**
         * check nullability
         */
        if (entity.getAccessFk() == null)
            this.sendError("register access id is required");

        if (entity.getEncryptedData() == null)
            this.sendError("register encrypted data is required");
    }

    @Override
    public void associationValidation(Register entity) {}

    @Override
    public void close() {
        this.closeFieldsValidation();
        this.personValidator.closeFieldsValidation();
        this.accessValidator.closeFieldsValidation();
    }
}
