package com.elex.oa.controller;

import com.elex.oa.entity.SysDic;
import com.elex.oa.service.IProjectService;
import com.elex.oa.service.ISysDicService;
import io.swagger.annotations.Api;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/2/2 16:40
*/
@Api(value = "Test", description = "test the swagger API")
@Controller
public class testController {
    @Autowired
    private  RepositoryService repositoryService;

    @Autowired
    private  RuntimeService runtimeService;

    @Autowired
    private IProjectService projectService;

/*    @Autowired
    private ISysDicService sysDicService;
    @RequestMapping("/view")
    public List<SysDic> test(Model model){
        return  sysDicService.getByTreeId();
    }*/
    @RequestMapping("/test")
    public void  startProcess(){
        ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey("leaveFlow");
        System.out.println("流程实例ID:"+processInstance.getId());
        System.out.println("活动节点ID:"+processInstance.getActivityId());//正在活动的流程节点
        System.out.println("流程定义ID:"+processInstance.getProcessDefinitionId());
    }
    public void  task(){

    }
    public void queryProcessInstance(){
      String processDefinitionKey = "";
      ProcessInstanceQuery processInstanceQuery =  runtimeService.createProcessInstanceQuery();
    }

    @RequestMapping("/test1")
    public String test1(Map<String,Object> map){
        map.put("name", "[Angel -- 守护天使]");
        return "test";

    }


}
