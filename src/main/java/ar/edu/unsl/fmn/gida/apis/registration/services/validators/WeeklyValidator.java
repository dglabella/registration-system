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
	public void validate(Weekly entity) {
		/**
		 * check nullability
		 */
		if (!(entity.getPersonFk() != null || Constraints.Weekly.PERSONFK_NULLABLE))
			this.sendError("weekly person id is required");

		/**
		 * check size
		 */

		/**
		 * check pattern
		 */
	}
}
