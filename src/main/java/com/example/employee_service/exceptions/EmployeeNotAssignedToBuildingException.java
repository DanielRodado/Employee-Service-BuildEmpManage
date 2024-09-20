package com.example.employee_service.exceptions;

public class EmployeeNotAssignedToBuildingException extends RuntimeException {
    public EmployeeNotAssignedToBuildingException(String message) {
        super(message);
    }
}
