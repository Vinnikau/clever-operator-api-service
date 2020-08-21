package com.vinnikov.clever.operator.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@javax.persistence.Entity
@Table(name = "authorization_history")
@NoArgsConstructor
@Getter
@Setter
public class AuthorizationHistoryEntity {
    @Id

    private Long idAuthorization;
    private String authorizationKey;
    @Column(name = "Start_Authorization")
    private Date start;
    @Column(name = "End_Authorization")
    private Date end;
    @Column(name = "Remote_Address")
    private String remote;
    @Column(name = "ID_Employee")
    private Long idEmployee;
}
