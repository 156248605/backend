package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmSolFvDao;
import com.elex.oa.entity.activiti.BpmSolFv;
import com.elex.oa.service.IBpmSolFvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/4/13 10:23
*/
@Service
public class BpmSolFvServiceImpl extends BaseServiceImpl<BpmSolFv> implements IBpmSolFvService {
    @Autowired
    private IBpmSolFvDao bpmSolFvDao;
    @Override
    public BpmSolFv getBySolIdActDefIdNodeId(String solId, String actDefId, String nodeId) {
        Map<String,String> map = new HashMap<>();
        map.put("solId",solId);
        map.put("actDefId",actDefId);
        map.put("nodeId",nodeId);
        return this.bpmSolFvDao.getBySolIdActDefIdNodeId(map);
    }
}
