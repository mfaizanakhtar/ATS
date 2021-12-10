package com.backendDelivery.backendDelivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(UserException.class)
    public final ResponseEntity<Object> handleUserException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
