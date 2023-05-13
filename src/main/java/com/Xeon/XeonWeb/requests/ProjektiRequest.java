package com.Xeon.XeonWeb.requests;


import lombok.Data;

import javax.persistence.Column;

@Data
public class ProjektiRequest {
    private String comments;

    private Integer afati;

    private Integer PorosiaId;
}
