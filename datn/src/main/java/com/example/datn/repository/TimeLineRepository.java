package com.example.datn.repository;

import com.example.datn.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {

    @Query("select p from TimeLine p where p.hoaDon.hoaDonId = :hoaDonId")
    List<TimeLine> findByHoaDonId(Long hoaDonId);

}
