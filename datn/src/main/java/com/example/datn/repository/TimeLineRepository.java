package com.example.datn.repository;

import com.example.datn.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {
}
