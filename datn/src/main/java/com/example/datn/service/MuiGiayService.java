package com.example.datn.service;

import com.example.datn.entity.CoGiay;
import com.example.datn.entity.MuiGiay;

import java.util.List;

public interface MuiGiayService {
    
    List<MuiGiay> getAllMuiGiay();

    MuiGiay getById(Long id);

    void saveMuiGiay(MuiGiay muiGiay);

    void deleteMuiGiay(Long id);

    List<MuiGiay> getMuiGiaysByTrangThai(boolean trangThai);
    
}
