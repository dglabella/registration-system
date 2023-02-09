package ar.edu.unsl.fmn.gida.apis.registration.services.validators;

import org.springframework.http.HttpStatus;
import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ErrorResponse;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.validation.ValidationMessenger;

public abstract class Validator<T> {
	private ExpressionValidator expressionValidator;
	private ValidationMessenger entityValidationMessages;

	public Validator(ExpressionValidator expressionValidator,
			ValidationMessenger entityValidationMessages) {
		this.expressionValidator = expressionValidator;
		this.entityValidationMessages = entityValidationMessages;
	}

	public ExpressionValidator getExpressionValidator() {
		return this.expressionValidator;
	}

	public ValidationMessenger getEntityValidationMessages() {
		return this.entityValidationMessages;
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
