package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.AuthorizationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;


public interface AuthorizationHistoryRepository extends JpaRepository<AuthorizationHistoryEntity, Long>{

    @Query("SELECT d FROM AuthorizationHistoryEntity d WHERE d.authorizationKey = :key")
    Collection<AuthorizationHistoryEntity> findByAuthorizationKey(String key);

}
