package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.db.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("SELECT d FROM TicketEntity d WHERE d.numberTicket = :numberTicket")
    TicketEntity findByTicketNumber(String numberTicket);
}
