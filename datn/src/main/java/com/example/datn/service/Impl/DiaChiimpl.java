package com.example.datn.service.Impl;

import com.example.datn.entity.DiaChi;
import com.example.datn.repository.DiaChiRepository;
import com.example.datn.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaChiimpl implements DiaChiService {

    @Autowired
    DiaChiRepository diaChiRepository;

    @Override
    public List<DiaChi> getAllDiaChi() {
        return diaChiRepository.findAll();
    }

    @Override
    public DiaChi saveDiaChi(DiaChi diaChi) {
        return diaChiRepository.save(diaChi);
    }

    @Override
    public void deleteDiaChi(Long id) {
        diaChiRepository.deleteById(id);
    }

    @Override
    public Optional<DiaChi> getDiaChiById(Long id) {
        return diaChiRepository.findById(id);
    }

    @Override
    public DiaChi updateDiaChi(DiaChi updatedDiaChi) {
        DiaChi existingDiaChi = diaChiRepository.findById(updatedDiaChi.getDiaChiId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa chỉ có ID: " + updatedDiaChi.getDiaChiId()));

        existingDiaChi.setDiaChi(updatedDiaChi.getDiaChi());
        existingDiaChi.setXa(updatedDiaChi.getXa());
        existingDiaChi.setHuyen(updatedDiaChi.getHuyen());
        existingDiaChi.setThanhPho(updatedDiaChi.getThanhPho());

        return diaChiRepository.save(existingDiaChi);
    }
}
