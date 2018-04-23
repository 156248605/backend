package com.elex.oa.service;

import com.elex.oa.entity.activiti.BpmSolFv;

/**
 *@author hugo.zhao
 *@since 2018/4/13 10:22
*/
public interface IBpmSolFvService extends BaseService<BpmSolFv>{
    BpmSolFv getBySolIdActDefIdNodeId(String solId,String actDefId,String nodeId);

}
