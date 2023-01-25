package com.example.exercisenine.configuration;

import com.example.exercisenine.security.JwtFilter;
import com.example.exercisenine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((requests) -> {
                    requests.antMatchers("/", "/authenticate").permitAll();
                    requests.antMatchers("/").hasAnyRole("ADMIN", "METER", "OPERATOR");
                    requests.antMatchers("/api/v1.0/excell", "/api/v1.0/group", "/api/v1.0/report").hasAnyRole("ADMIN", "OPERATOR");
                    requests.antMatchers("/api/v1.0/read", "/api/v1.0/example").hasAnyRole("ADMIN", "METER");
                    requests.antMatchers("/api/v1.0/users", "/api/v1.0/users/*").hasRole("ADMIN");
                })
                .formLogin().and()
                .logout(LogoutConfigurer::permitAll)
                .rememberMe();

        return httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}