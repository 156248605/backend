package com.elex.oa.service;

import com.elex.oa.entity.BpmFormView;
import com.github.pagehelper.PageInfo;

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
}
