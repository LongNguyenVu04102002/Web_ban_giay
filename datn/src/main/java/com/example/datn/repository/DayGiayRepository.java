package com.example.datn.repository;

import com.example.datn.entity.DayGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DayGiayRepository extends JpaRepository<DayGiay, Long> {

    Optional<Object> findByTen(String ten);

    List<DayGiay> findAllByTenAndDayGiayIdNot(String ten, Long id);

    List<DayGiay> findAllByTrangThaiTrue();
}
