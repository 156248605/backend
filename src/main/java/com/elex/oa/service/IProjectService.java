package com.elex.oa.service;

import com.elex.oa.entity.Project;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by lsc on 2017/4/25.
 */
public interface IProjectService extends BaseService<Project>{
	/**
	 * 查询全部的项目信息
	 */
	public PageInfo<Project> queryAllProject(Map<String,Object> paramMap);
}
