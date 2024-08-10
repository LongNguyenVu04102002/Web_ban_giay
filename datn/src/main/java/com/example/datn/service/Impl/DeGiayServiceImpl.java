package com.example.datn.service.Impl;

import com.example.datn.entity.DeGiay;
import com.example.datn.repository.DeGiayRepository;
import com.example.datn.service.DeGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeGiayServiceImpl implements DeGiayService {

    @Autowired
    private DeGiayRepository deGiayRepository;

    @Override
    public List<DeGiay> getAllDeGiay() {
        return deGiayRepository.findAll();
    }

    @Override
    public DeGiay getById(Long id) {
        return deGiayRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDeGiay(DeGiay deGiay) {
        deGiayRepository.save(deGiay);
    }


    @Override
    public void deleteDeGiay(Long id) {
        deGiayRepository.deleteById(id);
    }

    @Override
    public boolean isTenExists(String ten) {
        return deGiayRepository.findByTen(ten).isPresent();
    }

    @Override
    public boolean isTenExistsForUpdate(String ten, Long id) {
        List<DeGiay> deGiayList = deGiayRepository.findAllByTenAndDeGiayIdNot(ten, id);
        return !deGiayList.isEmpty();
    }

    @Override
    public List<DeGiay> getDeGiaysByTrangThai(boolean trangThai) {
        return deGiayRepository.findByTrangThai(trangThai);
    }

}