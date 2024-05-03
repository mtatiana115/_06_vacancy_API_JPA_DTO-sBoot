package com.riwi.vacancies.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API to manage companies and company vacancies", version = "1.0", description = "API documentation for business administration and vacancies"))
public class OpenApiConfig {

}
