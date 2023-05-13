package com.Xeon.XeonWeb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "projekt")
public class Projekti {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String comments;

    @Column
    private Integer afati;

    @Column
    private Integer UserId;

    @Column
    private Integer PorosiaId;
}
