package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.UserValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.User;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class UserValidator extends Validator<User> {

	public UserValidator(ExpressionValidator expressionValidator,
			ValidationMessenger entityValidationMessages) {
		super(expressionValidator, entityValidationMessages);
	}

	@Override
	public void validateInsert(User entity) {
		this.validateUpdate(entity);

		if (!this.getExpressionValidator().isPasswordValid(entity.getPassword()))
			this.sendError(((UserValidationMessenger) this.getEntityValidationMessenger())
					.invalidPassword());
	}

	@Override
	public void validateUpdate(User entity) {
		/**
		 * check nullability
		 */
		if (entity.getId() != null)
			this.sendError(this.getEntityValidationMessenger().idNotRequired());

		if (!(this.getExpressionValidator().isPresent(entity.getUserName())
				|| Constraints.User.NAME_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(User.class.getSimpleName(), "name"));


		if (!(this.getExpressionValidator().isPresent(entity.getUserLastName())
				|| Constraints.User.LAST_NAME_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(User.class.getSimpleName(), "last name"));

		if (!(this.getExpressionValidator().isPresent(entity.getDni())
				|| Constraints.User.DNI_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(User.class.getSimpleName(), "dni"));

		if (!(this.getExpressionValidator().isPresent(entity.getEmail())
				|| Constraints.User.EMAIL_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(User.class.getSimpleName(), "email"));

		if (!(this.getExpressionValidator().isPresent(entity.getAccount())
				|| Constraints.User.ACCOUNT_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(User.class.getSimpleName(), "account"));

		if (!(this.getExpressionValidator().isPresent(entity.getPassword())
				|| Constraints.User.PASSWORD_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(User.class.getSimpleName(), "password"));

		/**
		 * check size
		 */
		if (!(Constraints.User.NAME_MIN_LENGHT <= entity.getUserName().length()
				&& entity.getUserName().length() <= Constraints.User.NAME_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					User.class.getSimpleName(), "name", Constraints.User.NAME_MIN_LENGHT,
					Constraints.User.NAME_MAX_LENGHT));

		if (!(Constraints.User.LAST_NAME_MIN_LENGHT <= entity.getUserLastName().length()
				&& entity.getUserLastName().length() <= Constraints.User.LAST_NAME_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					User.class.getSimpleName(), "last name", Constraints.User.LAST_NAME_MIN_LENGHT,
					Constraints.User.LAST_NAME_MIN_LENGHT));

		if (!(Constraints.User.DNI_MIN_LENGHT <= entity.getDni().length()
				&& entity.getDni().length() <= Constraints.User.DNI_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					User.class.getSimpleName(), "dni", Constraints.User.DNI_MIN_LENGHT,
					Constraints.User.DNI_MAX_LENGHT));

		if (!(Constraints.User.EMAIL_MIN_LENGHT <= entity.getEmail().length()
				&& entity.getEmail().length() <= Constraints.User.EMAIL_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					User.class.getSimpleName(), "email", Constraints.User.EMAIL_MIN_LENGHT,
					Constraints.User.EMAIL_MAX_LENGHT));

		if (!(Constraints.User.ACCOUNT_MIN_LENGHT <= entity.getAccount().length()
				&& entity.getAccount().length() <= Constraints.User.ACCOUNT_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					User.class.getSimpleName(), "account", Constraints.User.ACCOUNT_MIN_LENGHT,
					Constraints.User.ACCOUNT_MAX_LENGHT));

		if (!(Constraints.User.PASSWORD_MIN_LENGHT <= entity.getPassword().length()
				&& entity.getPassword().length() <= Constraints.User.PASSWORD_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					User.class.getSimpleName(), "password", Constraints.User.PASSWORD_MIN_LENGHT,
					Constraints.User.PASSWORD_MAX_LENGHT));

		/**
		 * check pattern
		 */
		if (!this.getExpressionValidator().composedName(entity.getUserName()))
			this.sendError(((UserValidationMessenger) this.getEntityValidationMessenger())
					.invalidNamePattern());

		if (!this.getExpressionValidator().composedName(entity.getUserLastName()))
			this.sendError(((UserValidationMessenger) this.getEntityValidationMessenger())
					.invalidLastnamePattern());


		if (!this.getExpressionValidator().onlyNumbers(entity.getDni()))
			this.sendError(this.getEntityValidationMessenger()
					.onlyNumberAllowed(User.class.getSimpleName(), "dni"));

		if (!this.getExpressionValidator().isEmail(entity.getEmail()))
			this.sendError(this.getEntityValidationMessenger()
					.notAnEmail(User.class.getSimpleName(), "email"));

		if (!this.getExpressionValidator().isAccountValid(entity.getAccount()))
			this.sendError(((UserValidationMessenger) this.getEntityValidationMessenger())
					.invalidAccount());
	}
}
