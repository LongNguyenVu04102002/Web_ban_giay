package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatLieuRepository extends JpaRepository<ChatLieu, Long> {

    Optional<Object> findByTen(String ten);

    List<ChatLieu> findAllByTenAndChatLieuIdNot(String ten, Long id);

    List<ChatLieu> findByTrangThai(boolean trangThai);

}
