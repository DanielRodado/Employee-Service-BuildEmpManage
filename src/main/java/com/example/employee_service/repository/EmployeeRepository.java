package com.example.employee_service.repository;

import com.example.employee_service.model.EmployeeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveCrudRepository<EmployeeEntity, Long> {

    Flux<EmployeeEntity> findByBuildingIsNull();

}
