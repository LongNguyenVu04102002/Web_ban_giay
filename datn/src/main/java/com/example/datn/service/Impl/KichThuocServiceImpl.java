package com.example.datn.service.Impl;

import com.example.datn.entity.KichThuoc;
import com.example.datn.repository.KichThuocRepository;
import com.example.datn.service.KichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KichThuocServiceImpl implements KichThuocService {

    @Autowired
    private KichThuocRepository kichThuocRepository;

    @Override
    public List<KichThuoc> getAll() {
        return kichThuocRepository.findAll();
    }

    @Override
    public KichThuoc getById(Long id) {
        return kichThuocRepository.findById(id).orElse(null);
    }

    @Override
    public void save(KichThuoc kichThuoc) {
        kichThuoc.setTen(kichThuoc.getTen().trim());
        if(kichThuoc.getKichThuocId() == null){
            kichThuoc.setTrangThai(true);
        }
        kichThuocRepository.save(kichThuoc);
    }

    @Override
    public void delete(Long id) {
        kichThuocRepository.deleteById(id);
    }

    @Override
    public boolean isTenExists(String ten) {
        return kichThuocRepository.findByTen(ten).isPresent();
    }

    @Override
    public boolean isTenExistsForUpdate(String ten, Long id) {
        List<KichThuoc> kichThuocList = kichThuocRepository.findAllByTenAndKichThuocIdNot(ten, id);
        return !kichThuocList.isEmpty();
    }

    @Override
    public List<KichThuoc> findAllByTrangThaiTrue() {
        return kichThuocRepository.findAllByTrangThaiTrue();
    }

}
