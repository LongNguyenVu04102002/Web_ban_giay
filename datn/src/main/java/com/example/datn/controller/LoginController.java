package com.example.datn.controller;

import com.example.datn.entity.ERole;
import com.example.datn.entity.TaiKhoan;
import com.example.datn.entity.VaiTro;
import com.example.datn.model.request.LoginRequest;
import com.example.datn.model.request.SignupRequest;
import com.example.datn.service.Impl.TaiKhoanServiceImpl;
import com.example.datn.service.Impl.VaiTroServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    private TaiKhoanServiceImpl taiKhoanService;

    @Autowired
    private VaiTroServiceImpl vaiTroService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "authentication/login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("sigUpRequest", new SignupRequest());
        return "authentication/register";
    }

    @GetMapping("/logout")
    public String logOut(Model model, HttpSession session) {
        session.invalidate();
        model.addAttribute("loginRequest", new LoginRequest());
        return "authentication/login";
    }

//    @PostMapping("/signin")
//    public String login(@Valid @ModelAttribute LoginRequest loginRequest, Model model, HttpSession session) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//            session.setAttribute("userDetails", userDetails);
//
//            // Kiểm tra vai trò của người dùng và chuyển hướng
//            if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
//                return "redirect:/admin/thongke";
//            } else if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
//                return "redirect:/home";
//            } else {
//                // Nếu không có vai trò nào phù hợp, chuyển hướng về trang login với thông báo lỗi
//                model.addAttribute("error", "User does not have a valid role");
//                return "authentication/login";
//            }
//
//        } catch (Exception e) {
//            model.addAttribute("error", "Invalid username or password");
//            return "authentication/login";
//        }
//    }


    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute SignupRequest signUpRequest, Model model) {
        if (taiKhoanService.existsByEmail(signUpRequest.getEmail())) {
            model.addAttribute("error", "Error: Email is already in use!");
            return "authentication/login";
        }

        TaiKhoan taiKhoan = TaiKhoan.builder()
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();

        Set<String> strRoles = signUpRequest.getRole();
        List<VaiTro> vaiTros = new ArrayList<>();

        if (strRoles == null) {
            VaiTro vaiTro = vaiTroService.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            vaiTros.add(vaiTro);
        } else {
            for (String role : strRoles) {
                switch (role) {
                    case "admin":
                        VaiTro adminRole = vaiTroService.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        vaiTros.add(adminRole);
                        break;
                    case "mod":
                        VaiTro modRole = vaiTroService.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        vaiTros.add(modRole);
                        break;
                    default:
                        VaiTro userRole = vaiTroService.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        vaiTros.add(userRole);
                }
            }
        }
        taiKhoan.setNgayTao(LocalDate.now());
        taiKhoan.setVaiTros(vaiTros);
        taiKhoanService.save(taiKhoan);

        model.addAttribute("message", "User registered successfully!");
        return "redirect:/login";
    }


}