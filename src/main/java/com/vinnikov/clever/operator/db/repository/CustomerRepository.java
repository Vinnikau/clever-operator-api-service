package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}
