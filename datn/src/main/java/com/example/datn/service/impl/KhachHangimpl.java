package com.example.datn.service.impl;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.repository.DiaChiRepository;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KhachHangimpl implements KhachHangService {
    @Autowired
    KhachHangRepository khachHangRepository;


    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getKhachHangById(Long id) {


        Optional<KhachHang> optionalKhachHang = khachHangRepository.findById(id);

        // Kiểm tra khách hàng có tồn tại không
        if (optionalKhachHang.isPresent()) {
            return optionalKhachHang.get();
        } else {
            // Xử lý khi không tìm thấy khách hàng với id tương ứng
            throw new RuntimeException("Không tìm thấy khách hàng với ID: " + id);
        }
    }

    public KhachHang saveKhachHang(KhachHang khachHang) {
        if (!khachHang.isTrangThai()) {
            khachHang.setTrangThai(true); // Set default value to true
        }

        return khachHangRepository.save(khachHang);

    }



    public void deleteKhachHang(Long id) {
        khachHangRepository.deleteById(id);
    }

    @Override
    public KhachHang toggleTrangThai(Long khachHangId) {
        Optional<KhachHang> optionalKhachHang = khachHangRepository.findById(khachHangId);
        if (optionalKhachHang.isPresent()) {
            KhachHang khachHang = optionalKhachHang.get();
            khachHang.setTrangThai(!khachHang.isTrangThai());
            return khachHangRepository.save(khachHang);
        }
        return null;
    }

    @Override
    public Page<KhachHang> getAllKhachHangByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize); // PageRequest đếm từ 0
        return khachHangRepository.findKhachHangByTrangThaiTrue(pageable);
    }


    @Override
    public KhachHang updateKhachHang(KhachHang khachHang, Long id) {
        Optional<KhachHang> khachHangOptional = khachHangRepository.findById(id);
        if(khachHangOptional.isPresent()){
            KhachHang existingKhachHang = khachHangOptional.get();
            existingKhachHang.setHoTen(khachHang.getHoTen());
            existingKhachHang.setGioiTinh(khachHang.isGioiTinh());
            existingKhachHang.setNgaySinh(khachHang.getNgaySinh());
            existingKhachHang.setSdt(khachHang.getSdt());
            existingKhachHang.setEmail(khachHang.getEmail());
            existingKhachHang.setDiaChiList(khachHang.getDiaChiList());
            existingKhachHang.setTrangThai(khachHang.isTrangThai());
            return khachHangRepository.save(existingKhachHang);
        }
        return null;
    }

    @Override
    public List<KhachHang> findBySdt(String sdt) {
        return khachHangRepository.findBySdt(sdt);
    }

    @Override
    public Page<KhachHang> findByGender(boolean gioiTinh, Pageable pageable) {
        return khachHangRepository.findByGioiTinh(gioiTinh, pageable);
    }

    @Override
    public Page<KhachHang> findByGenderAndTrangThai(boolean gioiTinh, boolean trangThai, Pageable pageable) {
        return khachHangRepository.findByGioiTinhAndTrangThai(gioiTinh, trangThai, pageable);
    }

    @Override
    public Page<KhachHang> getAllKhachHangByTrangThai(boolean trangThai, Pageable pageable) {
        return khachHangRepository.findByTrangThai(trangThai, pageable);
    }

    @Override
    public List<KhachHang> findKhachHangByNgaySinhBetween(LocalDate fromDate, LocalDate toDate) {

        return khachHangRepository.findByNgaySinhBetween(fromDate, toDate);
    }
}







