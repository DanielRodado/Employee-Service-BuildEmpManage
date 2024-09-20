package com.example.employee_service.exceptions;

public class CustomServiceException extends RuntimeException {
    public CustomServiceException(String message) {
        super(message);
    }
}
