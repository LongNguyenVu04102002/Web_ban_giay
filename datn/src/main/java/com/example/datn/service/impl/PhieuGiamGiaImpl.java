package com.example.datn.service.impl;

import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.PhieuGiamGiaRepository;
import com.example.datn.service.PhieuGiamGiaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class PhieuGiamGiaImpl implements PhieuGiamGiaService {

    @Autowired
    PhieuGiamGiaRepository repository;

    //    @Override
//    public Page<PhieuGiamGia> getAllPhieu(Pageable pageable) {
//        Pageable sortedByIdDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "phieuGiamGiaId"));
//        Page<PhieuGiamGia> phieuGiamGias = repository.findAll(sortedByIdDesc);
//
//        List<PhieuGiamGia> phieuCanCapNhat = new ArrayList<>();
//
//        for (PhieuGiamGia phieu : phieuGiamGias) {
//            String newStatus = phieu.getTrangThaiHienTai();
//            if (!newStatus.equals(phieu.getTrangThai())) {
//                phieu.setTrangThai(newStatus);
//                phieuCanCapNhat.add(phieu);
//            }
//        }
//
//        // Batch update
//        if (!phieuCanCapNhat.isEmpty()) {
//            repository.saveAll(phieuCanCapNhat);
//        }
//
//        return phieuGiamGias;
//    }
    @Override
    public Page<PhieuGiamGia> getAllPhieu(Pageable pageable) {
        Pageable sortedByIdDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "phieuGiamGiaId"));
        Page<PhieuGiamGia> phieuGiamGias = repository.findAll(sortedByIdDesc);

        for (PhieuGiamGia phieu : phieuGiamGias) {
            String newStatus = phieu.getTrangThaiHienTai();
            if (!newStatus.equals(phieu.getTrangThai())) {
                phieu.setTrangThai(newStatus);
                repository.save(phieu);
            }
        }

        return phieuGiamGias;
    }


    @Override
    public PhieuGiamGia getPhieuById(Long id) {
        Optional<PhieuGiamGia> phieuGiamGiaOptional = repository.findById(id);
        if (phieuGiamGiaOptional.isPresent()) {
            return phieuGiamGiaOptional.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public PhieuGiamGia savePhieuGiamGia(PhieuGiamGia phieuGiamGia) {
        phieuGiamGia.setNgayTao(LocalDate.now());
        if (phieuGiamGia.getMaGiamGia().isEmpty()) {
            phieuGiamGia.setMaGiamGia(generateVoucherCode());
            return repository.save(phieuGiamGia);
        }
        else {
            return repository.save(phieuGiamGia);
        }
    }


    private String generateVoucherCode() {
        String prefix = "XBOY";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Generate 5 random characters
        for (int i = 0; i < 5; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return prefix + sb.toString();
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
        if (phieu.isPresent()) {
            PhieuGiamGia existingPhieu = phieu.get();
            existingPhieu.setMaGiamGia(phieuGiamGia.getMaGiamGia());
            existingPhieu.setLoaiPhieu(phieuGiamGia.getLoaiPhieu());
            existingPhieu.setPhanTramGiam(phieuGiamGia.getPhanTramGiam());
            existingPhieu.setTienGiam(phieuGiamGia.getTienGiam());
            existingPhieu.setSoLuongPhieu(phieuGiamGia.getSoLuongPhieu());
            existingPhieu.setNgayBatDau(phieuGiamGia.getNgayBatDau());
            existingPhieu.setNgayKetThuc(phieuGiamGia.getNgayKetThuc());
            existingPhieu.setGiaTriDonToiThieu(phieuGiamGia.getGiaTriDonToiThieu());
            existingPhieu.setGiaTriGiamToiDa(phieuGiamGia.getGiaTriGiamToiDa());
            existingPhieu.setNgayTao(LocalDate.now());
            // existingPhieu.setTrangThai(phieuGiamGia.getTrangThai());

            return repository.save(existingPhieu);
        } else {
            throw new EntityNotFoundException("PhieuGiamGia with id " + id + " not found");
        }

    }

    @Override
    public PhieuGiamGia delete(Long id) {
        return null;
    }

    @Override
    public Page<PhieuGiamGia> searchLoaiPhieu(Long loaiPhieu, Pageable pageable) {
        Pageable sortedByIdDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "phieuGiamGiaId"));
        Page<PhieuGiamGia> phieuGiamGias = repository.findByLoaiPhieu(loaiPhieu, sortedByIdDesc);
        return phieuGiamGias;
    }


    @Override
    public Page<PhieuGiamGia> searchTrangThai(String trangThai, Pageable pageable) {
        Pageable sortedByIdDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "phieuGiamGiaId"));
        Page<PhieuGiamGia> phieuGiamGias = repository.findByTrangThai(trangThai, sortedByIdDesc);
        return phieuGiamGias;
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
