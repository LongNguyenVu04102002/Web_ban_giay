package com.example.datn.service.impl;

import com.example.datn.entity.CoGiay;
import com.example.datn.repository.CoGiayRepository;
import com.example.datn.service.CoGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoGiayServiceImpl implements CoGiayService {

    @Autowired
    private CoGiayRepository coGiayRepository;
    @Override
    public List<CoGiay> getAllCoGiay() {
        return coGiayRepository.findAll();
    }

    @Override
    public CoGiay getById(Long id) {
        return coGiayRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCoGiay(CoGiay coGiay) {
        coGiayRepository.save(coGiay);
    }

    @Override
    public void deleteCoGiay(Long id) {
        coGiayRepository.deleteById(id);
    }

}
