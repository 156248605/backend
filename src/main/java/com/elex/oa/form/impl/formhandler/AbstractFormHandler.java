package com.elex.oa.form.impl.formhandler;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.dao.IBpmInstDao;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.entity.BpmInstData;
import com.elex.oa.entity.activiti.BpmInst;
import com.elex.oa.entity.activiti.BpmSolFv;
import com.elex.oa.form.api.IFormHandler;
import com.elex.oa.form.entity.FormModel;
import com.elex.oa.service.IBpmFormViewService;
import com.elex.oa.service.IBpmInstDataService;
import com.elex.oa.util.BeanUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
@Component
public abstract class AbstractFormHandler implements IFormHandler {
    @Resource
   // BpmInstManager bpmInstManager;
    IBpmInstDao bpmInstDao;
    @Resource
   // BpmFormViewManager bpmFormViewManager;
    IBpmFormViewService bpmFormViewService;
    @Resource
   // BpmInstDataManager bpmInstDataManager;
    IBpmInstDataService bpmInstDataService;
    public AbstractFormHandler() {
    }

    public List<FormModel> getByInstId(String instId, boolean isPrint) throws Exception {
       // BpmInst bpmInst = (BpmInst)this.bpmInstManager.get(instId);
        BpmInst bpmInst = this.bpmInstDao.selectByPrimaryKey(instId);
       // List bpmFormViews = this.bpmFormViewManager.getDetailFormView(bpmInst.getSolId(), bpmInst.getActDefId(), instId);
        List bpmFormViews = this.bpmFormViewService.getDetailFormView(bpmInst.getSolId(), bpmInst.getActDefId(), instId);
        if(BeanUtil.isEmpty(bpmFormViews)) {
            return null;
        } else {
            ArrayList list = new ArrayList();
            BpmFormView view = (BpmFormView)bpmFormViews.get(0);
            BpmSolFv bpmSolFv = view.getBpmSolFv();
            if(bpmSolFv.getFormType().equals("SEL-DEV")) {
                FormModel bpmInstDatas1 = new FormModel();
                FormUtil.setFormModelByFormView(bpmInstDatas1, view, bpmInst);
                list.add(bpmInstDatas1);
                return list;
            } else {
                //List bpmInstDatas = this.bpmInstDataManager.getByInstId(instId);
                List bpmInstDatas = this.bpmInstDataService.getByInstId(instId);
                Map viewMap = this.convertToViewMap(bpmFormViews);
                ArrayList rtnModels = new ArrayList();
                Iterator var11 = bpmInstDatas.iterator();

                while(var11.hasNext()) {
                    BpmInstData instData = (BpmInstData)var11.next();
                    String boDefId = instData.getBoDefId();
                    BpmFormView formView = (BpmFormView)viewMap.get(boDefId);
                    if(formView != null) {
                        JSONObject jsonData = FormUtil.getData(bpmInst, boDefId);
                        FormUtil.setOpinionData(bpmInst, jsonData);
                        FormModel model = FormUtil.getByInst(formView, bpmInst, jsonData, true, isPrint);
                        model.setBoDefId(boDefId);
                        model.setFormKey(formView.getKey());
                        rtnModels.add(model);
                    }
                }

                return rtnModels;
            }
        }
    }

    protected Map<String, BpmFormView> convertToViewMap(List<BpmFormView> formViews) {
        HashMap map = new HashMap();
        Iterator var3 = formViews.iterator();

        while(var3.hasNext()) {
            BpmFormView form = (BpmFormView)var3.next();
            map.put(form.getBoDefId(), form);
        }

        return map;
    }
}

