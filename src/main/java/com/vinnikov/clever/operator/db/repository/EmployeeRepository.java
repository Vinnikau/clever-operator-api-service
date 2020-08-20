package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {

}
