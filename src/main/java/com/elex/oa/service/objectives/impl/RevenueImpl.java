package com.elex.oa.service.objectives.impl;

import com.elex.oa.dao.objectives.RevenueDao;
import com.elex.oa.entity.objectives.Goal2;
import com.elex.oa.service.objectives.RevenueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class RevenueImpl implements RevenueService {
    @Resource
    private RevenueDao revenueDao;

    //根据年度查询销售收入信息
    @Override
    public Goal2 queryData(Goal2 goal2) {
        return revenueDao.queryData(goal2);
    }

    //保存销售收入信息
    @Override
    @Transactional
    public String saveData(Goal2 goal2) {
        revenueDao.deleteData(goal2); //删除销售收入信息
        revenueDao.saveData(goal2);
        return "1";
    }
}
