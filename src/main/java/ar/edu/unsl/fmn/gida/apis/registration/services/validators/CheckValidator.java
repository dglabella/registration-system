package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.auxiliaries.Check;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

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
		if (!(entity.getAccessId() != null) || Constraints.Check.ACCESS_ID_NULLABLE)
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Check.class.getSimpleName(), "access id"));

		if (!(entity.getEncryptedData() != null) || Constraints.Check.ENCRYPTED_DATA_NULLABLE)
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
