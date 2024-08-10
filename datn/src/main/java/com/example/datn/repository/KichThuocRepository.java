package com.example.datn.repository;

import com.example.datn.entity.CoGiay;
import com.example.datn.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KichThuocRepository extends JpaRepository<KichThuoc, Long> {

    Optional<KichThuoc> findByTen(String ten);

    List<KichThuoc> findAllByTenAndKichThuocIdNot(String ten, Long kichThuocId);

    List<KichThuoc> findByTrangThai(boolean trangThai);
}
