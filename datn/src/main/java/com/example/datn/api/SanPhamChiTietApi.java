package com.example.datn.api;

import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.SanPhamChiTiet;
import com.example.datn.model.response.SanPhamChiTietResponse;
import com.example.datn.model.response.request.HinhAnhRequest;
import com.example.datn.model.response.request.SanPhamChiTietRequest;
import com.example.datn.service.impl.HinhAnhServiceImpl;
import com.example.datn.service.impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;


@RestController
@RequestMapping("/sanphamchitiet")
public class SanPhamChiTietApi {

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

    @Autowired
    private HinhAnhServiceImpl hinhAnhService;

    @Autowired
    private RestTemplate restTemplate;

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
        for (SanPhamChiTietRequest request : sanPhamChiTietRequestList) {
            SanPhamChiTiet sanPhamChiTiet = request.getSanPhamChiTiet();
            List<HinhAnhRequest> hinhAnhs = request.getHinhAnhs();

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
    }

}
