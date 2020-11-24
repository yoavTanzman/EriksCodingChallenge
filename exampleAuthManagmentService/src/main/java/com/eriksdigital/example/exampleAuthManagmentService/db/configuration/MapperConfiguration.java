package com.eriksdigital.example.exampleAuthManagmentService.db.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
