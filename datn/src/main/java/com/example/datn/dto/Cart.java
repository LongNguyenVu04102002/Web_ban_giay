package com.example.datn.dto;

import com.example.datn.entity.SanPham;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<SanPham, Integer> items = new HashMap<>();

    // Getter cho thuộc tính items
    public Map<SanPham, Integer> getItems() {
        return items;
    }

    // Phương thức để thêm sản phẩm vào giỏ hàng
    public void addItem(SanPham sanPham, int soLuong) {
        if (items.containsKey(sanPham)) {
            items.put(sanPham, items.get(sanPham) + soLuong);
        } else {
            items.put(sanPham, soLuong);
        }
    }

    // Phương thức để loại bỏ sản phẩm khỏi giỏ hàng
    public void removeItem(SanPham sanPham) {
        items.remove(sanPham);
    }

    // Phương thức để xóa toàn bộ sản phẩm trong giỏ hàng
    public void clear() {
        items.clear();
    }
}
