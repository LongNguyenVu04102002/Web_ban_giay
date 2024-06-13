package com.example.datn.service;

import com.example.datn.entity.DiaChi;
import org.springframework.http.ResponseEntity;

public interface DiaChiService {

    ResponseEntity<?> getAllDiaCHi();
    ResponseEntity<?> addDiaChi(DiaChi diaChi);
    ResponseEntity<?> updateDiaChi(DiaChi diaChi, Long id);
    ResponseEntity<?> deleteDiaChi(Long id);
}
