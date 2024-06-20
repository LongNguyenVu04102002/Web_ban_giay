package com.example.datn.service.impl;

import com.example.datn.entity.DayGiay;
import com.example.datn.repository.DayGiayRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayGiayServiceImpl implements IService<DayGiay> {

    @Autowired
    private DayGiayRepo repo;

    @Override
    public List<DayGiay> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<DayGiay> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public DayGiay addOrUpdate(DayGiay dayGiay) {
        return repo.save(dayGiay);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<DayGiay> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
