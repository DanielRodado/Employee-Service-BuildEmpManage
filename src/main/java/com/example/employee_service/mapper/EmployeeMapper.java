package com.example.employee_service.mapper;

import com.example.employee_service.dto.EmployeeApplicationDTO;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.model.EmployeeEntity;

public class EmployeeMapper {

    // to entity dto
    public static EmployeeDTO toEmployeeDTO(EmployeeEntity employee) {
        return new EmployeeDTO(employee);
    }

    // from Application DTO to entity
    public static EmployeeEntity toEmployeeEntity(EmployeeApplicationDTO employeeApplicationDTO) {
        return EmployeeEntity.builder()
                .name(employeeApplicationDTO.name())
                .lastName(employeeApplicationDTO.lastName())
                .email(employeeApplicationDTO.email())
                .password(employeeApplicationDTO.password())
                .build();
    }

}
