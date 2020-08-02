package com.example.jpademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class VariablesConfig {
    @Bean
    public String greeting(){
        return new String("Her vaxtiniz xeyir");
    }


}
