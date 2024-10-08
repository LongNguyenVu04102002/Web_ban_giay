package com.example.datn.service;

import com.example.datn.entity.LotGiay;

import java.util.List;

public interface LotGiayService {

    List<LotGiay> getAllLotGiay();

    LotGiay getById(Long id);

    void saveLotGiay(LotGiay lotGiay);

    void deleteLotGiay(Long id);

    boolean isTenExists(String ten);

    boolean isTenExistsForUpdate(String ten, Long id);

    List<LotGiay> findAllByTrangThaiTrue();
    
}
