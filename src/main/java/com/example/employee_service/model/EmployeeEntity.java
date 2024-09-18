package com.example.employee_service.model;

import com.example.employee_service.enums.RoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public EmployeeEntity(String name, String lastName, RoleType roleType, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.roleType = roleType;
        this.email = email;
        this.password = password;
    }
}
