package com.jtc.jtcaccountstudentloan.company.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain studentSecurityFilterChains(HttpSecurity httpSecurity) throws Exception
    {
        return   httpSecurity.authorizeHttpRequests((authorizeConfig)-> {
            authorizeConfig.requestMatchers("/student").authenticated();
            authorizeConfig.requestMatchers("/student/view").authenticated();
            authorizeConfig.requestMatchers("/student/save/student").hasRole("ADMIN");
            authorizeConfig.requestMatchers("/jtc/api").permitAll();
            authorizeConfig.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults()).build();

    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
    {
        return null;
    }
}
