package com.elex.oa.core.script.config;
import java.util.ArrayList;
import java.util.List;
public class ScriptServiceConfig {
    public static List<ScriptServiceClass> serviceClasses = new ArrayList();

    public ScriptServiceConfig() {
    }

    public static List<ScriptServiceClass> getServiceClasses() {
        return serviceClasses;
    }

    public static void setServiceClasses(List<ScriptServiceClass> serviceClasses) {
        ScriptServiceConfig.serviceClasses = serviceClasses;
    }
}