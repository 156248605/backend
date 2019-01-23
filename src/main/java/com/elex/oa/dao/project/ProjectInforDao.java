package com.elex.oa.dao.project;

import com.elex.oa.entity.project.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectInforDao {
    //查询已建立项目详情信息的编号
    List<String> queryCodes();
    //查询已成功立项但没有建立项目详情的项目信息
    List<ApprovalList> queryApproval(Map<String,Object> content);
    //添加项目详情信息
    void addInfor(List<ApprovalList> list);
    //列表查询项目详情信息
    List<ProjectInfor> queryList(OperationQuery operationQuery);
    //修改项目信息
    void amendInfor(ProjectInfor projectInfor);
    //查询os_user表所有用户信息
    List<OsUser> queryOsUser();

    List<ProjectInfor> queryBusiness();

    void updateBusinessId(List<ProjectInfor> infors);

    List<ProjectInfor> queryProjectMembers();

    void updateProjectMembers(List<ProjectInfor> infors);

    List<ProjectInfor> queryRelatedMembers();

    void updateRelatedMembers(List<ProjectInfor> infors);

    List<ProjectInfor> queryProjects();

    void updateProjects(List<ProjectInfor> infors);

    void projectStatus(ProjectVarious various);

    void projectSource(ProjectVarious various);

    void projectType(ProjectVarious various);
}
