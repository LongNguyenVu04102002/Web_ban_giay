package com.example.datn.service;

import com.example.datn.entity.PhieuGiamGia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhieuGiamGiaService {

    ResponseEntity<?> getPhieuGiamGiaByMa(String ma);

    List<PhieuGiamGia> getAll();

    PhieuGiamGia getById(Long id);

    void save(PhieuGiamGia phieuGiamGia);
<<<<<<< HEAD

    void update(Long id);

=======
>>>>>>> master
}
