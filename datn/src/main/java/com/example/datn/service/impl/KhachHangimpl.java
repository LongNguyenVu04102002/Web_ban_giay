package com.example.datn.service.impl;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.repository.DiaChiRepository;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        // Giả sử bạn có một repository để truy xuất dữ liệu từ cơ sở dữ liệu
        // Ví dụ sử dụng JpaRepository
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
    public KhachHang findById(Long khachHangId) {
        Optional<KhachHang> khachHangOptional = khachHangRepository.findById(khachHangId);
        return khachHangOptional.orElse(null);
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
        // Implement logic to find customers by SDT (assuming you have a repository or DAO)
        return khachHangRepository.findBySdt(sdt);
    }

}



