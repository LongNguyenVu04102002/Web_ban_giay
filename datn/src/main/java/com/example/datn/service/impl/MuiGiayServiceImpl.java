package com.example.datn.service.impl;

import com.example.datn.entity.MuiGiay;
import com.example.datn.repository.MuiGiayRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuiGiayServiceImpl implements IService<MuiGiay> {

    @Autowired
    private MuiGiayRepo repo;

    @Override
    public List<MuiGiay> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<MuiGiay> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public MuiGiay addOrUpdate(MuiGiay muiGiay) {
        return repo.save(muiGiay);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<MuiGiay> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
