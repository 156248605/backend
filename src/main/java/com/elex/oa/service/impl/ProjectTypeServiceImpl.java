package com.elex.oa.service.impl;

import com.elex.oa.dao.IProjectTypeDao;
import com.elex.oa.entity.ProjectType;
import com.elex.oa.service.IProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目类型Service类
 */
@Service
public class ProjectTypeServiceImpl extends BaseServiceImpl<ProjectType> implements IProjectTypeService {
    @Autowired
    private IProjectTypeDao projectTypeDao;

	/**
	 * 查询全部的项目类型信息
	 */
	public List<ProjectType> queryAllProjectType(){
		return projectTypeDao.queryAllProjectType();
	}

}

