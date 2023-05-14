package com.Xeon.XeonWeb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "proces")
public class Procesi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String procesi;
    @Column
    private Integer koha;
    @Column
    private Integer ProjektId;
    @Column
    private Integer FazaId;
    @Column
    private Integer UserId;
    @Column
    private Integer makineriaId;
    @Column
    private Integer tipiProcesitId;
}
