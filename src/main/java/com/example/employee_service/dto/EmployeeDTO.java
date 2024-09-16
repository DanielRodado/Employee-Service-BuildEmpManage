package com.example.employee_service.dto;

import com.example.employee_service.enums.RoleType;
import com.example.employee_service.model.EmployeeEntity;
import lombok.Getter;

@Getter
public class EmployeeDTO {

    private final Long id;

    private final String name, lastName, building, email;

    private final RoleType role;

    public EmployeeDTO(EmployeeEntity employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.lastName = employee.getLastName();
        this.building = employee.getBuilding();
        this.email = employee.getEmail();
        this.role = employee.getRoleType();
    }

}
