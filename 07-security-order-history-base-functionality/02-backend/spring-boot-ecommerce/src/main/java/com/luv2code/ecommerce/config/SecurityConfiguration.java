package com.luv2code.ecommerce.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
//    @Value("${security.success-url}")
//    private String successUrl;
//
//    @Value("${security.logout-url}")
//    private String logoutUrl;
//
//    @Value("${security.redirect-uri}")
//    private String redirectUri;
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated();

       http.oauth2ResourceServer().jwt()
       .jwtAuthenticationConverter(jwtAuthConverter);

       http.sessionManagement().sessionCreationPolicy(STATELESS);
        return http.build();
    }

}
