package com.example.datn.service;

import com.example.datn.entity.ThuongHieu;

import java.util.List;

public interface ThuongHieuService {

    List<ThuongHieu> getAll();

    ThuongHieu getById(Long id);

    void save(ThuongHieu thuongHieu);

    void delete(Long id);

    boolean isTenExists(String ten);

    boolean isTenExistsForUpdate(String ten, Long id);

    List<ThuongHieu> findAllByTrangThaiTrue();
    
}
