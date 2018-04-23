package com.elex.oa.service.activiti;

import com.elex.oa.activiti.cmd.ProcessInstanceEndCmd;
import com.elex.oa.dao.IBpmInstDao;
import com.elex.oa.dao.IBpmRuPathDao;
import com.elex.oa.entity.BpmRuPath;
import com.elex.oa.entity.activiti.BpmInst;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *@author hugo.zhao
 *@since 2018/4/9 17:57
*/
@Service
public class ActInstService {
    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IBpmRuPathDao bpmRuPath;

    @Autowired
    private IBpmInstDao bpmInstDao;


    public ActInstService() {
    }

    public void endProcessInstance(String bpmnInstanceId) {
        ProcessEngineImpl engine = (ProcessEngineImpl)this.processEngine;
        CommandExecutor cmdExecutor = engine.getProcessEngineConfiguration().getCommandExecutor();
        cmdExecutor.execute(new ProcessInstanceEndCmd(bpmnInstanceId));
    }

    public Collection<String> getJumpFlowsByActInstId(String actInstId){
        HashSet flowIds = new HashSet();
        HashMap pathMap = new HashMap();
        List paths = bpmRuPath.getByActInstId(actInstId);
        Iterator inst = paths.iterator();
        while (inst.hasNext()){
            BpmRuPath processDefEntity = (BpmRuPath)inst.next();
            pathMap.put(processDefEntity.getPathId(),processDefEntity);
        }
        BpmInst inst1 = bpmInstDao.getByActInstId(actInstId);
        ProcessDefinitionEntity processDefEntity1 = (ProcessDefinitionEntity)this.repositoryService.getProcessDefinition(inst1.getActDefId());
        Iterator var7 = paths.iterator();
        while(true) {
            BpmRuPath path;
            BpmRuPath parent;
            do {
                do {
                    if(!var7.hasNext()) {
                        return flowIds;
                    }

                    path = (BpmRuPath)var7.next();
                } while("0".equals(path.getParentId()));

                parent = (BpmRuPath)pathMap.get(path.getParentId());
            } while(parent == null);

            ActivityImpl pvmActivity = processDefEntity1.findActivity(path.getNodeId());
            Iterator var11 = pvmActivity.getIncomingTransitions().iterator();

            while(var11.hasNext()) {
                PvmTransition tran = (PvmTransition)var11.next();
                if(tran.getSource().getId().equals(parent.getNodeId())) {
                    flowIds.add(tran.getId());
                }
            }
        }
    }
}
