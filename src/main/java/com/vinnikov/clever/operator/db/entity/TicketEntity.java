package com.vinnikov.clever.operator.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTicket;
    private Long idCustomer;
    private Long idPurchase;
    private String numberTicket;
    private Long service;
    private Long idRefund;
    private Long idService;

}