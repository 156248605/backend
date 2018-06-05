package com.elex.oa.form.impl.formhandler;
import com.elex.oa.dao.IBpmInstDao;
import com.elex.oa.dao.IBpmTaskDao;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.entity.BpmSolution;
import com.elex.oa.entity.activiti.BpmInst;
import com.elex.oa.entity.activiti.BpmTask;
import com.elex.oa.form.entity.FormModel;
import com.elex.oa.json.FastjsonUtil;
import com.elex.oa.service.IBpmFormViewService;
import com.elex.oa.service.IBpmSolutionService;
import com.elex.oa.service.IBpmTaskService;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Component
public class PrintFormHandler extends AbstractFormHandler {
    @Resource
    IBpmSolutionService bpmSolutionService;
    @Resource
    IBpmTaskService bpmTaskService;
    @Resource
    //BpmInstManager bpmInstManager;
    IBpmInstDao bpmInstDao;
    @Resource
    IBpmFormViewService bpmFormViewService;
    @Resource
    IBpmTaskDao bpmTaskDao;

    public PrintFormHandler() {
    }

    public String getType() {
        return "print";
    }

    public List<FormModel> getStartForm(String solId, String instId, String jsonStr) throws Exception {
        //BpmSolution bpmSolution = (BpmSolution)this.bpmSolutionManager.get(solId);
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        //List bpmFormViews = this.bpmFormViewManager.getStartFormView(solId, bpmSolution.getActDefId());
        List bpmFormViews = this.bpmFormViewService.getStartFormView(solId, bpmSolution.getActDefId());
        ArrayList formModels = new ArrayList();
        if(BeanUtil.isEmpty(bpmFormViews)) {
            FormModel bpmInst1 = new FormModel();
            bpmInst1.setResult(false);
            bpmInst1.setMsg("表单设置为空");
            formModels.add(bpmInst1);
            return formModels;
        } else {
            BpmInst bpmInst = null;
            if(StringUtil.isNotEmpty(instId)) {
               // bpmInst = (BpmInst)this.bpmInstManager.get(instId);
                bpmInst = this.bpmInstDao.selectByPrimaryKey(instId);
            }

            if(StringUtil.isEmpty(jsonStr)) {
                jsonStr = "{}";
            }

            JSONObject jsonData = JSONObject.parseObject(jsonStr);
            Map map = FormUtil.convertJsonToMap(jsonData);
            Iterator var10 = bpmFormViews.iterator();

            while(var10.hasNext()) {
                BpmFormView bpmFormView = (BpmFormView)var10.next();
                FormModel model = new FormModel();
                FormUtil.setFormModelByFormView(model, bpmFormView, bpmInst);
                if(model.getType().equals("SEL-DEV")) {
                    formModels.add(model);
                } else {
                    JSONObject json = (JSONObject)map.get(bpmFormView.getBoDefId());
                    model = FormUtil.getByStart(bpmSolution, bpmFormView, json, true, true);
                    formModels.add(model);
                }
            }

            return formModels;
        }
    }

    public List<FormModel> getByTaskId(String taskId, String jsonStr) throws Exception {
       // BpmTask bpmTask = (BpmTask)this.bpmTaskManager.get(taskId);
        BpmTask bpmTask = this.bpmTaskDao.selectByPrimaryKey(taskId);
        //BpmInst bpmInst = this.bpmInstManager.getByActInstId(bpmTask.getProcInstId());
        BpmInst bpmInst = this.bpmInstDao.getByActInstId(bpmTask.getProcInstId());
        String solId = bpmInst.getSolId();
        String actDefId = bpmInst.getActDefId();
        String nodeId = bpmTask.getTaskDefKey();
       // List bpmFormViews = this.bpmFormViewManager.getTaskFormViews(solId, actDefId, nodeId, bpmInst.getInstId());
        List bpmFormViews = this.bpmFormViewService.getTaskFormViews(solId, actDefId, nodeId, bpmInst.getInstId());
        ArrayList models = new ArrayList();
        FormModel model = null;
        if(BeanUtil.isEmpty(bpmFormViews)) {
            model = new FormModel();
            model.setResult(false);
            model.setMsg("表单设置为空");
            models.add(model);
            return models;
        } else {
            JSONObject jsonData = JSONObject.parseObject(jsonStr);
            Map map = FormUtil.convertJsonToMap(jsonData);
            Iterator var13 = bpmFormViews.iterator();

            while(var13.hasNext()) {
                BpmFormView bpmFormView = (BpmFormView)var13.next();
                model = new FormModel();
                FormUtil.setFormModelByFormView(model, bpmFormView, bpmInst);
                if(!model.getType().equals("SEL-DEV")) {
                    String boDefId = bpmFormView.getBoDefId();
                    JSONObject dataObj = FormUtil.getData(bpmInst, boDefId);
                    JSONObject newDataObj = (JSONObject)map.get(boDefId);
                    if(newDataObj == null) {
                        newDataObj = new JSONObject();
                    }

                    FastjsonUtil.copyProperties(dataObj, newDataObj);
                    model = FormUtil.getByTask(bpmInst, bpmTask, bpmFormView, dataObj, true, true);
                    models.add(model);
                }
            }

            return models;
        }
    }

    public List<FormModel> getByInstId(String instId) throws Exception {
        return this.getByInstId(instId, true);
    }

    public FormModel getFormByFormAlias(String alias, String pk, boolean readOnly, Map<String, Object> params) throws Exception {
        FormModel model = FormUtil.getFormByFormAlias(alias, pk, true, true, params);
        return model;
    }
}

