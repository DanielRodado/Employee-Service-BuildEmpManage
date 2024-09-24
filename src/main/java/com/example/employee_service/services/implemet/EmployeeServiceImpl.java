package com.example.employee_service.services.implemet;

import com.example.employee_service.dto.EmployeeApplicationDTO;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.exceptions.EmployeeNotAssignedToBuildingException;
import com.example.employee_service.exceptions.EmployeeNotFoundException;
import com.example.employee_service.mapper.EmployeeMapper;
import com.example.employee_service.model.EmployeeEntity;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.services.BuildingClientService;
import com.example.employee_service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.employee_service.utils.Messages.EMPLOYEE_NOT_FOUND;
import static com.example.employee_service.utils.Messages.EMPLOYEE_UNASSIGNED;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BuildingClientService buildingClientService;

    // Methods repository

    @Override
    public Mono<EmployeeEntity> getEmployeeById(Long employeeId) {
        return employeeRepository
                .findById(employeeId)
                .switchIfEmpty(Mono.error(new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND)));
    }

    @Override
    public Flux<EmployeeEntity> getEmployeesWithUnassignedBuilding() {
        return employeeRepository.findByBuildingIsNull();
    }

    @Override
    public Flux<EmployeeEntity> getEmployeesWithAssignedBuilding() {
        return employeeRepository.findByBuildingNotNull();
    }

    @Override
    public Flux<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Mono<EmployeeEntity> saveEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Mono<Void> existsEmployeeById(Long employeeId) {
        return employeeRepository
                .existsById(employeeId)
                .filter(exists -> exists)
                .switchIfEmpty(Mono.error(new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND)))
                .then();
    }

    // Methods repository return dto

    @Override
    public Mono<EmployeeDTO> getEmployeeDTOById(Long employeeId) {
        return getEmployeeById(employeeId).map(EmployeeMapper::toEmployeeDTO);
    }

    @Override
    public Flux<EmployeeDTO> getEmployeesDTOWithUnassignedBuilding() {
        return getEmployeesWithUnassignedBuilding().map(EmployeeMapper::toEmployeeDTO);
    }

    @Override
    public Flux<EmployeeDTO> getEmployeesDTOWithAssignedBuilding() {
        return getEmployeesWithAssignedBuilding().map(EmployeeMapper::toEmployeeDTO);
    }

    @Override
    public Flux<EmployeeDTO> getAllEmployeesDTO() {
        return getAllEmployees().map(EmployeeMapper::toEmployeeDTO);
    }


    // Methods Controller

    @Override
    public Mono<Void> requestAssignEmployeeToBuilding(Long employeeId, String buildingName) {
        return existsEmployeeById(employeeId)
                .then(buildingClientService.requestServiceEmployeeToBuilding(buildingName, "assign"))
                .then(getEmployeeById(employeeId))
                .flatMap(employee -> setBuilding(employee, buildingName))
                .flatMap(this::saveEmployee)
                .then();
    }

    @Override
    public Mono<EmployeeEntity> setBuilding(EmployeeEntity employee, String buildingName) {
        employee.setBuilding(buildingName);
        return Mono.just(employee);
    }

    @Override
    public Mono<Void> requestRemoveEmployeeToBuilding(Long employeeId) {
        return getEmployeeById(employeeId)
                .flatMap(employee ->
                        employee.getBuilding() != null
                                ? Mono.just(employee)
                                : Mono.error(new EmployeeNotAssignedToBuildingException(EMPLOYEE_UNASSIGNED))
                )
                .flatMap(employee -> buildingClientService
                        .requestServiceEmployeeToBuilding(employee.getBuilding(), "remove")
                        .thenReturn(employee))
                .flatMap(employee -> setBuilding(employee, null))
                .flatMap(this::saveEmployee)
                .then();
    }

    @Override
    public Mono<Void> createEmployee(Mono<EmployeeApplicationDTO> employeeAppMono) {
        return employeeAppMono
                .map(EmployeeMapper::toEmployeeEntity)
                .flatMap(this::saveEmployee)
                .then();
    }

}
