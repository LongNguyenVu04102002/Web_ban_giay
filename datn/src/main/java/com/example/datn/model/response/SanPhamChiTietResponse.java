package com.example.datn.model.response;

import com.example.datn.dto.HinhAnhDTO;
import com.example.datn.dto.SanPhamChiTietDTO;
import com.example.datn.entity.HinhAnh;
import com.example.datn.entity.SanPhamChiTiet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SanPhamChiTietResponse {
<<<<<<< HEAD
    private SanPhamChiTiet sanPhamChiTiet;
    private String dataImg;
=======
    private List<SanPhamChiTiet> sanPhamChiTietList;
    private List<HinhAnhDTO> hinhAnhDTOList;
    private List<SanPhamChiTietDTO> sanPhamChiTietDTOList;
    private List<HinhAnh> hinhAnhList;
    private List<MultipartFile> logoFiles;
>>>>>>> KhachHang_LongFix
}
