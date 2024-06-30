package com.example.datn.service;

import org.springframework.http.ResponseEntity;

public interface PhieuGiamGiaService {

    ResponseEntity<?> getPhieuGiamGiaByMa(String ma);

}
