package com.example.datn.service.impl;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PhieuGiamGiaImpl implements PhieuGiamGiaService {

    @Autowired
    PhieuGiamGiaRepository repository;

//    @Override
//    public Page<PhieuGiamGia> getAllPhieu(Pageable pageable) {
//
//        Page<PhieuGiamGia> phieuGiamGias = repository.findAll(pageable);
//
//        for (PhieuGiamGia phieu : phieuGiamGias) {
//            String newStatus = phieu.getTrangThaiHienTai();
//            if (!newStatus.equals(phieu.getTrangThai())) {
//                phieu.setTrangThai(newStatus);
//                repository.save(phieu);
//            }
//        }
//        return phieuGiamGias;
//    }

    @Override
    public Page<PhieuGiamGia> filterPhieuGiamGia(String search, String type, Pageable pageable) {
        if (type.equals("Tất cả")) {
            return repository.findByMaGiamGiaContaining(search, pageable);
        } else if (type.equals("1")) {
            return repository.findByMaGiamGiaContainingAndLoaiPhieu(search, 1, pageable);
        } else if (type.equals("2")) {
            return repository.findByMaGiamGiaContainingAndLoaiPhieu(search, 2, pageable);
        } else {
            return repository.findByMaGiamGiaContaining(search, pageable);
        }
    }

    @Override
    public Optional<PhieuGiamGia> getPhieuById(Long id) {
        return repository.findById(id);
    }

    @Override
    public PhieuGiamGia savePhieuGiamGia(PhieuGiamGia phieuGiamGia) {
        return repository.save(phieuGiamGia);
    }

    @Override
    public Optional<PhieuGiamGia> endPhieu(Long id) {
        Optional<PhieuGiamGia> phieu = repository.findById(id);
        PhieuGiamGia phieuGiamGia = phieu.get();
        phieuGiamGia.setTrangThai("Kết thúc");
        repository.save(phieuGiamGia);
        return Optional.of(phieuGiamGia);
    }

    @Override
    public PhieuGiamGia update(PhieuGiamGia phieuGiamGia, Long id) {
        Optional<PhieuGiamGia> phieu = repository.findById(id);
        if (phieu.isPresent()){

            phieuGiamGia.setMaGiamGia(phieuGiamGia.getMaGiamGia());
            phieuGiamGia.setLoaiPhieu(phieuGiamGia.getLoaiPhieu());
            phieuGiamGia.setPhanTramGiam(phieuGiamGia.getPhanTramGiam());
            phieuGiamGia.setTienGiam(phieuGiamGia.getTienGiam());
            phieuGiamGia.setSoLuongPhieu(phieuGiamGia.getSoLuongPhieu());
            phieuGiamGia.setNgayBatDau(phieuGiamGia.getNgayBatDau());
            phieuGiamGia.setNgayKetThuc(phieuGiamGia.getNgayKetThuc());
            phieuGiamGia.setGiaTriDonToiThieu(phieuGiamGia.getGiaTriDonToiThieu());
            phieuGiamGia.setGiaTriGiamToiDa(phieuGiamGia.getGiaTriGiamToiDa());
            phieuGiamGia.setNgayTao(LocalDate.now());
//            phieuGiamGia.setTrangThai(phieuGiamGia.getTrangThai());
            return repository.save(phieuGiamGia);
        }
           else {
            throw new EntityNotFoundException("PhieuGiamGia with id " + id + " not found");

        }
    }

    @Override
    public PhieuGiamGia delete(Long id) {
        return null;
    }


//    @Override
//    public ResponseEntity<?> getAllPhieuGiamGia() {
//        List<PhieuGiamGia> phieuGiamGiaList = repository.findAll();
//        return ResponseEntity.ok(phieuGiamGiaList);
//    }
//
//    @Override
//    public ResponseEntity<?> addPhieuGiamGia(PhieuGiamGia phieuGiamGia) {
//        PhieuGiamGia createPhieu =  repository.save(phieuGiamGia);
//        return ResponseEntity.ok(createPhieu);
//    }
//
//    @Override
//    public ResponseEntity<?> updatePhieuGiamGia(PhieuGiamGia phieuGiamGia, Long id) {
//        Optional<PhieuGiamGia> phieuGiamGiaOptional = repository.findById(id);
//        if (phieuGiamGiaOptional.isPresent()){
//            PhieuGiamGia phieu = phieuGiamGiaOptional.get();
//            phieu.setMaGiamGia(phieuGiamGia.getMaGiamGia());
//            phieu.setLoaiPhieu(phieuGiamGia.getLoaiPhieu());
//            phieu.setPhanTramGiam(phieuGiamGia.getPhanTramGiam());
//            phieu.setTienGiam(phieuGiamGia.getTienGiam());
//            phieu.setSoLuongPhieu(phieuGiamGia.getSoLuongPhieu());
//            phieu.setNgayBatDau(phieuGiamGia.getNgayBatDau());
//            phieu.setNgayKetThuc(phieuGiamGia.getNgayKetThuc());
//            phieu.setGiaTriDonToiThieu(phieuGiamGia.getGiaTriDonToiThieu());
//            phieu.setGiaTriGiamToiDa(phieuGiamGia.getGiaTriGiamToiDa());
//            phieu.setNgayTao(LocalDate.now());
//            phieu.setTrangThai(phieuGiamGia.isTrangThai());
//            PhieuGiamGia updatePhieu = repository.save(phieu);
//            return ResponseEntity.ok(updatePhieu);
//
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @Override
//    public ResponseEntity<?> deletePhieuGiamGia(Long id) {
//       Optional<PhieuGiamGia> phieuGiamGiaOptional = repository.findById(id);
//       if(phieuGiamGiaOptional.isPresent()){
//           repository.deleteById(id);
//           return ResponseEntity.ok().build();
//       } else {
//           return ResponseEntity.notFound().build();
//       }
//    }
}
