package com.iacovelli.fakeamazon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, CartNotFoundException.class})
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return setMessage(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<String> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        return setMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return setMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return setMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ResponseEntity<String> setMessage(HttpStatus httpStatus, Throwable ex) {
        return ResponseEntity
                .status(httpStatus)
                .body(ex.getMessage());
    }

}
