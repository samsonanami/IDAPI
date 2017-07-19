package com.fintech.orion.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-18T09:12:11.427Z")

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("ORION")
            .description("This is ORION API definition. ORION is the API endpoint for [Selified](http://selified.com/)  " +
                    "If you are an regeistred user, you can use the given credentials to try this api out ")
            .license("")
            .licenseUrl("")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", ""))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.fintech"))
                    .build()
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}
