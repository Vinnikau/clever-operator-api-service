package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.AuthorizationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface AuthorizationHistoryRepository extends JpaRepository<AuthorizationHistoryEntity, Long>{

}
