package com.example.datn.repository;

import com.example.datn.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KichThuocRepo extends JpaRepository<KichThuoc, Long> {
}
