package com.example.datn.service;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiaChiService {

    List<DiaChi> getAllDiaCHi();
    ResponseEntity<?> addDiaChi(DiaChi diaChi);
    ResponseEntity<?> updateDiaChi(DiaChi diaChi, Long id);
    ResponseEntity<?> deleteDiaChi(Long id);
    Page<DiaChi> getAllDiaChiByPage(int pageNumber, int pageSize);
}
