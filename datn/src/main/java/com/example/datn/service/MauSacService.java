package com.example.datn.service;

import com.example.datn.entity.MauSac;
import com.example.datn.entity.SanPham;

import java.util.List;

public interface MauSacService {

    List<MauSac> getAll();

    MauSac getById(Long id);

    void save(MauSac mauSac);

    void delete(Long id);

    List<MauSac> getMauSacsByTrangThai(boolean trangThai);
    
}
