package com.example.datn.service.Impl;

import com.example.datn.entity.LotGiay;
import com.example.datn.repository.LotGiayRepository;
import com.example.datn.service.LotGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotGiayServiceImpl implements LotGiayService {

    @Autowired
    private LotGiayRepository lotGiayRepository;

    @Override
    public List<LotGiay> getAllLotGiay() {
        return lotGiayRepository.findAll();
    }

    @Override
    public LotGiay getById(Long id) {
        return lotGiayRepository.findById(id).orElse(null);
    }

    @Override
    public void saveLotGiay(LotGiay lotGiay) {
        lotGiayRepository.save(lotGiay);
    }

    @Override
    public void deleteLotGiay(Long id) {
        lotGiayRepository.deleteById(id);
    }

    @Override
    public boolean isTenExists(String ten) {
        return lotGiayRepository.findByTen(ten).isPresent();
    }

    @Override
    public boolean isTenExistsForUpdate(String ten, Long id) {
        List<LotGiay> lotGiayList = lotGiayRepository.findAllByTenAndLotGiayIdNot(ten, id);
        return !lotGiayList.isEmpty();
    }

    @Override
    public List<LotGiay> getLotGiaysByTrangThai(boolean trangThai) {
        return lotGiayRepository.findByTrangThai(trangThai);
    }

}
