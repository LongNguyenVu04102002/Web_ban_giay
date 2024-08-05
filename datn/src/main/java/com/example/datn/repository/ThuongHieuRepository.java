package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long> {

    List<ThuongHieu> findByTrangThai(boolean trangThai);
}
