/**
 * @ClassName: Test
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\1 0001 10:36
 * @Version 1.0
 **/
package com.elex.oa.entity.hr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class Test {

    @Value("${swagger.api.title:HR REST API}")
    private String apiTitle;
    @Value("${swagger.api.version:1.0.0}")
    private String apiVersion;
    @Value("${swagger.base-package:com.elex.oa.controller.hr}")
    private String basePackage;

    public Test() {
        System.out.println("Test容器启动初始化。。。");
    }

    @Bean(name = "testBean")
    public Test2 test2(){
        return new Test2(apiTitle,apiVersion,basePackage);
    }
}