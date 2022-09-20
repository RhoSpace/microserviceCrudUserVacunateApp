package com.vacuna.microserviciousuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

//HTML: http://localhost:8080/swagger-ui/
//JSON: http://localhost:8080/v2/api-docs

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiLibro() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(detallesApi())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo detallesApi() {
        return new ApiInfo(
                "Spring Boot Usuarios API REST",
                "Order Service API Description",
                "1.0",
                "URL Terms of service",
                new Contact("Sebastian Espinoza", "https://www.linkedin.com/in/jebuzdev/", "sebaamts@gmail.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList());
    }
}