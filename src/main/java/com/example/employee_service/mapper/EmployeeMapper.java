package com.example.employee_service.mapper;

import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.model.EmployeeEntity;

public class EmployeeMapper {

    // to entity dto

    public static EmployeeDTO toEmployeeDTO(EmployeeEntity employee) {
        return new EmployeeDTO(employee);
    }

}
