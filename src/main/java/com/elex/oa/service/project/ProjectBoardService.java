package com.elex.oa.service.project;

import com.elex.oa.entity.project.Staff;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ProjectBoardService {
    //详情
    Map<String,Object> detail(String projectCode);
    //项目数量
    Map<String, Object> overview(String department);
    //查看某一类型的项目
    PageInfo projectVarious(Integer pageNum, String status, String type, String department);
    //数据库人员更新
    void informationUpdate();
    //查询所有人员
    List<Staff> queryStaff();
    //看板手机部门相关详情（手机）
    List<Map<String, String>> projectTotal(String department);
    //看板根据类型查询概况（手机）
    List<Map<String, String>> projectSurvey(String status, String type, String department);
    //查看某类型的项目
    PageInfo projectStatus(Integer pageNum, String status, String type, String department);
    //查看某阶段的项目
    PageInfo projectPhase(Integer pageNum, String phase, String type, String department);
    //查看是否延期的项目
    PageInfo projectWeek(Integer pageNum, String punctuality, String type, String department);
    //查看进行中项目的类型及数量
    List<Object> queryProceed(String department);
}
