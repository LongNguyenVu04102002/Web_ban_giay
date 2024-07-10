package com.example.datn.service.Impl;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuGiamGiaImpl implements PhieuGiamGiaService {

    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;

    @Override
    public ResponseEntity<?> getPhieuGiamGiaByMa(String ma) {
        return ResponseEntity.ok(phieuGiamGiaRepository.findByMaGiamGia(ma));
    }

    @Override
    public List<PhieuGiamGia> getAll() {
        return phieuGiamGiaRepository.findAll();
    }

    @Override
    public PhieuGiamGia getById(Long id) {
        return phieuGiamGiaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(PhieuGiamGia phieuGiamGia) {
        phieuGiamGiaRepository.save(phieuGiamGia);
    }

}
