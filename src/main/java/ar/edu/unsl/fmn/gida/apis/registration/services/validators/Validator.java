package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import org.springframework.http.HttpStatus;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;

public abstract class Validator<T> {
    private ExpressionValidator expressionValidator;

    public Validator(ExpressionValidator expressionValidator) {
        this.expressionValidator = expressionValidator;
    }

    public ExpressionValidator getExpressionValidator() {
        return this.expressionValidator;
    }

    public void sendError(String message) {
        throw new ErrorResponse(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method should implement the fields validation process. In order to return an error if
     * some validation condition is not satisfied, the underlying implementation should call
     * sendError() method.
     * 
     * @param entity The entity to be validated.
     * @see {@link #sendError()}
     */
    public abstract void validate(T entity);
}
