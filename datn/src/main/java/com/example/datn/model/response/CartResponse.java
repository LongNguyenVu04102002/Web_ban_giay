package com.example.datn.model.response;

import com.example.datn.dto.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private List<CartItem> cartItems;
    private PhieuGiamGiaResponse phieuGiamGiaResponse;
    private ThanhToanResponse thanhToanResponse;
}
