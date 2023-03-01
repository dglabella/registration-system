package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.Credential;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class CredentialValidator extends Validator<Credential> {

	public CredentialValidator(ExpressionValidator expressionValidator,
			ValidationMessenger entityValidationMessages) {
		super(expressionValidator, entityValidationMessages);
	}

	@Override
	public void validateInsert(Credential entity) {
		/**
		 * check nullability
		 */
		if (entity.getId() != null)
			this.sendError(this.getEntityValidationMessenger().idNotRequired());

		if (!(entity.getPersonFk() != null || Constraints.Credential.PERSON_FK_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Credential.class.getSimpleName(), "person id"));

		/**
		 * check size
		 */

		/**
		 * check pattern
		 */
	}

	@Override
	public void validateUpdate(Credential entity) {
		this.validateInsert(entity);
	}
}
