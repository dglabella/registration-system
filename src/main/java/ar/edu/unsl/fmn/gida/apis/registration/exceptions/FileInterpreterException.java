package ar.edu.unsl.fmn.gida.apis.registration.exceptions;

public class FileInterpreterException extends RuntimeException {

    public FileInterpreterException(String message) {
        super(message);
    }

    public FileInterpreterException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
