package com.elex.oa.controller.project;

import com.elex.oa.service.project.ProjectLaborService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/api/tms")
public class ProjectLaborController {

    @Resource
    ProjectLaborService projectLaborService;

    @RequestMapping("/employee/{employeeNumber}/update")
    @ResponseBody
    public String updateLaborHourInfo (HttpServletRequest request,@PathVariable("employeeNumber") String employeeNumber) {
        employeeNumber = request.getParameter("employeeNumber");
        return projectLaborService.updateLaborHourInfo(request);
    }
}
