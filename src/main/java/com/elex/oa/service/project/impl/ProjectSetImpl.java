package com.elex.oa.service.project.impl;

import com.elex.oa.dao.project.ProjectSetDao;
import com.elex.oa.entity.project.ProjectVarious;
import com.elex.oa.service.project.ProjectSetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //查询所有项目属性类型
    @Override
    public Map<String, List<ProjectVarious>> queryVarious() {
        Map<String, List<ProjectVarious>> result = new HashMap<>();
        List<ProjectVarious> typeList = projectSetDao.queryType();
        List<ProjectVarious> sourceList = projectSetDao.querySource();
        List<ProjectVarious> statusList = projectSetDao.queryStatus();
        List<ProjectVarious> phaseList = projectSetDao.queryPhase();
        result.put("type", typeList);
        result.put("source", sourceList);
        result.put("status", statusList);
        result.put("phase", phaseList);
        return result;
    }
}
