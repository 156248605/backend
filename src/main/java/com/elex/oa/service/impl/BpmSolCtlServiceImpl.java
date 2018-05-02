package com.elex.oa.service.impl;
import com.elex.oa.dao.IBpmSolCtlDao;
import com.elex.oa.entity.BpmSolCtl;
import com.elex.oa.service.IBpmSolCtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/25 11:14
*/
@Service
public class BpmSolCtlServiceImpl implements IBpmSolCtlService {
    @Autowired
    private IBpmSolCtlDao bpmSolCtlDao;
    @Override
    public List<BpmSolCtl> getBySolIdAndType(String solId, String type) {
        BpmSolCtl bpmSolCtl = new BpmSolCtl();
        bpmSolCtl.setSolId(solId);
        bpmSolCtl.setType(type);
        return bpmSolCtlDao.select(bpmSolCtl);
    }

    @Override
    public List<BpmSolCtl> getBySolAndTypeAndRight(String solId, String type, String right) {
        BpmSolCtl bpmSolCtl = new BpmSolCtl();
        bpmSolCtl.setSolId(solId);
        bpmSolCtl.setType(type);
        bpmSolCtl.setRight(right);
        return bpmSolCtlDao.select(bpmSolCtl);
    }
}
