package com.example.datn.service.impl;

import com.example.datn.entity.ThuongHieu;
import com.example.datn.repository.ThuongHieuRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThuongHieuServiceImpl implements IService<ThuongHieu> {

    @Autowired
    private ThuongHieuRepo repo;

    @Override
    public List<ThuongHieu> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<ThuongHieu> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public ThuongHieu addOrUpdate(ThuongHieu thuongHieu) {
        return repo.save(thuongHieu);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<ThuongHieu> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
