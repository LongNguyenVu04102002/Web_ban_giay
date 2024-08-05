package com.example.datn.service;

import com.example.datn.entity.GioHangChiTiet;

import java.util.List;
import java.util.Optional;

public interface GioHangChiTietService {

    GioHangChiTiet save(GioHangChiTiet gioHangChiTiet);
    Optional<GioHangChiTiet> findById(Long id);
    void deleteById(Long id);
    List<GioHangChiTiet> findByGioHangId(Long gioHangId);
    Optional<GioHangChiTiet> findBySanPhamChiTietAndGioHang(Long sanPhamChiTietId, Long gioHangId);
    public void addToCart(Long gioHangId, Long sanPhamChiTietId) ;

    }
