package ar.edu.unsl.fmn.gida.apis.registration.validators;

import org.springframework.http.HttpStatus;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Dependency;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class DependencyValidator implements Validator<Dependency> {

    private CustomExpressionValidator expresionValidator;

    public DependencyValidator(CustomExpressionValidator expresionValidator) {
        this.expresionValidator = expresionValidator;
    }

    private void validateClassFields(Dependency dependency) {
        /**
        * check nullability
        */
        if(dependency.getId() == null)
            throw new ErrorResponse("dependency id must not be null", HttpStatus.BAD_REQUEST);
        
        if (!(this.expresionValidator.isPresent(dependency.getDependencyName())
                                || Constraints.Dependency.NAME_NULLABLE))
                        throw new ErrorResponse("dependency name is required", HttpStatus.BAD_REQUEST);

        /**
        * check size
        */
        if(!(Constraints.Dependency.NAME_MIN_LENGHT < dependency.getDependencyName().length()
        && dependency.getDependencyName().length() < Constraints.Dependency.NAME_MAX_LENGHT))
        throw new ErrorResponse("invalid dependency name: must contain between "
                                        + Constraints.Dependency.NAME_MIN_LENGHT + " and "
                                        + Constraints.Dependency.NAME_MAX_LENGHT + " characters",
                                        HttpStatus.BAD_REQUEST);

        if(!(Constraints.Dependency.DESCRIPTION_MIN_LENGHT < dependency.getDependencyName().length()
        && dependency.getDependencyName().length() < Constraints.Dependency.DESCRIPTION_MAX_LENGHT ))
        throw new ErrorResponse("invalid dependency description: must contain between "
                                        + Constraints.Dependency.NAME_MIN_LENGHT + " and "
                                        + Constraints.Dependency.NAME_MAX_LENGHT + " characters",
                                        HttpStatus.BAD_REQUEST);
    }

    private void validateAssociations(Dependency dependency) {

    }

    @Override
    public void validate(Dependency entity) {
        this.validateClassFields(entity);
        this.validateAssociations(entity);
    }
}
