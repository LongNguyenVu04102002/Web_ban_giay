package com.example.datn.service.impl;

import com.example.datn.entity.DotGiamGia;
import com.example.datn.repository.DotGiamGiaRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DotGiamGiaServiceImpl implements IService<DotGiamGia> {

    @Autowired
    private DotGiamGiaRepo repo;

    @Override
    public List<DotGiamGia> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<DotGiamGia> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public DotGiamGia addOrUpdate(DotGiamGia dotGiamGia) {
        return repo.save(dotGiamGia);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<DotGiamGia> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
