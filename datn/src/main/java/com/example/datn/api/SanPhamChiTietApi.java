package com.example.datn.api;

import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.model.request.HinhAnhRequest;
import com.example.datn.model.request.SanPhamChiTietRequest;
import com.example.datn.service.Impl.HinhAnhServiceImpl;
import jakarta.validation.ValidationException;
import com.example.datn.service.Impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/sanphamchitiet")
public class SanPhamChiTietApi {

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @Autowired
    private HinhAnhServiceImpl hinhAnhService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSanPhamChiTiet() {
        return sanPhamChiTietService.getAllSanPhamChiTiet();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getSanPhamChiTietById(@PathVariable Long id) {
        return sanPhamChiTietService.getSanPhamChiTietById(id);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) {
        try {
            byte[] imageData = hinhAnhService.getImageBySanPhamChiTietIdWithPriority(id, 1);
            ByteArrayResource resource = new ByteArrayResource(imageData);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg")
                    .contentLength(imageData.length)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/bienthegiay/save")
    public ResponseEntity<?> save(@RequestBody List<SanPhamChiTietRequest> sanPhamChiTietRequestList) {
        try {
            for (SanPhamChiTietRequest request : sanPhamChiTietRequestList) {
                SanPhamChiTiet sanPhamChiTiet = request.getSanPhamChiTiet();
                List<HinhAnhRequest> hinhAnhs = request.getHinhAnhs();

                validateSanPhamChiTiet(sanPhamChiTiet);

                sanPhamChiTietService.add(sanPhamChiTiet);

                for (HinhAnhRequest hinhAnhRequest : hinhAnhs) {

                    HinhAnh hinhAnh = new HinhAnh();
                    hinhAnh.setLink(hinhAnhRequest.getLink());
                    hinhAnh.setDataImg(Base64.getDecoder().decode(hinhAnhRequest.getDataImg()));
                    hinhAnh.setUuTien(hinhAnhRequest.getUuTien());
                    hinhAnh.setSanPhamChiTiet(sanPhamChiTiet);

                    hinhAnhService.add(hinhAnh);
                }
            }

            return ResponseEntity.ok(sanPhamChiTietRequestList);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private void validateSanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet) {
        if (sanPhamChiTiet.getGiaBan().compareTo(BigDecimal.valueOf(1000)) < 0) {
            throw new ValidationException("Giá bán phải lớn hơn hoặc bằng 1000.");
        }
        if (sanPhamChiTiet.getSoLuong() < 1) {
            throw new ValidationException("Số lượng phải lớn hơn hoặc bằng 1.");
        }
        if (sanPhamChiTiet.getKichThuoc() == null) {
            throw new ValidationException("Vui lòng chọn kích thước.");
        }
        if (sanPhamChiTiet.getMauSac() == null) {
            throw new ValidationException("Vui lòng chọn màu sắc.");
        }
    }

    @GetMapping("/price")
    public ResponseEntity<BigDecimal> getPrice(@RequestParam("sanPhamId") Long sanPhamId,
                                               @RequestParam("sizeId") Long sizeId,
                                               @RequestParam("colorId") Long colorId) {
        BigDecimal price = sanPhamChiTietService.getPrice(sanPhamId,sizeId,colorId);
        if (price != null) {
            return ResponseEntity.ok(price);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
