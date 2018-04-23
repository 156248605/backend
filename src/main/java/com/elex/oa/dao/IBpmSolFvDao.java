package com.elex.oa.dao;

import com.elex.oa.entity.activiti.BpmSolFv;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/4/13 10:06
*/
@Mapper
public interface IBpmSolFvDao extends BaseDao<BpmSolFv> {
    BpmSolFv getBySolIdActDefIdNodeId(String solId,String actDefId,String nodeId);
    //List<BpmSolFv> getBySolutionId(String solId, String actDefId);
    List<BpmSolFv> getBySolutionId(Map<String,String> map);
}
