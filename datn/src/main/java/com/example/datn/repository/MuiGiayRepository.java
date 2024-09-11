package com.example.datn.repository;

import com.example.datn.entity.MuiGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MuiGiayRepository extends JpaRepository<MuiGiay, Long> {
    List<MuiGiay> findAllByTenAndMuiGiayIdNot(String ten, Long id);

    Optional<Object> findByTen(String ten);

    List<MuiGiay> findAllByTrangThaiTrue();

}
