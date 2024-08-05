package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.DayGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayGiayRepository extends JpaRepository<DayGiay, Long> {

    List<DayGiay> findByTrangThai(boolean trangThai);

}
