package com.example.datn.config.security;

import com.example.datn.dto.MyUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public MyUserDetail getPrincipal() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof MyUserDetail) {
                return (MyUserDetail) principal;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
