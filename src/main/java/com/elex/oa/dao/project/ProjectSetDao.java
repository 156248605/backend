package com.elex.oa.dao.project;

import com.elex.oa.entity.project.ProjectVarious;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectSetDao {
    //查询所有项目类型
    List<ProjectVarious> queryType();
    //查询所有项目来源
    List<ProjectVarious> querySource();
    //查询所有项目状态
    List<ProjectVarious> queryStatus();
    //查询所有项目阶段
    List<ProjectVarious> queryPhase();
    //查询项目状态值
    String queryStatusValue(String Status);

}
