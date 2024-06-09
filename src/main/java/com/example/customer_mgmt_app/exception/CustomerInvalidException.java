package com.example.customer_mgmt_app.exception;

public class CustomerInvalidException extends Exception {
    public CustomerInvalidException() {
    }

    public CustomerInvalidException(String message) {
        super(message);
    }

    public CustomerInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
