package com.elex.oa.dao;

import com.elex.oa.entity.BpmFormView;
import com.elex.oa.service.BaseService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:18
*/
@Mapper
public interface IBpmFormViewDao extends BaseDao<BpmFormView> {
    List<BpmFormView> getByTreeFilterNew(Map<String, String> map);

    BpmFormView getById(String id);

    void  create(BpmFormView formView);

    int  update(BpmFormView formView);
    //批量删除表单记录
    int deleteForm(List<String> list);

    BpmFormView getMainByKey(String key, String tenantId);



}
