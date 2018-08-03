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
    //添加项目类型
    void addType(String name);
    //添加项目来源
    void addSource(String name);
    //添加项目状态
    void addStatus(String name);
    //添加项目阶段
    void addPhase(String name);
    //查询当前项目中使用该类型的数量
    int countType(String s);
    //删除项目类型
    void deleteType(int code);
    //查询当前项目中使用该来源的数量
    int countSource(String s);
    //删除项目来源
    void deleteSource(int code);
    //查询当前项目中使用该状态的数量
    int countStatus(String s);
    //删除项目状态
    void deleteStatus(int code);
}
