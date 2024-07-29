package com.example.datn.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long sanPhamChiTietId;
    private String tenSanPham;
    private BigDecimal giaBan;
    private Integer soLuong;
    private BigDecimal thanhTien;

}
