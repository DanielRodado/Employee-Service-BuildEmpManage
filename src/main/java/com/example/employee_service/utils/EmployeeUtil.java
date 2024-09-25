package com.example.employee_service.utils;

import com.example.employee_service.enums.RoleType;
import com.example.employee_service.exceptions.InvalidRoleException;

public final class EmployeeUtil {

    public static RoleType toRole(String role) {
        try {
            return RoleType.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleException("The role: \"" + role + "\" is not valid.");
        }
    }

}
