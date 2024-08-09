package com.example.datn.service;

import com.example.datn.entity.DayGiay;

import java.util.List;

public interface DayGiayService {

    List<DayGiay> getAllDayGiay();

    DayGiay getById(Long id);

    void saveDayGiay(DayGiay dayGiay);

    void deleteDayGiay(Long id);

}
