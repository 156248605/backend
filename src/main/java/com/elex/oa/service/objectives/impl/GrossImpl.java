package com.elex.oa.service.objectives.impl;

import com.elex.oa.dao.objectives.GrossDao;
import com.elex.oa.entity.objectives.Goal2;
import com.elex.oa.service.objectives.GrossService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class GrossImpl implements GrossService {
    @Resource
    private GrossDao grossDao;

    //根据年度查询销售毛利信息
    @Override
    public Goal2 queryData(Goal2 goal2) {
        return grossDao.queryData(goal2);
    }

    //保存销售毛利信息
    @Override
    @Transactional
    public String saveData(Goal2 goal2) {
        grossDao.deleteData(goal2); //删除销售毛利信息
        grossDao.saveData(goal2);
        return "1";
    }
}
