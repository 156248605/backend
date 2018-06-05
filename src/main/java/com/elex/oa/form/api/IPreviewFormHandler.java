package com.elex.oa.form.api;

/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
import com.alibaba.fastjson.JSONObject;

public interface IPreviewFormHandler {
    String previewForm(String var1, String var2, String var3) throws Exception;

    String previewFormById(String var1) throws Exception;

    JSONObject getInitDataByForm(String var1);
}
