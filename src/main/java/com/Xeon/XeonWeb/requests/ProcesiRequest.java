package com.Xeon.XeonWeb.requests;

import lombok.Data;

@Data
public class ProcesiRequest {
    private Integer procesiId;
    private String procesi;
    private Integer koha;
    private String makineria;
    private String tipiProcesit;
}
