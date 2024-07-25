package com.example.datn.service;

import com.example.datn.entity.HinhAnh;

import java.util.List;

public interface HinhAnhService {

    List<HinhAnh> getAll();

    HinhAnh getById(Long id);

    void save(List<HinhAnh> hinhAnhList);

    void add(HinhAnh hinhAnh);

    byte[] getImageBySanPhamChiTietIdWithPriority(Long sanPhamChiTietId, Integer priority);

    void saveAndUpdateOne(HinhAnh hinhAnh);

}
