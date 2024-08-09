package com.example.datn.service;

import com.example.datn.entity.CoGiay;

import java.util.List;

public interface CoGiayService {
    List<CoGiay> getAllCoGiay();

    CoGiay getById(Long id);

    void saveCoGiay(CoGiay coGiay);

    void deleteCoGiay(Long id);

}
