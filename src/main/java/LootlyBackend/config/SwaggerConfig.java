package LootlyBackend.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("LootlyBackend")) // Restrict scanning to your package
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo(
                "LootLy: E-commerce Application API Documentation",
                "This API is developed for an e-commerce application",
                "1.0",
                "Terms of Service URL",
                new Contact("Soumadip Bhowmick", "https://soumadip.com", "souamdip17720@gmail.com"),
                "API License",
                "API License URL",
                Collections.emptyList()
        );
    }
}
