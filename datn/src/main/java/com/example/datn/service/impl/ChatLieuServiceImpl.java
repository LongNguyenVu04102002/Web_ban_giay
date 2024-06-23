package com.example.datn.service.impl;

import com.example.datn.entity.ChatLieu;
import com.example.datn.repository.ChatLieuRepo;
import com.example.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatLieuServiceImpl implements IService<ChatLieu> {

    @Autowired
    private ChatLieuRepo repo;

    @Override
    public List<ChatLieu> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<ChatLieu> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public ChatLieu addOrUpdate(ChatLieu chatLieu) {
        return repo.save(chatLieu);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<ChatLieu> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repo.findAll(pageable);
    }
}
