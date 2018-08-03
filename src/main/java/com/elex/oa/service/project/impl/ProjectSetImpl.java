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

    //添加项目类型
    @Override
    public String addType(String name) {
        projectSetDao.addType(name);
        return "1";
    }

    //添加项目来源
    @Override
    public String addSource(String name) {
        projectSetDao.addSource(name);
        return "1";
    }

    //添加项目状态
    @Override
    public String addStatus(String name) {
        projectSetDao.addStatus(name);
        return "1";
    }

    //添加项目阶段
    @Override
    public String addPhase(String name) {
        projectSetDao.addPhase(name);
        return "1";
    }

    //删除项目类型
    @Override
    public String deleteType(int code) {
        int count = projectSetDao.countType(code+""); //查询当前项目中使用该类型的数量
        if(count > 0) {
            return count+"";
        } else {
            projectSetDao.deleteType(code); //删除项目类型
            return "0";
        }
    }

    //删除项目来源
    @Override
    public String deleteSource(int code) {
        int count = projectSetDao.countSource(code+""); //查询当前项目中使用该来源的数量
        if(count > 0) {
            return count+"";
        } else {
            projectSetDao.deleteSource(code); //删除项目来源
            return "0";
        }
    }

    //删除项目状态
    @Override
    public String deleteStatus(int code) {
        int count = projectSetDao.countStatus(code+""); //查询当前项目中使用该状态的数量
        if(count > 0) {
            return count+"";
        } else {
            projectSetDao.deleteStatus(code); //删除项目状态
            return "0";
        }
    }
}
