package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.ProjectLaborDao;
import com.elex.oa.entity.project.ProjectLabor;
import com.elex.oa.service.project.ProjectLaborService;
import com.elex.oa.util.hr_util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectLaborImpl implements ProjectLaborService {

    @Resource
    ProjectLaborDao projectLaborDao;

    public static List<String> findDates(String stime, String etime) throws ParseException {
        List<String> allDate = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = sdf.parse(stime);
        Date dEnd = sdf.parse(etime);
        allDate.add(sdf.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            allDate.add(sdf.format(calBegin.getTime()));
        }
        return allDate;
    }

    @Override
    public Map<String, Object> queryLaborHourInfo(HttpServletRequest request, String employeeNumber) throws ParseException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<String> dateList = findDates(startDate,endDate);
        String lockStatus = projectLaborDao.queryLaborStatus();
        String projectCode = request.getParameter("projectCode");
        JSONArray projectArray =JSONArray.parseArray(projectCode);
        List<ProjectLabor> projectLaborList;
        List status = new ArrayList();
        Map<String, Object> projectlist = new HashMap<>();
        List message = new ArrayList();
        for (int j = 0;j < projectArray.size();j++) {
            projectLaborList = projectLaborDao.queryLaborHourInfo(employeeNumber,projectArray.get(j).toString(),startDate,endDate);
            if (startDate != null && endDate != null && employeeNumber != null && projectCode != null && projectLaborList.size() > 0) {
                Map<String,Object> map = new HashMap<>();
                map.put("projectCode",projectLaborList.get(0).getProjectCode());
                map.put("projectName",projectLaborList.get(0).getProjectName());
                List laborHour = new ArrayList();
                List list = new ArrayList();
                for (int i = 0;i < projectLaborList.size();i++) {
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(projectLaborList.get(i).getFillingDate().getTime()));
                    list.add(date);
                }
                for (int k = 0;k < dateList.size();k++) {
                    if (!list.contains(dateList.get(k))) {
                        laborHour.add("0");
                    }else{
                        laborHour.add(projectLaborDao.queryDateLabor(employeeNumber,projectLaborList.get(0).getProjectCode(),list.get(list.indexOf(dateList.get(k))).toString()));
                    }
                }
                map.put("laborHour",laborHour);
                message.add(map);
            }
        }
        projectlist.put("message",message);
        for (int k = 0;k < dateList.size();k++) {
            if (lockStatus.compareTo(dateList.get(k).substring(0,7)) < 0) {
                status.add("unlocking");
            }else {
                status.add("locking");
            }
        }
        projectlist.put("status",status);
        return projectlist;
    }

    @Override
    public String updateLaborHourInfo(HttpServletRequest request,String employeeNumber) {
        String projectList = request.getParameter("projectList");
        JSONArray projectArray =JSONArray.parseArray(projectList);
        String result = "success";
        ProjectLabor projectLabor = new ProjectLabor();
        for (int i = 0;i < projectArray.size();i++) {
            projectLabor.setEmployeeNumber( employeeNumber );
            projectLabor.setEmployeeName( projectArray.getJSONObject(i).getString("employeeName") );
            projectLabor.setProjectCode( projectArray.getJSONObject(i).getString("projectCode") );
            projectLabor.setProjectName( projectArray.getJSONObject(i).getString("projectName") );
            TimeUtil time = new TimeUtil();
            List dateList = Arrays.asList(projectArray.getJSONObject(i).getString("fillingDate").replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(","));
            List hourList = Arrays.asList(projectArray.getJSONObject(i).getString("laborHour").replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(","));
            for (int j = 0;j < dateList.size();j++) {
                projectLabor.setFillingDate( time.strToDate(dateList.get(j).toString(),"yyyy-MM-dd") );
                projectLabor.setLaborHour( BigDecimal.valueOf(Double.parseDouble(hourList.get(j).toString())) );
                projectLabor.setStatus( "unlocking" );
                projectLabor.setId(employeeNumber + projectLabor.getProjectCode() + dateList.get(j).toString());
                try {
                    projectLaborDao.updateLaborHourInfo(projectLabor);
                } catch (Exception e){
                    result = "fail";
                }
            }
        }
        return result;
    }

    @Override
    public String[] queryFrequentProjectInfo(String employeeNumber) {
        ProjectLabor projectLabor = new ProjectLabor();
        projectLabor.setEmployeeNumber(employeeNumber);
        String projectCode = projectLaborDao.queryFrequentProjectInfo(projectLabor);
        String[] projectList = new String[0];
        if (projectCode != null) {
            projectList = projectCode.split(",");
        }
        return projectList;
    }

    @Override
    public String updateFrequentProjectInfo(HttpServletRequest request,String employeeNumber) {
        String projectList = request.getParameter("projectCode");
        String projectCode = "";
        JSONArray projectArray =JSONArray.parseArray(projectList);
        String result = "success";
        ProjectLabor projectLabor = new ProjectLabor();
        projectLabor.setEmployeeNumber(employeeNumber);
        for (int i = 0;i < projectArray.size();i++) {
            if (i == projectArray.size() -1) {
                projectCode += projectArray.get(i).toString();
            }else {
                projectCode += projectArray.get(i).toString() + ",";
            }
        }
        projectLabor.setProjectCode(projectCode);
        try {
            projectLaborDao.updateFrequentProjectInfo(projectLabor);
        } catch (Exception e){
            result = "fail";
        }
        return result;
    }

    @Override
    public String insertLockingInfo(String date) {
        String result = "success";
        projectLaborDao.updateLockingInfo(date);
        return result;
    }

    @Override
    public Map<String, Object> queryLaborHourInfoByDepartment(HttpServletRequest request, String deptId) {
        Map<String, Object> map = new HashMap<>();
        String employeeNumber = request.getParameter("employeeNumber");
        JSONArray employeeArray =JSONArray.parseArray(employeeNumber);
        String employeeName = request.getParameter("employeeName");
        JSONArray employeeNameArray =JSONArray.parseArray(employeeName);
        String fillingDate = request.getParameter("fillingDate");
        int year = Integer.parseInt(fillingDate.split("-")[0]);  //年
        int month = Integer.parseInt(fillingDate.split("-")[1]); //月
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = fillingDate + "-01";
        String endDate = sdf.format(cal.getTime());
        List<ProjectLabor> projectList;
        List message = new ArrayList();
        for (int i = 0;i < employeeArray.size();i++) {
            Map<String,Object> projectMap = new HashMap<>();
            projectMap.put("employeeNumber",employeeArray.get(i).toString());
            projectMap.put("employeeName",employeeNameArray.get(i).toString());
            projectList = projectLaborDao.queryProject(employeeArray.get(i).toString());
            Map<String,Object> laborMap = new HashMap<>();
            List projectCodeList = new ArrayList();
            List projectNameList = new ArrayList();
            for (int j = 0;j < projectList.size();j++) {
                if (!"0.0".equals(projectLaborDao.queryLaborHourInfoByDepartment(employeeArray.get(i).toString(),projectList.get(j).getProjectCode(),startDate,endDate))) {
                    laborMap.put(projectList.get(j).getProjectCode(),projectLaborDao.queryLaborHourInfoByDepartment(employeeArray.get(i).toString(),projectList.get(j).getProjectCode(),startDate,endDate));
                    projectCodeList.add(projectList.get(j).getProjectCode());
                    projectNameList.add(projectList.get(j).getProjectName());
                }
            }
            projectMap.put("projectCode",projectCodeList);
            projectMap.put("projectName",projectNameList);
            projectMap.put("laborHour",laborMap);
            message.add(projectMap);
        }
        map.put("message",message);
        String lockStatus = projectLaborDao.queryLaborStatus();
        if (lockStatus.compareTo(fillingDate) >= 0) {
            map.put("status","locking");
        }else {
            map.put("status","unlocking");
        }
        return map;
    }

    @Override
    public Map<String, Object> queryDeptEmployee(HttpServletRequest request) {
        String deptId = request.getParameter("deptId");
        List deptList = projectLaborDao.queryDepartment(deptId + '.');
        List employeeList = new ArrayList();
        List employeeNameList = new ArrayList();
        if (deptList.contains(deptId)) {
            for (int i = 0;i < deptList.size();i++) {
                for (int j = 0;j < projectLaborDao.queryEmployee(deptList.get(i).toString()).size();j++) {
                    employeeList.add(projectLaborDao.queryEmployee(deptList.get(i).toString()).get(j).get("EMPLOYEE_NUMBER_"));
                    employeeNameList.add(projectLaborDao.queryEmployee(deptList.get(i).toString()).get(j).get("FULLNAME_"));
                }
            }
        }else {
            for (int k = 0;k < projectLaborDao.queryEmployee(deptId).size();k++) {
                employeeList.add(projectLaborDao.queryEmployee(deptId).get(k).get("EMPLOYEE_NUMBER_"));
                employeeNameList.add(projectLaborDao.queryEmployee(deptId).get(k).get("FULLNAME_"));
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("employeeNumber",employeeList);
        map.put("employeeName",employeeNameList);
        return map;
    }

    @Override
    public Map<String, Object> queryLaborHourInfoByMonth(HttpServletRequest request) {
        String fillingDate = request.getParameter("fillingDate");
        int year = Integer.parseInt(fillingDate.split("-")[0]);  //年
        int month = Integer.parseInt(fillingDate.split("-")[1]); //月
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = fillingDate + "-01";
        String endDate = sdf.format(cal.getTime());
        List employeeNumber = new ArrayList();
        List employeeName = new ArrayList();
        for (int i =0;i < projectLaborDao.queryLaborHourInfoByMonth(startDate,endDate).size();i++){
            employeeNumber.add(projectLaborDao.queryLaborHourInfoByMonth(startDate,endDate).get(i).get("employee_number"));
            employeeName.add(projectLaborDao.queryLaborHourInfoByMonth(startDate,endDate).get(i).get("employee_name"));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("employeeNumber",employeeNumber);
        map.put("employeeName",employeeName);
        return map;
    }

    @Override
    public List queryLaborHourInfoByProject(HttpServletRequest request) {
        String projectCode = request.getParameter("projectCode");
        List infoList = new ArrayList();
        String fillingDate = request.getParameter("fillingDate");
        String startDate = fillingDate + "-01-01";
        String endDate = fillingDate + "-12-31";
        for (int i = 0;i < projectLaborDao.queryEmployeeByMonth(projectCode,startDate,endDate).size();i++) {
            Map<String, Object> projectMap = new HashMap<>();
            projectMap.put("employeeNumber", projectLaborDao.queryEmployeeByMonth(projectCode,startDate,endDate).get(i).get("employee_number").toString());
            projectMap.put("employeeName", projectLaborDao.queryEmployeeByMonth(projectCode,startDate,endDate).get(i).get("employee_name").toString());
            List laborHour = new ArrayList();
            for (int j = 1;j < 13;j++) {
                fillingDate = request.getParameter("fillingDate") + "-" + (j < 10 ? "0" + j : j);
                int year = Integer.parseInt(fillingDate.split("-")[0]);  //年
                int month = Integer.parseInt(fillingDate.split("-")[1]); //月
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                int lastDay = cal.getActualMaximum(Calendar.DATE);
                cal.set(Calendar.DAY_OF_MONTH, lastDay);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String start = fillingDate + "-01";
                String end = sdf.format(cal.getTime());
                laborHour.add(projectLaborDao.queryLaborHourInfoByDepartment(projectLaborDao.queryEmployeeByMonth(projectCode,startDate,endDate).get(i).get("employee_number").toString(),projectCode,start,end) == null ? "0" : projectLaborDao.queryLaborHourInfoByDepartment(projectLaborDao.queryEmployeeByMonth(projectCode,startDate,endDate).get(i).get("employee_number").toString(),projectCode,start,end));
            }
            projectMap.put("laborHour",laborHour);
            infoList.add(projectMap);
        }
        return infoList;
    }

    @Override
    public List queryLaborHourInfoByYear(HttpServletRequest request) {
        List infoList = new ArrayList();
        String fillingDate = request.getParameter("fillingDate");
        String startDate = fillingDate + "-01-01";
        String endDate = fillingDate + "-12-31";
        for (int i = 0;i < projectLaborDao.queryProjectByYear(startDate,endDate).size();i++) {
            Map<String, Object> projectMap = new HashMap<>();
            String projectCode = projectLaborDao.queryProjectByYear(startDate,endDate).get(i).get("project_code").toString();
            projectMap.put("projectCode", projectCode);
            projectMap.put("projectName", projectLaborDao.queryProjectByYear(startDate,endDate).get(i).get("project_name").toString());
            List laborHour = new ArrayList();
            for (int j = 1;j < 13;j++) {
                fillingDate = request.getParameter("fillingDate") + "-" + (j < 10 ? "0" + j : j);
                int year = Integer.parseInt(fillingDate.split("-")[0]);  //年
                int month = Integer.parseInt(fillingDate.split("-")[1]); //月
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                int lastDay = cal.getActualMaximum(Calendar.DATE);
                cal.set(Calendar.DAY_OF_MONTH, lastDay);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String start = fillingDate + "-01";
                String end = sdf.format(cal.getTime());
                String hour = "";
                for (int k = 0;k < projectLaborDao.queryEmployeeByMonth(projectCode,start,end).size();k++) {
                    hour += projectLaborDao.queryLaborHourInfoByDepartment(projectLaborDao.queryEmployeeByMonth(projectCode,start,end).get(k).get("employee_number").toString(),projectCode,start,end).length() == 1 ? "0" :
                            projectLaborDao.queryLaborHourInfoByDepartment(projectLaborDao.queryEmployeeByMonth(projectCode,start,end).get(k).get("employee_number").toString(),projectCode,start,end);
                }
                laborHour.add(hour.length() == 0 ? "0.0" : hour);
            }
            projectMap.put("laborHour",laborHour);
            double total = 0;
            for (int k = 0;k < laborHour.size();k++) {
                total += Double.parseDouble(laborHour.get(k).toString());
            }
            projectMap.put("total",String.valueOf(total));
            if (total != 0) {
                infoList.add(projectMap);
            }

        }
        return infoList;
    }
}
