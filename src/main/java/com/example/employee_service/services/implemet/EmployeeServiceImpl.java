package com.example.employee_service.services.implemet;

import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.exceptions.EmployeeNotFoundException;
import com.example.employee_service.mapper.EmployeeMapper;
import com.example.employee_service.model.EmployeeEntity;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Methods repository

    @Override
    public Mono<EmployeeEntity> getEmployeeById(Long employeeId) {
        return employeeRepository
                .findById(employeeId)
                .switchIfEmpty(Mono.error(new EmployeeNotFoundException("The employee with id '" + employeeId + "' was not found.")));
    }

    @Override
    public Flux<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Mono<EmployeeDTO> getEmployeeDTOById(Long employeeId) {
        return getEmployeeById(employeeId).map(EmployeeMapper::toEmployeeDTO);
    }

    @Override
    public Flux<EmployeeDTO> getAllEmployeesDTO() {
        return getAllEmployees().map(EmployeeMapper::toEmployeeDTO);
    }

    // Methods Controller
}
