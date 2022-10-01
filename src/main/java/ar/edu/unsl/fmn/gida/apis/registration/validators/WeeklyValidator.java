package ar.edu.unsl.fmn.gida.apis.registration.validators;

import org.springframework.http.HttpStatus;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.model.Weekly;
import ar.edu.unsl.fmn.gida.apis.registration.model.constraints.Constraints;

public class WeeklyValidator implements Validator<Weekly> {
    private CustomExpressionValidator expresionValidator;

    public WeeklyValidator(CustomExpressionValidator expresionValidator) {
        this.expresionValidator = expresionValidator;
    }
    
    private void validateClassFields(Weekly weekly) {
        /**
         * check nullability
         */
        if (weekly.getId() == null)
            throw new ErrorResponse("weekly id must not be null", HttpStatus.BAD_REQUEST);

        if (weekly.getPersonFk() == null)///PREGUNTAR YA QUE PERSONFK ES PARTE DE UNA ASOCIACION
            throw new ErrorResponse("weekly personfk must not be null", HttpStatus.BAD_REQUEST);

        // if (weekly.isMonday().)  PREGUNTAR SI LOS BOOLEANOS DE LAS FECHA HACER VALIDADOR YA QUE SE LE ASIGNA UN VALOR ENTONCES NUNCA VA A SER VACIO
        //                     || Constraints.Weekly.MONDAY_NULLABLE))

        if (!(this.expresionValidator.isPresent(String.format("%1$tY-%1$tm-%1$td", weekly.getStart()))
                                || Constraints.Weekly.START_NULLABLE))
            throw new ErrorResponse("weekly start is required", HttpStatus.BAD_REQUEST);
    }

    private void validateAssociations(Weekly weekly) {}

    @Override
    public void validate(Weekly entity) {
        // TODO Auto-generated method stub
        this.validateClassFields(entity);
        this.validateAssociations(entity);

    }
}
