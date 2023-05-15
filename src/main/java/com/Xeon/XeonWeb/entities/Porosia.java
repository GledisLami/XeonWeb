package com.Xeon.XeonWeb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "porosia")
public class Porosia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String comments;

    @Column
    private Integer statusi;

    @Column
    private Integer userId;

}
