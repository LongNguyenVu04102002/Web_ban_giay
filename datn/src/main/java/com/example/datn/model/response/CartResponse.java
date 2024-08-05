package com.example.datn.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private Long sanPhamId;
    private Long sanPhamChiTietId;
    private String ten;
    private String mauSac;
    private String kichThuoc;
    private int gia;
    private String image;
    private int soLuong;
}
