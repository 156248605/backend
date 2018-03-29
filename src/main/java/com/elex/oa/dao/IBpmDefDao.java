package com.elex.oa.dao;

import com.elex.oa.entity.BpmDef;
import com.elex.oa.service.BaseService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:18
*/
@Mapper
public interface IBpmDefDao extends BaseService<BpmDef> {
    //查询流程定义数据
    public List<BpmDef> query(Map<String,Object> paramMap);
}
