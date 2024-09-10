package com.learn.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveRequest {
    private Integer stockId;
    private String namaBarang;
    private Integer jumlahStockBarang;
    private String nomorSeriBarang;
    private AdditionalInfoDTO additionalInfo;
    private String gambarBarang;
}
