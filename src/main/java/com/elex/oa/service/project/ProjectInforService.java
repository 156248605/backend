package com.elex.oa.service.project;

import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectInfor;
import com.github.pagehelper.PageInfo;

public interface ProjectInforService {
    //查询已立项成功的项目，添加到项目信息中
    void addInfor();
    //列表查询项目详情信息
    PageInfo queryList(OperationQuery operationQuery, Integer pageNum);
    //修改项目信息
    String amendInfor(ProjectInfor projectInfor);
}
