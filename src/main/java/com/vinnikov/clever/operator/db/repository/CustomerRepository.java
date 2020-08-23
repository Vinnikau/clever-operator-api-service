package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("SELECT d FROM CustomerEntity d WHERE d.nameCustomer like :name or d.patronymicCustomer like :name or d.surnameCustomer like :name")
    Collection<CustomerEntity> findByName(String name);

    @Query("SELECT d FROM CustomerEntity d WHERE d.phoneCustomer = :phone")
    Collection<CustomerEntity> findByPhone(String phone);
}
