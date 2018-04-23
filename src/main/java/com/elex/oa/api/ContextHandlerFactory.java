package com.elex.oa.api;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.core.KeyValEnt;
import org.springframework.stereotype.Component;

/**
 *@author hugo.zhao
 *@since 2018/4/13 14:48
*/
@Component
public class ContextHandlerFactory {
    private Map<String, ContextHandler> handlerMap = new HashMap();
    private List<ContextHandler> handlers = new ArrayList();

    public ContextHandlerFactory() {
    }

    public void setHandlers(List<ContextHandler> handlers) {
        Iterator var2 = handlers.iterator();

        while(var2.hasNext()) {
            ContextHandler handler = (ContextHandler)var2.next();
            this.handlerMap.put(handler.getKey(), handler);
        }

        this.handlers = handlers;
    }

    public Object getValByKey(String key, Map<String, Object> vars) {
        return this.handlerMap.containsKey(key)?((ContextHandler)this.handlerMap.get(key)).getValue(vars):null;
    }

    public List<KeyValEnt> getHandlers() {
        ArrayList list = new ArrayList();
        Iterator var2 = this.handlers.iterator();

        while(var2.hasNext()) {
            ContextHandler handler = (ContextHandler)var2.next();
            list.add(new KeyValEnt(handler.getKey(), handler.getName()));
        }

        return list;
    }

    public JSONArray getJsonHandlers() {
        JSONArray ary = new JSONArray();
        Iterator var2 = this.handlers.iterator();

        while(var2.hasNext()) {
            ContextHandler handler = (ContextHandler)var2.next();
            JSONObject obj = new JSONObject();
            obj.put("key", handler.getKey());
            obj.put("val", handler.getName());
            ary.add(obj);
        }

        return ary;
    }
}
