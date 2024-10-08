package com.example.datn.service.Impl;

import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.repository.HinhAnhRepository;
import com.example.datn.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void saveOrUpdateImages(SanPhamChiTiet sanPhamChiTiet, List<byte[]> imageDatas, Long[] imageIds) throws IOException {
        List<HinhAnh> existingImages = hinhAnhRepository.findBySanPhamChiTietSanPhamChiTietId(sanPhamChiTiet.getSanPhamChiTietId());

        // Tính toán giá trị ưu tiên cho ảnh mới
        int priority = 1;

        // Remove images that are no longer in the updated list
        List<Long> updatedImageIds = (imageIds != null) ? List.of(imageIds) : new ArrayList<>();
        for (HinhAnh hinhAnh : existingImages) {
            if (!updatedImageIds.contains(hinhAnh.getHinhAnhId())) {
                hinhAnhRepository.delete(hinhAnh);
            }
        }

        // Lưu ảnh mới hoặc cập nhật ảnh hiện có
        for (int i = 0; i < imageDatas.size(); i++) {
            Long imageId = (imageIds != null && i < imageIds.length) ? imageIds[i] : null;
            byte[] imageData = imageDatas.get(i);

            HinhAnh hinhAnh;
            if (imageId != null) {
                hinhAnh = existingImages.stream()
                        .filter(img -> img.getHinhAnhId().equals(imageId))
                        .findFirst()
                        .orElse(null);

                if (hinhAnh != null) {
                    hinhAnh.setDataImg(imageData);
                    hinhAnhRepository.save(hinhAnh);
                } else {
                    hinhAnh = new HinhAnh();
                    hinhAnh.setDataImg(imageData);
                    hinhAnh.setSanPhamChiTiet(sanPhamChiTiet);
                    hinhAnh.setUuTien(priority++); // Set priority for new images
                    hinhAnhRepository.save(hinhAnh);
                }
            } else {
                hinhAnh = new HinhAnh();
                hinhAnh.setDataImg(imageData);
                hinhAnh.setSanPhamChiTiet(sanPhamChiTiet);
                hinhAnh.setUuTien(priority++); // Set priority for new images
                hinhAnhRepository.save(hinhAnh);
            }
        }
    }

    @Override
    public void deleteImages(List<Long> imageIds) {
        for (Long imageId : imageIds) {
            hinhAnhRepository.deleteById(imageId);
        }
    }

    @Override
    public void deleteImageById(Long id) {
        Optional<HinhAnh> imageOptional = hinhAnhRepository.findById(id);

        if (imageOptional.isPresent()) {
            HinhAnh imageToDelete = imageOptional.get();
            int uuTienToDelete = imageToDelete.getUuTien();

            // Xóa ảnh
            hinhAnhRepository.deleteById(id);

            // Nếu ảnh bị xóa có uuTien khác 1, không cần cập nhật lại thứ tự uuTien
            if (uuTienToDelete == 1) {
                // Cập nhật uuTien cho ảnh tiếp theo thành 1
                List<HinhAnh> remainingImages = hinhAnhRepository.findBySanPhamChiTietSanPhamChiTietIdAndUuTienGreaterThan(
                        imageToDelete.getSanPhamChiTiet().getSanPhamChiTietId(), uuTienToDelete);

                if (!remainingImages.isEmpty()) {
                    HinhAnh firstImage = remainingImages.get(0);
                    firstImage.setUuTien(1);
                    hinhAnhRepository.save(firstImage);
                }

                // Cập nhật lại uuTien cho các ảnh còn lại, nếu cần
                for (int i = 1; i < remainingImages.size(); i++) {
                    HinhAnh img = remainingImages.get(i);
                    img.setUuTien(i + 1);
                    hinhAnhRepository.save(img);
                }
            }
        } else {
            throw new IllegalArgumentException("Image with ID " + id + " does not exist.");
        }
    }
}
