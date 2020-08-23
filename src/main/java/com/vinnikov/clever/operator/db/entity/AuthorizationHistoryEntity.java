package com.vinnikov.clever.operator.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity
@Table(name = "authorization_history")
@NoArgsConstructor
@Getter
@Setter
public class AuthorizationHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAuthorization;
    private String authorizationKey;
    private Date startAuthorization;
    private Date endAuthorization;
    private String remoteAddress;
    private Long idEmployee;
}
