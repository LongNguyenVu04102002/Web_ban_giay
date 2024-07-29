package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    private Long id;
    private String tenSanPham;
    private String mauSac;
    private String kichThuoc;
    private BigDecimal gia;
    private String image;
    private int soLuong;

}
