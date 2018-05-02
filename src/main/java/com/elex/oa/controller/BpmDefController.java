package com.elex.oa.controller;
import com.elex.oa.core.constants.MBoolean;
import com.elex.oa.dao.IBpmDefDao;
import com.elex.oa.entity.BpmDef;
import com.elex.oa.entity.SysTree;
import com.elex.oa.json.JsonResult;
import com.elex.oa.service.IBpmDefService;
import com.elex.oa.service.ISysTreeService;
import com.elex.oa.util.IdGenerator;
import com.elex.oa.util.StringUtil;
import com.elex.oa.util.TimeUtil;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang.StringUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
@Controller
@CrossOrigin
@RequestMapping("/bpmDef")
public class BpmDefController extends GenericController{
    @Autowired
    private IBpmDefService bpmDefService;

    @Autowired
    private ISysTreeService sysTreeService;

    @Autowired
    private IdGenerator  idGenerator;

    @Autowired
    private IBpmDefDao bpmDefDao;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private ObjectMapper objectMapper;

    @ResponseBody
    @PostMapping("/listData")
    public Object bpmFormView(HttpServletRequest request){
        String treeId = request.getParameter("treeId");
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String subject = request.getParameter("subject");
        String key = request.getParameter("key");
        String status = request.getParameter("status");
        if("0".equals(status)){
            status = "INIT";
        }else if("1".equals(status)){
            status = "DEPLOYED";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tenantId","1");//租户ID
        map.put("treeId",treeId);
        map.put("page",page);
        map.put("rows",rows);
        map.put("subject",subject);
        map.put("key",key);
        map.put("status",status);
        PageInfo<BpmDef> pageInfo = this.bpmDefService.query(map);
        return RespUtil.successResp("200","success",pageInfo);
   }

    @ModelAttribute("bpmDef")
    public BpmDef processForm(HttpServletRequest request) {
        String defId = request.getParameter("defId");
        BpmDef bpmDef = null;
        if(StringUtils.isNotEmpty(defId)) {
            bpmDef = this.bpmDefService.getByDefId(defId);
        } else {
            bpmDef = new BpmDef();
            bpmDef.setVersion(Integer.valueOf(1));
            bpmDef.setStatus("INIT");
        }

        return bpmDef;
    }


     @ResponseBody
     @PostMapping("/save")
    public JsonResult save(HttpServletRequest request,@ModelAttribute("bpmDef") @Validated BpmDef bpmDef, BindingResult result) throws Exception{
         if(result.hasFieldErrors()) {
             return new JsonResult(false, this.getErrorMsg(result));
         } else {
             //String tenantId = ContextUtil.getCurrentTenantId();
             String tenantId = "1";
             bpmDef.setTenantId(tenantId);
             //JsonResult rtn = this.bpmDefManager.getCanSave(bpmDef);
             JsonResult rtn = this.bpmDefService.getCanSave(bpmDef);
             if(!rtn.isSuccess()) {
                 return rtn;
             } else {
                 String treeId = request.getParameter("treeId");
                 SysTree msg;
                 if(StringUtil.isNotEmpty(treeId)) {
                     //this.bpmDefManager.detach(bpmDef.getSysTree());
                     msg = this.sysTreeService.getTreeById(treeId);
                     bpmDef.setSysTree(msg);
                 }
                 msg = null;
                 String msg1;
                 if(StringUtils.isEmpty(bpmDef.getDefId())) {
                     bpmDef.setDefId(this.idGenerator.getSID());
                     bpmDef.setMainDefId(this.idGenerator.getSID());
                     bpmDef.setIsMain(MBoolean.YES.toString());
                     bpmDef.setCreateTime(TimeUtil.getCurrentTimeStr());
                     //this.bpmDefManager.create(bpmDef);
                     //新建一个空模型
                     String modelId = this.newModel(bpmDef.getSubject(),bpmDef.getKey(),bpmDef.getDescp());
                     bpmDef.setModelId(modelId);
                     this.bpmDefDao.insert(bpmDef);
                     msg1 = this.getMessage("bpmDef.created", new Object[]{bpmDef.getIdentifyLabel()}, "流程定义成功创建!");
                 } else {
                     //this.bpmDefManager.update(bpmDef);
                     this.bpmDefDao.updateByPrimaryKeySelective(bpmDef);
                     msg1 = this.getMessage("bpmDef.updated", new Object[]{bpmDef.getIdentifyLabel()}, "流程定义成功更新!");
                 }
                 return new JsonResult(true, msg1, bpmDef);
             }
         }
     }
    @RequestMapping("/edit")
    public String  edit(HttpServletRequest request, Map<String,Object> map) throws Exception {
        String pkId = request.getParameter("pkId");
        String forCopy = request.getParameter("forCopy");
        BpmDef bpmDef = null;
        if(StringUtils.isNotEmpty(pkId)) {
           // bpmDef = (BpmDef)this.bpmDefManager.get(pkId);
            bpmDef = this.bpmDefService.getByDefId(pkId);
            if("true".equals(forCopy)) {
                bpmDef.setDefId((String)null);
            }
        } else {
            bpmDef = new BpmDef();
        }
          map.put("bpmDef", bpmDef);
        //return this.getPathView(request).addObject("bpmDef", bpmDef);
        return "/bpmDef/bpmDefEdit";
    }
    //新建一个空模型
     private  String newModel(String name,String key,String desc) throws UnsupportedEncodingException{
         RepositoryService repositoryService = processEngine.getRepositoryService();
         Model model = repositoryService.newModel();
         int revision = 1;
         ObjectNode modelNode = objectMapper.createObjectNode();
         modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
         modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, desc);
         modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

         model.setName(name);
         model.setKey(key);
         model.setMetaInfo(modelNode.toString());

         repositoryService.saveModel(model);
         String id = model.getId();

         //完善ModelEditorSource
         ObjectNode editorNode = objectMapper.createObjectNode();
         editorNode.put("id", "canvas");
         editorNode.put("resourceId", "canvas");
         ObjectNode stencilSetNode = objectMapper.createObjectNode();
         stencilSetNode.put("namespace",
                 "http://b3mn.org/stencilset/bpmn2.0#");
         editorNode.put("stencilset", stencilSetNode);
         repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
//        return new ModelAndView("redirect:/modeler.html?modelId=" + id);
         return  id;
     }




}
