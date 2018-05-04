package com.elex.oa.activiti.rest.editor.controller;

import com.elex.oa.activiti.service.ActRepService;
import com.elex.oa.dao.IBpmInstDao;
import com.elex.oa.dao.IBpmRuPathDao;
import com.elex.oa.dao.IBpmTaskDao;
import com.elex.oa.entity.BpmDef;
import com.elex.oa.entity.activiti.BpmInst;
import com.elex.oa.entity.activiti.BpmTask;
import com.elex.oa.service.IBpmDefService;
import com.elex.oa.service.activiti.ActInstService;
import com.elex.oa.service.activiti.BpmRuPathService;
import com.elex.oa.util.ClassLoadUtil;
import com.elex.oa.util.JsaasUtil;
import com.elex.oa.util.StringUtil;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
/**
 *@author hugo.zhao
 *@since 2018/4/8 15:22
*/

@Controller
@RequestMapping({"/bpm/activiti"})
public class ProcessImageController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IBpmDefService bpmDefService;

    @Autowired
    private ActRepService actRepService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IBpmTaskDao bpmTaskDao;

    @Autowired
    private IBpmInstDao bpmInstDao;

    @Autowired
    private ActInstService actInstService;

    @Autowired
    private IBpmRuPathDao bpmRuPathDao;

    @Autowired
    private BpmRuPathService bpmRuPathService;


    @RequestMapping("/processImage")
    public void processImage(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type","text/html;charset=UTF-8");
        resp.setContentType("image/PNG");
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0L);
        String actDefId = req.getParameter("actDefId");
        String actInstId = req.getParameter("actInstId");
        String taskId = req.getParameter("taskId");
        String instId = req.getParameter("instId");
        ServletOutputStream output = resp.getOutputStream();
        if(StringUtil.isNotEmpty(actDefId)) {
            this.getImageStreamFromActDefId(actDefId,output);
        }else if(StringUtil.isNotEmpty(actInstId)){
            ProcessInstance processInstance = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(actInstId).singleResult();
            if(processInstance == null){
                BpmInst bpmInst = bpmInstDao.getByActInstId(actInstId);
                this.getImageStreamFromInstId(bpmInst.getInstId(), output);
            }else {
                this.getImageStreamFromActInstId(processInstance.getProcessInstanceId(), output);

            }
        }else if(StringUtil.isNotEmpty(taskId)){
            this.getImageStreamFromTaskId(taskId,output);
        }else if(StringUtil.isNotEmpty(instId)) {
            this.getImageStreamFromTaskId(taskId,output);
        }else if(StringUtil.isNotEmpty(instId)){
            this.getImageStreamFromInstId(instId,output);

        }
    }
    private void getImageStreamFromActDefId(String actDefId, OutputStream output) throws Exception {
        BpmnModel bpmnModel = this.repositoryService.getBpmnModel(actDefId);
        //BpmDef bpmDef = this.bpmDefManager.getByActDefId(actDefId);
        BpmDef bpmDef = bpmDefService.getByActDefId(actDefId);
        String xml = this.actRepService.getBpmnXmlByDeployId(bpmDef.getActDepId());
        JsaasUtil.generateArea(xml,bpmnModel);
        JsaasUtil.handleImage(bpmnModel,output);
       // ClassLoadUtil.execute("com.elex.oa.util.JsaasUtil", "generateArea", new Object[]{xml, bpmnModel});
       // ClassLoadUtil.execute("com.elex.oa.util.JsaasUtil", "handleImage", new Object[]{bpmnModel, output});
    }
    private void getImageStreamFromTaskId(String taskId, OutputStream output) throws Exception {
        Task task = (Task)((TaskQuery)this.taskService.createTaskQuery().taskId(taskId)).singleResult();
      //  BpmTask bpmTask = (BpmTask)this.bpmTaskManager.get(taskId);
        BpmTask bpmTask = (BpmTask)bpmTaskDao.selectByPrimaryKey(taskId);
        //BpmDef bpmDef = this.bpmDefManager.getByActDefId(bpmTask.getProcDefId());
        BpmDef bpmDef = bpmDefService.getByActDefId(bpmTask.getProcDefId());
        String xml = this.actRepService.getBpmnXmlByDeployId(bpmDef.getActDepId());
        BpmnModel bpmnModel = this.repositoryService.getBpmnModel(task.getProcessDefinitionId());
        ClassLoadUtil.execute("com.elex.oa.util.JsaasUtil", "generateArea", new Object[]{xml, bpmnModel});
        Collection flowIds = this.actInstService.getJumpFlowsByActInstId(task.getProcessInstanceId());
        ArrayList flowIdList = new ArrayList();
        flowIdList.addAll(flowIds);
        Map maps = bpmRuPathService.getBpmInstNodeStatus(bpmTask.getProcInstId());
        ClassLoadUtil.execute("com.elex.oa.util.JsaasUtil", "handleImage", new Object[]{bpmnModel, maps, flowIdList, output});
    }

    private void  getImageStreamFromInstId(String instId, OutputStream output) throws Exception{
        BpmInst bpmInst = bpmInstDao.selectByPrimaryKey(instId);
        BpmnModel bpmnModel = this.repositoryService.getBpmnModel(bpmInst.getActDefId());
        BpmDef bpmDef = (BpmDef)bpmDefService.getByDefId(bpmInst.getDefId());
        String xml = actRepService.getBpmnXmlByDeployId(bpmDef.getActDefId());
        ClassLoadUtil.execute("com.elex.oa.util.JsaasUtil","generateArea", new Object[]{xml, bpmnModel});
        ArrayList flowIdList = new ArrayList();
        if("RUNNING".equals(bpmInst.getStatus())) {
            Collection maps = this.actInstService.getJumpFlowsByActInstId(bpmInst.getActInstId());
            flowIdList.addAll(maps);
        }
        Map maps1 = bpmRuPathService.getBpmInstNodeStatus(bpmInst.getActInstId());
        ClassLoadUtil.execute("com.elex.oa.util.JsaasUtil","handleImage", new Object[]{bpmnModel, maps1, flowIdList, output});
    }

    private void getImageStreamFromActInstId(String actInstId, OutputStream output) throws Exception {
        ProcessInstance processInst = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processInstanceId(actInstId).singleResult();
        BpmnModel bpmnModel = this.repositoryService.getBpmnModel(processInst.getProcessDefinitionId());
        Collection flowIds = this.actInstService.getJumpFlowsByActInstId(actInstId);
        ArrayList flowIdList = new ArrayList();
        flowIdList.addAll(flowIds);
        Map maps =bpmRuPathService.getBpmInstNodeStatus(actInstId);
        ClassLoadUtil.execute("com.elex.oa.util.JsaasUtil", "handleImage", new Object[]{bpmnModel, maps, flowIdList, output});
    }
}
