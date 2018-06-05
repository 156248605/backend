package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmOpinionTempDao;
import com.elex.oa.entity.BpmOpinionTemp;
import com.elex.oa.service.IBpmOpinionTempService;
import org.springframework.stereotype.Service;

/**
 *@author hugo.zhao
 *@since 2018/5/4 15:24
*/
@Service
public class BpmOpinionTempServiceImpl implements IBpmOpinionTempService {

    private IBpmOpinionTempDao bpmOpinionTempDao;

    @Override
    public BpmOpinionTemp getByType(String type, String typeId) {
        BpmOpinionTemp bpmOpinionTemp = new BpmOpinionTemp();
        bpmOpinionTemp.setType(type);
        bpmOpinionTemp.setInstId(typeId);
        return bpmOpinionTempDao.selectOne(bpmOpinionTemp);
    }
}
