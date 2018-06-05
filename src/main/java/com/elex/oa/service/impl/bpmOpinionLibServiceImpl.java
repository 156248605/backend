package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmOpinionLibDao;
import com.elex.oa.entity.BpmOpinionLib;
import com.elex.oa.service.IBpmOpinionLibService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class bpmOpinionLibServiceImpl implements IBpmOpinionLibService {

    @Resource
    private IBpmOpinionLibDao bpmOpinionLibDao;


    @Override
    public List<BpmOpinionLib> getByUserId(String userId) {
        BpmOpinionLib bpmOpinionLib = new BpmOpinionLib();
        bpmOpinionLib.setUserId("0");
        List<BpmOpinionLib> alllist = bpmOpinionLibDao.select(bpmOpinionLib);
        bpmOpinionLib.setUserId(userId);
        List<BpmOpinionLib> userlist = bpmOpinionLibDao.select(bpmOpinionLib);
        alllist.addAll(userlist);
        return alllist;
    }

    @Override
    public boolean isOpinionSaved(String userId, String opText) {
        BpmOpinionLib bpmOpinionLib = new BpmOpinionLib();
        bpmOpinionLib.setUserId(userId);
        bpmOpinionLib.setOpText(opText);
        Boolean a =  bpmOpinionLibDao.selectCount(bpmOpinionLib)>0;
        bpmOpinionLib.setUserId("0");
        Boolean b = bpmOpinionLibDao.selectCount(bpmOpinionLib)>0;
        return a || b;
    }
    @Override
    public int save(BpmOpinionLib bpmOpinionLib){
        return bpmOpinionLibDao.insertSelective(bpmOpinionLib);
    }

}
