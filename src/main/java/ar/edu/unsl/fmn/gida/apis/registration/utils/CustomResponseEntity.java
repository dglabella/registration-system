package ar.edu.unsl.fmn.gida.apis.registration.utils;

import org.springframework.http.HttpStatus;

public class CustomResponseEntity<T> {

    public static final String OK = "is ok";
    public static final String NOT_FOUND = "resource not found";

    private HttpStatus status;
    private String message;
    private T body;

    public CustomResponseEntity(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomResponseEntity(HttpStatus status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
