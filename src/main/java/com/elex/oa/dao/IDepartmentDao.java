package com.elex.oa.dao;

import com.elex.oa.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门数据查询接口类
 */
@Mapper
public interface IDepartmentDao extends BaseDao<Department> {
    /**
     * 查询全部的部门信息
     */
    public List<Department> queryAllDepartment();
}
