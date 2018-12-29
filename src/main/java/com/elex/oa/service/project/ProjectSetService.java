package com.elex.oa.service.project;

import com.elex.oa.entity.project.ProjectVarious;

import java.util.List;
import java.util.Map;

public interface ProjectSetService {
    //查询所有项目类型
    List<ProjectVarious> queryType();
    //查询所有项目来源
    List<ProjectVarious> querySource();
    //查询所有项目状态
    List<ProjectVarious> queryStatus();
    //查询所有项目阶段
    List<ProjectVarious> queryPhase();
    //查询所有项目属性类型
    Map<String,List<ProjectVarious>> queryVarious();
}
