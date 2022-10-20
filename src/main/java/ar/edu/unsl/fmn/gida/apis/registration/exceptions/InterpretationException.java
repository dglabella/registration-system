package ar.edu.unsl.fmn.gida.apis.registration.exceptions;

public class InterpretationException extends RuntimeException {

    public InterpretationException(String message) {
        super(message);
    }

    public InterpretationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
