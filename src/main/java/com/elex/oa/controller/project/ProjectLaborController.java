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

    @RequestMapping("/common/{employeeNumber}/update")
    @ResponseBody
    public String updateCommonProjectInfo (HttpServletRequest request, @PathVariable("employeeNumber") String employeeNumber) {
        return projectLaborService.updateCommonProjectInfo(request,employeeNumber);
    }

    @RequestMapping("/common/{employeeNumber}/query")
    @ResponseBody
    public String[] queryCommonProjectInfo (@PathVariable("employeeNumber") String employeeNumber) {
        return projectLaborService.queryCommonProjectInfo(employeeNumber);
    }

    @RequestMapping("/lock/{date}/insert")
    @ResponseBody
    public String insertLockingInfo (@PathVariable("date") String date) {
        return projectLaborService.insertLockingInfo(date);
    }
}
