package ar.edu.unsl.fmn.gida.apis.registration.validators;

import org.springframework.http.HttpStatus;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;

/**
 * The validation object should be instanciated in service layer.
 */
public abstract class Validator<T> {

    private boolean validated = false;

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
    public abstract void fieldsValidation(T entity);

    /**
     * This method should implement the assosiation validation process.
     * 
     * @param entity The entity to be validated.
     */
    public abstract void associationValidation(T entity);

    public void validate(T entity) {
        if (this.validated) {
            this.fieldsValidation(entity);
            this.validated = true;
            this.associationValidation(entity);
        }
    }

    public void closeValidation() {
        this.validated = false;
    }
}
