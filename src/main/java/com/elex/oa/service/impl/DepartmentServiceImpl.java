package com.elex.oa.service.impl;

import com.elex.oa.dao.IDepartmentDao;
import com.elex.oa.entity.Department;
import com.elex.oa.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门数据Service类
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {
    @Autowired
    private IDepartmentDao departmentDao;

	/**
	 * 查询全部的部门信息
	 */
	public List<Department> queryAllDepartment(){
		return departmentDao.queryAllDepartment();
	}

}

