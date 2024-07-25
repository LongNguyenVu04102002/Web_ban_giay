package com.example.datn.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private List<CartItemDTO> items = new ArrayList<>();
    private BigDecimal tongTien;

    public void addItem(CartItemDTO item) {
        this.items.add(item);
        calculateTongTien();
    }

    public void calculateTongTien() {
        this.tongTien = items.stream()
                .map(CartItemDTO::getThanhTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
