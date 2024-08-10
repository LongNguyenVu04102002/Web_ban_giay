package com.example.datn.repository;

import com.example.datn.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    List<SanPham> findAllByThuongHieu_ThuongHieuId(Long id);

    List<SanPham> findByTrangThai(boolean trangThai);

    List<SanPham> findByTenAndChatLieu_ChatLieuIdAndCoGiay_CoGiayIdAndDayGiay_DayGiayIdAndDeGiay_DeGiayIdAndLotGiay_LotGiayIdAndMuiGiay_MuiGiayIdAndThuongHieu_ThuongHieuId(String ten, Long chatLieuId, Long coGiayId, Long dayGiayId, Long deGiayId, Long lotGiayId, Long muiGiayId, Long thuongHieuId);
}
