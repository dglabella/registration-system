package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.PersonValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class PersonValidator extends Validator<Person> {

	public PersonValidator(ExpressionValidator expressionValidator,
			ValidationMessenger entityValidationMessages) {
		super(expressionValidator, entityValidationMessages);
	}

	@Override
	public void validateInsert(Person entity) {
		/**
		 * check nullability
		 */
		if (entity.getId() != null)
			this.sendError(this.getEntityValidationMessenger().idNotRequired());

		if (!(entity.getDependencyId() != null || Constraints.Person.DEPENDENCY_FK_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Person.class.getSimpleName(), "dependency id"));

		if (!(this.getExpressionValidator().isPresent(entity.getPersonName())
				|| Constraints.Person.NAME_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Person.class.getSimpleName(), "name"));

		if (!(this.getExpressionValidator().isPresent(entity.getPersonLastName())
				|| Constraints.Person.LAST_NAME_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Person.class.getSimpleName(), "last name"));

		if (!(this.getExpressionValidator().isPresent(entity.getDni())
				|| Constraints.Person.DNI_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Person.class.getSimpleName(), "dni"));

		if (entity.getRoles() == null || entity.getRoles().size() == 0)
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Person.class.getSimpleName(), "role"));

		/**
		 * check size
		 */
		if (!(Constraints.Person.NAME_MIN_LENGHT < entity.getPersonName().length()
				&& entity.getPersonName().length() < Constraints.Person.NAME_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					Person.class.getSimpleName(), "name", Constraints.Person.NAME_MIN_LENGHT,
					Constraints.Person.NAME_MAX_LENGHT));

		if (!(Constraints.Person.LAST_NAME_MIN_LENGHT < entity.getPersonLastName().length()
				&& entity.getPersonLastName().length() < Constraints.Person.LAST_NAME_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					Person.class.getSimpleName(), "last name",
					Constraints.Person.LAST_NAME_MIN_LENGHT,
					Constraints.Person.LAST_NAME_MAX_LENGHT));

		if (!(Constraints.Person.DNI_MIN_LENGHT < entity.getDni().length()
				&& entity.getDni().length() < Constraints.Person.DNI_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					Person.class.getSimpleName(), "dni", Constraints.Person.DNI_MIN_LENGHT,
					Constraints.Person.DNI_MAX_LENGHT));

		/**
		 * check pattern
		 */
		if (!this.getExpressionValidator().composedName(entity.getPersonName()))
			this.sendError(((PersonValidationMessenger) this.getEntityValidationMessenger())
					.invalidNamePattern());

		if (!this.getExpressionValidator().composedName(entity.getPersonLastName()))
			this.sendError(((PersonValidationMessenger) this.getEntityValidationMessenger())
					.invalidLastnamePattern());

		if (!this.getExpressionValidator().onlyNumbers(entity.getDni()))
			this.sendError(this.getEntityValidationMessenger()
					.onlyNumberAllowed(Person.class.getSimpleName(), "dni"));
	}

	@Override
	public void validateUpdate(Person entity) {
		this.validateInsert(entity);
	}
}
