package com.elex.oa.service;


import com.elex.oa.core.entity.DestNodeUsers;
import com.elex.oa.core.entity.TaskNodeUser;
import com.elex.oa.entity.ActNodeDef;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/5/5 13:18
*/
public interface IBpmTaskService {

    List<DestNodeUsers> getDestNodeUsers(String taskId);

    List<DestNodeUsers> getDestNodeUsers(String actInstId, String nodeId);

    List<DestNodeUsers> getDestNodeUsers(String actDefId, List<ActNodeDef> nodeDefs, Map<String, Object> vars);

    TaskNodeUser calUserNode(String actDefId, ActNodeDef nodeDef, Map<String, Object> vars);



}
