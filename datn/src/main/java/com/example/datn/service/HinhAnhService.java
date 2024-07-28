package com.example.datn.service;

import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.SanPhamChiTiet;

import java.util.List;

public interface HinhAnhService {

    List<HinhAnh> getAll();

    HinhAnh getById(Long id);

    void save(List<HinhAnh> hinhAnhList);

    void add(HinhAnh hinhAnh);

    byte[] getImageBySanPhamChiTietIdWithPriority(Long sanPhamChiTietId, Integer priority);

    void saveAndUpdateOne(HinhAnh hinhAnh);

    List<HinhAnh> getImagesBySanPhamChiTietId(Long sanPhamChiTietId);

    void saveOrUpdateImages(SanPhamChiTiet spct, List<byte[]> imageDatas);

    void deleteById(Long id);

}
