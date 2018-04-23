package com.elex.oa.service.activiti;

import com.elex.oa.dao.IBpmRuPathDao;
import com.elex.oa.entity.BpmRuPath;
import com.elex.oa.entity.activiti.BpmNodeStatus;
import com.elex.oa.util.StringUtil;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/4/10 13:24
*/
@Service
public class BpmRuPathService {

    @Autowired
    private IBpmRuPathDao bpmRuPathDao;

    @Autowired
    private TaskService taskService;


    public Map<String,BpmNodeStatus> getBpmInstNodeStatus(String actInstId){
        HashMap maps = new HashMap();
        HashMap params = new HashMap();
        params.put("actInstId", actInstId);
        List paths = bpmRuPathDao.getLatestByActInstId(params);
        Iterator tasks = paths.iterator();
        while (tasks.hasNext()){
            BpmRuPath path = (BpmRuPath)tasks.next();
            BpmNodeStatus task = new BpmNodeStatus();
            task.setNodeId(path.getNodeId());
            task.setJumpType(path.getJumpType());
            task.setTimeoutStatus(StringUtil.isEmpty(path.getTimeoutStatus())?"0":path.getTimeoutStatus());
            maps.put(path.getNodeId(), task);
        }
        List tasks1 = ((TaskQuery)this.taskService.createTaskQuery().processInstanceId(actInstId)).list();
        Iterator path1 = tasks1.iterator();
        while(path1.hasNext()) {
            Task task1 = (Task)path1.next();
            TaskEntity taskEnt = (TaskEntity)task1;
            BpmNodeStatus bns = (BpmNodeStatus)maps.get(task1.getTaskDefinitionKey());
            if(bns == null) {
                bns = new BpmNodeStatus();
                bns.setNodeId(task1.getTaskDefinitionKey());
                bns.setTimeoutStatus(taskEnt.getTimeoutStatus());
                bns.setJumpType("UNHANDLE");
                maps.put(task1.getTaskDefinitionKey(), bns);
            } else {
                bns.setJumpType("UNHANDLE");
                bns.setTimeoutStatus(taskEnt.getTimeoutStatus());
            }
        }
        return maps;
    }



}
