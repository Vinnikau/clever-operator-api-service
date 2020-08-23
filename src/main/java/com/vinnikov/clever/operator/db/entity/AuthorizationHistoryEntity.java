package com.vinnikov.clever.operator.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "authorization_history")
@NoArgsConstructor
@Getter
@Setter
@ToString
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
