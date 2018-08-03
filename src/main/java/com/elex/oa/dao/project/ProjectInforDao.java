package com.elex.oa.dao.project;

import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectInfor;
import com.elex.oa.entity.project.ProjectInforQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectInforDao {
    //数据列表查询
    List<ProjectInfor> listQuery(ProjectInforQuery projectInforQuery);
    //在已有的项目详情中查询某人主管的项目编号
    List<String> queryCodeByName(String name);
    //新建项目详情
    int constructionDetails(ProjectInfor projectInfor);
    //修改项目详情
    int modifyDetails(ProjectInfor projectInfor);
    //删除详情
    int deleteDetails(Integer id);
    //查询可新建详情的项目信息
    List<ApprovalList> queryProjectList(OperationQuery operationQuery);
    //查询跟某人相关的项目
    List<ProjectInfor> queryProName(Map<String,String> name);
}
