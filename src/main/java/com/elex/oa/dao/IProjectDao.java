package com.elex.oa.dao;

import com.elex.oa.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by lsc on 2017/4/25.
 */
@Mapper
public interface IProjectDao extends BaseDao<Project> {
    /**
     * 查询全部的项目信息
     */
    public List<Project> queryAllProject(Map<String,Object> paramMap);
}
