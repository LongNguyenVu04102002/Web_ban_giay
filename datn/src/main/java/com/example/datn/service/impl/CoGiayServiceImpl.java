package com.example.datn.service.impl;

import com.example.datn.entity.CoGiay;
import com.example.datn.repository.CoGiayRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoGiayServiceImpl implements IService<CoGiay> {

    @Autowired
    private CoGiayRepo repo;

    @Override
    public List<CoGiay> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<CoGiay> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public CoGiay addOrUpdate(CoGiay coGiay) {
        return repo.save(coGiay);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<CoGiay> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
