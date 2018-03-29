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
     public PageInfo<BpmDef> query(Map<String,Object> paramMap);
}
