package com.example.datn.service.Impl;

import com.example.datn.entity.KhachHang;
import com.example.datn.model.request.SignupRequest;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.service.KhachHangService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll((Sort.by(Sort.Direction.DESC, "khachHangId")));
    }

    @Override
    public KhachHang getById(Long id) {
        return khachHangRepository.findById(id).orElse(null);
    }


    @Override
    public KhachHang save(KhachHang khachHang) {
        khachHang.setPassword(passwordEncoder.encode(khachHang.getSdt()));
        return khachHangRepository.save(khachHang);
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
    @Transactional
    public KhachHang update(KhachHang khachHang, BindingResult result) {
        // Tìm khách hàng hiện tại từ cơ sở dữ liệu
        KhachHang existingKhachHang = khachHangRepository.findById(khachHang.getKhachHangId()).orElseThrow();

        // Kiểm tra nếu khách hàng đã có 3 địa chỉ
        if (existingKhachHang.getDiaChiList().size() >= 3) {
            result.rejectValue("diaChiList", "error.khachHang", "Khách hàng đã có tối đa 3 địa chỉ. Không thể cập nhật thêm.");
            return existingKhachHang;
        }

        // Xóa tất cả các địa chỉ cũ
        existingKhachHang.getDiaChiList().clear();

        // Thêm lại các địa chỉ hợp lệ mới
        existingKhachHang.getDiaChiList().addAll(khachHang.getDiaChiList());

        // Lưu khách hàng với danh sách địa chỉ đã được cập nhật
        return khachHangRepository.save(existingKhachHang);
    }



    @Override
    public boolean isSdtExist(String sdt) {
        return khachHangRepository.existsBySdt(sdt);
    }

    @Override
    public boolean isEmailExist(String email) {
        return khachHangRepository.existsByEmail(email);
    }

    public boolean isPhoneNumberDuplicate(String sdt, Long excludeId) {
        return khachHangRepository.existsBySdtAndKhachHangIdNot(sdt, excludeId);
    }

    public boolean isEmailDuplicate(String email, Long excludeId) {
        return khachHangRepository.existsByEmailAndKhachHangIdNot(email, excludeId);
    }

    public void register(SignupRequest signupRequest) {
        KhachHang khachHang = KhachHang.builder()
                .email(signupRequest.getEmail())
                .hoTen(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .trangThai(true)
                .gioiTinh(true)
                .build();
        khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang findByResetToken(String token) {
        return khachHangRepository.findByResetToken(token);
    }

    @Override
    public KhachHang findByEmailAndSdt(String email, String sdt) {
        return khachHangRepository.findByEmailAndSdt(email, sdt);
    }

    @Override
    public KhachHang login(String email, String password) {
        KhachHang khachHang = khachHangRepository.findKhachHangByEmailAndPassword(email, password);
        if(khachHang != null) {
            if (!khachHang.isTrangThai()) {
                return null;
            }
            return khachHang;
        }
        return null;
    }

    @Override
    public List<KhachHang> findKhachHangByTrangThaiTrue() {
        return khachHangRepository.findKhachHangByTrangThaiTrue();
    }

}