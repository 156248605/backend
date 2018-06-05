package com.elex.oa.controller;
import com.elex.oa.activiti.service.ActRepService;
import com.elex.oa.core.entity.ProcessConfig;
import com.elex.oa.core.entity.StartEventConfig;
import com.elex.oa.dao.*;
import com.elex.oa.entity.*;
import com.elex.oa.entity.activiti.BpmInst;
import com.elex.oa.form.api.FormHandlerFactory;
import com.elex.oa.form.api.IFormHandler;
import com.elex.oa.form.entity.FormModel;
import com.elex.oa.form.impl.formhandler.PcFormHandler;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.service.*;
import com.elex.oa.service.activiti.BpmNodeSetService;
import com.elex.oa.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang.StringUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *@author hugo.zhao
 *@since 2018/5/4 13:47
*/
@Controller
@CrossOrigin
@RequestMapping("/bpm/core/bpmInst/")
public class BpmInstController {

    @Resource
    private IBpmSolutionService bpmSolutionService;

    @Resource
    private IBpmInstDao bpmInstDao;

    @Resource
    private IBpmOpinionTempService bpmOpinionTempService;

    @Resource
    private ActRepService actRepService;

    @Resource
    private IBpmDefService bpmDefService;

    @Resource
    private BpmNodeSetService bpmNodeSetService;

    @Resource
    private IBpmTaskService bpmTaskService;

    @Resource
    private IBpmSolCtlService bpmSolCtlService;

    @Resource
    private GroupService osGroupService;

    @Resource
    private IOsGroupDao osGroupDao;

    @Resource
    private IOsUserDao osUserDao;

    @Resource
    private FormHandlerFactory formHandlerFactory;

    @Resource
    private PcFormHandler pcFormHandler;

    //bpmSolCtlManager

    @RequestMapping("start")
    public String start(HttpServletRequest request,Map<String,Object> map) throws Exception{
        return this.start(request,"",map);
    }

    public String  start(HttpServletRequest request, String solKey,Map<String,Object> map) throws Exception {
        String solId = request.getParameter("solId");
        String instId = request.getParameter("instId");
        String from = request.getParameter("from");
        String userName = ContextUtil.getCurrentUser().getFullname();
        BpmSolution bpmSolution = null;
        String opinion = "";
        String fileIds = "";
        if(StringUtil.isNotEmpty(solId)){
            bpmSolution = this.bpmSolutionService.getById(solId);
        }else if(StringUtil.isNotEmpty(solKey)){
            bpmSolution = this.bpmSolutionService.getByKey(solKey,"1");
            solId = bpmSolution.getSolId();
        }else {
            BpmInst bpmDef = this.bpmInstDao.selectByPrimaryKey(instId);
            bpmSolution = this.bpmSolutionService.getById(bpmDef.getSolId());
            solId = bpmSolution.getSolId();
            map.put("bpmInst",bpmDef);
            BpmOpinionTemp startNodeDef = this.bpmOpinionTempService.getByType("instId", bpmDef.getInstId());
            if(startNodeDef != null){
                opinion = startNodeDef.getOpinion();
                fileIds = startNodeDef.getAttachment();
            }
        }
        BpmDef bpmDef = this.bpmDefService.getValidBpmDef(bpmSolution.getActDefId(), bpmSolution.getDefKey());
        ActNodeDef actNodeDef = this.actRepService.getStartNode(bpmDef.getActDefId());
        ProcessConfig processConfig = this.bpmNodeSetService.getProcessConfig(solId, bpmDef.getActDefId());
        List destNodes = this.actRepService.getStartFlowUserNodes(bpmDef.getActDefId(), "true".equals(processConfig.getIsSkipFirst()));
        Object destNodeUserList = new ArrayList();
        StartEventConfig startEventConfig = this.bpmNodeSetService.getStartEventConfig(bpmSolution.getSolId(), bpmDef.getActDefId(), actNodeDef.getNodeId());
        if("true".equals(startEventConfig.getAllowNextExecutor())) {
            HashMap bpmSolCtls2 = new HashMap();
            bpmSolCtls2.put("startUserId", ContextUtil.getCurrentUserId());
            bpmSolCtls2.put("solId", bpmSolution.getSolId());
            destNodeUserList = this.bpmTaskService.getDestNodeUsers(bpmDef.getActDefId(), destNodes, bpmSolCtls2);
        }
       // List var33 = this.bpmSolCtlManager.getBySolAndTypeAndRight(solId, "FILE", "DOWN");
        List<BpmSolCtl> bpmSolCtls = this.bpmSolCtlService.getBySolAndTypeAndRight(solId, "FILE", "DOWN");
        String ADownGroupIds = "";
        String ADownGroupNames = "";
        String ADownUserIds = "";
        String ADownUserNames = "";
        String alowAttachDownStartor = "false";
        if(bpmSolCtls .size() > 0) {
            ADownGroupIds = (bpmSolCtls.get(0)).getGroupIds();
            ADownUserIds = (bpmSolCtls .get(0)).getUserIds();
            if(StringUtils.isEmpty(ADownGroupIds)) {
                ADownGroupIds = "";
            }

            if(StringUtils.isEmpty(ADownUserIds)) {
                ADownUserIds = "";
            }

            String[] formHandler = ADownGroupIds.split(",");
            StringBuffer formModels = new StringBuffer("");
            String[] userids = ADownUserIds.split(",");
            StringBuffer userNames = new StringBuffer("");
            String[] var27 = formHandler;
            int var28 = formHandler.length;

            int var29;
            String userId;
            for(var29 = 0; var29 < var28; ++var29) {
                userId = var27[var29];
                if(userId.length() > 0) {
                    //formModels.append(((OsGroup)this.osGroupManager.get(userId)).getName());
                    formModels.append(this.osGroupDao.selectByPrimaryKey(userId).getName());
                    formModels.append(",");
                }
            }

            var27 = userids;
            var28 = userids.length;

            for(var29 = 0; var29 < var28; ++var29) {
                userId = var27[var29];
                if(userId.length() > 0) {
                    //userNames.append(((OsUser)this.osUserManager.get(userId)).getFullname());
                    userNames.append(this.osUserDao.selectByPrimaryKey(userId).getFullname());
                    userNames.append(",");
                }
            }

            if(formModels.length() > 0) {
                formModels.deleteCharAt(formModels.length() - 1);
            }

            if(userNames.length() > 0) {
                userNames.deleteCharAt(userNames.length() - 1);
            }

            ADownGroupNames = formModels.toString();
            ADownUserNames = userNames.toString();
            alowAttachDownStartor = (bpmSolCtls.get(0)).getAllowStartor();
        }
        //mv.addObject("alowAttachDownStartor", alowAttachDownStartor);
        map.put("alowAttachDownStartor",alowAttachDownStartor);
       // mv.addObject("ADownGroupIds", ADownGroupIds);
        map.put("ADownGroupIds",ADownGroupIds);
        //mv.addObject("ADownGroupNames", ADownGroupNames);
        map.put("ADownGroupNames",ADownGroupNames);
        //mv.addObject("ADownUserIds", ADownUserIds);
        map.put("ADownUserIds",ADownUserIds);
       // mv.addObject("ADownUserNames", ADownUserNames);
        map.put("ADownUserNames",ADownUserNames);
       // mv.addObject("processConfig", processConfig);
        map.put("processConfig", processConfig);
       // mv.addObject("startEventConfig", startEventConfig);
        map.put("startEventConfig", startEventConfig);
       // mv.addObject("destNodeUserList", destNodeUserList);
        map.put("destNodeUserList", destNodeUserList);
       // mv.addObject("bpmDef", var31);
        map.put("bpmDef", bpmDef);
        //mv.addObject("bpmSolution", bpmSolution);
        map.put("bpmSolution", bpmSolution);
        //mv.addObject("userName", userName);
        map.put("userName", userName);
       // mv.addObject("instId", instId);
        map.put("instId", instId);
       // mv.addObject("from", from);
        map.put("from", from);
       // mv.addObject("opinion", opinion);
        map.put("opinion", opinion);
        //mv.addObject("fileIds", fileIds);
        map.put("fileIds", fileIds);
       // IFormHandler var34 = this.formHandlerFactory.getByType("pc");
        //List var35 = var34.getStartForm(solId, instId, "");
        List<FormModel> formModels = pcFormHandler.getStartForm(solId, instId, "");
        map.put("formModels", formModels);
        if(formModels!=null&&formModels.size()>0){
           map.put("formModel",formModels.get(0));
        }

        //mv.addObject("formModels", var35);
        return "/bpm/core/bpmInstStart";
    }

}
