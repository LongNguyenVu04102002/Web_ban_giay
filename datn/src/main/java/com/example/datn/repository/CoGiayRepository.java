package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.CoGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoGiayRepository extends JpaRepository<CoGiay, Long> {

    List<CoGiay> findByTrangThai(boolean trangThai);

}
