package com.elex.oa.form.impl.formhandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.elex.oa.dao.IBpmInstDao;
import com.elex.oa.dao.IBpmTaskDao;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.entity.BpmSolution;
import com.elex.oa.entity.activiti.BpmInst;
import com.elex.oa.entity.activiti.BpmTask;
import com.elex.oa.form.entity.FormModel;
import com.elex.oa.service.IBpmFormViewService;
import com.elex.oa.service.IBpmSolutionService;
import com.elex.oa.service.IBpmTaskService;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import org.activiti.engine.RuntimeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class PcFormHandler extends AbstractFormHandler {

    @Resource
    RuntimeService runtimeService;

    @Resource
    IBpmSolutionService bpmSolutionService;

    @Resource
    IBpmFormViewService bpmFormViewService;

    @Resource
    IBpmTaskService bpmTaskService;

    @Resource
    IBpmTaskDao bpmTaskDao;

    @Resource
    IBpmInstDao bpmInstDao;


    public PcFormHandler() {
    }

    public String getType() {
        return "pc";
    }

    public List<FormModel> getStartForm(String solId, String instId, String jsonStr) throws Exception {
       // BpmSolution bpmSolution = (BpmSolution)this.bpmSolutionManager.get(solId);
        BpmSolution bpmSolution = bpmSolutionService.getById(solId);
        //List bpmFormViews = this.bpmFormViewManager.getStartFormView(solId, bpmSolution.getActDefId());
        List bpmFormViews = bpmFormViewService.getStartFormView(solId, bpmSolution.getActDefId());
        ArrayList modelList = new ArrayList();
        if(BeanUtil.isEmpty(bpmFormViews)) {
            FormModel bpmInst1 = new FormModel();
            bpmInst1.setResult(false);
            bpmInst1.setMsg("表单设置为空!");
            modelList.add(bpmInst1);
            return modelList;
        } else {
            BpmInst bpmInst = null;
            if(StringUtil.isNotEmpty(instId)) {
                //bpmInst = (BpmInst)this.bpmInstManager.get(instId);
                bpmInst = this.bpmInstDao.selectByPrimaryKey(instId);
            }

            HashMap vars = new HashMap();
            Iterator var9 = bpmFormViews.iterator();

            while(var9.hasNext()) {
                BpmFormView bpmFormView = (BpmFormView)var9.next();
                FormModel model = new FormModel();
                FormUtil.setFormModelByFormView(model, bpmFormView, bpmInst);
                if(model.getType().equals("SEL-DEV")) {
                    modelList.add(model);
                    return modelList;
                }

                JSONObject jsonData = null;
                if(StringUtil.isEmpty(instId)) {
                    jsonData = FormUtil.getData(bpmSolution, bpmFormView);
                } else {
                    jsonData = FormUtil.getData(bpmInst, bpmFormView, vars);
                }

                model = FormUtil.getByStart(bpmSolution, bpmFormView, jsonData, false, false);
                model.setDescription(bpmFormView.getName());
                model.setBoDefId(bpmFormView.getBoDefId());
                model.setFormKey(bpmFormView.getKey());
                modelList.add(model);
            }

            return modelList;
        }
    }

    public List<FormModel> getByTaskId(String taskId, String jsonStr) throws Exception {
        //BpmTask bpmTask = (BpmTask)this.bpmTaskManager.get(taskId);
        BpmTask bpmTask = this.bpmTaskDao.selectByPrimaryKey(taskId);
        //BpmInst bpmInst = this.bpmInstManager.getByActInstId(bpmTask.getProcInstId());
        BpmInst bpmInst = this.bpmInstDao.getByActInstId(bpmTask.getProcInstId());
        Map vars = this.runtimeService.getVariables(bpmTask.getProcInstId());
        String solId = bpmInst.getSolId();
        String actDefId = bpmInst.getActDefId();
        String nodeId = bpmTask.getTaskDefKey();
        ArrayList list = new ArrayList();
        //List bpmFormViews = this.bpmFormViewManager.getTaskFormViews(solId, actDefId, nodeId, bpmInst.getInstId());
        List bpmFormViews = this.bpmFormViewService.getTaskFormViews(solId, actDefId, nodeId, bpmInst.getInstId());
        //List bpmFormViews =this.bpmFormViewService.get
        if(BeanUtil.isEmpty(bpmFormViews)) {
            return list;
        } else {
            ArrayList rtnModels = new ArrayList();
            Iterator var12 = bpmFormViews.iterator();

            while(var12.hasNext()) {
                BpmFormView fv = (BpmFormView)var12.next();
                if("SEL-DEV".equals(fv.getType())) {
                    FormModel jsonData1 = new FormModel();
                    FormUtil.setFormModelByFormView(jsonData1, fv, bpmInst);
                    rtnModels.add(jsonData1);
                    return rtnModels;
                }

                JSONObject jsonData = FormUtil.getData(bpmInst, fv, vars);
                FormUtil.setOpinionData(bpmInst, jsonData);
                FormModel model = FormUtil.getByTask(bpmInst, bpmTask, fv, jsonData, false, false);
                model.setBoDefId(fv.getBoDefId());
                model.setFormKey(fv.getKey());
                model.setType(fv.getType());
                rtnModels.add(model);
            }

            return rtnModels;
        }
    }

    public List<FormModel> getByInstId(String instId) throws Exception {
        return this.getByInstId(instId, false);
    }

    public FormModel getFormByFormAlias(String alias, String pk, boolean readOnly, Map<String, Object> params) throws Exception {
        FormModel model = FormUtil.getFormByFormAlias(alias, pk, readOnly, false, params);
        return model;
    }
}

