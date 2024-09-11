package com.example.datn.service.Impl;

import com.example.datn.entity.CoGiay;
import com.example.datn.repository.CoGiayRepository;
import com.example.datn.service.CoGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoGiayServiceImpl implements CoGiayService {

    @Autowired
    private CoGiayRepository coGiayRepository;

    @Override
    public List<CoGiay> getAllCoGiay() {
        return coGiayRepository.findAll();
    }

    @Override
    public CoGiay getById(Long id) {
        return coGiayRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCoGiay(CoGiay coGiay) {
        coGiay.setTen(coGiay.getTen().trim());
        if(coGiay.getCoGiayId() == null){
            coGiay.setTrangThai(true);
        }
        coGiayRepository.save(coGiay);
    }

    @Override
    public void deleteCoGiay(Long id) {
        coGiayRepository.deleteById(id);
    }

    @Override
    public boolean isTenExists(String ten) {
        return coGiayRepository.findByTen(ten).isPresent();
    }

    @Override
    public boolean isTenExistsForUpdate(String ten, Long id) {
        List<CoGiay> coGiayList = coGiayRepository.findAllByTenAndCoGiayIdNot(ten, id);
        return !coGiayList.isEmpty();
    }

    @Override
    public List<CoGiay> findAllByTrangThaiTrue() {
        return coGiayRepository.findAllByTrangThaiTrue();
    }

}
