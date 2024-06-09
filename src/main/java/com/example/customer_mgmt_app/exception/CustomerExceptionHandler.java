package com.example.customer_mgmt_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(value = {CustomerInvalidException.class, CustomerNotFoundException.class})
    public ResponseEntity<String> handleCustomerExceptions(Exception ex) {
        if (ex instanceof CustomerInvalidException) {
            CustomerInvalidException ce = (CustomerInvalidException) ex;
            return new ResponseEntity<>(ce.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            CustomerNotFoundException cf = (CustomerNotFoundException) ex;
            return new ResponseEntity<>(cf.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
