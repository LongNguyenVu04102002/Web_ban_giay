package com.example.datn.service.impl;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.repository.DiaChiRepository;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private DiaChiRepository diaChiRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getById(Long id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public void save(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
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
<<<<<<< HEAD

    @Override
    public void changeDiaChiStatus(Long diaChiId, boolean newStatus) {
        // Tìm địa chỉ để thay đổi trạng thái
        DiaChi diaChi = diaChiRepository.findById(diaChiId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy địa chỉ với id " + diaChiId));

        // Thay đổi trạng thái của địa chỉ
        diaChi.setTrangThai(newStatus);

        // Lưu lại thay đổi
        diaChiRepository.save(diaChi);
    }


    @Override
    public List<KhachHang> searchKhachHang(String sdt, String hoTen, String email) {
        return List.of();
    }
=======
>>>>>>> parent of 04f5341 (fix lại)
}
