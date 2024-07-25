package com.example.datn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hinhAnh")
public class HinhAnh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hinhAnhId", nullable = false)
    private Long hinhAnhId;

    @Nationalized
    @Lob
    @Column(name = "link")
    private String link;

    @Lob
    @Column(name = "data_img")
    private byte[] dataImg;

    @Column(name = "uuTien")
    private Integer uuTien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sanPhamChiTietId")
    @JsonBackReference(value = "sanPhamChiTiet")
    private SanPhamChiTiet sanPhamChiTiet;

}