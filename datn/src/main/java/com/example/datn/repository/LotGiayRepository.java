package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.LotGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotGiayRepository extends JpaRepository<LotGiay, Long> {

    List<LotGiay> findByTrangThai(boolean trangThai);

}
