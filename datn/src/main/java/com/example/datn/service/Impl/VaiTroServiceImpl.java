package com.example.datn.service.Impl;

import com.example.datn.entity.ERole;
import com.example.datn.entity.VaiTro;
import com.example.datn.repository.VaiTroRepository;
import com.example.datn.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VaiTroServiceImpl implements VaiTroService {

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    public Optional<VaiTro> findByName(ERole name) {
        return vaiTroRepository.findByName(name);
    }

}
