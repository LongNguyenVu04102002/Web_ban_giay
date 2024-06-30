package com.example.datn.service.Impl;

import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PhieuGiamGiaServiceImpl implements PhieuGiamGiaService {

    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;

    @Override
    public ResponseEntity<?> getPhieuGiamGiaByMa(String maGiamGia) {
        return ResponseEntity.ok(phieuGiamGiaRepository.findByMaGiamGia(maGiamGia));
    }

}
