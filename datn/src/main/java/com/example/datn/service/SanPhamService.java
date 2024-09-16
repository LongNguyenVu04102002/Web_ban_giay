package com.example.datn.service;

import com.example.datn.dto.SanPhamHomeDTO;
import com.example.datn.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAll();

    SanPham getSanPhamById(Long id);

    void save(SanPham sanPham);

    void update(Long id);

    boolean findByTenAndChatLieu_ChatLieuIdAndCoGiay_CoGiayIdAndDayGiay_DayGiayIdAndDeGiay_DeGiayIdAndLotGiay_LotGiayIdAndMuiGiay_MuiGiayIdAndThuongHieu_ThuongHieuId(String ten, Long chatLieuId, Long coGiayId, Long dayGiayId, Long deGiayId, Long lotGiayId, Long muiGiayId, Long thuongHieuId, Long sanPhamId);

    Page<SanPhamHomeDTO> getSanPhamForHomePage(Pageable pageable);

    Page<SanPhamHomeDTO> getSanPhamForShopPage(Long thuongHieuId, Long kichThuocId,
                                               Long mauSacId,
                                               String keyword,
                                               Pageable pageable);


    List<SanPham> findAllByTrangThaiTrue();
     List<SanPhamHomeDTO> getTopSellingProduct();
}
