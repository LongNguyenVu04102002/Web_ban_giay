package com.example.datn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/shop", "/register", "/forgot-password", "/reset-password", "/css/**", "/js/**", "/images/**").permitAll() // Các trang không yêu cầu đăng nhập
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN","NHANVIEN") // Chỉ ADMIN mới được vào các trang /admin/**
                        .anyRequest().permitAll() // Các request khác đều được phép truy cập không cần đăng nhập
                )
                .formLogin((form) -> form
                        .loginPage("/login") // Trang login tùy chỉnh
                        .loginProcessingUrl("/login") // URL xử lý đăng nhập
                        .successHandler(customAuthenticationSuccessHandler) // Xử lý khi đăng nhập thành công
                        .failureUrl("/login?error") // Chuyển hướng khi đăng nhập thất bại, với thông báo lỗi trong query parameter
                        .permitAll() // Cho phép tất cả truy cập trang login
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout") // URL xử lý logout
                        .logoutSuccessUrl("/login?logout") // Chuyển hướng khi logout thành công
                        .invalidateHttpSession(true) // Vô hiệu hóa session hiện tại
                        .deleteCookies("JSESSIONID") // Xóa cookie JSESSIONID
                        .permitAll() // Cho phép tất cả thực hiện logout
                )
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .accessDeniedHandler(customAccessDeniedHandler) // Xử lý khi truy cập bị từ chối
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
