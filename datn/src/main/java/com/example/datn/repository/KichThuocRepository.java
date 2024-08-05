package com.example.datn.repository;

import com.example.datn.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KichThuocRepository extends JpaRepository<KichThuoc, Long> {

    List<KichThuoc> findByTrangThai(boolean trangThai);
}
