package com.example.datn.api;

import com.example.datn.service.Impl.PhieuGiamGiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/phieugiamgia")
public class PhieuGiamGiaApi {

    @Autowired
    private PhieuGiamGiaServiceImpl phieuGiamGiaService;

    @GetMapping("/getByMa")
    public ResponseEntity<?> getPhieuGiamGiaByMa(@RequestParam String maGiamGia){
        return phieuGiamGiaService.getPhieuGiamGiaByMa(maGiamGia);
    }

}
