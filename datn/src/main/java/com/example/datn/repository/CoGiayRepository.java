package com.example.datn.repository;

import com.example.datn.entity.CoGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoGiayRepository extends JpaRepository<CoGiay, Long> {

    Optional<Object> findByTen(String ten);

    List<CoGiay> findAllByTenAndCoGiayIdNot(String ten, Long id);

    List<CoGiay> findByTrangThai(boolean trangThai);

}
