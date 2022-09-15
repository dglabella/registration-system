package ar.edu.unsl.fmn.gida.apis.registration.exceptions;

import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponseBody> handle(MethodArgumentNotValidException exception,
                        WebRequest request) {
                String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                                .map(fieldError -> fieldError.getField() + ": "
                                                + fieldError.getDefaultMessage())
                                .collect(Collectors.joining("; "));

                return new ResponseEntity<>(
                                new ErrorResponseBody(new Date(), HttpStatus.BAD_REQUEST.value(),
                                                errorMessage, request.getDescription(false)),
                                HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ErrorResponse.class)
        public ResponseEntity<ErrorResponseBody> handle(ErrorResponse error, WebRequest request) {
                return new ResponseEntity<>(
                                new ErrorResponseBody(new Date(), error.getStatus().value(),
                                                error.getMessage(), request.getDescription(false)),
                                error.getStatus());
        }
}
