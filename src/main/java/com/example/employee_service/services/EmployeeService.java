package com.example.employee_service.services;

import com.example.employee_service.dto.EmployeeApplicationDTO;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.enums.RoleType;
import com.example.employee_service.model.EmployeeEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    // Methods repository

    Mono<EmployeeEntity> getEmployeeById(Long employeeId);

    Flux<EmployeeEntity> getEmployeesWithUnassignedBuilding();

    Flux<EmployeeEntity> getEmployeesWithAssignedBuilding();

    Flux<EmployeeEntity> getAllEmployees();

    Mono<EmployeeEntity> saveEmployee(EmployeeEntity employee);

    Mono<Void> existsEmployeeById(Long employeeId);

    Mono<Boolean> existsEmployeeByIdAndRole(Long employeeId, RoleType role);

    // Methods repository return dto

    Mono<EmployeeDTO> getEmployeeDTOById(Long employeeId);

    Flux<EmployeeDTO> getEmployeesDTOWithUnassignedBuilding();

    Flux<EmployeeDTO> getEmployeesDTOWithAssignedBuilding();

    Flux<EmployeeDTO> getAllEmployeesDTO();

    // Methods Controller

    Mono<Void> createEmployee(Mono<EmployeeApplicationDTO> employeeAppMono);

    Mono<Void> deleteEmployee(Long employeeId);

    Mono<Void> requestAssignEmployeeToBuilding(Long employeeId, String buildingName);

    Mono<EmployeeEntity> setBuilding(EmployeeEntity employee, String buildingName);

    Mono<Void> requestRemoveEmployeeToBuilding(Long employeeId);

    Mono<Void> requestChangeRole(Long employeeId, RoleType role);

    Mono<Void> validateEmployeeRole(Long employeeId,  RoleType role);

    Mono<EmployeeEntity> changeRole(EmployeeEntity employee,  RoleType role);

}
