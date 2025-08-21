package com.ecommerce.sb_project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appConfig {
    @Bean
    public ModelMapper modelmapper(){
        return new ModelMapper();
    }
}
