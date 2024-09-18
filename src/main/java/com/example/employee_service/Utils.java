package com.example.employee_service;

import com.example.employee_service.enums.RoleType;
import com.example.employee_service.model.EmployeeEntity;
import com.example.employee_service.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {
    @Bean
    public CommandLineRunner initData(EmployeeRepository employeeRepository) {
        return args -> {
            /*EmployeeEntity employee = new EmployeeEntity("Melba", "Morel",
                    RoleType.EMPLOYEE, "melba@managify.com", "12345");
            employeeRepository.save(employee).subscribe();

            EmployeeEntity employeeTwo = new EmployeeEntity("Ferb", "Smith",
                    RoleType.EMPLOYEE, "melba@managify.com", "12345");
            employeeRepository.save(employeeTwo).subscribe();

            EmployeeEntity employeeManage = new EmployeeEntity("Carlos", "Perez",
                    RoleType.MANAGER, "cperz@managify.com", "12345");
            employeeRepository.save(employeeManage).subscribe();

            EmployeeEntity employeeAdmin = new EmployeeEntity("Daniel", "Rodado",
                    RoleType.ADMIN, "danielr@managify.com", "12345");
            employeeRepository.save(employeeAdmin).subscribe();*/
        };
    }
}
