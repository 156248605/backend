package com.elex.oa.service;

import com.elex.oa.entity.Department;

import java.util.List;

/**
 * 部门数据Service接口类
 */
public interface IDepartmentService extends BaseService<Department>{
	/**
	 * 查询全部的部门信息
	 */
	public List<Department> queryAllDepartment();
}
