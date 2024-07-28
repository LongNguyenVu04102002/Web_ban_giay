package com.example.datn.service.impl;

import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.HinhAnhRepository;
import com.example.datn.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HinhAnhServiceImpl implements HinhAnhService {

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Override
    public List<HinhAnh> getAll() {
        return hinhAnhRepository.findAll();
    }

    @Override
    public HinhAnh getById(Long id) {
        return hinhAnhRepository.findById(id).orElse(null);
    }

    @Override
    public void save(List<HinhAnh> hinhAnhList) {
        hinhAnhRepository.saveAll(hinhAnhList);
    }

    @Override
    public void add(HinhAnh hinhAnh) {
        hinhAnhRepository.save(hinhAnh);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getImageBySanPhamChiTietIdWithPriority(Long sanPhamChiTietId, Integer priority) {
        HinhAnh hinhAnh = hinhAnhRepository.findBySanPhamChiTietSanPhamChiTietIdAndUuTien(sanPhamChiTietId, priority)
                .orElseThrow(() -> new RuntimeException("Image not found with priority " + priority));
        return hinhAnh.getDataImg();
    }

    @Override
    public void saveAndUpdateOne(HinhAnh hinhAnh) {
        hinhAnhRepository.save(hinhAnh);
    }

    @Override
    public List<HinhAnh> getImagesBySanPhamChiTietId(Long sanPhamChiTietId) {
        return hinhAnhRepository.findBySanPhamChiTietSanPhamChiTietId(sanPhamChiTietId);
    }

    @Override
    public void saveOrUpdateImages(SanPhamChiTiet spct, List<byte[]> imageDatas) {
        // Lấy danh sách các hình ảnh hiện tại của sản phẩm chi tiết
        List<HinhAnh> existingHinhAnhs = hinhAnhRepository.findBySanPhamChiTiet(spct);

        // Nếu số lượng hình ảnh mới ít hơn số lượng hình ảnh hiện tại, thì cập nhật từng hình ảnh hiện tại
        for (int i = 0; i < existingHinhAnhs.size() && i < imageDatas.size(); i++) {
            HinhAnh hinhAnh = existingHinhAnhs.get(i);
            hinhAnh.setDataImg(imageDatas.get(i));
            hinhAnhRepository.save(hinhAnh);
        }

        // Nếu số lượng hình ảnh mới nhiều hơn số lượng hình ảnh hiện tại, thêm các hình ảnh mới vào cơ sở dữ liệu
        for (int i = existingHinhAnhs.size(); i < imageDatas.size(); i++) {
            HinhAnh hinhAnh = new HinhAnh();
            hinhAnh.setSanPhamChiTiet(spct);
            hinhAnh.setDataImg(imageDatas.get(i));
            hinhAnhRepository.save(hinhAnh);
        }

        // Nếu số lượng hình ảnh hiện tại nhiều hơn số lượng hình ảnh mới, xóa các hình ảnh thừa
        for (int i = imageDatas.size(); i < existingHinhAnhs.size(); i++) {
            hinhAnhRepository.delete(existingHinhAnhs.get(i));
        }
    }

    @Override
    public void deleteById(Long id) {
        hinhAnhRepository.deleteById(id);
    }
}
