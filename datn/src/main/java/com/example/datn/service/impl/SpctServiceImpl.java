package com.example.datn.service.impl;

import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.SanPhamChiTietRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpctServiceImpl implements IService<SanPhamChiTiet> {

    @Autowired
    private SanPhamChiTietRepo repo;

    @Override
    public List<SanPhamChiTiet> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<SanPhamChiTiet> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public SanPhamChiTiet addOrUpdate(SanPhamChiTiet sanPhamChiTiet) {
        return repo.save(sanPhamChiTiet);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<SanPhamChiTiet> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
