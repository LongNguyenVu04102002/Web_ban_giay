package com.example.datn.service.Impl;

import com.example.datn.entity.ThuongHieu;
import com.example.datn.repository.ThuongHieuRepository;
import com.example.datn.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> getAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu getById(Long id) {
        return thuongHieuRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ThuongHieu thuongHieu) {
        thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public void delete(Long id) {
       thuongHieuRepository.deleteById(id);
    }

    @Override
    public List<ThuongHieu> getThuongHieusByTrangThai(boolean trangThai) {
        return thuongHieuRepository.findByTrangThai(trangThai);
    }

}
