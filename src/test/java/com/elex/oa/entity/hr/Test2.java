package com.elex.oa.entity.hr;

import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName: Test2
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\1 0001 10:37
 * @Version 1.0
 **/
public class Test2 {

    private String apiTitle;
    private String apiVersion;
    private String basePackage;


    public Test2() {
    }

    public Test2(String apiTitle, String apiVersion, String basePackage) {
        this.apiTitle = apiTitle;
        this.apiVersion = apiVersion;
        this.basePackage = basePackage;
    }

    public String getApiTitle() {
        return apiTitle;
    }

    public void setApiTitle(String apiTitle) {
        this.apiTitle = apiTitle;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "apiTitle='" + apiTitle + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                ", basePackage='" + basePackage + '\'' +
                '}';
    }
}