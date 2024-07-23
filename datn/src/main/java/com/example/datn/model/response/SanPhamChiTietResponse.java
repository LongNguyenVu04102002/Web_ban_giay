package com.example.datn.model.response;


import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.SanPhamChiTiet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class SanPhamChiTietResponse {
    private List<SanPhamChiTiet> sanPhamChiTietList;
    private List<HinhAnh> hinhAnhList;
    private List<MultipartFile> logoFiles;
}
