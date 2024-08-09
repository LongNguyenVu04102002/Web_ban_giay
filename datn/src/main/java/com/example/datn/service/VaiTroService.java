package com.example.datn.service;

import com.example.datn.entity.ERole;
import com.example.datn.entity.VaiTro;

import java.util.Optional;

public interface VaiTroService {
    Optional<VaiTro> findByName(ERole name);

}
