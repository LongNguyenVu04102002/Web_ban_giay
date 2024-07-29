package com.example.datn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TabDataDTO {
    private Long id;
    private String name;
    private Long phieuGiamGiaId;
    private String maPhieu;
    private BigDecimal tienGiam;
    private BigDecimal muaThemDeGiam;
}
