package com.example.datn.repository;

import com.example.datn.entity.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeGiayRepo extends JpaRepository<DeGiay, Long> {
}
