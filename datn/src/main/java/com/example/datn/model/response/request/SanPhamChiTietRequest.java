package com.example.datn.model.response.request;

import com.example.datn.entity.SanPhamChiTiet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietRequest {
    private SanPhamChiTiet sanPhamChiTiet;
    private List<HinhAnhRequest> hinhAnhs;
}
