package com.example.datn.service.impl;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PhieuGiamGiaImpl implements PhieuGiamGiaService {

    @Autowired
    PhieuGiamGiaRepository repository;

    @Override
    public ResponseEntity<?> getAllPhieuGiamGia() {
        List<PhieuGiamGia> phieuGiamGiaList = repository.findAll();
        return ResponseEntity.ok(phieuGiamGiaList);
    }

    @Override
    public ResponseEntity<?> addPhieuGiamGia(PhieuGiamGia phieuGiamGia) {
        PhieuGiamGia createPhieu =  repository.save(phieuGiamGia);
        return ResponseEntity.ok(createPhieu);
    }

    @Override
    public ResponseEntity<?> updatePhieuGiamGia(PhieuGiamGia phieuGiamGia, Long id) {
        Optional<PhieuGiamGia> phieuGiamGiaOptional = repository.findById(id);
        if (phieuGiamGiaOptional.isPresent()){
            PhieuGiamGia phieu = phieuGiamGiaOptional.get();
            phieu.setMaGiamGia(phieuGiamGia.getMaGiamGia());
            phieu.setLoaiPhieu(phieuGiamGia.getLoaiPhieu());
            phieu.setPhanTramGiam(phieuGiamGia.getPhanTramGiam());
            phieu.setTienGiam(phieuGiamGia.getTienGiam());
            phieu.setSoLuongPhieu(phieuGiamGia.getSoLuongPhieu());
            phieu.setNgayBatDau(phieuGiamGia.getNgayBatDau());
            phieu.setNgayKetThuc(phieuGiamGia.getNgayKetThuc());
            phieu.setGiaTriDonToiThieu(phieuGiamGia.getGiaTriDonToiThieu());
            phieu.setGiaTriGiamToiDa(phieuGiamGia.getGiaTriGiamToiDa());
            phieu.setNgayTao(LocalDate.now());
            phieu.setTrangThai(phieuGiamGia.isTrangThai());
            PhieuGiamGia updatePhieu = repository.save(phieu);
            return ResponseEntity.ok(updatePhieu);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> deletePhieuGiamGia(Long id) {
       Optional<PhieuGiamGia> phieuGiamGiaOptional = repository.findById(id);
       if(phieuGiamGiaOptional.isPresent()){
           repository.deleteById(id);
           return ResponseEntity.ok().build();
       } else {
           return ResponseEntity.notFound().build();
       }
    }
}
