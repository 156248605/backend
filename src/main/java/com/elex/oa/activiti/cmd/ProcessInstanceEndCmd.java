package com.elex.oa.activiti.cmd;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/**
 *@author hugo.zhao
 *@since 2018/4/9 18:09
*/
public class ProcessInstanceEndCmd  implements Command<Void>{

    private String processInstanceId = null;

    public ProcessInstanceEndCmd(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Void execute(CommandContext cmdContext) {
        ExecutionEntity executionEntity = cmdContext.getExecutionEntityManager().findExecutionById(this.processInstanceId);
        ExecutionEntity parentEnt = this.getTopExecution(executionEntity);
        parentEnt.end();
        return null;
    }

    private ExecutionEntity getTopExecution(ExecutionEntity executionEntity) {
        ExecutionEntity parentEnt = executionEntity.getParent();
        return parentEnt == null?executionEntity:this.getTopExecution(parentEnt);
    }
}
