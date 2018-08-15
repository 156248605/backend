package com.elex.oa.service.project;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.NProjectQuery;
import com.elex.oa.entity.project.NewProject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NewProjcetService {
    //对某人已建项目进行列表查询
    PageInfo<NewProject> listQuery(Page page, NProjectQuery nProjectQuery);
    //获取表头部分select中的内容
    List<NewProject> selectData(String proposer);
    //根据人名获取项目编号
    String obtainCodeName(String name);
}
