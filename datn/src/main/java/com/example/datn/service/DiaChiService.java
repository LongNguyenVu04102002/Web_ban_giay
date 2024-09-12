package com.example.datn.service;

import com.example.datn.entity.DiaChi;

public interface DiaChiService {
    void deleteDiaChi(Long diaChiId);
    DiaChi toggleTrangThai(Long diaChiId);
    DiaChi findById(Long diaChiId);
    void save(DiaChi diaChi);

}
