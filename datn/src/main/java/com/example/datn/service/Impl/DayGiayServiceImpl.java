package com.example.datn.service.Impl;

import com.example.datn.entity.DayGiay;
import com.example.datn.repository.DayGiayRepository;
import com.example.datn.service.DayGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayGiayServiceImpl implements DayGiayService {

    @Autowired
    private DayGiayRepository dayGiayRepository;

    @Override
    public List<DayGiay> getAllDayGiay() {
        return dayGiayRepository.findAll();
    }

    @Override
    public DayGiay getById(Long id) {
        return dayGiayRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDayGiay(DayGiay dayGiay) {
        dayGiayRepository.save(dayGiay);
    }

    @Override
    public void deleteDayGiay(Long id) {
        dayGiayRepository.deleteById(id);
    }

    @Override
    public List<DayGiay> getDayGiaysByTrangThai(boolean trangThai) {
        return dayGiayRepository.findByTrangThai(trangThai);
    }

}
