package com.example.datn.service.impl;

import com.example.datn.entity.HinhAnh;
import com.example.datn.repository.HinhAnhRepository;
import com.example.datn.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HinhAnhServiceImpl implements HinhAnhService {

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Override
    public List<HinhAnh> getAll() {
        return hinhAnhRepository.findAll();
    }

    @Override
    public HinhAnh getById(Long id) {
        return hinhAnhRepository.findById(id).orElse(null);
    }

    @Override
    public void save(List<HinhAnh> hinhAnhList) {
        hinhAnhRepository.saveAll(hinhAnhList);
    }

    @Override
    public void add(HinhAnh hinhAnh) {
        hinhAnhRepository.save(hinhAnh);
    }

    @Override
    public byte[] getImageBySanPhamChiTietIdWithPriority(Long sanPhamChiTietId, Integer priority) {
        HinhAnh hinhAnh = hinhAnhRepository.findBySanPhamChiTietSanPhamChiTietIdAndUuTien(sanPhamChiTietId, priority)
                .orElseThrow(() -> new RuntimeException("Image not found with priority " + priority));
        return hinhAnh.getDataImg();
    }

    @Override
    public void saveAndUpdateOne(HinhAnh hinhAnh) {
        hinhAnhRepository.save(hinhAnh);
    }
}
