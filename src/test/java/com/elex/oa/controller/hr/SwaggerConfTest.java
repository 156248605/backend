package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr.Test2;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\8 0008 13:47
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations={"classpath:application.properties"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SwaggerConfTest {

    @Test
    public void test1_getBean(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        Test2 test2 = (Test2) context.getBean("testBean");
        System.out.println(test2.toString());
    }
}