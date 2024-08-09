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
						.requestMatchers("/", "/home", "/shop", "/register", "/forgot-password", "/reset-password", "/css/**", "/js/**", "/images/**").permitAll()
						.requestMatchers("/admin/taikhoan/**").hasRole("ADMIN")
						.requestMatchers("/admin/**").hasAnyRole("ADMIN", "NHANVIEN")
						.anyRequest().permitAll()
				)
				.formLogin((form) -> form
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.successHandler(customAuthenticationSuccessHandler)
						.failureUrl("/login?error") // Thêm tham số error vào URL khi đăng nhập thất bại
						.permitAll()
				)
				.logout((logout) -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout") // Thêm tham số logout vào URL khi đăng xuất thành công
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll()
				)
				.exceptionHandling((exceptionHandling) -> exceptionHandling
						.accessDeniedHandler(customAccessDeniedHandler)
				);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}