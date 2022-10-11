package ar.edu.unsl.fmn.gida.apis.registration.validators;

import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class DependencyValidator extends Validator<Dependency> {

    public DependencyValidator(ExpressionValidator expressionValidator) {
        super(expressionValidator);
    }

    @Override
    public void fieldsValidation(Dependency entity) {
        /**
         * check nullability
         */
        if (entity.getId() == null)
            this.sendError("dependency id must not be null");

        if (!(this.getExpressionValidator().isPresent(entity.getDependencyName())
                || Constraints.Dependency.NAME_NULLABLE))
            this.sendError("dependency name is required");

        /**
         * check size
         */
        if (!(Constraints.Dependency.NAME_MIN_LENGHT < entity.getDependencyName().length()
                && entity.getDependencyName().length() < Constraints.Dependency.NAME_MAX_LENGHT))
            this.sendError("invalid dependency name: must contain between "
                    + Constraints.Dependency.NAME_MIN_LENGHT + " and "
                    + Constraints.Dependency.NAME_MAX_LENGHT + " characters");

        if (!(Constraints.Dependency.DESCRIPTION_MIN_LENGHT < entity.getDependencyName().length()
                && entity.getDependencyName()
                        .length() < Constraints.Dependency.DESCRIPTION_MAX_LENGHT))
            this.sendError("invalid dependency description: must contain between "
                    + Constraints.Dependency.NAME_MIN_LENGHT + " and "
                    + Constraints.Dependency.NAME_MAX_LENGHT + " characters");
    }

    @Override
    public void associationValidation(Dependency entity) {}

    @Override
    public void close() {
        this.closeFieldsValidation();
    }
}
