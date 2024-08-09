package com.example.datn.service;

import com.example.datn.entity.CoGiay;
import com.example.datn.entity.LotGiay;

import java.util.List;

public interface LotGiayService {

    List<LotGiay> getAllLotGiay();

    LotGiay getById(Long id);

    void saveLotGiay(LotGiay lotGiay);

    void deleteLotGiay(Long id);

    List<LotGiay> getLotGiaysByTrangThai(boolean trangThai);
    
}
