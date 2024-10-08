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
        dayGiay.setTen(dayGiay.getTen().trim());
        if (dayGiay.getDayGiayId() == null) {
            dayGiay.setTrangThai(true);
        }
        dayGiayRepository.save(dayGiay);
    }

    @Override
    public void deleteDayGiay(Long id) {
        dayGiayRepository.deleteById(id);
    }

    @Override
    public boolean isTenExists(String ten) {
        return dayGiayRepository.findByTen(ten).isPresent();
    }

    @Override
    public boolean isTenExistsForUpdate(String ten, Long id) {
        List<DayGiay> dayGiayList = dayGiayRepository.findAllByTenAndDayGiayIdNot(ten, id);
        return !dayGiayList.isEmpty();
    }

    @Override
    public List<DayGiay> findAllByTrangThaiTrue() {
        return dayGiayRepository.findAllByTrangThaiTrue();
    }

}
