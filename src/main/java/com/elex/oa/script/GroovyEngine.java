package com.elex.oa.script;
import com.elex.oa.core.script.config.ScriptServiceClass;
import com.elex.oa.core.script.config.ScriptServiceConfig;
import com.elex.oa.saweb.context.ContextUtil;
import groovy.lang.GroovyShell;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


@Component
public class GroovyEngine implements BeanPostProcessor {
    private Log logger = LogFactory.getLog(GroovyEngine.class);
    GroovyBinding groovyBinding = new GroovyBinding();

    public GroovyEngine() {
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        boolean isInherit = GroovyScript.class.isAssignableFrom(bean.getClass());
        if(isInherit) {
            this.groovyBinding.setProperty(beanName, bean);
            ScriptServiceClass serviceClass = new ScriptServiceClass(bean, beanName);
            ScriptServiceConfig.getServiceClasses().add(serviceClass);
        }

        return bean;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object executeScripts(String scriptText, Map<String, Object> vars) {
        if(!StringUtils.isEmpty(scriptText) && scriptText.trim() != "") {
            Object result = null;

            try {
                if(vars != null) {
                    this.groovyBinding.setVariables(vars);
                    this.groovyBinding.getVariables().put("curUser", ContextUtil.getCurrentUser());
                }

                GroovyShell shell = new GroovyShell(this.groovyBinding);
                result = shell.evaluate(scriptText);
            } finally {
                this.groovyBinding.clearVariables();
            }

            return result;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Object是String的父类:" + Object.class.isAssignableFrom(String.class));
    }
}