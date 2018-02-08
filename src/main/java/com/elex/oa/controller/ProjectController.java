package com.elex.oa.controller;

import com.elex.oa.entity.Department;
import com.elex.oa.entity.Project;
import com.elex.oa.entity.ProjectType;
import com.elex.oa.service.IDepartmentService;
import com.elex.oa.service.IProjectService;
import com.elex.oa.service.IProjectTypeService;
import com.elex.oa.util.TimeUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author lilulu
 *@since 2018/2/7
*/
@Api(value = "Project", description = "the project API")
@RequestMapping("/project")
@CrossOrigin
@Controller
public class ProjectController {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IProjectTypeService projectTypeService;
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/queryProject")
    @ResponseBody
    @ApiOperation(value = "根据条件查询项目信息", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo queryProject(@RequestParam("page") int page ,
                                 @RequestParam("rows") int rows,
                                 @RequestParam("projectType") String projectType,
                                 @RequestParam("projectCode") String projectCode,
                                 @RequestParam("projectName") String projectName,
                                 @RequestParam("approvalDepartment") String approvalDepartment,
                                 @RequestParam("businessManager") String businessManager,
                                 @RequestParam("projectManager") String projectManager,
                                 @RequestParam("status") String status,
                                 @RequestParam("approvalStartDate") String approvalStartDate,
                                 @RequestParam("approvalEndDate") String approvalEndDate) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("page",page);
        paramMap.put("rows",rows);
        paramMap.put("projectType",projectType);
        paramMap.put("projectCode",projectCode);
        paramMap.put("projectName",projectName);
        paramMap.put("approvalDepartment",approvalDepartment);
        paramMap.put("businessManager",businessManager);
        paramMap.put("projectManager",projectManager);
        paramMap.put("status",status);

        String approvalStartDateStr = "";
        if(approvalStartDate!=null && !"".equals(approvalStartDate)){
            Date startDate = TimeUtil.strToDate(approvalStartDate, "yyyy-MM-dd");
            approvalStartDateStr = TimeUtil.dateToStr(startDate, "yyyy-MM-dd HH:mm:ss");
        }
        paramMap.put("approvalStartDate",approvalStartDateStr);

        String approvalEndDateStr = "";
        if(approvalEndDate!=null && !"".equals(approvalEndDate)){
            Date endDate = TimeUtil.strToDate(approvalEndDate, "yyyy-MM-dd");
            approvalEndDateStr = TimeUtil.dateToStr(endDate, "yyyy-MM-dd HH:mm:ss");
        }
        paramMap.put("approvalEndDate",approvalEndDateStr);
        PageInfo<Project> list = projectService.queryAllProject(paramMap);
        return list;
    }

    @RequestMapping("/queryAllProjectTypeAndDepartment")
    @ResponseBody
    @ApiOperation(value = "查询项目类型信息和部门信息", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object> queryAllProjectTypeAndDepartment(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<ProjectType> projectTypeList = projectTypeService.queryAllProjectType();
        List<Department> departmentList = departmentService.queryAllDepartment();
        map.put("projectTypeList",projectTypeList);
        map.put("departmentList",departmentList);
        return map;
    }
}
