package com.elex.oa.controller;
import com.elex.oa.activiti.service.ActRepService;
import com.elex.oa.dao.IBpmSolFvDao;
import com.elex.oa.entity.*;
import com.elex.oa.entity.activiti.BpmSolFv;
import com.elex.oa.entity.bo.SysBoDef;
import com.elex.oa.json.JsonPageResult;
import com.elex.oa.json.JsonResult;
import com.elex.oa.org.model.IGroup;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.org.service.UserService;
import com.elex.oa.service.*;
import com.elex.oa.util.IdUtil;
import com.elex.oa.util.StringUtil;
import com.elex.oa.util.resp.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/bpmSolution")
public class BpmSolutionController {
      @Autowired
      private ISysTreeService sysTreeService;

      @Autowired
      private IBpmSolutionService bpmSolutionService;

      @Autowired
      private IBpmDefService bpmDefService;

      @Autowired
      private IBpmSolUserService bpmSolUserService;

      @Autowired
      private ISysBoDefService sysBoDefService;

      @Autowired
      private ActRepService actRepService;

      @Autowired
      private IBpmSolFvDao bpmSolFvDao;

      @Autowired
      private IBpmSolCtlService bpmSolCtlService;

      @Autowired
      private UserService userService;

      @Autowired
      private GroupService groupService;

    @RequestMapping("/solutionList")
    @ResponseBody
    public Object solutionList(HttpServletRequest request) {
       String treeId = request.getParameter("treeId");
        Map<String,Object> map = new HashMap<>();
       if(!StringUtils.isEmpty(treeId)){
           SysTree bpmSolutions = (SysTree)sysTreeService.getTreeById(treeId);
           if(bpmSolutions != null){
               String path = bpmSolutions.getPath();
               map.put("TREE_PATH_",path);
           }
       }
       map.put("rightType", "start");
       map.put("profileMap",new HashMap());
       map.put("TENANT_ID_","1");
      // List bpmSolutions1 = bpmSolutionService.getSolutions(map);
        List bpmSolutions1 = bpmSolutionService.getSolutionsByAdmin(map);
       return RespUtil.successResp("200","success",bpmSolutions1);
   }

    @RequestMapping("/mgr")
    public String mgr(HttpServletRequest request,Map<String,Object> map){
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        BpmSolution bpmSolution = bpmSolutionService.getById(solId);
        bpmSolution.setSysTree(sysTreeService.getTreeById(bpmSolution.getTreeId()));
        BpmDef bpmDef = null;
        boolean isBindFlow;
        if(StringUtil.isNotEmpty(actDefId)){
            bpmDef = bpmDefService.getByActDefId(actDefId);
            if(bpmSolution.getActDefId().equals(actDefId)){
                isBindFlow = bpmSolUserService.isExistConfig(solId,actDefId);
                if(!isBindFlow){
                    map.put("allowCopyConfig",true);
                }
            }
        }else  if(StringUtil.isNotEmpty(bpmSolution.getActDefId())){
            bpmDef = bpmDefService.getByActDefId(bpmSolution.getActDefId());
        }/*else  if(StringUtil.isNotEmpty(bpmSolution.getDefKey())) {
            bpmDef = this.bpmDefManager.getLatestBpmByKey(bpmSolution.getDefKey(), ContextUtil.getCurrentTenantId());
        }*/
         isBindFlow = StringUtil.isNotEmpty(bpmSolution.getActDefId());
         map.put("bpmDef", bpmDef);
         map.put("isBindFlow", String.valueOf(isBindFlow));
         map.put("bpmSolution", bpmSolution);
        //return RespUtil.successResp("200","success",map);
        return "/bpmSolution/bpmSolutionMgr";
    }
    @RequestMapping("/updStep")
    @ResponseBody
    public Object updStep(HttpServletRequest request){
        String solId = request.getParameter("solId");
        String step = request.getParameter("step");
        BpmSolution bpmSolution = bpmSolutionService.getById(solId);
        bpmSolution.setStep(Integer.valueOf(step));
        this.bpmSolutionService.update(bpmSolution);
        return  RespUtil.successResp("200","success","成功更新步骤");
    }
    @RequestMapping("/bpmDef")
    public String bpmDef(HttpServletRequest request,Map<String,Object> map){
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        map.put("solId",solId);
        map.put("actDefId",actDefId);
        BpmSolution bpmSolution = bpmSolutionService.getById(solId);
        BpmDef bpmDef = null;
        if(StringUtil.isNotEmpty(actDefId)) {
            bpmDef = bpmDefService.getByActDefId(actDefId);
        }else if(StringUtil.isNotEmpty(bpmSolution.getDefKey())){
           /* Map<String,String> params = new HashMap<>();
            params.put("tenantId","1");
            params.put("key",bpmSolution.getDefKey());
            params.put("status","DEPLOYED");
            params.put("isMain","YES");*/
            bpmDef = bpmDefService.getLatestBpmByKey(bpmSolution.getDefKey(),"1");
        }
        map.put("bpmDef", bpmDef);
        map.put("bpmSolution", bpmSolution);
        return "/bpmSolution/bpmSolutionBpmDef";
    }
    @RequestMapping("/formView")
    public String formView(HttpServletRequest request, HttpServletResponse response, Map<String,Object> map) throws Exception {
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        //BpmSolution bpmSolution = (BpmSolution)this.bpmSolutionManager.get(solId);
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        String bodefId = bpmSolution.getBoDefId();
        String dataSaveMode = bpmSolution.getDataSaveMode();
        BpmDef bpmDef = this.bpmDefService.getByActDefId(actDefId);
        map.put("bpmDef", bpmDef);
        map.put("bodefId", bodefId);
        map.put("dataSaveMode", dataSaveMode);
        if(StringUtil.isNotEmpty(bodefId)) {
            String[] aryBoDefId = bodefId.split(",");
            String bodefName = "";

            for(int i = 0; i < aryBoDefId.length; ++i) {
                String name = this.getBoName(aryBoDefId[i]);
                if(i > 0) {
                    bodefName = bodefName + ",";
                }

                bodefName = bodefName + name;
            }

            map.put("bodefName", bodefName);
        }
        map.put("bpmSolution", bpmSolution);
        map.put("solId",solId);
        map.put("actDefId",actDefId);
        return "/bpmSolution/bpmSolutionFormView";
    }

    @RequestMapping("/getNodeFormView")
    @ResponseBody
    public JsonPageResult<BpmSolFv> getNodeFormView(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList fvList = new ArrayList();
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        //BpmSolution bpmSolution = (BpmSolution)this.bpmSolutionManager.get(solId);
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        //BpmDef bpmDef = this.bpmDefManager.getValidBpmDef(actDefId, bpmSolution.getDefKey());
        BpmDef bpmDef = this.bpmDefService.getValidBpmDef(actDefId,bpmSolution.getDefKey());
        Collection taskDefs = this.actRepService.getTaskNodes(bpmDef.getActDefId());
        ArrayList allNodeDefs = new ArrayList();
        allNodeDefs.add(new ActNodeDef("_PROCESS", "全局", "Process"));
        allNodeDefs.add(new ActNodeDef("_START", "开始", "Start"));
        allNodeDefs.add(new ActNodeDef("_DETAIL", "明细", "Detail"));
        allNodeDefs.addAll(taskDefs);
       /* QueryFilter queryFilter = this.getQueryFilter(request);
        queryFilter.addParam("solId", solId);
        queryFilter.addParam("actDefId", actDefId);*/
       //List solFvList = this.bpmSolFvManager.getBySolutionId(solId, actDefId);
        Map<String,String> queryFilter = new HashMap<>();
        queryFilter.put("solId",solId);
        queryFilter.put("actDefId",actDefId);
        List solFvList = this.bpmSolFvDao.getBySolutionId(queryFilter);
        Map fvMap = this.convertToMap(solFvList);
        BpmSolFv fv;
        for(Iterator var13 = allNodeDefs.iterator(); var13.hasNext(); fvList.add(fv)) {
            ActNodeDef def = (ActNodeDef)var13.next();
            String nodeId = def.getNodeId();
            fv = (BpmSolFv)fvMap.get(nodeId);
            if(fv == null) {
                fv = new BpmSolFv();
                fv.setNodeId(def.getNodeId());
                fv.setFormType("ONLINE-DESIGN");
            }

            fv.setNodeText(def.getNodeName());
            if(!"userTask".equals(def.getNodeType())) {
                fv.setGroupTitle(def.getNodeName() + "表单");
            } else {
                fv.setGroupTitle("节点表单");
            }
        }
        return new JsonPageResult(fvList, Integer.valueOf(fvList.size()));
    }
    @RequestMapping("/getEmptyNodeFormView")
    @ResponseBody
    public JsonPageResult<BpmSolFv> getEmptyNodeFormView(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArrayList fvList = new ArrayList();
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        //BpmSolution bpmSolution = (BpmSolution)this.bpmSolutionManager.get(solId);
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        //BpmDef bpmDef = this.bpmDefManager.getValidBpmDef(actDefId, bpmSolution.getDefKey());
        BpmDef bpmDef = this.bpmDefService.getValidBpmDef(actDefId, bpmSolution.getDefKey());
        Collection taskDefs = this.actRepService.getTaskNodes(bpmDef.getActDefId());
        ArrayList allNodeDefs = new ArrayList();
        allNodeDefs.add(new ActNodeDef("_PROCESS", "全局", "Process"));
        allNodeDefs.add(new ActNodeDef("_START", "开始", "Start"));
        allNodeDefs.add(new ActNodeDef("_DETAIL", "明细", "Detail"));
        allNodeDefs.addAll(taskDefs);
        ArrayList solFvList = new ArrayList();
        Map fvMap = this.convertToMap(solFvList);

        BpmSolFv fv;
        for(Iterator var12 = allNodeDefs.iterator(); var12.hasNext(); fvList.add(fv)) {
            ActNodeDef def = (ActNodeDef)var12.next();
            String nodeId = def.getNodeId();
            fv = (BpmSolFv)fvMap.get(nodeId);
            if(fv == null) {
                fv = new BpmSolFv();
                fv.setNodeId(def.getNodeId());
                fv.setFormType("ONLINE-DESIGN");
            }

            fv.setNodeText(def.getNodeName());
            if(!"userTask".equals(def.getNodeType())) {
                fv.setGroupTitle(def.getNodeName() + "表单");
            } else {
                fv.setGroupTitle("节点表单");
            }
        }

        return new JsonPageResult(fvList, Integer.valueOf(fvList.size()));
    }
    @RequestMapping("/saveFormView")
    @ResponseBody
    public JsonResult saveFormView(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        String boDefId = request.getParameter("boDefId");
        String dataSaveMode = request.getParameter("dataSaveMode");
        this.saveBoSetting(solId, boDefId, dataSaveMode);
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        //this.bpmSolFvManager.delBySolIdActDefId(solId, actDefId);
        BpmSolFv bpmSolFv = new BpmSolFv();
        bpmSolFv.setSolId(solId);
        bpmSolFv.setActDefId(actDefId);
        this.bpmSolFvDao.delete(bpmSolFv);
        String gridJson = request.getParameter("gridJson");
        JSONArray arr = JSONArray.fromObject(gridJson);
        boolean supportMobile = false;
        for(int i = 0; i < arr.size(); ++i) {
            JSONObject obj = arr.getJSONObject(i);
            String tabRights = obj.get("tabRights").toString();
            obj.remove("tabRights");
            BpmSolFv fv = (BpmSolFv)com.alibaba.fastjson.JSONObject.parseObject(obj.toString(), BpmSolFv.class);
            fv.setId(IdUtil.getId());
            fv.setActDefId(actDefId);
            fv.setBpmSolution(bpmSolution);
            fv.setTabRights(tabRights);
            if(StringUtil.isNotEmpty(fv.getMobileForms())) {
                supportMobile = true;
            }
           // this.bpmSolFvManager.create(fv);
              this.bpmSolFvDao.insert(fv);
        }
        bpmSolution.setSupportMobile(supportMobile?1:0);
        //this.bpmSolutionManager.update(bpmSolution);
        this.bpmSolutionService.update(bpmSolution);
        return new JsonResult(true, "成功保存!");
    }

    @RequestMapping("/vars")
    public String vars(HttpServletRequest request, HttpServletResponse response,Map<String,Object> map) throws Exception{
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        BpmDef bpmDef = this.bpmDefService.getByActDefId(actDefId);
        map.put("bpmDef", bpmDef);
        map.put("bpmSolution", bpmSolution);
        return "/bpmSolution/bpmSolutionVars";
    }
    @RequestMapping("/user")
    public String user (HttpServletRequest request, HttpServletResponse response,Map<String,Object> map) throws Exception{
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        String nodeId = request.getParameter("nodeId");
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        BpmDef bpmDef = this.bpmDefService.getValidBpmDef(actDefId,bpmSolution.getDefKey());
        map.put("bpmSolution", bpmSolution);
        map.put("bpmDef", bpmDef);
        map.put("solId",solId);
        map.put("actDefId",actDefId);
        map.put("nodeId",nodeId);
        return "/bpmSolution/bpmSolutionUser";
    }

    @RequestMapping("/nodeSet")
    public String nodeSet(HttpServletRequest request, Map<String,Object> map) throws Exception {
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        map.put("bpmSolution", bpmSolution);
        BpmDef bpmDef = this.bpmDefService.getValidBpmDef(actDefId, bpmSolution.getDefKey());
        map.put("bpmDef", bpmDef);
        return "/bpmSolution/bpmSolutionNodeSet";
    }
    @RequestMapping("/grant")
    public String grant(HttpServletRequest request, HttpServletResponse response,Map<String,Object> v) throws Exception {
        String solId = request.getParameter("solId");
        v.put("solId",solId);
        BpmSolution bpmSolution = this.bpmSolutionService.getById(solId);
        List bpmSolCtlsRead = this.bpmSolCtlService.getBySolIdAndType(solId,"READ");
        String bpmSolCtlsAPrint;
        String groupIds;
        if(bpmSolCtlsRead.size() > 0) {
            if("ALL".equals(((BpmSolCtl)bpmSolCtlsRead.get(0)).getRight())) {
                v.put("readUserNames", "");
                v.put("readUserIds", "");
                v.put("readGroupNames", "");
                v.put("readGroupIds", "");
                v.put("grantReadAllOrNot", Integer.valueOf(0));
            } else {
                String bpmSolCtlsADown = ((BpmSolCtl)bpmSolCtlsRead.get(0)).getUserIds();
                bpmSolCtlsAPrint = ((BpmSolCtl)bpmSolCtlsRead.get(0)).getGroupIds();
                Map userIds;
                if(StringUtil.isNotEmpty(bpmSolCtlsADown)) {
                    userIds = this.getNameByIds(bpmSolCtlsADown);
                    groupIds = (String)userIds.get("userNames");
                    bpmSolCtlsADown = (String)userIds.get("userIds");
                    v.put("readUserNames", groupIds.toString());
                    v.put("readUserIds", bpmSolCtlsADown);
                    v.put("grantReadAllOrNot", Integer.valueOf(1));
                } else {
                    v.put("readUserNames", "");
                    v.put("readUserIds", "");
                    v.put("grantReadAllOrNot", Integer.valueOf(1));
                }

                if(StringUtil.isNotEmpty(bpmSolCtlsAPrint)) {
                    userIds = this.getGroupNameByIds(bpmSolCtlsAPrint);
                    groupIds = (String)userIds.get("groupNames");
                    bpmSolCtlsAPrint = (String)userIds.get("groupIds");
                    v.put("readGroupNames", groupIds.toString());
                    v.put("readGroupIds", bpmSolCtlsAPrint);
                    v.put("grantReadAllOrNot", Integer.valueOf(1));
                } else {
                    v.put("readGroupNames", "");
                    v.put("readGroupIds", "");
                    v.put("grantReadAllOrNot", Integer.valueOf(1));
                }
            }

               v.put("alowReadStartor", ((BpmSolCtl)bpmSolCtlsRead.get(0)).getAllowStartor());
               v.put("alowReadAttend", ((BpmSolCtl)bpmSolCtlsRead.get(0)).getAllowAttend());
        } else {
               v.put("grantReadAllOrNot", Integer.valueOf(0));
               v.put("alowReadStartor", "false");
        }
        List bpmSolCtlsADown1 = this.bpmSolCtlService.getBySolAndTypeAndRight(solId, "FILE", "DOWN");
        String userIds1;
        if(bpmSolCtlsADown1.size() > 0) {
            if("ALL".equals(((BpmSolCtl)bpmSolCtlsADown1.get(0)).getRight())) {
                v.put("ADownUserNames", "");
                v.put("ADownUserIds", "");
                v.put("ADownGroupNames", "");
                v.put("ADownUserIds", "");
            } else {
                bpmSolCtlsAPrint = ((BpmSolCtl)bpmSolCtlsADown1.get(0)).getUserIds();
                userIds1 = ((BpmSolCtl)bpmSolCtlsADown1.get(0)).getGroupIds();
                String map;
                Map groupIds1;
                if(StringUtil.isNotEmpty(bpmSolCtlsAPrint)) {
                    groupIds1 = this.getNameByIds(bpmSolCtlsAPrint);
                    map = (String)groupIds1.get("userNames");
                    bpmSolCtlsAPrint = (String)groupIds1.get("userIds");
                    v.put("ADownUserNames", map.toString());
                    v.put("ADownUserIds", bpmSolCtlsAPrint);
                } else {
                    v.put("ADownUserNames", "");
                    v.put("ADownUserIds", "");
                }

                if(StringUtil.isNotEmpty(userIds1)) {
                    groupIds1 = this.getGroupNameByIds(userIds1);
                    map = (String)groupIds1.get("groupNames");
                    userIds1 = (String)groupIds1.get("groupIds");
                    v.put("ADownGroupNames", map);
                    v.put("ADownGroupIds", userIds1);
                } else {
                    v.put("ADownGroupNames", "");
                    v.put("ADownGroupIds", "");
                }
            }

            v.put("alowAttachDownStartor", ((BpmSolCtl)bpmSolCtlsADown1.get(0)).getAllowStartor());
        } else {
            v.put("ADownGroupNames", "");
            v.put("ADownGroupIds", "");
            v.put("ADownGroupNames", "");
            v.put("ADownGroupIds", "");
            v.put("alowAttachDownStartor", "false");
        }

        List bpmSolCtlsAPrint1 = this.bpmSolCtlService.getBySolAndTypeAndRight(solId, "FILE", "PRINT");
        if(bpmSolCtlsAPrint1.size() > 0) {
            if("ALL".equals(((BpmSolCtl)bpmSolCtlsAPrint1.get(0)).getRight())) {
                v.put("APrintUserNames", "");
                v.put("APrintUserIds", "");
                v.put("APrintGroupNames", "");
                v.put("APrintGroupIds", "");
            } else {
                userIds1 = ((BpmSolCtl)bpmSolCtlsAPrint1.get(0)).getUserIds();
                groupIds = ((BpmSolCtl)bpmSolCtlsAPrint1.get(0)).getGroupIds();
                String groupNames;
                Map map1;
                if(StringUtil.isNotEmpty(userIds1)) {
                    map1 = this.getNameByIds(userIds1);
                    groupNames = (String)map1.get("userNames");
                    userIds1 = (String)map1.get("userIds");
                    v.put("APrintUserNames", groupNames.toString());
                    v.put("APrintUserIds", userIds1);
                } else {
                    v.put("APrintUserNames", "");
                    v.put("APrintUserIds", "");
                }

                if(StringUtil.isNotEmpty(groupIds)) {
                    map1 = this.getGroupNameByIds(groupIds);
                    groupNames = (String)map1.get("groupNames");
                    groupIds = (String)map1.get("groupIds");
                    v.put("APrintGroupNames", groupNames.toString());
                    v.put("APrintGroupIds", groupIds);
                } else {
                    v.put("APrintGroupNames", "");
                    v.put("APrintGroupIds", "");
                }
            }

            v.put("alowAttachPrintStartor", ((BpmSolCtl)bpmSolCtlsAPrint1.get(0)).getAllowStartor());
        } else {
            v.put("APrintGroupNames", "");
            v.put("APrintGroupIds", "");
            v.put("APrintGroupNames", "");
            v.put("APrintGroupIds", "");
            v.put("alowAttachPrintStartor", "false");
        }

        return "/bpmSolution/bpmSolutionGrant";
    }

    private Map<String, String> getNameByIds(String userIds) {
        ArrayList listUserId = new ArrayList();
        ArrayList listUserName = new ArrayList();
        String[] aryUserId = userIds.split(",");
        String[] map = aryUserId;
        int var6 = aryUserId.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String userId = map[var7];
            IUser osUser = this.userService.getByUserId(userId);
            if(osUser != null) {
                listUserId.add(userId);
                listUserName.add(osUser.getFullname());
            }
        }

        HashMap var10 = new HashMap();
        var10.put("userIds", StringUtil.join(listUserId, ","));
        var10.put("userNames", StringUtil.join(listUserName, ","));
        return var10;
    }
    private Map<String, String> getGroupNameByIds(String groupIds) {
        ArrayList listGroupId = new ArrayList();
        ArrayList listGroupName = new ArrayList();
        String[] aryGroupId = groupIds.split(",");
        String[] map = aryGroupId;
        int var6 = aryGroupId.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String groupId = map[var7];
            IGroup group = this.groupService.getById(groupId);
            if(group != null) {
                listGroupId.add(groupId);
                listGroupName.add(group.getIdentityName());
            }
        }

        HashMap var10 = new HashMap();
        var10.put("groupIds", StringUtil.join(listGroupId, ","));
        var10.put("groupNames", StringUtil.join(listGroupName, ","));
        return var10;
    }




    private Map<String, BpmSolFv> convertToMap(List<BpmSolFv> solFvList) {
        HashMap map = new HashMap();
        Iterator var3 = solFvList.iterator();
        while(var3.hasNext()) {
            BpmSolFv fv = (BpmSolFv)var3.next();
            map.put(fv.getNodeId(), fv);
        }
        return map;
    }
    private String getBoName(String boDefId) {
        if(StringUtil.isEmpty(boDefId)) {
            return "";
        } else {
            SysBoDef def = this.sysBoDefService.get(boDefId);
            return def != null?def.getName():"";
        }
    }

    private void saveBoSetting(String solId, String boDefId, String dataSaveMode) {
        BpmSolution solution = this.bpmSolutionService.getById(solId);
        solution.setBoDefId(boDefId);
        solution.setDataSaveMode(dataSaveMode);
        this.bpmSolutionService.update(solution);
    }

}
