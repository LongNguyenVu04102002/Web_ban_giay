package com.example.datn.api;

import com.example.datn.model.response.HoaDonResponse;
import com.example.datn.service.Impl.HoaDonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/hoadon")
public class HoaDonApi {

    @Autowired
    private HoaDonServiceImpl hoaDonService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return hoaDonService.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveHoaDon(@RequestBody HoaDonResponse hoaDonResponse) {
        return ResponseEntity.ok(hoaDonService.addHoaDon(hoaDonResponse));

    }

}
