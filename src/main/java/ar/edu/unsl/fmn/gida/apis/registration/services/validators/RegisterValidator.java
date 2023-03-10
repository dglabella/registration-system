package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.Register;

public class RegisterValidator extends Validator<Register> {

	public RegisterValidator(ExpressionValidator expressionValidator,
			ValidationMessenger entityValidationMessages) {
		super(expressionValidator, entityValidationMessages);
	}

	@Override
	public void validateInsert(Register entity) {
		/**
		 * check nullability
		 */
		if (entity.getId() != null)
			this.sendError(this.getEntityValidationMessenger().idNotRequired());

		if (entity.getAccessId() == null)
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Register.class.getSimpleName(), "access id"));

		if (entity.getEncryptedData() == null)
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Register.class.getSimpleName(), "encrypted data"));

		/**
		 * check size
		 */


		/**
		 * check pattern
		 */
	}

	@Override
	public void validateUpdate(Register entity) {
		this.validateInsert(entity);
	}
}
