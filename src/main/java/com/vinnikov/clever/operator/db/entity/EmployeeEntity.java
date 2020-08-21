package com.vinnikov.clever.operator.db.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    private Long idEmployee;
    private String fioEmployee;
    private Long emplPosition;
    private String phoneEmployee;
    private String emailEmployee;
    private String loginEmployee;
    private String passwordEmployee;
    private Boolean actingEmployee;
    private Integer accessRights;

}
