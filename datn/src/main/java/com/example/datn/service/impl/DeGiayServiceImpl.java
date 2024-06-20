package com.example.datn.service.impl;

import com.example.datn.entity.DeGiay;
import com.example.datn.repository.DeGiayRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeGiayServiceImpl implements IService<DeGiay> {

    @Autowired
    private DeGiayRepo repo;

    @Override
    public List<DeGiay> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<DeGiay> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public DeGiay addOrUpdate(DeGiay deGiay) {
        return repo.save(deGiay);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<DeGiay> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
