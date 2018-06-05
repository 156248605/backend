package com.elex.oa.service;

import com.elex.oa.core.entity.StartEventConfig;
import com.elex.oa.entity.BpmDef;
import com.elex.oa.json.JsonResult;
import com.github.pagehelper.PageInfo;
import java.util.Map;
import org.activiti.engine.repository.Model;

/**
 *@author hugo.zhao
 *@since 2018/3/17 13:19
*/
public interface IBpmDefService {
     //查询流程定义数据
     PageInfo<BpmDef> query(Map<String,Object> paramMap);

     BpmDef getByActDefId(String actDefId);

     BpmDef getLatestBpmByKey(String key, String tenantId);

     BpmDef getByDefId(String DefId);

     BpmDef getValidBpmDef(String actDefId, String defKey);

     BpmDef getByModelId(String modelId);

     void  updateDefAndModifyActivitiDef (Model model, String name, String description, String designJson) throws Exception;

     void doDeployModelAndUpdDef(Model model, String name, String description, String designJson) throws Exception;

     void doDeployNewVersion(Model model, String name, String description, String designJson) throws Exception;

     void updateDef(Model model, String name, String description, String designJson) throws Exception;

     JsonResult getCanSave(BpmDef bpmDef);

}
