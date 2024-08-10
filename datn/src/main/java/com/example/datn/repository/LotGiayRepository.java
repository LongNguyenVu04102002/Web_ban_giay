package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.CoGiay;
import com.example.datn.entity.LotGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LotGiayRepository extends JpaRepository<LotGiay, Long> {

    Optional<LotGiay> findByTen(String ten);

    List<LotGiay> findAllByTenAndLotGiayIdNot(String ten, Long lotGiayId);

    List<LotGiay> findByTrangThai(boolean trangThai);

}
