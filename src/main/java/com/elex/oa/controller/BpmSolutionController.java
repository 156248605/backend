package com.elex.oa.controller;
import com.elex.oa.dao.IBpmSolFvDao;
import com.elex.oa.entity.ActNodeDef;
import com.elex.oa.entity.BpmDef;
import com.elex.oa.entity.BpmSolution;
import com.elex.oa.entity.SysTree;
import com.elex.oa.entity.activiti.BpmSolFv;
import com.elex.oa.entity.bo.SysBoDef;
import com.elex.oa.json.JsonPageResult;
import com.elex.oa.service.*;
import com.elex.oa.service.activiti.ActRepService;
import com.elex.oa.util.StringUtil;
import com.elex.oa.util.resp.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public Object bpmDef(HttpServletRequest request){
        String solId = request.getParameter("solId");
        String actDefId = request.getParameter("actDefId");
        BpmSolution bpmSolution = bpmSolutionService.getById(solId);
        BpmDef bpmDef = null;
        if(StringUtil.isNotEmpty(actDefId)) {
            bpmDef = bpmDefService.getByActDefId(actDefId);
        }else if(StringUtil.isNotEmpty(bpmSolution.getDefKey())){
            Map<String,String> map = new HashMap<>();
            map.put("tenantId","1");
            map.put("key",bpmSolution.getDefKey());
            map.put("status","DEPLOYED");
            map.put("isMain","YES");
            bpmDef = bpmDefService.getLatestBpmByKey(map);
        }
        return  RespUtil.successResp("200","success",bpmDef);
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
}
