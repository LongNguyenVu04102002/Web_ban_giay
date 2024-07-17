package com.example.datn.service.impl;

import com.example.datn.entity.DotGiamGia;
import com.example.datn.repository.DotGiamGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DotGiamGiaServiceImpl {

    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepository;

    public List<DotGiamGia> getAll() {
        return dotGiamGiaRepository.findAll();
    }
}
