package com.catan.config;
import io.swagger.annotations.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("OpenApiController")
                .packagesToScan("com.catan.api")
                .build();
    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
////                .apis(RequestHandlerSelectors.basePackage("com.catan"))
////                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
////                .paths(PathSelectors.regex("/users.*"))
//                .apis(RequestHandlerSelectors.basePackage("com.catan"))
//                .build();
//    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SpringShop API")
                        .description("Spring shop sample application")
                        .version("2.0.4"));
    }
}
