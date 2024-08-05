package com.example.datn.service.Impl;


import com.example.datn.entity.HinhAnh;
import com.example.datn.repository.HinhAnhRepository;
import com.example.datn.service.HinhAnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    }
=======
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
    public void saveAndUpdateOne(HinhAnh hinhAnh) {
        hinhAnhRepository.save(hinhAnh);
    }
}
>>>>>>> parent of f4e9d10 (update)
