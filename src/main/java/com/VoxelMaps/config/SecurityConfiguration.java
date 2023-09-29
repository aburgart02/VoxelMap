package com.VoxelMaps.config;

import com.VoxelMaps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .permitAll()
            );
        http.logout(form -> form
            .permitAll()
            .logoutSuccessUrl("/")
        );
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .requestMatchers("/admin").hasRole("ADMIN")
            .requestMatchers("/account").hasRole("USER")
            .requestMatchers("/", "/registration", "/login", "/maps/**").permitAll();


        return http.build();
    }
}