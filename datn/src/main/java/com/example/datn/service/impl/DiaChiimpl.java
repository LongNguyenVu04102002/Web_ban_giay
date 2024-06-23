package com.example.datn.service.impl;

import com.example.datn.entity.DiaChi;
import com.example.datn.repository.DiaChiRepository;
import com.example.datn.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaChiimpl implements DiaChiService {

    @Autowired
    DiaChiRepository diaChiRepository;
    @Override
    public List<DiaChi> getAllDiaCHi() {
      return diaChiRepository.findAll();

    }

    @Override
    public ResponseEntity<?> addDiaChi(DiaChi diaChi) {
        DiaChi diaChiAdd = diaChiRepository.save(diaChi);
        return ResponseEntity.ok(diaChiAdd);
    }

    @Override
    public ResponseEntity<?> updateDiaChi(DiaChi diaChi, Long id) {
        Optional<DiaChi> diaChiOptional = diaChiRepository.findById(id);
        if(diaChiOptional.isPresent()) {
            DiaChi existingDiaChi = diaChiOptional.get();
            existingDiaChi.setDiaChiNhan(diaChi.getDiaChiNhan());
            existingDiaChi.setNgayTao(diaChi.getNgayTao());
            existingDiaChi.setSdt(diaChi.getSdt());
            existingDiaChi.setHoTen(diaChi.getHoTen());
            existingDiaChi.setTrangThai(diaChi.isTrangThai());
            existingDiaChi.setXa(diaChi.getXa());
            existingDiaChi.setHuyen(diaChi.getHuyen());
            existingDiaChi.setThanhPho(diaChi.getThanhPho());
            existingDiaChi.setKhachHang(diaChi.getKhachHang());
            DiaChi updateDiaChi = diaChiRepository.save(existingDiaChi);
            return ResponseEntity.ok(updateDiaChi);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<?> deleteDiaChi(Long id) {
        Optional<DiaChi> diaChiOptional = diaChiRepository.findById(id);
        if (diaChiOptional.isPresent()){
            diaChiRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public Page<DiaChi> getAllDiaChiByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize); // PageRequest đếm từ 0
        return diaChiRepository.findAll(pageable);
    }
}
