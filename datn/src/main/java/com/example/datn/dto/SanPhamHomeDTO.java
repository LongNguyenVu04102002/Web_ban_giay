package com.example.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SanPhamHomeDTO {
    private Long sanPhamId;
    private String ten;
    private BigDecimal giaThapNhat;
    private String anhDauTien;


}
