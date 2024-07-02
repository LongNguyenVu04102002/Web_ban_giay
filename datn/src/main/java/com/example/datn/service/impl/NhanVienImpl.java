package com.example.datn.service.impl;

import com.example.datn.entity.NhanVien;
import com.example.datn.repository.NhanVienRepository;
import com.example.datn.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NhanVienImpl implements NhanVienService {
    @Autowired
    NhanVienRepository nhanVienRepository;
    @Override
    public List<NhanVien> getAllNhanhVien() {
        return nhanVienRepository.findAll();
    }

    @Override
    public Optional<NhanVien> getNhanVienById(Long id) {
        return nhanVienRepository.findById(id);
    }

    @Override
    public NhanVien saveNhanVien(NhanVien nhanVien) {

        return nhanVienRepository.save(nhanVien);
    }

    @Override
    public void deleteNhanVien(Long id) {
nhanVienRepository.deleteById(id);
    }

    @Override
    public NhanVien toggleTrangThai(Long nhanVienId) {
      Optional<NhanVien> optionalNhanVien = nhanVienRepository.findById(nhanVienId);
      if (optionalNhanVien.isPresent()){
          NhanVien nhanVien = optionalNhanVien.get();
          nhanVien.setTrangThai(!nhanVien.isTrangThai());
          return  nhanVienRepository.save(nhanVien);
      }

        return null;
    }

    @Override
    public Page<NhanVien> getAllNhanVienByPage(int pagenumber, int pageSize) {
        Pageable pageable = PageRequest.of(pagenumber -1 ,pageSize);

        return nhanVienRepository.findAll(pageable);
    }




    @Override
    public List<NhanVien> findByGioiTinh(boolean gioiTinh) {
        return nhanVienRepository.findByGioiTinh(gioiTinh);
    }

    @Override
    public NhanVien updateNhanVien(NhanVien nhanVien) {
        return null;
    }

    @Override
    public List<NhanVien> findBySdtContainingAndGioiTinh(String sdt, Boolean gioiTinh) {
        return nhanVienRepository.findBySdtContainingAndGioiTinh(sdt,gioiTinh);
    }

    @Override
    public List<NhanVien> findBySdtContaining(String sdt) {
        return nhanVienRepository.findBySdtContaining(sdt);
    }

    @Override
    public List<NhanVien> findByGioiTinh(Boolean gioiTinh) {
        return nhanVienRepository.findByGioiTinh(gioiTinh);
    }

}



