package com.example.ecommerce.security;

import com.example.ecommerce.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    UserDetailsService customUserDetailService(){
        return new UserDetailsServiceImpl();
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(customUserDetailService());
        return provider;
    }
    private static final String[] WHITELIST_RESSOURCES = {"/css/**", "/js/**", "/images/**"};
    private static final String[] BLACKLIST_RESSOURCES = {"/cart/**", "/payment","/order/**","profile","/account", "/contact-form","/edit-profile","/my-orders","/order-details/**","/thanks","/api/**"};

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(WHITELIST_RESSOURCES).permitAll()
                            .requestMatchers("/signup","/login","/products/**","/error", "/404").permitAll()
                            .requestMatchers(BLACKLIST_RESSOURCES).authenticated();
                })
                .formLogin(form ->{
                    form
                            .loginPage("/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .permitAll()
                            .defaultSuccessUrl("/products");
                })
                .logout(logout->{
                    logout
                            .permitAll()
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/login");
                })
                .build();
    }
}
