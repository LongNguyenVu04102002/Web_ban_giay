package com.example.datn.service.impl;

import com.example.datn.entity.LotGiay;
import com.example.datn.repository.LotGiayRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotGiayServiceImpl implements IService<LotGiay> {

    @Autowired
    private LotGiayRepo repo;

    @Override
    public List<LotGiay> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<LotGiay> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public LotGiay addOrUpdate(LotGiay lotGiay) {
        return repo.save(lotGiay);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<LotGiay> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
