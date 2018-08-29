package com.elex.oa.service.objectives.impl;

import com.elex.oa.dao.objectives.NetDao;
import com.elex.oa.entity.objectives.Goal2;
import com.elex.oa.service.objectives.NetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class NetImpl implements NetService {
    @Resource
    private NetDao netDao;

    //根据年度查询税后净利信息
    @Override
    public Goal2 queryData(Goal2 goal2) {
        return netDao.queryData(goal2);
    }

    //保存税后净利信息
    @Override
    @Transactional
    public String saveData(Goal2 goal2) {
        System.out.println(goal2);
        netDao.deleteData(goal2); //删除净利信息
        netDao.saveData(goal2);
        return "1";
    }
}
