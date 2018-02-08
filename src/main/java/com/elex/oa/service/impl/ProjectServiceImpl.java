package com.elex.oa.service.impl;

import com.elex.oa.dao.IProjectDao;
import com.elex.oa.entity.Project;
import com.elex.oa.service.IProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lsc on 2017/4/25.
 */
@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements IProjectService {
    @Autowired
    private IProjectDao projectDao;

	/**
	 * 查询全部的项目信息
	 */
	public PageInfo<Project> queryAllProject(Map<String,Object> paramMap){
		//页码
		int page = Integer.parseInt(paramMap.get("page").toString());
		//每页显示记录数
		int rows = Integer.parseInt(paramMap.get("rows").toString());

		PageHelper.startPage(page,rows);

		List<Project> list = projectDao.queryAllProject(paramMap);
		return new PageInfo<Project>(list);
	}

}

