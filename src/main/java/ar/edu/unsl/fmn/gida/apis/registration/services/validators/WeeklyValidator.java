package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;


public class WeeklyValidator extends Validator<Weekly> {

	public WeeklyValidator(ExpressionValidator expressionValidator,
			ValidationMessenger entityValidationMessages) {
		super(expressionValidator, entityValidationMessages);
	}

	@Override
	public void validateInsert(Weekly entity) {
		/**
		 * check nullability
		 */
		if (entity.getId() != null)
			this.sendError(this.getEntityValidationMessenger().idNotRequired());

		if (!(entity.getPersonFk() != null || Constraints.Weekly.PERSONFK_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Weekly.class.getSimpleName(), "person id"));

		/**
		 * check size
		 */

		/**
		 * check pattern
		 */
	}

	@Override
	public void validateUpdate(Weekly entity) {
		this.validateInsert(entity);
	}
}
