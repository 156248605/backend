package com.elex.oa.form.api;

import com.elex.oa.form.entity.FormModel;

import java.util.List;
import java.util.Map;
/**
 *@author hugo.zhao
 *@since 2018/5/7 11:53
*/
public interface IFormHandler {
    String FORM_TYPE_MOBILE = "mobile";
    String FORM_TYPE_PC = "pc";
    String FORM_TYPE_PRINT = "print";
    String PK_VAR = "pk";

    String getType();

    List<FormModel> getStartForm(String var1, String var2, String var3) throws Exception;

    List<FormModel> getByTaskId(String var1, String var2) throws Exception;

    List<FormModel> getByInstId(String var1) throws Exception;

    FormModel getFormByFormAlias(String var1, String var2, boolean var3, Map<String, Object> var4) throws Exception;
}
