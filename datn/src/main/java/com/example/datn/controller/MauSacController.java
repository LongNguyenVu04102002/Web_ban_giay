package com.example.datn.controller;

import com.example.datn.entity.ChatLieu;
import com.example.datn.entity.MauSac;
import com.example.datn.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
//@CrossOrigin("*")
public class MauSacController {

    @Autowired
    private IService<MauSac> mauSacService;

//    @GetMapping("/mausac/getAll")
//    public ResponseEntity<List<MauSac>> getAll() {
//        List<MauSac> lst = mauSacService.getAll();
//        return ResponseEntity.ok(lst);
//    }

    @GetMapping("/mau-sac")
    public String show(@ModelAttribute("mauSac") MauSac mauSac, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        Page<MauSac> page = mauSacService.pagination(p, 3);
        model.addAttribute("lst", page);
        mauSac.setTrangThai(true);
        return "mau-sac";
    }

    @PostMapping("/mau-sac/add")
    public String add(@Valid @ModelAttribute("mauSac") MauSac mauSac, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("mauSac", new MauSac());
        mauSacService.addOrUpdate(mauSac);
        Page<MauSac> page = mauSacService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "redirect:/mau-sac";
    }

    @GetMapping("/mau-sac/detail/{id}")
    public String detail(@ModelAttribute("mauSac") Optional<MauSac> mauSac, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("mauSac", mauSacService.getOne(id));
        Page<MauSac> page = mauSacService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "mau-sac";
    }

    @PostMapping("/mau-sac/update/{id}")
    public String update(@Valid @ModelAttribute("mauSac") MauSac mauSac, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        model.addAttribute("mauSac", new MauSac());
        mauSacService.addOrUpdate(mauSac);
        Page<MauSac> page = mauSacService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "redirect:/mau-sac";
    }

    @GetMapping("/mau-sac/remove/{id}")
    public String remove(@ModelAttribute("mauSac") MauSac mauSac, @PathVariable("id") Long id, Model model, @RequestParam(name = "p", defaultValue = "0") Integer p) {
        mauSacService.remove(id);
        Page<MauSac> page = mauSacService.pagination(p, 3);
        model.addAttribute("lst", page);
        return "redirect:/mau-sac";
    }
}
