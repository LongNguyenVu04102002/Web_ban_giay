package com.example.datn.service.Impl;

import com.example.datn.entity.KhachHang;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
//import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> 4b39c43c0139477d57559ecd982e34611c81893f
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;
<<<<<<< HEAD
=======
//    @Autowired
//    private PasswordEncoder passwordEncoder;
>>>>>>> 4b39c43c0139477d57559ecd982e34611c81893f

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll((Sort.by(Sort.Direction.DESC, "khachHangId")));
    }

    @Override
    public KhachHang getById(Long id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public KhachHang  save(KhachHang khachHang) {

        if (isSdtExist(khachHang.getSdt())) {
            throw new RuntimeException("Số điện thoại đã tồn tại");
        }
        if (isEmailExist(khachHang.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
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
    public KhachHang update(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
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


<<<<<<< HEAD
=======
        KhachHang khachHang = new KhachHang();
        khachHang.setEmail(email);
//        khachHang.setMatKhau(passwordEncoder.encode(password));
        khachHang.setHoTen(hoTen);
        khachHang.setSdt(sdt);
        khachHang.setNgaySinh(ngaySinh);
        khachHang.setGioiTinh(gioiTinh);
        khachHang.setTrangThai(trangThai);

        khachHangRepository.save(khachHang);
    }
>>>>>>> 4b39c43c0139477d57559ecd982e34611c81893f
}
