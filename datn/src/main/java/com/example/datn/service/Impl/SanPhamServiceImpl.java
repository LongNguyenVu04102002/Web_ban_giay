package com.example.datn.service.Impl;

import com.example.datn.dto.SanPhamHomeDTO;
import com.example.datn.entity.SanPham;
import com.example.datn.repository.SanPhamRepository;
import com.example.datn.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        sanPham.setTen(sanPham.getTen().trim());
        if(sanPham.getSanPhamId() == null){
            sanPham.setTrangThai(true);
        }
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
    public boolean findByTenAndChatLieu_ChatLieuIdAndCoGiay_CoGiayIdAndDayGiay_DayGiayIdAndDeGiay_DeGiayIdAndLotGiay_LotGiayIdAndMuiGiay_MuiGiayIdAndThuongHieu_ThuongHieuId(String ten, Long chatLieuId, Long coGiayId, Long dayGiayId, Long deGiayId, Long lotGiayId, Long muiGiayId, Long thuongHieuId) {
        List<SanPham> sanPhamList = sanPhamRepository.findByTenAndChatLieu_ChatLieuIdAndCoGiay_CoGiayIdAndDayGiay_DayGiayIdAndDeGiay_DeGiayIdAndLotGiay_LotGiayIdAndMuiGiay_MuiGiayIdAndThuongHieu_ThuongHieuId(ten, chatLieuId, coGiayId, dayGiayId, deGiayId, lotGiayId, muiGiayId, thuongHieuId);
        if (!sanPhamList.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Page<SanPhamHomeDTO> getSanPhamForHomePage(Pageable pageable) {
        return sanPhamRepository.getSanPhamForHomePage(PageRequest.of(0, 8));
    }

    @Override
    public Page<SanPhamHomeDTO> getSanPhamForShopPage(Long thuongHieuId, Long kichThuocId, Long mauSacId, String keyword, Pageable pageable) {

        return sanPhamRepository.findSanPhamForShopPage(thuongHieuId, kichThuocId, mauSacId, keyword, pageable);
    }

    @Override
    public List<SanPham> findAllByTrangThaiTrue() {
        return sanPhamRepository.findAllByTrangThaiTrue();
    }


}
