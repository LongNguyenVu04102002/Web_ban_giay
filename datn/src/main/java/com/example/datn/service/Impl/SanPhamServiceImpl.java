package com.example.datn.service.Impl;

import com.example.datn.entity.SanPham;
import com.example.datn.repository.SanPhamRepository;
import com.example.datn.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getSanPhamById(Long id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public void save(SanPham sanPham) {
        sanPhamRepository.save(sanPham);
    }

    @Override
    public void update(Long id) {
        Optional<SanPham> sanPham = sanPhamRepository.findById(id);
        if (sanPham.isPresent()) {
            SanPham sp = sanPham.get();
            sp.setTrangThai(!sp.isTrangThai());
            sanPhamRepository.save(sp);
        }
    }

    @Override
    public List<SanPham> getSanPhamsByTrangThai(boolean trangThai) {
        return sanPhamRepository.findByTrangThai(trangThai);
    }

    @Override
    public boolean findByTenAndChatLieu_ChatLieuIdAndCoGiay_CoGiayIdAndDayGiay_DayGiayIdAndDeGiay_DeGiayIdAndLotGiay_LotGiayIdAndMuiGiay_MuiGiayIdAndThuongHieu_ThuongHieuId(String ten, Long chatLieuId, Long coGiayId, Long dayGiayId, Long deGiayId, Long lotGiayId, Long muiGiayId, Long thuongHieuId) {
        List<SanPham> sanPhamList = sanPhamRepository.findByTenAndChatLieu_ChatLieuIdAndCoGiay_CoGiayIdAndDayGiay_DayGiayIdAndDeGiay_DeGiayIdAndLotGiay_LotGiayIdAndMuiGiay_MuiGiayIdAndThuongHieu_ThuongHieuId(ten, chatLieuId, coGiayId, dayGiayId, deGiayId, lotGiayId, muiGiayId, thuongHieuId);
        if (sanPhamList.size() > 0) {
            return true;
        }
        return false;
    }

}
