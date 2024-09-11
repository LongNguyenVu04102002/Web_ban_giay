package com.example.datn.repository;

import com.example.datn.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KichThuocRepository extends JpaRepository<KichThuoc, Long> {
    Optional<Object> findByTen(String ten);

    List<KichThuoc> findAllByTenAndKichThuocIdNot(String ten, Long id);

    List<KichThuoc> findAllByTrangThaiTrue();

}
