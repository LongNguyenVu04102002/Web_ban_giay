package com.example.datn.repository;

import com.example.datn.entity.DotGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DotGiamGiaRepo extends JpaRepository<DotGiamGia, Long> {
}
