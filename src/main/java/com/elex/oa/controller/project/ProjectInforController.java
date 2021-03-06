package com.elex.oa.controller.project;
import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.project.ProjectInforDao;
import com.elex.oa.entity.project.*;
import com.elex.oa.service.project.ProjectInforService;
import com.elex.oa.service.project.impl.ProjectAmendImpl;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/proInfor")
public class ProjectInforController {

    @Resource
    private ProjectInforService projectInforService;
    @Resource
    private ProjectAmendImpl projectAmendService;
    @Resource
    private ProjectInforDao projectInforDao;


    //列表查询项目详情信息
    @RequestMapping("/query_list")
    @ResponseBody
    public PageInfo queryList(OperationQuery operationQuery, Integer pageNum) {
        return projectInforService.queryList(operationQuery, pageNum);
    }

    //修改项目信息
    @RequestMapping("/amend_infor")
    @ResponseBody
    public String amendInfor(ProjectInfor projectInfor) {
        return projectInforService.amendInfor(projectInfor);
    }

    //添加新项目
    @RequestMapping("add_infor")
    @ResponseBody
    public String addInfor(){
        projectInforService.addInfor();
        return "1";
    }

    @RequestMapping("/business_manager")
    @ResponseBody
    public String businessManager(){
        return projectInforService.businessManager();
    }
    //修改项目成员的编号信息
    @RequestMapping("/project_members")
    @ResponseBody
    public String projectMembers(){
        return projectInforService.projectMembers();
    }
    //修改相关成员的编号信息
    @RequestMapping("/related_members")
    @ResponseBody
    public String relatedMembers(){
        return projectInforService.relatedMembers();
    }
    //修改交付经理的编号信息
    @RequestMapping("/project_manager")
    @ResponseBody
    public String projectManager(){
        return projectInforService.projectManager();
    }
    //修改项目状态的编号信息
    @RequestMapping("/project_status")
    @ResponseBody
    public String projectStatus() {
        return projectInforService.projectStatus();
    }
    //修改项目来源的编号信息
    @RequestMapping("/project_source")
    @ResponseBody
    public String projectSource() {
        return projectInforService.projectSource();
    }
    //修改项目类型的编号信息
    @RequestMapping("/project_type")
    @ResponseBody
    public String projectType() {
        return projectInforService.projectType();
    }

    //项目信息导入
    @RequestMapping("/import_data")
    @ResponseBody
    public Map<String,String> importData(MultipartFile file) {
        return projectInforService.importData(file);
    }

    //信息导出时查询数据
    @RequestMapping("/query_export")
    @ResponseBody
    public String queryExport(InforQuery inforQuery, HttpServletResponse response) {
        return projectInforService.queryExport(inforQuery, response);
    }


    //项目导入未导入的信息下载
    @RequestMapping("/import_unfinished")
    @ResponseBody
    public String importUnfinished(HttpServletResponse response) {
        try {
            return projectInforService.importUnfinished(response);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    //获取项目信息列表
    @RequestMapping("/obtain_list")
    @ResponseBody
    public PageInfo obtainList(InforQuery inforQuery, Integer pageNum, HttpServletRequest request) {
        return projectInforService.obtainList(inforQuery, pageNum, request);
    }

    //项目信息修改
    @RequestMapping("/amend_pro")
    @ResponseBody
    public Object amendPro(ProjectInfor projectInfor, String updateBy) {
        String result = projectInforService.amendPro(projectInfor, updateBy);
        return  RespUtil.response(result.isEmpty()?"201":"200",result.isEmpty()?"无任何变更":"修改成功",null);

    }

    //项目信息修改
    @RequestMapping("/amend_pro_json")
    @ResponseBody
    public String amendProJson(HttpServletRequest request) {
        String amendId = request.getParameter("amendId");
        ProjectAmend  projectAmend =  projectAmendService.getById(Integer.valueOf(amendId));
        ProjectRecord projectRecord = JSON.parseObject(projectAmend.getRecord_json(),ProjectRecord.class);
        ProjectInfor projectInfor = JSON.parseObject(projectAmend.getProject_json(),ProjectInfor.class);
        return projectInforService.amendPro(projectInfor, projectRecord.getUpdateBy());
    }

    //结项流程走完,更改项目信息
    @RequestMapping("/change_Project_Status")
    @ResponseBody
    public String changeProjectStatus(HttpServletRequest request) {
        String projectCode = request.getParameter("projectCode");
        String updateBy = request.getParameter("updateBy");
        String projectOldStatus = request.getParameter("projectOldStatus");
        String projectNewStatus = request.getParameter("projectNewStatus");
        ProjectInfor projectInfor = this.projectInforDao.queryInforByCode(projectCode);
        OsUser osUser = this.projectInforDao.queryOsUserByUserId(updateBy);
        projectInfor.setProjectStatus(projectNewStatus);
        return projectInforService.changeProjectStatus(projectInfor, osUser.getFullName(),projectOldStatus);
    }
    //项目信息差异
    @RequestMapping("/pro_diff")
    @ResponseBody
    public Object pro_diff(ProjectInfor projectInfor, String updateBy) {
        return  this.projectInforService.proDiff(projectInfor,updateBy);
    }

    //项目信息添加
    @RequestMapping("add_pro")
    @ResponseBody
    public String addPro(String id) {
        return projectInforService.addPro(id);
    }

    //项目周计划(新)
    @RequestMapping("/addWeeklyPlan")
    @ResponseBody
    public String addWeeklyPlan (HttpServletRequest request) {
        return projectInforService.newWeeklyPlan(request);
    }

    //项目周计划(新)
    @RequestMapping("/allWeeklyPlan")
    @ResponseBody
    public List<HashMap<String, Object>> allWeeklyPlan (HttpServletRequest request) {
        return projectInforService.allWeeklyPlan(request);
    }
}
