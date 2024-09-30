package com.example.employee_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import static com.example.employee_service.utils.ResponseUtil.httpStatusCode;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomServiceException.class)
    public Mono<ResponseEntity<String>> handleCustomServiceException(CustomServiceException ex) {
        return Mono.just(ResponseEntity.status(httpStatusCode(ex.getMessage())).body(ex.getMessage()));
    }

    @ExceptionHandler(EmployeeNotAssignedToBuildingException.class)
    public Mono<ResponseEntity<String>> handleEmployeeNotAssignedToBuildingException(EmployeeNotAssignedToBuildingException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage()));
    }

    @ExceptionHandler(InvalidRoleException.class)
    public Mono<ResponseEntity<String>> handleInvalidRoleException(InvalidRoleException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage()));
    }

    @ExceptionHandler(EmployeeAlreadyHasRoleException.class)
    public Mono<ResponseEntity<String>> handleEmployeeAlreadyHasRoleException(EmployeeAlreadyHasRoleException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage()));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public Mono<ResponseEntity<String>> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

}
