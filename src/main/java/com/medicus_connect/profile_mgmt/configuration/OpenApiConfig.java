package com.medicus_connect.profile_mgmt.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Profile Management Service for Medicus Connect")
                        .description("API for demonstrating profile management management")
                        .version("v1.0")
                        .contact(new Contact().name("akhil").email("akhilpgvr@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}

