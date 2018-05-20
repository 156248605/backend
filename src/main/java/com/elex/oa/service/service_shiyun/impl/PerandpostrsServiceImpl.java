package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IPerandpostrsDao;
import com.elex.oa.entity.entity_shiyun.PerAndPostRs;
import com.elex.oa.service.service_shiyun.IPerandpostrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:人事与岗位关系表
 * @Date:Created in  20:33 2018\5\12 0012
 * @Modify By:
 */
@Service
public class PerandpostrsServiceImpl implements IPerandpostrsService {
    @Autowired
    IPerandpostrsDao iPerandpostrsDao;

    /**
     *@Author:ShiYun;
     *@Description:添加一条关系信息
     *@Date: 20:34 2018\5\12 0012
     */
    @Override
    public void addOne(PerAndPostRs perAndPostRs) {
        iPerandpostrsDao.insertOne(perAndPostRs);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据人事ID查询岗位信息
     *@Date: 20:34 2018\5\12 0012
     */
    @Override
    public List<PerAndPostRs> queryPerAndPostRsByPerid(Integer perid) {
        PerAndPostRs perAndPostRs = new PerAndPostRs();
        perAndPostRs.setPerid(perid);
        List<PerAndPostRs> perAndPostRs1 = iPerandpostrsDao.selectPostidsByPerid(perAndPostRs);
        return perAndPostRs1;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据perid删除信息
     *@Date: 9:11 2018\5\18 0018
     */
    @Override
    public void removeByPerid(Integer perid) {
        iPerandpostrsDao.deleteByPerid(perid);
    }
}
