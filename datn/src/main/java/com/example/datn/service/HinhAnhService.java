package com.example.datn.service;

import com.example.datn.entity.HinhAnh;

import java.util.List;

public interface HinhAnhService {

    List<HinhAnh> getAll();

    HinhAnh getById(Long id);

    void save(List<HinhAnh> hinhAnhList);

    void saveAndUpdateOne(HinhAnh hinhAnh);

}
