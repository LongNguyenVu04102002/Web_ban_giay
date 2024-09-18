package com.example.datn.controller;

import com.example.datn.config.teamplate.Utility;
import com.example.datn.entity.KhachHang;
import com.example.datn.service.Impl.EmailService;
import com.example.datn.service.Impl.KhachHangServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private KhachHangServiceImpl khachHangService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/loginUser")
    public String showLoginUserPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {

        return "authentication/userlogin";
    }

    @PostMapping("/loginUser")
    public String login(Model model, @RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        KhachHang khachHang = khachHangService.login(email, password);
        if (khachHang != null) {
            HttpSession session = request.getSession();
            session.setAttribute("khachHang", khachHang);
            return "redirect:home";
        }
        model.addAttribute("message", "Đăng nhập thất bại! Vui lòng thử lại.");
        model.addAttribute("email", email);
        return "authentication/userlogin";
    }


    @GetMapping("/loginAdmin")
    public String showLoginAdminPage(@RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "logout", required = false) String logout,
                                     Model model) {

        return "authentication/adminlogin";
    }

    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout,
                             Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        if (logout != null) {
            model.addAttribute("logout", "You have been logged out successfully.");
        }
        return "authentication/adminlogin";
    }

    @GetMapping("/logoutUser")
    public String logoutSuccess( HttpSession session) {
        session.removeAttribute("khachHang");
        return "redirect:/loginUser";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(@RequestParam(value = "success", required = false) String success,
                                         @RequestParam(value = "error", required = false) String error,
                                         Model model) {
        if (success != null) {
            model.addAttribute("message", "Đã gửi liên kết đặt lại mật khẩu vào email của bạn.");
        }
        if (error != null) {
            model.addAttribute("error", "Email không hợp lệ. Vui lòng thử lại.");
        }
        return "authentication/forgot";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("sdt") String sdt, Model model) {
        KhachHang khachHang = khachHangService.findByEmailAndSdt(email, sdt);
        if (khachHang != null) {
            String token = UUID.randomUUID().toString();
            khachHang.setResetToken(token);
            khachHangService.save(khachHang);

            String resetPasswordLink = Utility.getSiteURL(request) + "/reset-password?token=" + token;
            try {
                emailService.sendEmail(khachHang.getEmail(), "Reset Password",
                        "Click the link to reset your password: " + resetPasswordLink);
                model.addAttribute("message", "Đã gửi liên kết đặt lại mật khẩu vào email của bạn.");
                return "authentication/forgot";
            } catch (MessagingException e) {
                model.addAttribute("error", "Có lỗi xảy ra khi gửi email. Vui lòng thử lại.");
                return "authentication/forgot";
            }
        } else {
            model.addAttribute("error", "Thông tin không chính xác. Vui lòng kiểm tra lại.");
            return "authentication/forgot";
        }
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        KhachHang khachHang = khachHangService.findByResetToken(token);
        if (khachHang != null) {
            model.addAttribute("token", token);
        } else {
            model.addAttribute("error", "Liên kết đặt lại mật khẩu không hợp lệ.");
        }
        return "authentication/reset";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam("token") String token,
                                       @RequestParam("password") String password,
                                       Model model) {
        KhachHang khachHang = khachHangService.findByResetToken(token);
        if (khachHang != null) {
            khachHang.setPassword(passwordEncoder.encode(password));
            khachHang.setResetToken(null);
            khachHangService.save(khachHang);
            model.addAttribute("message", "Mật khẩu đã được thay đổi thành công. Vui lòng đăng nhập.");
            return "redirect:/login?resetSuccess";
        } else {
            model.addAttribute("error", "Liên kết đặt lại mật khẩu không hợp lệ.");
            return "authentication/reset";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "authentication/register";
    }
    @PostMapping("/signup")
    public String registerEmployee(@RequestParam String username,
                                   @RequestParam String email,
                                   @RequestParam String sdt,
                                   @RequestParam boolean gioiTinh,
                                   @RequestParam LocalDate ngaySinh,
                                   @RequestParam String password,
                                   @RequestParam String diaChi,  // Thêm địa chỉ
                                   @RequestParam String xa,      // Thêm xã
                                   @RequestParam String huyen,   // Thêm huyện
                                   @RequestParam String thanhPho,// Thêm thành phố
                                   @RequestParam String diaChiSdt,  // Số điện thoại liên hệ tại địa chỉ
                                   @RequestParam String diaChiTen,  // Tên người liên hệ tại địa chỉ
                                   Model model) {
        try {
            // Kiểm tra xem email hoặc số điện thoại đã tồn tại chưa
            if (khachHangService.isEmailOrPhoneExist(email, sdt)) {
                // Ném ngoại lệ nếu email hoặc số điện thoại đã tồn tại
                throw new RuntimeException("Email hoặc số điện thoại đã tồn tại!");
            }

            // Nếu không trùng, tiến hành đăng ký
            khachHangService.register(username, email, sdt, gioiTinh, ngaySinh, password, diaChi, xa, huyen, thanhPho, diaChiSdt, diaChiTen);
            return "redirect:/loginUser";
        } catch (RuntimeException e) {
            // Hiển thị thông báo lỗi
            model.addAttribute("error", e.getMessage());
            return "authentication/register";
        }
    }


}