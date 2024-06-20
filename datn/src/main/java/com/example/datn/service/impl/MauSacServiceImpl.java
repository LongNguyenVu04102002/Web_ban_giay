package com.example.datn.service.impl;

import com.example.datn.entity.MauSac;
import com.example.datn.repository.MauSacRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacServiceImpl implements IService<MauSac> {

    @Autowired
    private MauSacRepo repo;

    @Override
    public List<MauSac> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<MauSac> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public MauSac addOrUpdate(MauSac mauSac) {
        return repo.save(mauSac);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<MauSac> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
