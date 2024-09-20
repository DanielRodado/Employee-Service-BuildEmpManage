package com.example.employee_service.controllers;

import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.dto.EmployeeToBuildingDTO;
import com.example.employee_service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PatchMapping("/building/assign")
    public Mono<ResponseEntity<String>> assignEmployeeToBuilding(@RequestBody EmployeeToBuildingDTO employeeToBuildingDTO) {
        return employeeService
                .requestAssignEmployeeToBuilding(employeeToBuildingDTO.employeeId(), employeeToBuildingDTO.buildingName().toUpperCase())
                .then(Mono.just(ResponseEntity.ok("The employee assigned to building " + employeeToBuildingDTO.buildingName().toUpperCase())));
    }

}
