package com.example.datn.repository;

import com.example.datn.entity.ERole;
import com.example.datn.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VaiTroRepository extends JpaRepository<VaiTro, Long> {
    Optional<VaiTro> findByName(ERole name);

}
