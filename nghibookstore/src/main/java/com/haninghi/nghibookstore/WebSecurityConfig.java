package com.haninghi.nghibookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    private UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /*
     * Create 2 in-memory users: 1 normal user and 1 admin user
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Even `withDefaultPasswordEncoder` is deprecated, but it is still fine for demo and learning
        UserDetails normalUser = User.withDefaultPasswordEncoder()
            .username("normalUser")
            .password("password")
            .roles("USER")
            .build();
        UserDetails adminUser = User.withDefaultPasswordEncoder()
            .username("adminUser")
            .password("password")
            .roles("ADMIN")
            .build();
        List<UserDetails> users = new ArrayList<UserDetails>();
        users.add(normalUser);
        users.add(adminUser);
        return new InMemoryUserDetailsManager(users);
    }

    /*
     * Secure all URLs that needs to have authentication
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            ) // requires all endpoints have to be authenticated
            .formLogin(formlogin -> formlogin
                .loginPage("/login")
                .defaultSuccessUrl("/booklist", true) // direct user to /booklist after login successfully
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );
        return http.build();
    }
}
