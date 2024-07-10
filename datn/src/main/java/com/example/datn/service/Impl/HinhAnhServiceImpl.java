package com.example.datn.service.Impl;

import com.example.datn.entity.HinhAnh;
import com.example.datn.repository.HinhAnhRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HinhAnhServiceImpl implements IService<HinhAnh> {

    @Autowired
    private HinhAnhRepo repo;

    @Override
    public List<HinhAnh> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<HinhAnh> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public HinhAnh addOrUpdate(HinhAnh hinhAnh) {
        return repo.save(hinhAnh);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<HinhAnh> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
