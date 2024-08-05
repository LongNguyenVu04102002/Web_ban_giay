package com.example.datn.service;

import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.SanPhamChiTiet;

import java.io.IOException;
import java.util.List;

public interface HinhAnhService {

    List<HinhAnh> getAll();

    HinhAnh getById(Long id);

    void save(List<HinhAnh> hinhAnhList);

    void add(HinhAnh hinhAnh);

    void saveOrUpdateImages(SanPhamChiTiet spct, List<byte[]> imageDatas, Long[] imageIds) throws IOException;

}
