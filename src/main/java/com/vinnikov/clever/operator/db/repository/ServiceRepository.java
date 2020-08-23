package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.EmployeeEntity;
import com.vinnikov.clever.operator.db.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {


    @Query("SELECT d FROM ServiceEntity d WHERE d.working = true")
    Collection<EmployeeEntity> findAllWorkingServices();
}
