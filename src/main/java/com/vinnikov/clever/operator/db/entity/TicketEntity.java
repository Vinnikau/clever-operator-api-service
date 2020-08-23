package com.vinnikov.clever.operator.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
public class TicketEntity {
    @Id
    private Long idTicket;
    private Long idCustomer;
    private Long idPurchase;
    private String numberTicket;
    private Long service;
    private Long idRefund;
    private Long idService;

}
