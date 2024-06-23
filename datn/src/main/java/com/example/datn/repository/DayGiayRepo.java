package com.example.datn.repository;

import com.example.datn.entity.DayGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayGiayRepo extends JpaRepository<DayGiay, Long> {
}
