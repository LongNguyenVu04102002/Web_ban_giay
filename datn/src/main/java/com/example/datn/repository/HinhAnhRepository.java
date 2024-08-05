package com.example.datn.repository;

import com.example.datn.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HinhAnhRepository extends JpaRepository<HinhAnh, Long> {
<<<<<<< HEAD
    Optional<HinhAnh> findBySanPhamChiTietSanPhamChiTietIdAndUuTien(Long sanPhamChiTietId, Integer uuTien);

    List<HinhAnh> findBySanPhamChiTietSanPhamChiTietId(Long sanPhamChiTietId);

    List<HinhAnh> findBySanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet);
=======
>>>>>>> parent of f4e9d10 (update)
}
