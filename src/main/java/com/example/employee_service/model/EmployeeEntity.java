package com.example.employee_service.model;

import com.example.employee_service.enums.RoleType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table("employees")
public class EmployeeEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name, lastName, building, email, password;

    @Column("role")
    private RoleType roleType;

    @Builder
    private EmployeeEntity(String name, String lastName, String building, String email, String password, RoleType roleType) {
        this.name = name;
        this.lastName = lastName;
        this.building = building;
        this.email = email;
        this.password = password;
        this.roleType = RoleType.EMPLOYEE;
    }
}
