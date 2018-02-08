package com.elex.oa.service;

import com.elex.oa.entity.ProjectType;

import java.util.List;

/**
 * 项目类型Service接口类
 */
public interface IProjectTypeService extends BaseService<ProjectType>{
	/**
	 * 查询全部的项目类型信息
	 */
	public List<ProjectType> queryAllProjectType();
}
