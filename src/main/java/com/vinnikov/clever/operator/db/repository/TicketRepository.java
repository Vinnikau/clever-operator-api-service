package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.api.response.util.SearchTicket;
import com.vinnikov.clever.operator.db.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Set;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("SELECT d FROM TicketEntity d WHERE d.numberTicket = :numberTicket")
    TicketEntity findByTicketNumber(String numberTicket);

    @Query("SELECT d FROM TicketEntity d WHERE d.idCustomer = :idCustomer")
    Collection<TicketEntity> findAllByIdCustomer(Set<Long> idCustomer);
}
