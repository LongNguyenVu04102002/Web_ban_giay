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
public class ThanhToanResponse {
    private String maVanDon;
    private String hoTen;
    private String email;
    private String sdt;
    private String diaChi;
    private BigDecimal tienShip;
    private boolean hinhThuc;
    private BigDecimal tongTien;
}
