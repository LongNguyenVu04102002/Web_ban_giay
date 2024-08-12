package com.example.datn.service.Impl;

import com.example.datn.entity.MauSac;
import com.example.datn.repository.MauSacRepository;
import com.example.datn.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac getById(Long id) {
        return mauSacRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MauSac mauSac) {
        mauSacRepository.save(mauSac);
    }

    @Override
    public void delete(Long id) {
        mauSacRepository.deleteById(id);
    }

    @Override
    public boolean isTenExists(String ten) {
        return mauSacRepository.findByTen(ten).isPresent();
    }

    @Override
    public boolean isTenExistsForUpdate(String ten, Long id) {
        List<MauSac> mauSacList = mauSacRepository.findAllByTenAndMauSacIdNot(ten, id);
        return !mauSacList.isEmpty();
    }

    @Override
    public List<MauSac> getMauSacsByTrangThai(boolean trangThai) {
        return mauSacRepository.findByTrangThai(trangThai);
    }


}
