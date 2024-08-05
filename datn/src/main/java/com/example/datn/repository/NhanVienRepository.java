package com.example.datn.repository;

import com.example.datn.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    Optional<NhanVien> findBySdt(String sdt);
    Optional<NhanVien> findByEmail(String email);
    @Query("SELECT nv FROM NhanVien nv ORDER BY nv.nhanVienId DESC")
    List<NhanVien> findAll();
    NhanVien getAllByEmail(String email);
}
