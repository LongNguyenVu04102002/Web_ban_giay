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

    List<NhanVien> findByGioiTinh(boolean gioiTinh) ;
NhanVien updateNhanVien(NhanVien nhanVien);
   List<NhanVien> findBySdtContainingAndGioiTinh(String sdt, Boolean gioiTinh);
   List<NhanVien> findBySdtContaining(String sdt);
   List<NhanVien> findByGioiTinh(Boolean gioiTinh);


}
