package com.example.datn.service;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DiaChiService {

    List<DiaChi> getAllDiaChi();
    DiaChi saveDiaChi(DiaChi diaChi);
    void deleteDiaChi(Long id);
    Optional<DiaChi> getDiaChiById(Long id);
    DiaChi updateDiaChi(DiaChi updatedDiaChi);
}
