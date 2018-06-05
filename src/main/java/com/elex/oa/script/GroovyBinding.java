package com.elex.oa.script;

import groovy.lang.Binding;
import java.util.HashMap;
import java.util.Map;

public class GroovyBinding extends Binding {
    private static ThreadLocal<Map<String, Object>> localVars = new ThreadLocal();

    public GroovyBinding() {
    }

    public void setVariables(Map<String, Object> vars) {
        Object localMap = (Map)localVars.get();
        if(localMap == null) {
            localMap = new HashMap();
            localVars.set((Map<String, Object>) localMap);
        }

        ((Map)localMap).putAll(vars);
    }

    public void clearVariables() {
        localVars.remove();
    }

    public Object getVariable(String name) {
        Map vars = (Map)localVars.get();
        if(vars != null) {
            Object val = vars.get(name);
            if(val != null) {
                return val;
            }
        }

        return super.getVariable(name);
    }
}
