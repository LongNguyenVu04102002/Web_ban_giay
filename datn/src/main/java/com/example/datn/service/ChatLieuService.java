package com.example.datn.service;

import com.example.datn.entity.ChatLieu;

import java.util.List;

public interface ChatLieuService {

    List<ChatLieu> getAllChatLieu();

    ChatLieu getById(Long id);

    void saveChatLieu(ChatLieu chatLieu);

    void deleteChatLieu(Long id);
    
}
