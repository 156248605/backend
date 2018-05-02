package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmSolUserDao;
import com.elex.oa.entity.BpmSolUser;
import com.elex.oa.service.IBpmSolUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/3 14:05
*/
@Service
public class BpmSolUserImpl extends BaseServiceImpl<BpmSolUser> implements IBpmSolUserService {

    @Autowired
    private IBpmSolUserDao bpmSolUserDao;

    public boolean isExistConfig(String solId,String actDefId){
         BpmSolUser bpmSolUser = new BpmSolUser();
         bpmSolUser.setSolId(solId);
         bpmSolUser.setActDefId(actDefId);
         int i =  bpmSolUserDao.selectCount(bpmSolUser);
         return i>0;
    }

    @Override
    public List<BpmSolUser> getBySolIdActDefIdNodeId(String solId, String actDefId, String nodeId, String groupType) {
        BpmSolUser bpmSolUser = new BpmSolUser();
        bpmSolUser.setSolId(solId);
        bpmSolUser.setActDefId(actDefId);
        bpmSolUser.setNodeId(nodeId);
        bpmSolUser.setCategory(groupType);
        return this.bpmSolUserDao.select(bpmSolUser);
    }
}
