package com.example.datn.config.teamplate;

import com.example.datn.entity.GioHang;
import com.example.datn.entity.KhachHang;
import com.example.datn.entity.NhanVien;
import com.example.datn.repository.GioHangRepository;
import com.example.datn.repository.KhachHangRepository;
import com.example.datn.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		if (nhanVienRepository.findByEmail("admin@gmail.com") == null) {
			NhanVien nhanVien = new NhanVien();
			nhanVien.setEmail("admin@gmail.com");
			nhanVien.setPassword(passwordEncoder.encode("123456"));
			nhanVien.setHoTen("Nguyễn Thành Vinh");
			nhanVien.setSdt("0123456789");
			nhanVien.setRole("ADMIN");
			nhanVien.setTrangThai(true);
			nhanVien.setGioiTinh(true);
			nhanVien.setMaNhanVien("admin");
			nhanVienRepository.save(nhanVien);
		}

		if (khachHangRepository.findByEmail("member@gmail.com") == null) {
			KhachHang khachHang = new KhachHang();
			khachHang.setEmail("member@gmail.com");
			khachHang.setPassword(passwordEncoder.encode("123456"));
			khachHang.setSdt("0987654321");
			khachHang.setNgaySinh(LocalDate.ofEpochDay(2024-07-30));
			khachHang.setTrangThai(true);
			khachHang.setGioiTinh(true);
			khachHang.setHoTen("Nguyễn Thị Hằng");
			khachHangRepository.save(khachHang);
		}

		for (long i = 1L; i <= 5L; i++) {
			Optional<GioHang> gioHang = gioHangRepository.findById(i);
			if (gioHang.isEmpty()) {
				GioHang gh = new GioHang();
				gh.setGioHangId(i);
				gh.setTienGiam(BigDecimal.ZERO);
				gioHangRepository.save(gh);
			}
		}
	}
}