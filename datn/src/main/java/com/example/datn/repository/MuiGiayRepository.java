package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.CoGiay;
import com.example.datn.entity.MuiGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MuiGiayRepository extends JpaRepository<MuiGiay, Long> {

    Optional<MuiGiay> findByTen(String ten);

    List<MuiGiay> findAllByTenAndMuiGiayIdNot(String ten, Long muiGiayId);

    List<MuiGiay> findByTrangThai(boolean trangThai);
}
