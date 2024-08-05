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
        kichThuocRepository.save(kichThuoc);
    }

    @Override
    public void delete(Long id) {
        kichThuocRepository.deleteById(id);
    }

    @Override
    public List<KichThuoc> getKichThuocsByTrangThai(boolean trangThai) {
        return kichThuocRepository.findByTrangThai(trangThai);
    }

}
