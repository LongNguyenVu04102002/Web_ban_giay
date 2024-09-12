package com.example.datn.service.Impl;

import com.example.datn.entity.DiaChi;
import com.example.datn.entity.KhachHang;
import com.example.datn.repository.DiaChiRepository;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.service.DiaChiService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaChiImpl implements DiaChiService {

    @Autowired
    DiaChiRepository diaChiRepository;

    @Autowired
    KhachHangRepository khachHangRepository;
    @Override
    public void deleteDiaChi(Long diaChiId) {
        Optional<DiaChi> diaChiOptional = diaChiRepository.findById(diaChiId);

        if (diaChiOptional.isPresent()) {
            DiaChi diaChi = diaChiOptional.get();
            KhachHang khachHang = diaChi.getKhachHang();

            // Đếm số lượng địa chỉ của khách hàng
            long diaChiCount = diaChiRepository.countByKhachHangId(khachHang.getKhachHangId());

            // Kiểm tra nếu khách hàng có nhiều hơn 1 địa chỉ thì mới cho phép xóa
            if (diaChiCount > 1) {
                diaChiRepository.deleteById(diaChiId);
            } else {
                throw new IllegalStateException("Khách hàng phải có ít nhất một địa chỉ.");
            }
        } else {
            throw new IllegalArgumentException("Địa chỉ không tồn tại.");
        }
    }


    @Override
    public DiaChi toggleTrangThai(Long diaChiId) {
        Optional<DiaChi> optionalDiaChi = diaChiRepository.findById(diaChiId);
        if(optionalDiaChi.isPresent()){
            DiaChi diaChi = optionalDiaChi.get();
            diaChi.setTrangThai(!diaChi.isTrangThai());
            return diaChiRepository.save(diaChi);
        }
        return null;
    }

    @Override
    public DiaChi findById(Long diaChiId) {
        return diaChiRepository.findById(diaChiId).orElse(null);
    }

    @Override
    public void save(DiaChi diaChi) {
        diaChiRepository.save(diaChi);
    }

    @Override
    public List<DiaChi> findByKhachHangId(Long khachHangId) {
        return diaChiRepository.findAllByKhachHangId(khachHangId);
    }

}
