package com.example.datn.service;

import com.example.datn.entity.KichThuoc;
import com.example.datn.entity.SanPham;

import java.util.List;

public interface KichThuocService {
    
    List<KichThuoc> getAll();

    KichThuoc getById(Long id);

    void save(KichThuoc kichThuoc);

    void delete(Long id);

    List<KichThuoc> getKichThuocsByTrangThai(boolean trangThai);
    
}
