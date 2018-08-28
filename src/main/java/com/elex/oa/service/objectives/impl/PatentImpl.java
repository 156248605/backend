package com.elex.oa.service.objectives.impl;

import com.elex.oa.dao.objectives.PatentDao;
import com.elex.oa.entity.objectives.Goal;
import com.elex.oa.service.objectives.PatentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PatentImpl implements PatentService {
    @Resource
    private PatentDao patentDao;

    //根据年度查询发明专利信息
    @Override
    public Goal queryData(Goal goal) {
        return patentDao.queryData(goal);
    }

    //保存发明专利信息
    @Override
    @Transactional
    public String saveData(Goal goal) {
        patentDao.deleteData(goal); //删除发明专利信息
        patentDao.saveData(goal);
        return "1";
    }
}
