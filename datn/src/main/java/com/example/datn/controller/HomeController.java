package com.example.datn.controller;

import com.example.datn.entity.SanPham;
import com.example.datn.service.impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private SanPhamServiceImpl sanPhamService;

    @Autowired
    private SanPhamServiceImpl chiTietService;

    @GetMapping("/home")
    public String home(){
        return "user/includes/content/home";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        List<SanPham> sanPhamList = sanPhamService.getAll();
        model.addAttribute("sanPhamList", sanPhamList);
        return "user/includes/content/shop";
    }

    @GetMapping("/about")
    public String about(){
        return "user/includes/content/about";
    }

    @GetMapping("/blog")
    public String blog(){
        return "user/includes/content/blog";
    }

    @GetMapping("/contact")
    public String contact(){
        return "user/includes/content/contact";
    }

    @GetMapping("/checkout")
    public String checkout(){
        return "user/includes/content/checkout";
    }

    @GetMapping("/cart")
    public String cart(){
        return "user/includes/content/cart";
    }

}
