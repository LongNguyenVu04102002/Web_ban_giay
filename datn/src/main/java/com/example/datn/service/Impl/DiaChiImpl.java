package com.example.datn.service.Impl;

import com.example.datn.repository.DiaChiRepository;
import com.example.datn.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaChiImpl implements DiaChiService {

    @Autowired
    DiaChiRepository diaChiRepository;
    @Override
    public void deleteDiaChi(Long diaChiId) {
        diaChiRepository.deleteById(diaChiId);
    }
}
