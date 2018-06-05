package com.elex.oa.core.entity;

import java.util.Map;
import com.alibaba.fastjson.JSONObject;

/**
 *@author hugo.zhao
 *@since 2018/5/5 15:21
*/
public interface IExecutionCmd {
    String FORM_OPINION = "FORM_OPINION_";

    String getDestNodeId();

    Map<String, BpmDestNode> getNodeUserMap();

    String getJsonData();

    Map<String, JSONObject> getBoDataMaps();

    String getNodeId();

    String getHandleNodeId();

    void setNodeId(String var1);

    String getJumpType();

    String getOpinion();

    String getOpinionName();

    String getOpFiles();

    String getBpmInstId();

    Map<String, String> getSkipCheckTaskUserIds();

    String getToken();

    void setToken(String var1);

    void setRunPathId(String var1);

    String getRunPathId();

    String getTimeoutStatus();
}
