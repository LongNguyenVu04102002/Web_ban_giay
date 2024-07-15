package com.example.datn.model.response;

import com.example.datn.entity.SanPhamChiTiet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SanPhamChiTietResponse {
    private List<SanPhamChiTiet> sanPhamChiTietList;
}
