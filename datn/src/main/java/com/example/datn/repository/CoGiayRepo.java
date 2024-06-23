package com.example.datn.repository;

import com.example.datn.entity.CoGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoGiayRepo extends JpaRepository<CoGiay, Long> {
}
