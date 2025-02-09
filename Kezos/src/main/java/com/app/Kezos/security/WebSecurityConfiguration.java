package com.app.Kezos.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
            auth->auth.requestMatchers("/").permitAll().anyRequest().authenticated()
        );
        http.sessionManagement(
            session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        http.csrf(csrf->csrf.disable());
        http.httpBasic(Customizer.withDefaults());
        http.headers(header->header.frameOptions(frame->frame.sameOrigin()));
        // http.oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
