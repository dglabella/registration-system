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

		if (!(entity.getPersonId() != null || Constraints.Credential.PERSON_FK_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Credential.class.getSimpleName(), "person id"));

		if (!(entity.getData() != null || Constraints.Credential.DATA_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Credential.class.getSimpleName(), "data"));

		if (!(entity.getImg() != null || Constraints.Credential.IMG_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Credential.class.getSimpleName(), "img"));

		/**
		 * check size
		 */
		if (!(Constraints.Credential.DATA_MIN_LENGHT < entity.getData().length()
				&& entity.getData().length() < Constraints.Credential.DATA_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					Credential.class.getSimpleName(), "data",
					Constraints.Credential.DATA_MIN_LENGHT,
					Constraints.Credential.DATA_MAX_LENGHT));

		/**
		 * check pattern
		 */
	}

	@Override
	public void validateUpdate(Credential entity) {
		this.validateInsert(entity);
	}
}
