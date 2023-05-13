package com.Xeon.XeonWeb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "faza")
public class Faza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String faza;
}
