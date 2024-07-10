package com.example.datn.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhieuGiamGiaResponse {
    private Long phieuGiamGiaId;
    private BigDecimal tienGiam;
}
