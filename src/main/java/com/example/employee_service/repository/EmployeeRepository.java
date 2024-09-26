package com.example.employee_service.repository;

import com.example.employee_service.enums.RoleType;
import com.example.employee_service.model.EmployeeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends ReactiveCrudRepository<EmployeeEntity, Long> {

    Mono<Boolean> existsByIdAndRoleType(Long id, RoleType role);

    Flux<EmployeeEntity> findByBuildingIsNull();

    Flux<EmployeeEntity> findByBuildingNotNull();

    Flux<EmployeeEntity> findByBuilding(String building);

}
