package com.example.datn.repository;

import com.example.datn.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuRepo extends JpaRepository<ChatLieu, Long> {
}
