package com.elex.oa.service.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.NProjectQuery;
import com.elex.oa.entity.project.NewProject;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface NewProjcetService {
    //对新建项目信息处理
    Map<String,Object> newSubmit(NewProject newProject);
    //对某人已建项目进行列表查询
    PageInfo<NewProject> listQuery(Page page, NProjectQuery nProjectQuery);
    //获取表头部分select中的内容
    List<NewProject> selectData(String proposer);
}
