package com.example.datn.service;

import com.example.datn.entity.KichThuoc;

import java.util.List;

public interface KichThuocService {
    
    List<KichThuoc> getAll();

    KichThuoc getById(Long id);

    void save(KichThuoc kichThuoc);

    void delete(Long id);

    boolean isTenExists(String ten);

    boolean isTenExistsForUpdate(String ten, Long id);

    List<KichThuoc> getKichThuocsByTrangThai(boolean trangThai);
    
}
