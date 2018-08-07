package com.elex.oa.service.project;

import com.elex.oa.entity.project.ProjectVarious;

import java.util.List;

public interface ProjectSetService {
    //查询所有项目类型
    List<ProjectVarious> queryType();
    //查询所有项目来源
    List<ProjectVarious> querySource();
    //查询所有项目状态
    List<ProjectVarious> queryStatus();
    //查询所有项目阶段
    List<ProjectVarious> queryPhase();
    //添加项目类型
    String addType(String name);
    //添加项目来源
    String addSource(String name);
    //添加项目状态
    String addStatus(String name);
    //添加项目阶段
    String addPhase(String name);
    //删除项目类型
    String deleteType(int code);
    //删除项目来源
    String deleteSource(int code);
    //删除项目状态
    String deleteStatus(int code);
    //删除项目阶段
    String deletePhase(int code);
}
