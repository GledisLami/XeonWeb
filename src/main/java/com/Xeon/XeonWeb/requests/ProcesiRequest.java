package com.Xeon.XeonWeb.requests;

import lombok.Data;

@Data
public class ProcesiRequest {
    private Integer procesiID;
//    private Integer projektiId; nuk duhet pasi do te merret nga pathvariable
    private String procesi;
    private Integer koha;
    private String makineria;
    private String tipiProcesit;
}
