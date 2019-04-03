package com.elex.oa.service.project;

import com.elex.oa.entity.project.InforQuery;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectInfor;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ProjectInforService {
    //查询已立项成功的项目，添加到项目信息中
    void addInfor();
    //列表查询项目详情信息
    PageInfo queryList(OperationQuery operationQuery, Integer pageNum);
    //修改项目信息
    String amendInfor(ProjectInfor projectInfor);
    //修改商务经理的编号信息
    String businessManager();
    //修改项目成员的编号信息
    String projectMembers();
    //修改相关成员的编号信息
    String relatedMembers();
    //修改交付经理的编号信息
    String projectManager();
    //修改项目状态的编号信息
    String projectStatus();
    //修改项目来源的编号信息
    String projectSource();
    //修改项目类型的编号信息
    String projectType();
    //项目信息导入
    Map<String,String> importData(MultipartFile file);
    //信息导出时查询数据
    String queryExport(InforQuery inforQuery, HttpServletResponse response);
    //项目导入未导入的信息下载
    String importUnfinished(HttpServletResponse response);
    //获取项目信息列表
    PageInfo obtainList(InforQuery inforQuery, Integer pageNum);
    //修改项目信息
    String amendPro(ProjectInfor projectInfor, String updateBy);
    //项目信息添加
    String addPro(String id);
    int proDiff(ProjectInfor projectInfor, String updateBy);
}
