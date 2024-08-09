package com.example.datn.service;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.KichThuoc;

import java.util.List;

public interface ChatLieuService {

    List<ChatLieu> getAllChatLieu();

    ChatLieu getById(Long id);

    void saveChatLieu(ChatLieu chatLieu);

    void deleteChatLieu(Long id);

    boolean isTenExists(String ten);

    boolean isTenExistsForUpdate(String ten, Long id);

    List<ChatLieu> getChatLieusByTrangThai(boolean trangThai);
    
}
