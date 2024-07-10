package com.example.datn.controller;

import com.example.datn.model.response.CartResponse;
import com.example.datn.model.response.HoaDonResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("cartItems")
public class CartController {

    @ModelAttribute("cartItems")
    public List<CartResponse> setUpCart() {
        return new ArrayList<>();
    }

    @GetMapping("/cart/view")
    public String viewCart(Model model, HttpSession session) {
        List<CartResponse> cartItems = (List<CartResponse>) model.getAttribute("cartItems");
        HoaDonResponse hoaDonResponse = new HoaDonResponse();
        hoaDonResponse.setCartResponseList(cartItems);
        model.addAttribute("hoaDonResponse", hoaDonResponse);
        return "user/includes/content/cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@ModelAttribute("cartItems") List<CartResponse> cartItems,
                            @ModelAttribute("hoaDonResponse") HoaDonResponse hoaDonResponse,
                            @ModelAttribute("cartResponse") CartResponse cartResponse,
                            Model model) {
        cartItems.add(cartResponse);
        model.addAttribute("hoaDonResponse", hoaDonResponse);
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/remove/{index}")
    public String removeFromCart(@ModelAttribute("cartItems") List<CartResponse> cartItems,
                                 @PathVariable("index") int index) {
        cartItems.remove(index);
        return "redirect:/cart/view";
    }

}
