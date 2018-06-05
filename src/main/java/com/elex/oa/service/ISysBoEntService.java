package com.elex.oa.service;
import com.elex.oa.entity.bo.SysBoEnt;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/13 11:50
*/
public interface ISysBoEntService extends BaseService<SysBoEnt>  {
    SysBoEnt getByBoDefId(String boDefId);

    JSONObject getInitData(SysBoEnt boEnt);

    List<SysBoEnt> getListByBoDefId(String boDefId, boolean needAttr);
}
