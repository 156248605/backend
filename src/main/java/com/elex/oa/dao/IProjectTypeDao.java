package com.elex.oa.dao;

import com.elex.oa.entity.ProjectType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 项目类型数据查询接口类
 */
@Mapper
public interface IProjectTypeDao extends BaseDao<ProjectType> {
    /**
     * 查询全部的项目类型信息
     */
    public List<ProjectType> queryAllProjectType();
}
