package com.elex.oa.core.script.config;
import com.elex.oa.core.annotion.cls.ClassDefine;
import com.elex.oa.core.annotion.cls.MethodDefine;
import com.elex.oa.core.annotion.cls.ParamDefine;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"methods"})
public class ScriptServiceClass implements ScriptLabel {
    private String packageName;
    private String className;
    private String classTitle;
    private List<ScriptMethod> methods = new ArrayList();
    private String beanName;
    private String id;
    private Class<?> beanClass;

    public String getShortClsName() {
        return this.beanClass.getSimpleName();
    }

    public ScriptServiceClass() {
    }

    public ScriptServiceClass(Object bean, String beanName) {
        int idIndex = ScriptServiceConfig.getServiceClasses().size() + 1;
        this.id = String.valueOf(idIndex);
        this.beanName = beanName;
        this.beanClass = bean.getClass();
        this.className = this.beanClass.getName();
        int cIndex = this.className.indexOf("$$");
        if(cIndex != -1) {
            this.className = this.className.substring(0, cIndex);

            try {
                this.beanClass = Class.forName(this.className);
            } catch (Exception var25) {
                var25.printStackTrace();
            }
        }

        this.packageName = this.beanClass.getPackage().getName();
        ClassDefine classDef = (ClassDefine)this.beanClass.getAnnotation(ClassDefine.class);
        if(classDef != null) {
            this.classTitle = classDef.title();
        }

        Method[] classMethods = this.beanClass.getMethods();
        int index = 1;
        Method[] var8 = classMethods;
        int var9 = classMethods.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            Method method = var8[var10];
            String modify = Modifier.toString(method.getModifiers());
            MethodDefine methDef = (MethodDefine)method.getAnnotation(MethodDefine.class);
            if(modify.indexOf("public") != -1 && methDef != null) {
                ScriptMethod scriptMethod = new ScriptMethod();
                scriptMethod.setId(String.valueOf(idIndex * 10 + index));
                ++index;
                scriptMethod.setMethodName(method.getName());
                scriptMethod.setTitle(methDef.title());
                scriptMethod.setScriptClass(this);
                Class[] parameterTypes = method.getParameterTypes();
                ParamDefine[] paramDefines = methDef.params();
                int i = 0;
                Class[] var18 = parameterTypes;
                int var19 = parameterTypes.length;

                for(int var20 = 0; var20 < var19; ++var20) {
                    Class t = var18[var20];
                    if(i < paramDefines.length) {
                        String varName = paramDefines[i].varName();
                        String title = paramDefines[i].title();
                        ScriptParam param = new ScriptParam(t.getSimpleName(), varName, title);
                        scriptMethod.getInputParams().add(param);
                    }

                    ++i;
                }

                scriptMethod.setReturnType(method.getReturnType().getName());
                this.methods.add(scriptMethod);
            }
        }

    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTitle() {
        return this.classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public List<ScriptMethod> getMethods() {
        return this.methods;
    }

    public void setMethods(List<ScriptMethod> methods) {
        this.methods = methods;
    }

    public String getName() {
        return this.className;
    }

    public String getTitle() {
        return "(" + this.beanClass.getSimpleName() + ")" + this.classTitle;
    }

    public String getExample() {
        return "";
    }

    public String getType() {
        return "class";
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return "0";
    }

    public String getIconCls() {
        return "icon-libary";
    }
}