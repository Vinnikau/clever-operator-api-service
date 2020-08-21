package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT d FROM EmployeeEntity d WHERE d.loginEmployee = :login")
    Collection<EmployeeEntity> findByLogin(String login);

}
