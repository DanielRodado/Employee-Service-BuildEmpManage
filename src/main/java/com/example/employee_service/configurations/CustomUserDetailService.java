package com.example.employee_service.configurations;

import com.example.employee_service.exceptions.EmployeeNotFoundException;
import com.example.employee_service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.example.employee_service.utils.Messages.EMPLOYEE_NOT_FOUND_EMAIL;

@Service
public class CustomUserDetailService implements ReactiveUserDetailsService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return employeeService
                .getEmployeeByEmail(username)
                .switchIfEmpty(Mono.error(new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND_EMAIL + username)))
                .map(employee ->
                        new User(
                                employee.getEmail(),
                                employee.getPassword(),
                                AuthorityUtils.createAuthorityList(employee.getRoleType().toString()))
                );
    }

}
