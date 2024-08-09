package com.example.datn.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhieuGiamGiaResponse {
    private Long phieuGiamGiaId;
    private String maPhieu;
    private BigDecimal tienGiam;
}
