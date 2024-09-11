package com.example.datn.service.Impl;

import com.example.datn.entity.MuiGiay;
import com.example.datn.repository.MuiGiayRepository;
import com.example.datn.service.MuiGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuiGiayServiceImpl implements MuiGiayService {

    @Autowired
    private MuiGiayRepository muiGiayRepository;

    @Override
    public List<MuiGiay> getAllMuiGiay() {
        return muiGiayRepository.findAll();
    }

    @Override
    public MuiGiay getById(Long id) {
        return muiGiayRepository.findById(id).orElse(null);
    }

    @Override
    public void saveMuiGiay(MuiGiay muiGiay) {
        muiGiay.setTen(muiGiay.getTen().trim());
        if(muiGiay.getMuiGiayId() ==  null){
            muiGiay.setTrangThai(true);
        }
        muiGiayRepository.save(muiGiay);
    }

    @Override
    public void deleteMuiGiay(Long id) {
        muiGiayRepository.deleteById(id);
    }

    @Override
    public boolean isTenExists(String ten) {
        return muiGiayRepository.findByTen(ten).isPresent();
    }

    @Override
    public boolean isTenExistsForUpdate(String ten, Long id) {
        List<MuiGiay> muiGiayList = muiGiayRepository.findAllByTenAndMuiGiayIdNot(ten, id);
        return !muiGiayList.isEmpty();
    }

    @Override
    public List<MuiGiay> findAllByTrangThaiTrue() {
        return muiGiayRepository.findAllByTrangThaiTrue();
    }
}
