package ar.edu.unsl.fmn.gida.apis.registration.validators;

import org.springframework.http.HttpStatus;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;

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

    /**
     * Call this method in order to re use the validator. Once the validation process is done, this
     * method should be called in order to reset the validation process. The underlying
     * implementation should call the closeValidation() method for all associated validators in this
     * validator. This method can be ignored if the validation process is used once per validator
     * intance.
     * 
     * @see {@link #closeValidation()}
     */
    public abstract void close();

    public void validate(T entity) {
        if (!this.validated) {
            this.fieldsValidation(entity);
            this.validated = true;
            this.associationValidation(entity);
        }
    }

    /**
     * This method tells this validator that the entity fields were already validated.
     */
    public void closeFieldsValidation() {
        this.validated = false;
    }
}
