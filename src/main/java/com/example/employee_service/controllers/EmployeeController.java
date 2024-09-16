package com.example.employee_service.controllers;

import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public Mono<EmployeeDTO> getEmployeeDTOById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeDTOById(employeeId);
    }

    @GetMapping("/building/unassigned")
    public Flux<EmployeeDTO> getEmployeesDTOWithUnassignedBuilding() {
        return employeeService.getEmployeesDTOWithUnassignedBuilding();
    }

    @GetMapping
    public Flux<EmployeeDTO> getAllEmployeesDTO() {
        return employeeService.getAllEmployeesDTO();
    }

}
