package com.example.config;

import com.example.dto.ContactDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Map<String, ContactDto> myContactMap(){
        return new HashMap<String, ContactDto>();
    }

}
