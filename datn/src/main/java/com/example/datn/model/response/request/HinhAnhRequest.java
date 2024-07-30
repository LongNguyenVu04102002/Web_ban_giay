package com.example.datn.model.response.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhRequest {

    private String link;
    private String dataImg;
    private Integer uuTien;
}
