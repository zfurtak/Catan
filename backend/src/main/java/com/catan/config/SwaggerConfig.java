package com.catan.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

//    @Bean
//    public GroupedOpenApi api(){
//        return GroupedOpenApi.builder()
//                .group("UserApi")
//                .packagesToScan("com.catan")
//                .pathsToMatch(String.valueOf(PathSelectors.any()))
//                .build();
//    }

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

//    @Bean
//    public OpenAPI springShopOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("SpringShop API")
//                        .description(String.valueOf(PathSelectors.any()))
//                        .version("2.0.4"));
//    }
}
