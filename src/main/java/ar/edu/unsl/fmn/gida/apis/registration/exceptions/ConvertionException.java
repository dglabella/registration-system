package ar.edu.unsl.fmn.gida.apis.registration.exceptions;

public class ConvertionException extends RuntimeException {

    public ConvertionException(String message) {
        super(message);
    }

    public ConvertionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
