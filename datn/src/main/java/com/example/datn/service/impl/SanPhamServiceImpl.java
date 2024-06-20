package com.example.datn.service.impl;

import com.example.datn.entity.SanPham;
import com.example.datn.repository.SanPhamRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements IService<SanPham> {

    @Autowired
    private SanPhamRepo repo;

    @Override
    public List<SanPham> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<SanPham> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public SanPham addOrUpdate(SanPham sanPham) {
       return repo.save(sanPham);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<SanPham> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
