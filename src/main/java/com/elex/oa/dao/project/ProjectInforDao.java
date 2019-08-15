package com.elex.oa.dao.project;

import com.elex.oa.entity.project.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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
    //查询在职人员
    List<OsUser> queryOsUserInJob();

    OsUser queryOsUserByUserId(String userId);

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
    //查询导入前最后一条的id
    String queryLastId();
    //导入操作
    int importData(List<ProjectInfor> list);
    //查询导入的项目编号
    List<String> queryCodeList(int lastId);
    //查询导出的数据
    List<ProjectInfor> queryExport(InforQuery inforQuery);
    //获取项目信息列表
    List<ProjectInfor> obtainList(InforQuery inforQuery);
    //根据项目编号获取项目信息
    ProjectInfor queryInforByCode(String projectCode);

    //根据项目编号获取项目信息
    ProjectInfor queryInforByCodeNew(String projectCode);
    //修改项目信息
    void amendPro(ProjectInfor projectInfor);
    //查询instStatus值
    String queryInstStatus(String id);
    //查询项目信息
    ApprovalList queryInforById(String id);
    //添加项目详情信息
    void addProjectInfor(ApprovalList approvalList);
    //项目周计划(新)
    void addWeeklyPlan(ProjectInfor projectInfor);
    void updateProjectInforWeeklyPlan(ProjectInfor projectInfor);
    //查询项目周计划历史
    List<HashMap<String, Object>> allWeeklyPlan(@Param("projectCode") String projectCode);
    //判断项目周报是否为最新
    String isNewestWeeklyReport(@Param("projectCode") String projectCode);
}
