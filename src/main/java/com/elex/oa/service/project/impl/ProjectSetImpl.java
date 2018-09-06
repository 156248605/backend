package com.elex.oa.service.project.impl;

import com.elex.oa.dao.project.ProjectSetDao;
import com.elex.oa.entity.project.ProjectVarious;
import com.elex.oa.service.project.ProjectSetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectSetImpl implements ProjectSetService {
    @Resource
    private ProjectSetDao projectSetDao;

    //查询所有项目类型
    @Override
    public List<ProjectVarious> queryType() {
        return projectSetDao.queryType();
    }

    //查询所有项目来源
    @Override
    public List<ProjectVarious> querySource() {
        return projectSetDao.querySource();
    }

    //查询所有项目状态
    @Override
    public List<ProjectVarious> queryStatus() {
        return projectSetDao.queryStatus();
    }

    //查询所有项目阶段
    @Override
    public List<ProjectVarious> queryPhase() {
        return projectSetDao.queryPhase();
    }

}
