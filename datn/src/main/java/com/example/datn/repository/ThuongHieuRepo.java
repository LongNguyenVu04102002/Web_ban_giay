package com.example.datn.repository;

import com.example.datn.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThuongHieuRepo extends JpaRepository<ThuongHieu, Long> {
}
