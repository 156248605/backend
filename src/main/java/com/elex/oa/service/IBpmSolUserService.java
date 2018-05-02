package com.elex.oa.service;

import com.elex.oa.entity.BpmSolUser;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/3 14:03
*/
public interface IBpmSolUserService extends BaseService<BpmSolUser> {
   boolean isExistConfig(String solId, String actDefId);
   List<BpmSolUser> getBySolIdActDefIdNodeId(String solId,String actDefId,String nodeId,String groupType);
 }
