package com.example.datn.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
//        request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
//        request.getSession().invalidate();
//
//        // Tạo phiên mới
//        request.getSession(true);
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        logger.info("Authentication successful. User roles: " + roles);

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/thongke");
        } else if (roles.contains("ROLE_NHANVIEN")) {
            response.sendRedirect("/admin/nhanvien");
        }
        else {
            response.sendRedirect("/home");
        }
    }
}
