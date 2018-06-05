package com.elex.oa.core.script.config;
import com.elex.oa.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties({"scriptClass", "inputParams"})
public class ScriptMethod implements ScriptLabel {
    private ScriptServiceClass scriptClass;
    private String methodName;
    private String title;
    private String returnType;
    private String id;
    private List<ScriptParam> inputParams = new ArrayList();

    public ScriptMethod() {
    }

    public ScriptServiceClass getScriptClass() {
        return this.scriptClass;
    }

    public void setScriptClass(ScriptServiceClass scriptClass) {
        this.scriptClass = scriptClass;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<ScriptParam> getInputParams() {
        return this.inputParams;
    }

    public void setInputParams(List<ScriptParam> inputParams) {
        this.inputParams = inputParams;
    }

    public String getReturnType() {
        return this.returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.methodName).append("(");
        int i = 0;

        for(Iterator var3 = this.inputParams.iterator(); var3.hasNext(); ++i) {
            ScriptParam param = (ScriptParam)var3.next();
            if(i > 0) {
                sb.append(",");
            }

            sb.append(param.getParamType()).append(" ").append(param.getParamName());
        }

        sb.append(")");
        return sb.toString();
    }

    public String getExample() {
        StringBuffer sb = (new StringBuffer("//")).append(this.title);
        Iterator i = this.inputParams.iterator();

        while(i.hasNext()) {
            ScriptParam param = (ScriptParam)i.next();
            sb.append("\r\n//").append(param.getParamName()).append(" ").append(param.getTitle());
        }

        sb.append("\r\n");
        if(!"void".equals(this.returnType)) {
            sb.append(this.returnType).append(" var=");
        }

        sb.append(StringUtil.makeFirstLetterLowerCase(this.scriptClass.getShortClsName())).append(".");
        sb.append(this.methodName).append("(");
        int var5 = 0;

        for(Iterator var6 = this.inputParams.iterator(); var6.hasNext(); ++var5) {
            ScriptParam param1 = (ScriptParam)var6.next();
            if(var5 > 0) {
                sb.append(",");
            }

            sb.append(param1.getParamName());
        }

        sb.append(");");
        return sb.toString();
    }

    public String getType() {
        return "method";
    }

    public String getId() {
        return this.id;
    }

    public String getParentId() {
        return this.scriptClass.getId();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIconCls() {
        return "icon-method";
    }
}
