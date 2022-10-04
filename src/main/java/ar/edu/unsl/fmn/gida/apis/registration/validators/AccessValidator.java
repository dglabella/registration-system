package ar.edu.unsl.fmn.gida.apis.registration.validators;

import org.springframework.http.HttpStatus;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Access;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class AccessValidator implements Validator<Access> {

    private CustomExpressionValidator expresionValidator;

    public AccessValidator(CustomExpressionValidator expresionValidator) {
        this.expresionValidator = expresionValidator;
    }

    private void validateClassFields(Access access) {
        /**
         * check nullability
         */
        if (access.getId() == null)
            throw new ErrorResponse("Access id must not be null", HttpStatus.BAD_REQUEST);
        
        if (!(this.expresionValidator.isPresent(access.getAccesseName())
                            || Constraints.Access.ACCESSNAME_NULLABLE))
                    throw new ErrorResponse("Access name is required", HttpStatus.BAD_REQUEST);
        /**
         * check size
         */

        if (!(Constraints.Access.ACCESSNAME_MIN_LENGHT < access.getAccesseName().length()
                    && access.getAccesseName().length() < Constraints.Access.ACCESSNAME_MAX_LENGHT))
            throw new ErrorResponse("invalid access name: must contain between "
                            + Constraints.Access.ACCESSNAME_MIN_LENGHT + " and "
                            + Constraints.Access.ACCESSNAME_MAX_LENGHT + " characters",
                            HttpStatus.BAD_REQUEST);
        
        if (!(access.getAccesseName().length() < Constraints.Access.DESCRIPTION_MAX_LENGHT))
            throw new ErrorResponse("invalid access description: must contain "
                            + Constraints.Access.DESCRIPTION_MAX_LENGHT + " characters",
                            HttpStatus.BAD_REQUEST);

    }   

    private void validateAssociations(Access access) {}

    @Override
    public void validate(Access access) {
        // TODO Auto-generated method stub
        this.validateClassFields(access);
        this.validateAssociations(access);
    }
    
}
