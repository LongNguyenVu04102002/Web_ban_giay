package com.example.datn.config.teamplate;

import com.example.datn.entity.GioHang;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.NhanVien;
import com.example.datn.entity.PhuongThucThanhToan;
import com.example.datn.repository.GioHangRepository;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.repository.NhanVienRepository;
import com.example.datn.repository.PhuongThucThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        createUserIfNotExists("admin@gmail.com", "123456", "Nguyễn Thành Vinh", "0123456789", "ADMIN", "admin");
        createUserIfNotExists("staff@gmail.com", "123123", "Nguyễn Thị Hằng", "0123454321", "STAFF", "staff");

        if (khachHangRepository.findByEmail("member@gmail.com") == null) {
            KhachHang khachHang = new KhachHang();
            khachHang.setEmail("member@gmail.com");
            khachHang.setPassword("123456");
            khachHang.setSdt("0987654321");
            khachHang.setNgaySinh(LocalDate.ofEpochDay(2024 - 07 - 30));
            khachHang.setTrangThai(true);
            khachHang.setGioiTinh(true);
            khachHang.setHoTen("Nguyễn Thị Hằng");
            khachHangRepository.save(khachHang);
        }

        for (long i = 1L; i <= 10L; i++) {
            Optional<GioHang> gioHang = gioHangRepository.findById(i);
            if (gioHang.isEmpty()) {
                GioHang gh = new GioHang();
                gh.setGioHangId(i);
                gh.setTienGiam(BigDecimal.ZERO);
                gioHangRepository.save(gh);
            }
        }

        PhuongThucThanhToan phuongThucThanhToan1 = new PhuongThucThanhToan();
        phuongThucThanhToan1.setPhuongThucThanhToanId(1L);
        phuongThucThanhToan1.setTen("Tiền mặt");
        phuongThucThanhToan1.setTrangThai(true);
        phuongThucThanhToanRepository.save(phuongThucThanhToan1);

        PhuongThucThanhToan phuongThucThanhToan2 = new PhuongThucThanhToan();
        phuongThucThanhToan2.setPhuongThucThanhToanId(2L);
        phuongThucThanhToan2.setTen("Thanh toán khi nhận hàng");
        phuongThucThanhToan2.setTrangThai(true);
        phuongThucThanhToanRepository.save(phuongThucThanhToan2);
    }

    private void createUserIfNotExists(String email, String password, String hoTen, String sdt, String role, String maNhanVien) {
        if (nhanVienRepository.findByEmail(email) == null) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setEmail(email);
            nhanVien.setPassword(passwordEncoder.encode(password));
            nhanVien.setHoTen(hoTen);
            nhanVien.setSdt(sdt);
            nhanVien.setRole(role);
            nhanVien.setTrangThai(true);
            nhanVien.setGioiTinh(true);
            nhanVien.setNgaySinh(LocalDate.of(2000, 8, 29));
            nhanVien.setMaNhanVien(maNhanVien);
            nhanVienRepository.save(nhanVien);
        }
    }
}