package com.Xeon.XeonWeb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tipiprocesit")
public class TipiProcesit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String tipiProcesit;
}
