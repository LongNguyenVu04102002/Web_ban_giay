package com.example.datn.service;

import com.example.datn.dto.CartItem;
import com.example.datn.entity.PhieuGiamGia;
import com.example.datn.model.response.PhieuGiamGiaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhieuGiamGiaService {

    List<PhieuGiamGia> getAll();

    PhieuGiamGia getById(Long id);

    void save(PhieuGiamGia phieuGiamGia);

    void update(Long id);

    PhieuGiamGia tonggleTrangThaiGiamGia(Long id);

    PhieuGiamGiaResponse apPhieu(String maGiamGia, List<CartItem> cartItem);

}
