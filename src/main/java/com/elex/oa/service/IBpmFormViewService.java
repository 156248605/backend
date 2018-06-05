package com.elex.oa.service;

import com.elex.oa.entity.BpmFormView;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:19
*/
public interface IBpmFormViewService{
     PageInfo<BpmFormView> getByTreeFilterNew(Map<String, String> map);
     BpmFormView getById(String id);
     boolean isKeyExist(String id);
     void create(BpmFormView formView);
     int update(BpmFormView formView);
     //删除表单记录
     int deleteForm(String viewIds);
     BpmFormView getLatestByKey(String key, String tenantId);
     List<BpmFormView> getDetailFormView(String solId, String actDefId, String instId);
     List<String> getFormList(String formJson, String instId);
     List<BpmFormView> getStartFormView(String solId, String actDefId);
     List<BpmFormView> getTaskFormViews(String solId, String actDefId, String nodeId, String instId);
}
