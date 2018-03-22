package com.elex.oa.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/19 15:47
*/
@Component
public class AppBeanUtil implements ApplicationContextAware {
    private static ApplicationContext appContext;

    public AppBeanUtil() {
    }

    public static Object getBean(String id) {
        return appContext.getBean(id);
    }

    public static boolean containBean(String id) {
        return appContext.containsBeanDefinition(id);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return appContext.getBean(beanClass);
    }

    public static <T> Collection<T> getBeanList(Class<T> beanClass) {
        Map map = appContext.getBeansOfType(beanClass);
        return map.values();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        appContext = context;
    }
}
