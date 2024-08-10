package com.example.datn.service;

import com.example.datn.entity.CoGiay;
import com.example.datn.entity.KichThuoc;

import java.util.List;

public interface CoGiayService {
    List<CoGiay> getAllCoGiay();

    CoGiay getById(Long id);

    void saveCoGiay(CoGiay coGiay);

    void deleteCoGiay(Long id);

    boolean isTenExists(String ten);

    boolean isTenExistsForUpdate(String ten, Long id);

    List<CoGiay> getCoGiaysByTrangThai(boolean trangThai);

}
