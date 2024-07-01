package com.example.datn.service;
import com.example.datn.entity.NhanVien;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface NhanVienService {
   List<NhanVien> getAllNhanhVien();
   Optional<NhanVien> getNhanVienById(Long id);
   NhanVien saveNhanVien(NhanVien nhanVien);
   void deleteNhanVien(Long id);
   NhanVien toggleTrangThai(Long nhanVienId);
   Page<NhanVien> getAllNhanVienByPage(int pagenumber,int pageSize);
   List<NhanVien> findByHoTen(String hoTen);
   List<NhanVien> findByHoTenContainingAndGioiTinh(String hoTen, boolean gioiTinh);
    List<NhanVien> findByGioiTinh(boolean gioiTinh) ;
NhanVien updateNhanVien(NhanVien nhanVien);

}
