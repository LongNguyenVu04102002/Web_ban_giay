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
    private String tenNguoiNhan;
    private String sdt;
    private String email;
    private String province;
    private String district;
    private String ward;
    private String diaChi;
    private Long paymentMethod;
    private BigDecimal tienShip;
    private BigDecimal tienGiam;
    private BigDecimal tongTien;
}
