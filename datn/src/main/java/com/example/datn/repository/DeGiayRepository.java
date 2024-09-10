package com.example.datn.repository;

import com.example.datn.entity.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeGiayRepository extends JpaRepository<DeGiay, Long> {

    Optional<Object> findByTen(String ten);

    List<DeGiay> findAllByTenAndDeGiayIdNot(String ten, Long id);

    List<DeGiay> findAllByTrangThaiTrue();

}
