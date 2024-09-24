package com.example.employee_service.controllers;

import com.example.employee_service.dto.EmployeeApplicationDTO;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.employee_service.utils.Messages.EMPLOYEE_ASSIGNED;
import static com.example.employee_service.utils.Messages.EMPLOYEE_REMOVE;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public Mono<EmployeeDTO> getEmployeeDTOById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeDTOById(employeeId);
    }

    @GetMapping("/building/assigned")
    public Flux<EmployeeDTO> getEmployeesDTOWithAssignedBuilding() {
        return employeeService.getEmployeesDTOWithAssignedBuilding();
    }

    @GetMapping("/building/unassigned")
    public Flux<EmployeeDTO> getEmployeesDTOWithUnassignedBuilding() {
        return employeeService.getEmployeesDTOWithUnassignedBuilding();
    }

    @GetMapping
    public Flux<EmployeeDTO> getAllEmployeesDTO() {
        return employeeService.getAllEmployeesDTO();
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createEmployee(@RequestBody Mono<EmployeeApplicationDTO> employeeApplicationDTO) {
        return employeeService
                .createEmployee(employeeApplicationDTO)
                .thenReturn(ResponseEntity.ok("Employee created."));
    }

    @PatchMapping("/building/assign/{employeeId}")
    public Mono<ResponseEntity<String>> assignEmployeeToBuilding(@PathVariable Long employeeId,
                                                                 @RequestBody String buildingName) {
        return employeeService
                .requestAssignEmployeeToBuilding(employeeId, buildingName.toUpperCase())
                .then(Mono.just(ResponseEntity.ok(EMPLOYEE_ASSIGNED + buildingName.toUpperCase())));
    }

    @PatchMapping("/building/remove/{employeeId}")
    public Mono<ResponseEntity<String>> removeEmployeeToBuilding(@PathVariable Long employeeId) {
        return employeeService
                .requestRemoveEmployeeToBuilding(employeeId)
                .then(Mono.just(ResponseEntity.ok(EMPLOYEE_REMOVE)));
    }

}
