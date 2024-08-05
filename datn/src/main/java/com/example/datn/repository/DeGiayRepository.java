package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeGiayRepository extends JpaRepository<DeGiay, Long> {

    List<DeGiay> findByTrangThai(boolean trangThai);

}
