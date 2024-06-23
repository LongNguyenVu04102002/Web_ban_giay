package com.example.datn.repository;

import com.example.datn.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HinhAnhRepo extends JpaRepository<HinhAnh, Long> {
}
