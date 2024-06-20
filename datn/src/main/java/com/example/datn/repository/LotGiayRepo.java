package com.example.datn.repository;

import com.example.datn.entity.LotGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotGiayRepo extends JpaRepository<LotGiay, Long> {
}
