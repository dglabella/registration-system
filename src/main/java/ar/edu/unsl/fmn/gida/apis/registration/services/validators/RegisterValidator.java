package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;

public class RegisterValidator extends Validator<Register> {

	public RegisterValidator(ExpressionValidator expressionValidator,
			ValidationMessenger entityValidationMessages) {
		super(expressionValidator, entityValidationMessages);
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
