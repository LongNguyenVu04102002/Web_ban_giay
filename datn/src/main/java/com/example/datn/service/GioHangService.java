package com.example.datn.service;

import com.example.datn.entity.GioHang;

public interface GioHangService {

    GioHang getById(Long id);

    void addToCart(Long gioHangId, Long sanPhamChiTietId);

    void stepDown(Long gioHangChiTietId, Long sanPhamChiTietId);

    void stepUp(Long gioHangChiTietId, Long sanPhamChiTietId);

    void delete(Long gioHangChiTietId);

    void save(GioHang gioHang);
}
