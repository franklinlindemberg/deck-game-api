package com.franklin.deck.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    public static final String AUTHORIZATION_HEADER = "token";

//    @Bean
//    public Docket apiDocket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.franklin"))
//                .paths(PathSelectors.ant("/v1/**"))
//                .build()
//                .apiInfo(getApiInfo());
//    }
//
//    private ApiInfo getApiInfo() {
//        return new ApiInfo(
//                "Deck game api",
//                "This api shows how to handle the deck game.",
//                "1.0.0",
//                "TERMS OF SERVICE URL",
//                new Contact("Franklin Guimaraes", "", "franklin.lindemberg@gmail.com"),
//                "MIT License",
//                "LICENSE URL",
//                Collections.emptyList()
//        );
//    }

    @Bean
    public Docket swaggerSpringfoxDocket() {

        ApiInfo apiInfo = new ApiInfo(
            "Deck game api",
            "This api shows how to handle the deck game.",
            "1.0.0",
            "TERMS OF SERVICE URL",
            new Contact("Franklin Guimaraes", "", "franklin.lindemberg@gmail.com"),
            "MIT License",
            "LICENSE URL",
            Collections.emptyList()
        );

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .pathMapping("/")
                .apiInfo(ApiInfo.DEFAULT)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .useDefaultResponseMessages(false);

        docket = docket.select()
                .paths(PathSelectors.ant("/v1/**"))
                .build();

        return docket;
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/v1/**"))
                .build();
    }

    ArrayList<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }
}