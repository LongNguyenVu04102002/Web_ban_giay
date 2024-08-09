package com.example.datn.service;

import com.example.datn.entity.NhanVien;
import java.util.List;

public interface NhanVienService {

    List<NhanVien> getAllNhanVien();

    NhanVien getById(Long id);

    void save(NhanVien nhanVien);
    boolean existsBySdt(String sdt);
    NhanVien existsByEmail(String email);

    NhanVien toggleTrangThai(Long nhanVienId);

}
