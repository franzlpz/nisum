package com.nisum.prueba;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customCofiguration(@Value("Nisum.Test") String appDescription,
                                      @Value("V1.0") String appVersion) {

        return new OpenAPI().info(new Info().title("NISUM API Docs").version(appVersion).description(appDescription)
                .contact(new Contact().email("franz.lopez@outlook.com").name("Franz Lopez").url("https://www.linkedin.com/in/franzlpz/"))
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
