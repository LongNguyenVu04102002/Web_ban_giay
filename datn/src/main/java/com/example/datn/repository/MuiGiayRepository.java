package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.MuiGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MuiGiayRepository extends JpaRepository<MuiGiay, Long> {

    List<MuiGiay> findByTrangThai(boolean trangThai);
}
