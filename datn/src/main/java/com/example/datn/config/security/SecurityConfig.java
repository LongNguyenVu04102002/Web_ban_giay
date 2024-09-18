package com.example.datn.config.security;

import com.example.datn.service.Impl.CustomAdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomAdminDetailsService customAdminDetailsService;

    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/admin/taikhoan/nhanvien",
                                "/admin/sanpham/thuonghieu",
                                "/admin/sanpham/chatlieu",
                                "/admin/sanpham/cogiay",
                                "/admin/sanpham/daygiay",
                                "/admin/sanpham/lotgiay",
                                "/admin/sanpham/muigiay",
                                "/admin/sanpham/mausac",
                                "/admin/sanpham/kichthuoc",
                                "/admin/giamgia"
                        ).hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "STAFF")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/loginAdmin")
                        .loginProcessingUrl("/loginAdmin")
                        .defaultSuccessUrl("/admin/hoadon")
                        .failureUrl("/loginAdmin?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logoutAdmin")
                        .logoutSuccessUrl("/loginAdmin?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("ADMINSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(customAccessDeniedHandler)
                );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customAdminDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}


