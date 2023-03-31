package ar.edu.unsl.fmn.gida.apis.registration.exceptions;

public class ConversionException extends RuntimeException {

	public ConversionException(String message) {
		super(message);
	}

	public ConversionException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
