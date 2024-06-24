package com.example.datn.repository;

import com.example.datn.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

    @Query("SELECT c FROM KhachHang c WHERE c.trangThai = true")
    Page<KhachHang> findKhachHangByTrangThaiTrue(Pageable pageable);

    List<KhachHang> findBySdt(String sdt);
}
