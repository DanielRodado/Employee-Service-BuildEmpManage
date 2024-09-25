package com.example.employee_service.exceptions;

public class EmployeeAlreadyHasRoleException extends RuntimeException {
    public EmployeeAlreadyHasRoleException(String message) {
        super(message);
    }
}
