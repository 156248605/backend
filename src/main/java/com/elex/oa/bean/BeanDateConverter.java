package com.elex.oa.bean;

/**
 *@author hugo.zhao
 *@since 2018/4/18 10:16
*/
import java.util.Date;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.time.DateUtils;
public class BeanDateConverter implements Converter {

    public BeanDateConverter() {
    }

    public Object convert(Class type, Object value) {
        if(value == null) {
            return null;
        } else if(type.getName().equals("java.util.Date") && value instanceof Date) {
            return value;
        } else {
            String dateStr = value.toString();
            if(dateStr.length() > 19) {
                dateStr = dateStr.substring(0, 19);
            }

            try {
                return DateUtils.parseDate(dateStr, new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"});
            } catch (Exception var5) {
                return null;
            }
        }
    }
}
