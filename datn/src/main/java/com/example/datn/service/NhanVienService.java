package com.example.datn.service;

import com.example.datn.entity.NhanVien;
import java.util.List;

public interface NhanVienService {

    List<NhanVien> getAllNhanVien();

    NhanVien getById(Long id);

    void save(NhanVien nhanVien);

    void updateTrangThai(Long id);

}
