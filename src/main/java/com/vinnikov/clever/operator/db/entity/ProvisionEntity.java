package com.vinnikov.clever.operator.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "service_provision")
@Getter
@Setter
@NoArgsConstructor
public class ProvisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProvision;
    private Long idEmployee;
    private Date dateService;
}
