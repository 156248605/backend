package com.elex.oa.bean;

/**
 *@author hugo.zhao
 *@since 2018/4/18 10:19
*/
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyPlaceholderConfigurerExt extends PropertyPlaceholderConfigurer {
    private Map<String, String> properties = new HashMap();

    public PropertyPlaceholderConfigurerExt() {
    }

    protected void convertProperties(Properties props) {
        Set keys = props.stringPropertyNames();
        Iterator var3 = keys.iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            String value = props.getProperty(key);
            this.properties.put(key, value);
        }

        super.convertProperties(props);
    }

    public String getProperty(String key) {
        return (String)this.properties.get(key);
    }
}

