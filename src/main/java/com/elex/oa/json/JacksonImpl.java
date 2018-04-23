package com.elex.oa.json;

/**
 *@author hugo.zhao
 *@since 2018/4/20 16:04
*/
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
/*import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;*/
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class JacksonImpl extends ObjectMapper implements IJson {
    private Log logger;
    private static final long serialVersionUID = 1232645849307489985L;

    public JacksonImpl() {
        this.logger = LogFactory.getLog(JacksonImpl.class);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

 /*   public JacksonImpl(boolean forceLazyLoading) {
        this.logger = LogFactory.getLog(JacksonImpl.class);
        Hibernate4Module mod = new Hibernate4Module();
        mod.configure(Feature.FORCE_LAZY_LOADING, forceLazyLoading);
        this.registerModule(mod);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }*/

    public JacksonImpl(String dateFormat) {
        this.logger = LogFactory.getLog(JacksonImpl.class);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.setDateFormat(new SimpleDateFormat(dateFormat));
    }

   /* public JacksonImpl(boolean forceLazyLoading, String dateFormat) {
        this(forceLazyLoading);
        this.setDateFormat(new SimpleDateFormat(dateFormat));
    }*/

    public String toJson(Object object) {
        try {
            return this.writeValueAsString(object);
        } catch (Exception var3) {
            this.logger.error(var3.getMessage());
            throw new RuntimeException("解析对象错误");
        }
    }

    public List<Map<String, Object>> toList(String json) {
        try {
            return (List)this.readValue(json, List.class);
        } catch (Exception var3) {
            this.logger.equals(var3.getMessage());
            throw new RuntimeException("解析json错误");
        }
    }

    public Map<String, Object> toMap(String json) {
        try {
            return (Map)this.readValue(json, Map.class);
        } catch (Exception var3) {
            this.logger.equals(var3.getMessage());
            throw new RuntimeException("解析json错误");
        }
    }

    public <T> List<T> toList(String jsonString, Class<T> clazz) {
        try {
            List e = (List)this.readValue(jsonString, this.getTypeFactory().constructCollectionType(List.class, clazz));
            return e;
        } catch (Exception var4) {
            this.logger.equals(var4.getMessage());
            throw new RuntimeException("解析json错误");
        }
    }

    public <T> T toObject(String json, Class<T> clazz) {
        try {
            return this.readValue(json, clazz);
        } catch (Exception var4) {
            var4.printStackTrace();
            throw new RuntimeException("解析json错误");
        }
    }

    public String toPageJson(List<?> list, Integer totalCounts) {
        StringBuilder sb = (new StringBuilder("{success:true,totalCounts:")).append(totalCounts).append(",results:");
        sb.append(this.toJson(list));
        sb.append("}");
        return sb.toString();
    }

    public String toDataJson(Object object) {
        StringBuilder sb = new StringBuilder("{success:true,data:");
        sb.append(this.toJson(object));
        sb.append("}");
        return sb.toString();
    }

    public String toResultJson(Object object) {
        StringBuilder sb = new StringBuilder("{success:true,result:");
        sb.append(this.toJson(object));
        sb.append("}");
        return sb.toString();
    }
}

