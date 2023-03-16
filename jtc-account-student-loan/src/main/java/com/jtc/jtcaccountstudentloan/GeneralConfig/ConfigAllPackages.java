package com.jtc.jtcaccountstudentloan.GeneralConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configurable
public class ConfigAllPackages {

// Security i
//    @Order(1)
 /*   @Bean
    public SecurityFilterChain companySecurityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity
                .authorizeHttpRequests((authorizeConfig)-> {
                    authorizeConfig.requestMatchers("/company").authenticated();
                    authorizeConfig.requestMatchers("/company/loan/").authenticated();
                    authorizeConfig.requestMatchers("/company/loan/{loanId}").hasRole("USER");
                    authorizeConfig.requestMatchers("/").permitAll();
                    authorizeConfig.requestMatchers("/check").hasRole("ADMIN");
                }).httpBasic(Customizer.withDefaults())
                .build();
    }*/
//Security 2
 //   @Order(2)
/*    @Bean
    public SecurityFilterChain studentSecurityFilterChains(HttpSecurity httpSecurity) throws Exception
    {
        return   httpSecurity.authorizeHttpRequests((authorizeConfig)-> {
            authorizeConfig.requestMatchers("/student").authenticated();
            authorizeConfig.requestMatchers("/student/view").authenticated();
            authorizeConfig.requestMatchers("/student/save/student").hasRole("ADMIN");
            authorizeConfig.requestMatchers("/jtc/api").permitAll();
            authorizeConfig.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults()).build();

    }*/

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



}
