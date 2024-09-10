package com.example.datn.service.Impl;

import com.example.datn.entity.ChatLieu;
import com.example.datn.repository.ChatLieuRepository;
import com.example.datn.service.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {

    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @Override
    public List<ChatLieu> getAllChatLieu() {
        return chatLieuRepository.findAll();
    }

    @Override
    public ChatLieu getById(Long id) {
        return chatLieuRepository.findById(id).orElse(null);
    }

    @Override
    public void saveChatLieu(ChatLieu chatLieu) {
        chatLieu.setTen(chatLieu.getTen().trim());
        if(chatLieu.getChatLieuId() == null){
            chatLieu.setTrangThai(true);
        }
        chatLieuRepository.save(chatLieu);
    }

    @Override
    public void deleteChatLieu(Long id) {
        chatLieuRepository.deleteById(id);
    }

    @Override
    public boolean isTenExists(String ten) {
        return chatLieuRepository.findByTen(ten).isPresent();
    }

    @Override
    public boolean isTenExistsForUpdate(String ten, Long id) {
        List<ChatLieu> chatLieuList = chatLieuRepository.findAllByTenAndChatLieuIdNot(ten, id);
        return !chatLieuList.isEmpty();
    }

    @Override
    public List<ChatLieu> findAllByTrangThaiTrue() {
        return chatLieuRepository.findAllByTrangThaiTrue();
    }

}
