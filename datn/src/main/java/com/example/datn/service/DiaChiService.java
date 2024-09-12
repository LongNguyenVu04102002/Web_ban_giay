package com.example.datn.service;

import com.example.datn.entity.DiaChi;

import java.util.List;

public interface DiaChiService {
    void deleteDiaChi(Long diaChiId);
    DiaChi toggleTrangThai(Long diaChiId);
    DiaChi findById(Long diaChiId);
    void save(DiaChi diaChi);

    List<DiaChi> findByKhachHangId(Long khachHangId);
}
