package com.example.datn.controller;

import com.example.datn.entity.ChatLieu;
import com.example.datn.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
//@CrossOrigin("*")
//@RequestMapping("/chat-lieu")
public class ChatLieuController {

    @Autowired
    private IService<ChatLieu> chatLieuService;

//    @GetMapping("/getAll")
//    public ResponseEntity<List<ChatLieu>> getAll() {
//        List<ChatLieu> lst = chatLieuService.getAll();
//        return ResponseEntity.ok(lst);
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<ChatLieu> add(@RequestBody ChatLieu chatLieu) {
//        ChatLieu addChatLieu = chatLieuService.addOrUpdate(chatLieu);
//        return ResponseEntity.ok(addChatLieu);
//    }
//
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<ChatLieu> update(@RequestBody ChatLieu chatLieu, @PathVariable Long id) {
//        chatLieu.setChatLieuId(id);
//        ChatLieu updateChatLieu = chatLieuService.addOrUpdate(chatLieu);
//        return ResponseEntity.ok(updateChatLieu);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id){
//        chatLieuService.remove(id);
//        return ResponseEntity.status(200).body("");
//    }


    @GetMapping("/chat-lieu")
    public String show(@ModelAttribute("chatLieu") ChatLieu chatLieu, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<ChatLieu> page = chatLieuService.pagination(p, 3);
        model.addAttribute("lst", page);
        chatLieu.setTrangThai(true);
        return "chat-lieu";
    }

    @PostMapping("/chatlieu/save")
    public String add(@Valid @ModelAttribute("chatLieu") ChatLieu chatLieu, BindingResult result, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("chatLieu", new ChatLieu());
        chatLieuService.addOrUpdate(chatLieu);
        Page<ChatLieu> page = chatLieuService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "redirect:/chat-lieu";
    }

    @GetMapping("/chatlieu/detail/{id}")
    public String detail(@ModelAttribute("chatLieu") Optional<ChatLieu> chatLieu, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("chatLieu", chatLieuService.getOne(id));
        Page<ChatLieu> page = chatLieuService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "chat-lieu";
    }

    @PostMapping("/chatlieu/update/{id}")
    public String update(@Valid @ModelAttribute("chatLieu") ChatLieu chatLieu, BindingResult result, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("chatLieu", new ChatLieu());
        chatLieuService.addOrUpdate(chatLieu);
        Page<ChatLieu> page = chatLieuService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "redirect:/chat-lieu";
    }

    @GetMapping("/chatlieu/delete/{id}")
    public String remove(@ModelAttribute("chatLieu") ChatLieu chatLieu, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        chatLieuService.remove(id);
        Page<ChatLieu> page = chatLieuService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "redirect:/chat-lieu";
    }
}
