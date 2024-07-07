package com.example.datn.controller;

import com.example.datn.entity.ChatLieu;
import com.example.datn.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ChatLieuController {

    @Autowired
    private IService<ChatLieu> chatLieuService;

    @GetMapping("/chat-lieu")
    public String show(@ModelAttribute("chatLieu") ChatLieu chatLieu, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<ChatLieu> page = chatLieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        chatLieu.setTrangThai(true);
        return "left-menu-chat-lieu";
    }

    @PostMapping("/chat-lieu/add")
    public String add(@Valid @ModelAttribute("chatLieu") ChatLieu chatLieu, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<ChatLieu> page = chatLieuService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-chat-lieu";
        }
        model.addAttribute("chatLieu", new ChatLieu());
        ChatLieu cl = ChatLieu.builder()
                .ten(chatLieu.getTen())
                .moTa(chatLieu.getMoTa())
                .trangThai(chatLieu.isTrangThai())
                .build();
        chatLieuService.addOrUpdate(cl);
        Page<ChatLieu> page = chatLieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/chat-lieu";
    }

    @GetMapping("/chat-lieu/detail/{id}")
    public String detail(@ModelAttribute("chatLieu") Optional<ChatLieu> chatLieu, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("chatLieu", chatLieuService.getOne(id));
        Page<ChatLieu> page = chatLieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "left-menu-chat-lieu";
    }

    @PostMapping("/chat-lieu/update/{id}")
    public String update(@Valid @ModelAttribute("chatLieu") ChatLieu chatLieu, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        if (result.hasErrors()) {
            Page<ChatLieu> page = chatLieuService.pagination(p, 10);
            model.addAttribute("lst", page);
            return "left-menu-chat-lieu";
        }
        model.addAttribute("chatLieu", new ChatLieu());
        chatLieuService.addOrUpdate(chatLieu);
        Page<ChatLieu> page = chatLieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/chat-lieu";
    }

    @GetMapping("/chat-lieu/remove/{id}")
    public String remove(@ModelAttribute("chatLieu") ChatLieu chatLieu, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        chatLieuService.remove(id);
        Page<ChatLieu> page = chatLieuService.pagination(p, 10);
        model.addAttribute("lst", page);
        return "redirect:/chat-lieu";
    }
}
