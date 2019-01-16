package com.elex.oa.util.hr_util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\17 0017 17:19
 * @Version 1.0
 **/
@Component
public class AppProperties {
    @Autowired
    private Environment env;

    public String getProperty(String paramName){
        return env.getProperty(paramName);
    }



}
