package ar.edu.unsl.fmn.gida.apis.registration.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends RuntimeException {

    private HttpStatus status;

    public ErrorResponse(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ErrorResponse(String message, HttpStatus status, Throwable throwable) {
        super(message, throwable);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
