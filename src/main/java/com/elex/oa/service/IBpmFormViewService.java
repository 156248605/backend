package com.elex.oa.service;

import com.elex.oa.entity.BpmFormView;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/14 9:33
*/
public interface IBpmFormViewService extends BaseService<BpmFormView> {
    //查询表单数据
    public PageInfo<BpmFormView> getBpmFormView(Map<String, Object> map);
}
