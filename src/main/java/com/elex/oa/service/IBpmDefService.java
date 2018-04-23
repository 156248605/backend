package com.elex.oa.service;

import com.elex.oa.entity.BpmDef;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:19
*/
public interface IBpmDefService {
     //查询流程定义数据
     PageInfo<BpmDef> query(Map<String,Object> paramMap);

     BpmDef getByActDefId(String actDefId);

     BpmDef getLatestBpmByKey(Map<String,String> map);

     BpmDef getByDefId(String DefId);

     BpmDef getValidBpmDef(String actDefId, String defKey);

}
