package com.example.datn.service;

import com.example.datn.entity.DeGiay;

import java.util.List;

public interface DeGiayService {
    List<DeGiay> getAllDeGiay();

    DeGiay getById(Long id);

    void saveDeGiay(DeGiay deGiay);

    void deleteDeGiay(Long id);

    boolean isTenExists(String ten);

    boolean isTenExistsForUpdate(String ten, Long id);

    List<DeGiay> getDeGiaysByTrangThai(boolean trangThai);
}
