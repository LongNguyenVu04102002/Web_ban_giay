package com.example.datn.controller;

import com.example.datn.entity.ChatLieu;
import com.example.datn.service.Impl.ChatLieuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/sanpham")
public class ChatLieuController {

    @Autowired
    private ChatLieuServiceImpl chatLieuService;
    @GetMapping("/chatlieu")
    public String show(Model model) {
        List<ChatLieu> chatLieuList = chatLieuService.getAllChatLieu();
        ChatLieu cl = new ChatLieu();
        cl.setTrangThai(true);
        model.addAttribute("chatLieuList", chatLieuList);
        model.addAttribute("chatLieu", cl);
        return "admin/includes/content/sanpham/chatlieu/home";
    }
    @PostMapping("/chatlieu/save")
    public String save(ChatLieu chatLieu, RedirectAttributes redirectAttributes) {
        if(chatLieu.getChatLieuId() != null){
            redirectAttributes.addFlashAttribute("update",true);
        }else {
            redirectAttributes.addFlashAttribute("add",true);
        }
        chatLieuService.saveChatLieu(chatLieu);
        return "redirect:/admin/sanpham/chatlieu";
    }
    @GetMapping("/chatlieu/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        List<ChatLieu> chatLieuList = chatLieuService.getAllChatLieu();
        model.addAttribute("chatLieuList", chatLieuList);
        ChatLieu chatLieu = chatLieuService.getById(id);
        model.addAttribute("chatLieu", chatLieu);
        return "admin/includes/content/sanpham/chatlieu/home";
    }
    @GetMapping("/chatlieu/delete/{id}")
    public String delete(@PathVariable Long id) {
        chatLieuService.deleteChatLieu(id);
        return "redirect:/admin/sanpham/chatlieu";
    }

}
