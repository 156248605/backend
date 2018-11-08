package com.elex.oa.controller.hr;

import com.elex.oa.OaApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description: Swagger的Spring的配置类
 * @Author shiyun
 * @Date 2018\11\7 0007 13:10
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConf {
    /*@Value("${swagger.api.title}")
    private String apiTitle;
    @Value("${swagger.api.version}")
    private String apiVersion;
    @Value("${swagger.base-package}")
    private String basePackage;

    @Bean
    public Docket docket(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(apiTitle)
                .version(apiVersion)
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }*/
}