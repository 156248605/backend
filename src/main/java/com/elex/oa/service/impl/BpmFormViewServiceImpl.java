package com.elex.oa.service.impl;

import com.elex.oa.core.entity.BpmNodeJump;
import com.elex.oa.dao.IBpmFormViewDao;
import com.elex.oa.dao.IBpmInstDao;
import com.elex.oa.dao.IBpmNodeSetDao;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.entity.activiti.BpmInst;
import com.elex.oa.entity.activiti.BpmSolFv;
import com.elex.oa.org.model.IUser;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.service.IBpmFormViewService;
import com.elex.oa.service.IBpmNodeJumpService;
import com.elex.oa.service.IBpmSolFvService;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javax.annotation.Resource;
import java.util.*;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:20
*/
@Service
public class BpmFormViewServiceImpl implements IBpmFormViewService {

   // bpmSolFvManager
    @Resource
    private IBpmSolFvService bpmSolFvService;

    @Resource
    private IBpmFormViewDao bpmFormViewDao;

    @Resource
    private IBpmInstDao bpmInstDao;
    //bpmNodeJumpManager
    @Resource
    private IBpmNodeSetDao bpmNodeSetDao;

    //bpmNodeJumpManager
    @Resource
    private IBpmNodeJumpService bpmNodeJumpService;
    public PageInfo<BpmFormView> getByTreeFilterNew(Map<String,String> paramMap) {
        //页码
        int page = Integer.parseInt(paramMap.get("page"));
        //每页显示记录数
        int rows = Integer.parseInt(paramMap.get("rows"));

        PageHelper.startPage(page,rows);

        List<BpmFormView> list = new ArrayList<BpmFormView>();
        list = bpmFormViewDao.getByTreeFilterNew(paramMap);
        return new PageInfo<BpmFormView>(list);

//        return this.bpmFormViewDao.getByTreeFilterNew(map);
    }
    public  BpmFormView getById(String id){
        return  this.bpmFormViewDao.getById(id);
    }

    public boolean isKeyExist(String id){
         BpmFormView bpmFormView = this.bpmFormViewDao.getById(id);
          return  bpmFormView!=null;
    }
    public  void  create(BpmFormView formView){
         bpmFormViewDao.create(formView);
    }
    public int update(BpmFormView formView){
       return   bpmFormViewDao.update(formView);
    }

    //删除表单记录
    public int deleteForm(String viewIds){
        String a[]=viewIds.split(",");
        List<String> list = new ArrayList<String >();
        for(int i=0;i<a.length;i++){
            list.add(a[i]);
        }
        int deleteNum = bpmFormViewDao.deleteForm(list);
        return deleteNum;
    }

    public BpmFormView getLatestByKey(String key, String tenantId){
        Map<String,String> params = new HashMap<>();
        params.put("key",key);
        params.put("tenantId",tenantId);
        BpmFormView formView = this.bpmFormViewDao.getMainByKey(params);
        if(formView == null){
            params.put("tenantId","1");
            formView = formView = this.bpmFormViewDao.getMainByKey(params);
        }
        return  formView;
    }

    @Override
    public List<BpmFormView> getDetailFormView(String solId, String actDefId, String instId) {
        return null;
    }
    private List<BpmFormView> getFormView(String solId, String actDefId, String nodeId, String type, String instId) {
        BpmFormViewServiceImpl.FormConfig config = this.getFormAlias(solId, actDefId, nodeId, type, instId);
        ArrayList formViews = new ArrayList();
        List formList = config.getFormKeys();
        if(BeanUtil.isEmpty(formList)) {
            return formViews;
        } else {
            Iterator var9 = formList.iterator();

            while(var9.hasNext()) {
                String alias = (String)var9.next();
                BpmFormView formView = this.getLatestByKey(alias, ContextUtil.getCurrentTenantId());
                if(formView != null) {
                    formView.setBpmSolFv(config.getBpmSolFv());
                    formViews.add(formView);
                }
            }

            return formViews;
        }
    }

    public BpmFormViewServiceImpl.FormConfig getFormAlias(String solId, String actDefId, String nodeId, String type, String instId) {
        BpmSolFv bpmSolFv = this.bpmSolFvService.getBySolIdActDefIdNodeId(solId, actDefId, nodeId);
        List formList = null;
        String condForms = "";
        if(bpmSolFv != null) {
            condForms = this.getFormConf(bpmSolFv, type);
            formList = this.getFormList(condForms, instId);
        }

        if(BeanUtil.isEmpty(formList)) {
            bpmSolFv = this.bpmSolFvService.getBySolIdActDefIdNodeId(solId, actDefId, "_PROCESS");
            condForms = this.getFormConf(bpmSolFv, type);
        }

        if(StringUtil.isNotEmpty(condForms)) {
            formList = this.getFormList(condForms, instId);
        }

        BpmFormViewServiceImpl.FormConfig config = new BpmFormViewServiceImpl.FormConfig(bpmSolFv, formList);
        return config;
    }


    private String getFormConf(BpmSolFv fv, String type) {
        return "MOBILE".equals(type)?fv.getMobileForms():fv.getCondForms();
    }

    public List<String> getFormList(String formJson, String instId) {
        ArrayList list = new ArrayList();
        if(StringUtil.isEmpty(formJson)) {
            return list;
        } else {
            JSONArray jsonAry = JSONArray.parseArray(formJson);
            if(jsonAry.size() == 0) {
                return list;
            } else {
                IUser curUser = ContextUtil.getCurrentUser();
                String startUserId = "";
                Object handleUsers = null;
                if(StringUtils.isNotEmpty(instId)) {
                    //BpmInst bpmInst = (BpmInst)this.bpmInstDao.get(instId);
                    BpmInst bpmInst = this.bpmInstDao.selectByPrimaryKey(instId);
                    if(BeanUtil.isEmpty(bpmInst)) {
                        handleUsers = new HashSet();
                        startUserId = curUser.getUserId();
                    } else {
                        startUserId = bpmInst.getCreateBy();
                       // handleUsers = this.bpmNodeJumpManager.getNodeHandleUserIds(bpmInst.getActInstId());
                        handleUsers = this.bpmNodeJumpService.getNodeHandleUserIds(bpmInst.getActInstId());
                    }
                } else {
                    handleUsers = new HashSet();
                    startUserId = curUser.getUserId();
                    //startUserId = "1";
                }

                Iterator bpmInst1 = jsonAry.iterator();

                while(bpmInst1.hasNext()) {
                    Object o = bpmInst1.next();
                    JSONObject jsonObj = (JSONObject)o;
                    String formAlias = jsonObj.getString("formAlias");
                    if(!StringUtils.isEmpty(formAlias)) {
                        boolean isContain = this.isContain(jsonObj, (Set)handleUsers, startUserId, curUser);
                        if(isContain) {
                            list.add(formAlias);
                        }
                    }
                }
                return list;
            }
        }
    }

    @Override
    public List<BpmFormView> getStartFormView(String solId, String actDefId) {
        return this.getFormView(solId, actDefId, "_START", "FORM", "");
    }

    @Override
    public List<BpmFormView> getTaskFormViews(String solId, String actDefId, String nodeId, String instId) {
        return this.getFormView(solId, actDefId, nodeId, "FORM", instId);
    }

    private boolean isContain(JSONObject jsonObj, Set<String> handleUsers, String startUserId, IUser curUser) {
        String isAll = jsonObj.getString("isAll");
        String startUser = jsonObj.getString("startUser");
        String userIds = jsonObj.getString("userIds");
        String groupIds = jsonObj.getString("groupIds");
        String attendUsers = jsonObj.getString("attendUsers");
        String curUserId = curUser.getUserId();
        if("true".equals(isAll)) {
            return true;
        } else if("true".equals(startUser) && curUserId.equals(startUserId)) {
            return true;
        } else {
            String[] gIds;
            String[] var12;
            int var13;
            int var14;
            String gId;
            if(userIds != null) {
                gIds = userIds.split("[,]");
                var12 = gIds;
                var13 = gIds.length;

                for(var14 = 0; var14 < var13; ++var14) {
                    gId = var12[var14];
                    if(curUserId.equals(gId)) {
                        return true;
                    }
                }
            }

            if(groupIds != null) {
                gIds = groupIds.split("[,]");
                var12 = gIds;
                var13 = gIds.length;

                for(var14 = 0; var14 < var13; ++var14) {
                    gId = var12[var14];
                    if(curUser.getGroupIds().contains(gId)) {
                        return true;
                    }
                }
            }

            return "true".equals(attendUsers) && handleUsers.contains(curUserId);
        }
    }



    public class FormConfig {
        private BpmSolFv bpmSolFv;
        private List<String> formKeys = new ArrayList();
        private List<BpmFormView> formViews = new ArrayList();

        public FormConfig() {
        }

        public FormConfig(BpmSolFv this$0, List<String> formKeys) {
            this.bpmSolFv = this$0;
            this.formKeys = formKeys;
        }

        public BpmSolFv getBpmSolFv() {
            return this.bpmSolFv;
        }

        public void setBpmSolFv(BpmSolFv bpmSolFv) {
            this.bpmSolFv = bpmSolFv;
        }

        public List<String> getFormKeys() {
            return this.formKeys;
        }

        public void setFormKeys(List<String> formKeys) {
            this.formKeys = formKeys;
        }

        public List<BpmFormView> getFormViews() {
            return this.formViews;
        }

        public void setFormViews(List<BpmFormView> formViews) {
            this.formViews = formViews;
        }
    }


}
