package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmInstDataDao;
import com.elex.oa.entity.BpmInstData;
import com.elex.oa.service.IBpmInstDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/5/8 11:29
*/
@Service
public class BpmInstDataServiceImpl implements IBpmInstDataService {

    @Resource
    private IBpmInstDataDao bpmInstDataDao;

    @Override
    public String getPk(String instId, String boDefId) {
        Map map = this.getMapByInstId(instId);


        return null;
    }

    private Map<String, BpmInstData> getMapByInstId(String instId) {
        List list = this.getByInstId(instId);
        HashMap map = new HashMap();
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            BpmInstData data = (BpmInstData)var4.next();
            map.put(data.getBoDefId(), data);
        }

        return map;
    }

     public List<BpmInstData> getByInstId(String instId){
        BpmInstData bpmInstData = new BpmInstData();
        bpmInstData.setInstId(instId);
        return  this.bpmInstDataDao.select(bpmInstData);
     }

}
