package com.vinnikov.clever.operator.db.repository;

import com.vinnikov.clever.operator.api.response.util.SearchTicket;
import com.vinnikov.clever.operator.db.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("SELECT d FROM TicketEntity d WHERE d.numberTicket = :numberTicket")
    TicketEntity findByTicketNumber(String numberTicket);

    @Query("SELECT l.id_service,\n" +
            "       p.name,\n" +
            "       l.number_ticket,\n" +
            "       l.name_customer,\n" +
            "       l.patronymic_customer,\n" +
            "       l.surname_customer,\n" +
            "       l.phone_customer\n" +
            "FROM ((TicketEntity t LEFT JOIN CustomerEntity c ON c.idCustomer = t.idCustomer) l LEFT JOIN\n" +
            "    (TicketEntity h LEFT JOIN ServiceEntity s ON h.idService = s.idService) p ON l.idTicket = p.idTicket)\n" +
            "WHERE phone_customer = %:phone%\n" +
            "   OR name_customer LIKE %:name%\n" +
            "   OR l.number_ticket LIKE %:code%\n" +
            "   OR surname_customer LIKE %:name%\n" +
            "   OR patronymic_customer LIKE %:name%")
    Collection<SearchTicket> findAllByParam(String phone, String code, String name);
}
