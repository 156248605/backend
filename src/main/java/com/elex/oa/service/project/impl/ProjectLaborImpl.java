package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.dao.project.ProjectLaborDao;
import com.elex.oa.entity.project.ProjectLabor;
import com.elex.oa.service.project.ProjectLaborService;
import com.elex.oa.util.hr_util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProjectLaborImpl implements ProjectLaborService {

    @Resource
    ProjectLaborDao projectLaborDao;

    @Override
    public String queryLaborHourInfo(HttpServletRequest request) {
        return null;
    }

    @Override
    public String updateLaborHourInfo(HttpServletRequest request) {
        String projectList = request.getParameter("projectList");
        JSONArray projectArray =JSONArray.parseArray(projectList);
        String result = "success";
        ProjectLabor projectLabor = new ProjectLabor();
        for (int i = 0;i < projectArray.size();i++) {
            projectLabor.setEmployeeNumber( projectArray.getJSONObject(i).getString("employeeNumber") );
            projectLabor.setEmployeeName( projectArray.getJSONObject(i).getString("employeeName") );
            projectLabor.setProjectCode( projectArray.getJSONObject(i).getString("projectCode") );
            projectLabor.setProjectName( projectArray.getJSONObject(i).getString("projectName") );
            TimeUtil time = new TimeUtil();
            List dateList = Arrays.asList(projectArray.getJSONObject(i).getString("fillingDate").replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(","));
            List hourList = Arrays.asList(projectArray.getJSONObject(i).getString("laborHour").replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(","));
            for (int j = 0;j < dateList.size();j++) {
                projectLabor.setFillingDate( time.strToDate(dateList.get(j).toString(),"yyyy-MM-dd") );
                projectLabor.setLaborHour( BigDecimal.valueOf(Double.parseDouble(hourList.get(j).toString())) );
                projectLabor.setId(projectLabor.getEmployeeNumber() + projectLabor.getProjectCode() + dateList.get(j).toString());
                try {
                    projectLaborDao.updateLaborHourInfo(projectLabor);
                } catch (Exception e){
                    result = "fail";
                }
            }
        }
        return result;
    }
}
