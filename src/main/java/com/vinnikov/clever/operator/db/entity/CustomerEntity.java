package com.vinnikov.clever.operator.db.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {

    @Id
    private Long idCustomer;
    private String nameCustomer;
    private String surnameCustomer;
    private String patronymicCustomer;
    private String phoneCustomer;
    private String emailCustomer;


}
