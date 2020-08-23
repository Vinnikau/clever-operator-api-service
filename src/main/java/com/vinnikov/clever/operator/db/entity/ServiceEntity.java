package com.vinnikov.clever.operator.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idService;
    private Integer price;
    private Boolean working;
    private String shortDescription;
    private String fullDescription;
    private String name;
    private String picture;
    private Integer heightMax;
    private Integer heightMin;
    private Integer weightMax;
    private Integer weightMin;
    private Integer ageMax;
    private Integer ageMin;
}
