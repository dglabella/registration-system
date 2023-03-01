package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;
import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class DependencyValidator extends Validator<Dependency> {

	public DependencyValidator(ExpressionValidator expressionValidator,
			ValidationMessenger entityValidationMessages) {
		super(expressionValidator, entityValidationMessages);
	}

	@Override
	public void validateInsert(Dependency entity) {
		/**
		 * check nullability
		 */
		if (entity.getId() != null)
			this.sendError(this.getEntityValidationMessenger().idNotRequired());

		if (!(this.getExpressionValidator().isPresent(entity.getDependencyName())
				|| Constraints.Dependency.NAME_NULLABLE))
			this.sendError(this.getEntityValidationMessenger()
					.attributeRequired(Dependency.class.getSimpleName(), "name"));

		/**
		 * check size
		 */
		if (!(Constraints.Dependency.NAME_MIN_LENGHT < entity.getDependencyName().length()
				&& entity.getDependencyName().length() < Constraints.Dependency.NAME_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					Dependency.class.getSimpleName(), "name",
					Constraints.Dependency.NAME_MIN_LENGHT,
					Constraints.Dependency.NAME_MAX_LENGHT));

		if (!(Constraints.Dependency.DESCRIPTION_MIN_LENGHT < entity.getDependencyName().length()
				&& entity.getDependencyName()
						.length() < Constraints.Dependency.DESCRIPTION_MAX_LENGHT))
			this.sendError(this.getEntityValidationMessenger().invalidAttributeSize(
					Dependency.class.getSimpleName(), "description",
					Constraints.Dependency.NAME_MIN_LENGHT,
					Constraints.Dependency.NAME_MAX_LENGHT));
	}

	@Override
	public void validateUpdate(Dependency entity) {
		this.validateInsert(entity);
	}
}
