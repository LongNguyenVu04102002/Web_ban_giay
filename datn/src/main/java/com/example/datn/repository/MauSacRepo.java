package com.example.datn.repository;

import com.example.datn.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepo extends JpaRepository<MauSac, Long> {
}
