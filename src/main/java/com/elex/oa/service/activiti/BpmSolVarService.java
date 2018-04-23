package com.elex.oa.service.activiti;

import com.elex.oa.dao.IBpmSolVarDao;
import com.elex.oa.dao.IBpmSolutionDao;
import com.elex.oa.entity.activiti.BpmSolVar;
import com.elex.oa.util.ProcessVarType;
import com.elex.oa.util.TaskVarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/12 15:42
*/
@Service
public class BpmSolVarService {

    @Autowired
    private IBpmSolutionDao bpmSolutionDao;

    @Autowired
    private IBpmSolVarDao bpmSolVarDao;
    public List<BpmSolVar> getNodeAllVars(String solId, String actDefId, String nodeType, String nodeId){
        ArrayList vars = new ArrayList();
        BpmSolVar startUserVar = new BpmSolVar("发起人ID", "startUserId", "String", "_PROCESS");
        BpmSolVar preUserVar = new BpmSolVar(ProcessVarType.PRE_NODE_USERID.getName(), ProcessVarType.PRE_NODE_USERID.getKey(), "String", "_PROCESS");
        BpmSolVar solIdVar = new BpmSolVar("解决方案ID", "solId", "String", "_PROCESS");
        BpmSolVar instIdVar = new BpmSolVar("扩展流程实例ID", "instId", "String", "_PROCESS");
        BpmSolVar alias = new BpmSolVar("事件SQL脚本数据源", ProcessVarType.DB_ALIAS.getKey(), "String", "_PROCESS");
        BpmSolVar busKey = new BpmSolVar("业务主键", ProcessVarType.BUS_KEY.getKey(), "String", "_PROCESS");
        BpmSolVar jsonData = new BpmSolVar(TaskVarType.JSON_DATA.getName(), TaskVarType.JSON_DATA.getKey(), "String", "_PROCESS");
        vars.add(startUserVar);
        vars.add(preUserVar);
        vars.add(solIdVar);
        vars.add(instIdVar);
        vars.add(alias);
        vars.add(busKey);
        vars.add(jsonData);
        List processVars = this.getBySolIdActDefIdNodeId(solId, actDefId, "_PROCESS");
        vars.addAll(processVars);
        if("userTask".equals(nodeType)) {
            List nodeVars = this.getBySolIdActDefIdNodeId(solId, actDefId, nodeId);
            vars.addAll(nodeVars);
            BpmSolVar taskId = new BpmSolVar(TaskVarType.TASKID.getName(), TaskVarType.TASKID.getKey(), "String", "_NODE");
            BpmSolVar taskEntity = new BpmSolVar(TaskVarType.TASK_ENTITY.getName(), TaskVarType.TASK_ENTITY.getKey(), "String", "_NODE");
            BpmSolVar variables = new BpmSolVar(TaskVarType.TASK_VARS.getName(), TaskVarType.TASK_VARS.getKey(), "Map", "_NODE");
            vars.add(taskId);
            vars.add(taskEntity);
            vars.add(variables);
            vars.add(jsonData);
        }
        return vars;
    }

      public List<BpmSolVar> getBySolIdActDefIdNodeId(String solId, String actDefId, String scope){
          BpmSolVar bpmSolVar = new BpmSolVar();
          bpmSolVar.setSolId(solId);
          bpmSolVar.setActDefId(actDefId);
          bpmSolVar.setScope(scope);
          return this.bpmSolVarDao.select(bpmSolVar);
      }
}
