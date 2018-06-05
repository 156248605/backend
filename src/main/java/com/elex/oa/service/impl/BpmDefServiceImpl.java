package com.elex.oa.service.impl;
import com.elex.oa.activiti.service.ActRepService;
import com.elex.oa.core.constants.MBoolean;
import com.elex.oa.core.entity.StartEventConfig;
import com.elex.oa.entity.activiti.BpmEventConfig;
import com.elex.oa.json.JSONUtil;
import com.elex.oa.json.JsonResult;
import com.elex.oa.json.JsonResultUtil;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.IdUtil;
import com.elex.oa.util.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.elex.oa.dao.IBpmDefDao;
import com.elex.oa.entity.BpmDef;
import com.elex.oa.service.IBpmDefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.activiti.engine.RepositoryService;
import java.io.IOException;
import java.util.*;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:20
*/
@Service
public class BpmDefServiceImpl implements IBpmDefService {
    @Autowired
    private IBpmDefDao bpmDefDao;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ActRepService actRepService;
    //查询流程定义数据
    public PageInfo<BpmDef> query(Map<String,Object> paramMap){
        //页码
        int page = Integer.parseInt(paramMap.get("page").toString());
        //每页显示记录数
        int rows = Integer.parseInt(paramMap.get("rows").toString());

        PageHelper.startPage(page,rows);

        List<BpmDef> list = new ArrayList<>();
        list = bpmDefDao.query(paramMap);

        return new PageInfo<BpmDef>(list);
    }

    public BpmDef getByActDefId(String actDefId){
        return bpmDefDao.getByActDefId(actDefId);
    }

    public BpmDef getLatestBpmByKey(String key, String tenantId){
        BpmDef  bpmDef = new BpmDef();
        bpmDef.setTenantId(tenantId);
        bpmDef.setKey(key);
        bpmDef.setStatus("DEPLOYED");
        bpmDef.setIsMain(MBoolean.YES.name());
        BpmDef maxVersion = this.bpmDefDao.selectOne(bpmDef);
        if(maxVersion != null) {
            return maxVersion;
        }
        Map<String,String> map = new HashMap<>();
        map.put("tenantId",tenantId);
        map.put("key",key);
        map.put("status","DEPLOYED");
        Integer maxVersion1=this.bpmDefDao.getMaxVersions(map);
        if(maxVersion1 !=null && maxVersion1 > 0){
            bpmDef.setStatus(null);
            return this.bpmDefDao.selectOne(bpmDef);
        }else {
            return null;
        }
    }

    public BpmDef getByDefId(String DefId){
        return bpmDefDao.getByDefId(DefId);
    }

    public BpmDef getValidBpmDef(String actDefId, String defKey) {
        BpmDef bpmDef = null;
        if(StringUtil.isNotEmpty(actDefId)) {
            bpmDef = this.getByActDefId(actDefId);
            if(bpmDef != null) {
                return bpmDef;
            }
        }
        if(StringUtil.isNotEmpty(defKey)) {
            bpmDef = this.getLatestBpmByKey(defKey,"1");
        }
        return bpmDef;
    }

    @Override
    public BpmDef getByModelId(String modelId) {
        BpmDef bpmDef = new BpmDef();
        bpmDef.setModelId(modelId);
        return bpmDefDao.selectOne(bpmDef);
    }

    @Override
    public void updateDefAndModifyActivitiDef(Model model, String name, String description, String designJson) throws Exception {
      this.updateModel(model,name,description,designJson);
      BpmDef bpmDef = this.getByModelId(model.getId());
      if(bpmDef != null){
          bpmDef.setSubject(name);
          bpmDef.setDescp(description);
          this.bpmDefDao.updateByPrimaryKey(bpmDef);
      }
      if(StringUtil.isNotEmpty(bpmDef.getActDepId())){
          String data = new String(this.repositoryService.getModelEditorSource(model.getId()),"UTF-8");
          ObjectNode modelNode = (ObjectNode)this.objectMapper.readTree(data);
          this.modifySignProperties(modelNode);
          BpmnModel bpmModel =   (new BpmnJsonConverter()).convertToBpmnModel(modelNode);
          byte[] bpmnBytes = (new BpmnXMLConverter()).convertToXML(bpmModel, "UTF-8");
          this.actRepService.doModifyXmlAndClearCache(bpmDef.getActDefId(), bpmDef.getActDepId(), new String(bpmnBytes, "UTF-8"));
      }
    }

    @Override
    public void doDeployModelAndUpdDef(Model model, String name, String description, String designJson) throws Exception {
        this.updateModel(model, name, description, designJson);
        Deployment deployment = this.doDeployModel(model.getId());
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        BpmDef bpmDef = this.getByModelId(model.getId());
        if(bpmDef != null) {
            bpmDef.setActDepId(deployment.getId());
            bpmDef.setActDefId(processDefinition.getId());
            bpmDef.setSubject(name);
            bpmDef.setDescp(description);
            bpmDef.setStatus("DEPLOYED");
            this.bpmDefDao.updateByPrimaryKey(bpmDef);
        }
    }

    @Override
    public void doDeployNewVersion(Model model, String name, String description, String designJson) throws Exception {
        ObjectNode modelJson = (ObjectNode)this.objectMapper.readTree(model.getMetaInfo());
        Model newModel = this.repositoryService.newModel();
        modelJson.put("name", name);
        modelJson.put("description", description);
        newModel.setMetaInfo(modelJson.toString());
        ObjectNode editorJson = (ObjectNode)this.objectMapper.readTree(designJson);
        ObjectNode propertiesNode = (ObjectNode)editorJson.get("properties");
        if(propertiesNode != null) {
            propertiesNode.put("name", name);
            propertiesNode.put("documentation", description);
           // propertiesNode.put("process_author", ContextUtil.getCurrentUser().getFullname());
        }

        newModel.setName(name);
        this.repositoryService.saveModel(newModel);
        this.repositoryService.addModelEditorSource(newModel.getId(), editorJson.toString().getBytes("utf-8"));
        Deployment deployment = this.doDeployModel(newModel.getId());
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        BpmDef oldBpmDef = this.getByModelId(model.getId());
        this.doDeployNew(oldBpmDef, newModel.getId(), deployment.getId(), processDefinition.getId());
    }

    @Override
    public void updateDef(Model model, String name, String description, String designJson) throws Exception {
        this.updateModel(model, name, description, designJson);
        BpmDef bpmDef = this.getByModelId(model.getId());
        if(bpmDef != null) {
            bpmDef.setSubject(name);
            bpmDef.setDescp(description);
            this.bpmDefDao.updateByPrimaryKeySelective(bpmDef);
        }
    }

    @Override
    public JsonResult getCanSave(BpmDef bpmDef) {
        JsonResult result = JsonResultUtil.success();
        if(StringUtil.isEmpty(bpmDef.getDefId())){
            Integer rtn = this.bpmDefDao.getCountByKey(bpmDef);
            if(rtn>0){
                result= JsonResultUtil.fail("流程key已经存在");
            }
        }else {
            int rtn1 = this.bpmDefDao.getCountByKeyAndId(bpmDef);
            if(rtn1 == 0){
                result = JsonResultUtil.fail("不能修改流程定义KEY!");
            }
        }
           return result;
    }

    public void updateModel(Model model, String name, String description, String designJson) throws Exception {
        ObjectNode modelJson = (ObjectNode)this.objectMapper.readTree(model.getMetaInfo());
        modelJson.put("name", name);
        modelJson.put("description", description);
        model.setMetaInfo(modelJson.toString());
        model.setName(name);
        this.repositoryService.saveModel(model);
        this.repositoryService.addModelEditorSource(model.getId(), designJson.getBytes("utf-8"));
    }

    public void modifySignProperties(ObjectNode objNode) {
        ArrayNode arrayNodes = (ArrayNode)objNode.get("childShapes");

        for(int i = 0; i < arrayNodes.size(); ++i) {
            ObjectNode node = (ObjectNode)arrayNodes.get(i);
            ObjectNode properties = (ObjectNode)node.get("properties");
            JsonNode multiinstance_type = properties.get("multiinstance_type");
            if(multiinstance_type != null) {
                properties.put("multiinstance_collection", "${counterSignService.getUsers(execution)}");
                properties.put("multiinstance_condition", "${counterSignService.isComplete(execution)}");
                this.modifySignProperties(node);
            }
        }
    }

    public Deployment doDeployModel(String modelId) throws JsonProcessingException, IOException {
        Model modelData = this.repositoryService.getModel(modelId);
        String data = new String(this.repositoryService.getModelEditorSource(modelId), "UTF-8");
        ObjectNode modelNode = (ObjectNode)this.objectMapper.readTree(data);
        this.modifySignProperties(modelNode);
        BpmnModel model = (new BpmnJsonConverter()).convertToBpmnModel(modelNode);
        byte[] bpmnBytes = (new BpmnXMLConverter()).convertToXML(model, "UTF-8");
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = this.repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
        return deployment;
    }

    public BpmDef doDeployNew(BpmDef oldBpmDef, String newModelId, String actDepId, String actDefId) {
        Map<String,String> params = new HashMap<>();
        params.put("mainDefId",oldBpmDef.getMainDefId());
        params.put("isMain", MBoolean.NO.name());
        this.bpmDefDao.updateIsMainByMailDefId(params);
        BpmDef newBpmDef = new BpmDef();
        BeanUtil.copyProperties(newBpmDef, oldBpmDef);
        newBpmDef.setDefId(IdUtil.getId());
        newBpmDef.setIsMain(MBoolean.YES.toString());
        newBpmDef.setModelId(newModelId);
        newBpmDef.setActDepId(actDepId);
        newBpmDef.setActDefId(actDefId);
        newBpmDef.setStatus("DEPLOYED");
        params.clear();
        params.put("tenantId","1");
        params.put("key",oldBpmDef.getKey());
        Integer maxVersion = this.bpmDefDao.getMaxVersions(params);
        newBpmDef.setVersion(Integer.valueOf(maxVersion.intValue() + 1));
        //super.create(newBpmDef);
        bpmDefDao.insert(newBpmDef);
        return newBpmDef;
    }




}
