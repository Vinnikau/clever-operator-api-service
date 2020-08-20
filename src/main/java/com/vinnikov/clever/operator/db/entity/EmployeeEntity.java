package com.vinnikov.clever.operator.db.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @Column(name = "ID_Employee")
    private Long id;
    @Column(name = "FIO_Employee")
    private String fio;
    @Column(name = "Empl_Position")
    private Long position;
    @Column(name = "phone_Employee")
    private String phone;
    @Column(name = "email_Employee")
    private String email;
    @Column(name = "login_Employee")
    private String login;
    @Column(name = "password_Employee")
    private String pass;
    @Column(name = "acting_Employee")
    private Boolean acting;
    @Column(name = "access_Rights")
    private Integer rights;

}
