package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.AuthorizationHistoryEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface AuthorizationHistoryRepository extends PagingAndSortingRepository<AuthorizationHistoryEntity, Long>,
        JpaSpecificationExecutor<AuthorizationHistoryEntity> {

}
