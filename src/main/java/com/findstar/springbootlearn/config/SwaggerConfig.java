package com.findstar.springbootlearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: findStar
 * @Date: 2021/10/6 4:39 下午
 */
@Component
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        //返回文档摘要信息
        return new Docket(DocumentationType.OAS_30)
                .groupName("user")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.findstar.springbootlearn.controller"))
                .build();
    }

    //生成接口信息，包括标题、联系人等
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Learn")
                .description("SpringBoot学习项目")
                .contact(new Contact("findStar", "", "wangjunjiestar@gmail.com"))
                .version("1.0")
                .build();
    }

}
