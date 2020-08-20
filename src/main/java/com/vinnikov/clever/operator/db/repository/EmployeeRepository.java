package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long>,
        JpaSpecificationExecutor<EmployeeEntity> {

    @Query("SELECT d FROM EmployeeEntity d WHERE d.login = :login")
    Collection<EmployeeEntity> findByLogin(String login);

}
