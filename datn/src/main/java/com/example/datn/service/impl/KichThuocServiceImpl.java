package com.example.datn.service.impl;

import com.example.datn.entity.KichThuoc;
import com.example.datn.repository.KichThuocRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KichThuocServiceImpl implements IService<KichThuoc> {

    @Autowired
    private KichThuocRepo repo;

    @Override
    public List<KichThuoc> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<KichThuoc> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public KichThuoc addOrUpdate(KichThuoc kichThuoc) {
        return repo.save(kichThuoc);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<KichThuoc> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
