package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.auxiliaries.Check;

public class CheckValidator extends Validator<Check> {

	public CheckValidator(ExpressionValidator expressionValidator,
			ValidationMessenger entityValidationMessages) {
		super(expressionValidator, entityValidationMessages);
	}

	@Override
	public void validateInsert(Check entity) {
		/**
		 * check nullability
		 */
		if (entity.getAccessId() == null)
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Check.class.getSimpleName(), "access id"));

		if (entity.getEncryptedData() == null)
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Check.class.getSimpleName(), "encrypted data"));

		/**
		 * check size
		 */


		/**
		 * check pattern
		 */
	}

	@Override
	public void validateUpdate(Check entity) {
		this.validateInsert(entity);
	}
}
