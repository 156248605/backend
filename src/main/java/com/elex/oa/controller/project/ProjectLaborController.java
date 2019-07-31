package com.elex.oa.controller.project;

import com.elex.oa.service.project.ProjectLaborService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/api/tms")
public class ProjectLaborController {

    @Resource
    ProjectLaborService projectLaborService;

    @RequestMapping("/employee/{employeeNumber}/update")
    @ResponseBody
    public String updateLaborHourInfo (HttpServletRequest request,@PathVariable("employeeNumber") String employeeNumber) {
        return projectLaborService.updateLaborHourInfo(request,employeeNumber);
    }

    @RequestMapping("/employee/{employeeNumber}/query")
    @ResponseBody
    public Map<String, Object> queryLaborHourInfo (HttpServletRequest request, @PathVariable("employeeNumber") String employeeNumber) throws ParseException {
        return projectLaborService.queryLaborHourInfo(request,employeeNumber);
    }

    @RequestMapping("/frequent/{employeeNumber}/update")
    @ResponseBody
    public String updateFrequentProjectInfo (HttpServletRequest request, @PathVariable("employeeNumber") String employeeNumber) {
        return projectLaborService.updateFrequentProjectInfo(request,employeeNumber);
    }

    @RequestMapping("/frequent/{employeeNumber}/query")
    @ResponseBody
    public String[] queryFrequentProjectInfo (@PathVariable("employeeNumber") String employeeNumber) {
        return projectLaborService.queryFrequentProjectInfo(employeeNumber);
    }

    @RequestMapping("/lock/{date}/insert")
    @ResponseBody
    public String insertLockingInfo (@PathVariable("date") String date) {
        return projectLaborService.insertLockingInfo(date);
    }

    @RequestMapping("/department/{deptId}/query")
    @ResponseBody
    public Map<String, Object> queryLaborHourInfoByDepartment (HttpServletRequest request, @PathVariable("deptId") String deptId) {
        return projectLaborService.queryLaborHourInfoByDepartment(request, deptId);
    }

    @RequestMapping("/department/queryEmployee")
    @ResponseBody
    public Map<String, Object> queryDeptEmployee (HttpServletRequest request) {
        return projectLaborService.queryDeptEmployee(request);
    }

    @RequestMapping("/month/queryEmployee")
    @ResponseBody
    public Map<String, Object> queryLaborHourInfoByMonth (HttpServletRequest request) {
        return projectLaborService.queryLaborHourInfoByMonth(request);
    }

    @RequestMapping("/project/queryLaborHour")
    @ResponseBody
    public List queryLaborHourInfoByProject (HttpServletRequest request) {
        return projectLaborService.queryLaborHourInfoByProject(request);
    }

    @RequestMapping("/project/queryLaborHour/year")
    @ResponseBody
    public List queryLaborHourInfoByYear (HttpServletRequest request) {
        return projectLaborService.queryLaborHourInfoByYear(request);
    }
}
