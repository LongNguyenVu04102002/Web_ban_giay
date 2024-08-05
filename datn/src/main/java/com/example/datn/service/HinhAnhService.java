package com.example.datn.service;

import com.example.datn.entity.HinhAnh;

import java.util.List;

public interface HinhAnhService {

    List<HinhAnh> getAll();

    HinhAnh getById(Long id);

    void save(List<HinhAnh> hinhAnhList);

<<<<<<< HEAD
    void add(HinhAnh hinhAnh);

    byte[] getImageBySanPhamChiTietIdWithPriority(Long sanPhamChiTietId, Integer priority);

    void saveAndUpdateOne(HinhAnh hinhAnh);

    List<HinhAnh> getImagesBySanPhamChiTietId(Long sanPhamChiTietId);

    void saveOrUpdateImages(SanPhamChiTiet spct, List<byte[]> imageDatas, Long[] imageIds) throws IOException;
=======
    void saveAndUpdateOne(HinhAnh hinhAnh);
>>>>>>> parent of f4e9d10 (update)

    void deleteImages(List<Long> imageIds);

}
