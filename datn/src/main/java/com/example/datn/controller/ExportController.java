package com.example.datn.controller;
import com.example.datn.service.ExcelExportService;
import com.example.datn.entity.KhachHang;
import com.example.datn.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @GetMapping("/khachhang")
    public ResponseEntity<InputStreamResource> exportKhachHang() throws IOException {
        List<KhachHang> khachHangList = khachHangRepository.findAll();
        ByteArrayOutputStream baos = excelExportService.exportKhachHangToExcel(khachHangList);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=khachhang.xlsx");

        return new ResponseEntity<>(new InputStreamResource(bais), headers, HttpStatus.OK);
    }
}
