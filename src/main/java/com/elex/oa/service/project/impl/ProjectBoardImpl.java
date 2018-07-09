package com.elex.oa.service.project.impl;

import com.elex.oa.dao.project.ProjectBoardDao;
import com.elex.oa.entity.project.*;
import com.elex.oa.service.project.ProjectBoardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectBoardImpl implements ProjectBoardService {

    @Resource
    private ProjectBoardDao projectBoardDao;

    //列表总览
    @Override
    public Map<String, Object> overview() {
        Map<String,Object> result = new HashMap<>();

        int total1 = 0, total2 = 0, total3 = 0, total4 = 0, total5 = 0, total6 = 0;

        List<ApprovalList> approvalLists = projectBoardDao.queryApprovalList(); //查询立项相关的项目

        List<Map<String,String>> listP1 = new ArrayList<>();
        List<Map<String,String>> listP2 = new ArrayList<>();
        List<Map<String,String>> listP3 = new ArrayList<>();
        List<Map<String,String>> listP4 = new ArrayList<>();
        List<Map<String,String>> listP5 = new ArrayList<>();
        List<Map<String,String>> listP6 = new ArrayList<>();
        for(ApprovalList approvalList: approvalLists) {
            Map<String,String> content = new HashMap<>();
            content.put("code",approvalList.getProjectCode());
            content.put("name",approvalList.getProjectName());
            if(approvalList.getProjectType().equals("1")) {
                listP1.add(content);
                total1 ++;
            } else if(approvalList.getProjectType().equals("2")) {
                listP2.add(content);
                total2 ++;
            } else if(approvalList.getProjectType().equals("3")) {
                listP3.add(content);
                total3 ++;
            } else if(approvalList.getProjectType().equals("4")) {
                listP4.add(content);
                total4 ++;
            } else if(approvalList.getProjectType().equals("5")) {
                listP5.add(content);
                total5 ++;
            } else if(approvalList.getProjectType().equals("6")) {
                listP6.add(content);
                total6 ++;
            }
        }
        List<Map<String,Object>> projectList = new ArrayList<>();
        Map<String,Object> mapP1 = new HashMap<>();
        mapP1.put("list",listP1);
        mapP1.put("num",total1);
        projectList.add(mapP1);
        Map<String,Object> mapP2 = new HashMap<>();
        mapP2.put("list",listP2);
        mapP2.put("num",total2);
        projectList.add(mapP2);
        Map<String,Object> mapP3 = new HashMap<>();
        mapP3.put("list",listP3);
        mapP3.put("num",total3);
        projectList.add(mapP3);
        Map<String,Object> mapP4 = new HashMap<>();
        mapP4.put("list",listP4);
        mapP4.put("num",total4);
        projectList.add(mapP4);
        Map<String,Object> mapP5 = new HashMap<>();
        mapP5.put("list",listP5);
        mapP5.put("num",total5);
        projectList.add(mapP5);
        Map<String,Object> mapP6 = new HashMap<>();
        mapP6.put("list",listP6);
        mapP6.put("num",total6);
        projectList.add(mapP6);
        Map<String,Object> mapP7 = new HashMap<>();
        mapP7.put("num",approvalLists.size()+"");
        projectList.add(mapP7);
        result.put("project",projectList);

        int totala1 = 0, totala2 = 0, totala3 = 0, totala4 = 0, totala5 = 0, totala6 = 0, totala7 = 0;
        int totalb1 = 0, totalb2 = 0, totalb3 = 0, totalb4 = 0, totalb5 = 0, totalb6 = 0, totalb7 = 0;
        int totalc1 = 0, totalc2 = 0, totalc3 = 0, totalc4 = 0, totalc5 = 0, totalc6 = 0, totalc7 = 0;
        int totald1 = 0, totald2 = 0, totald3 = 0, totald4 = 0, totald5 = 0, totald6 = 0, totald7 = 0;
        int totale1 = 0, totale2 = 0, totale3 = 0, totale4 = 0, totale5 = 0, totale6 = 0, totale7 = 0;
        int totalf1 = 0, totalf2 = 0, totalf3 = 0, totalf4 = 0, totalf5 = 0, totalf6 = 0, totalf7 = 0;
        List<Map<String,String>> lista1 = new ArrayList<>(),lista2 = new ArrayList<>(),lista3 = new ArrayList<>(),lista4 = new ArrayList<>(),lista5 = new ArrayList<>(),lista6 = new ArrayList<>();
        List<Map<String,String>> listb1 = new ArrayList<>(),listb2 = new ArrayList<>(),listb3 = new ArrayList<>(),listb4 = new ArrayList<>(),listb5 = new ArrayList<>(),listb6 = new ArrayList<>();
        List<Map<String,String>> listc1 = new ArrayList<>(),listc2 = new ArrayList<>(),listc3 = new ArrayList<>(),listc4 = new ArrayList<>(),listc5 = new ArrayList<>(),listc6 = new ArrayList<>();
        List<Map<String,String>> listd1 = new ArrayList<>(),listd2 = new ArrayList<>(),listd3 = new ArrayList<>(),listd4 = new ArrayList<>(),listd5 = new ArrayList<>(),listd6 = new ArrayList<>();
        List<Map<String,String>> liste1 = new ArrayList<>(),liste2 = new ArrayList<>(),liste3 = new ArrayList<>(),liste4 = new ArrayList<>(),liste5 = new ArrayList<>(),liste6 = new ArrayList<>();
        List<Map<String,String>> listf1 = new ArrayList<>(),listf2 = new ArrayList<>(),listf3 = new ArrayList<>(),listf4 = new ArrayList<>(),listf5 = new ArrayList<>(),listf6 = new ArrayList<>();
        List<ProjectInfor> projectInfors = projectBoardDao.queryProjectInfor(); //查询项目详情信息
        for(ProjectInfor projectInfor: projectInfors) {
            Map<String,String> content = new HashMap<>();
            content.put("code",projectInfor.getProjectCode());
            content.put("name",projectInfor.getProjectName());
            if(projectInfor.getProjectStatus().equals("1")) {
                totala7 ++;
                switch (projectInfor.getProjectType()) {
                    case "1":
                        totala1++;
                        lista1.add(content);
                        break;
                    case "2":
                        totala2 ++;
                        lista2.add(content);
                        break;
                    case "3":
                        totala3 ++;
                        lista3.add(content);
                        break;
                    case "4":
                        totala4 ++;
                        lista4.add(content);
                        break;
                    case "5":
                        totala5 ++;
                        lista5.add(content);
                        break;
                    case "6":
                        totala6 ++;
                        lista6.add(content);
                        break;
                }
            } else if(projectInfor.getProjectStatus().equals("2")) {
                totalb7 ++;
                switch (projectInfor.getProjectType()) {
                    case "1":
                        totalb1++;
                        listb1.add(content);
                        break;
                    case "2":
                        totalb2 ++;
                        listb2.add(content);
                        break;
                    case "3":
                        totalb3 ++;
                        listb3.add(content);
                        break;
                    case "4":
                        totalb4 ++;
                        listb4.add(content);
                        break;
                    case "5":
                        totalb5 ++;
                        listb5.add(content);
                        break;
                    case "6":
                        totalb6 ++;
                        listb6.add(content);
                        break;
                }
            } else if(projectInfor.getProjectStatus().equals("3")) {
                totalc7 ++;
                switch (projectInfor.getProjectType()) {
                    case "1":
                        totalc1++;
                        listc1.add(content);
                        break;
                    case "2":
                        totalc2 ++;
                        listc2.add(content);
                        break;
                    case "3":
                        totalc3 ++;
                        listc3.add(content);
                        break;
                    case "4":
                        totalc4 ++;
                        listc4.add(content);
                        break;
                    case "5":
                        totalc5 ++;
                        listc5.add(content);
                        break;
                    case "6":
                        totalc6 ++;
                        listc6.add(content);
                        break;
                }
            } else if(projectInfor.getProjectStatus().equals("4")) {
                totald7 ++;
                switch (projectInfor.getProjectType()) {
                    case "1":
                        totald1++;
                        listd1.add(content);
                        break;
                    case "2":
                        totald2 ++;
                        listd2.add(content);
                        break;
                    case "3":
                        totald3 ++;
                        listd3.add(content);
                        break;
                    case "4":
                        totald4 ++;
                        listd4.add(content);
                        break;
                    case "5":
                        totald5 ++;
                        listd5.add(content);
                        break;
                    case "6":
                        totald6 ++;
                        listd6.add(content);
                        break;
                }
            } else if(projectInfor.getProjectStatus().equals("5")) {
                totale7 ++;
                switch (projectInfor.getProjectType()) {
                    case "1":
                        totale1++;
                        liste1.add(content);
                        break;
                    case "2":
                        totale2 ++;
                        liste2.add(content);
                        break;
                    case "3":
                        totale3 ++;
                        liste3.add(content);
                        break;
                    case "4":
                        totale4 ++;
                        liste4.add(content);
                        break;
                    case "5":
                        totale5 ++;
                        liste5.add(content);
                        break;
                    case "6":
                        totale6 ++;
                        liste6.add(content);
                        break;
                }
            } else if(projectInfor.getProjectStatus().equals("6")) {
                totalf7 ++;
                switch (projectInfor.getProjectType()) {
                    case "1":
                        totalf1++;
                        listf1.add(content);
                        break;
                    case "2":
                        totalf2 ++;
                        listf2.add(content);
                        break;
                    case "3":
                        totalf3 ++;
                        listf3.add(content);
                        break;
                    case "4":
                        totalf4 ++;
                        listf4.add(content);
                        break;
                    case "5":
                        totalf5 ++;
                        listf5.add(content);
                        break;
                    case "6":
                        totalf6 ++;
                        listf6.add(content);
                        break;
                }
            }
        }

        Map<String,Object> mapa = new HashMap<>(), mapb = new HashMap<>(), mapc = new HashMap<>(), mapd =  new HashMap<>(), mape = new HashMap<>(), mapf = new HashMap<>();
        mapa.put("num",totala7+"");
        mapb.put("num",totalb7+"");
        mapc.put("num",totalc7+"");
        mapd.put("num",totald7+"");
        mape.put("num",totale7+"");
        mapf.put("num",totalf7+"");
        Map<String,Object> mapa1 = new HashMap<>(), mapa2 = new HashMap<>(), mapa3 = new HashMap<>(), mapa4 = new HashMap<>(), mapa5 = new HashMap<>(), mapa6 = new HashMap<>();
        Map<String,Object> mapb1 = new HashMap<>(), mapb2 = new HashMap<>(), mapb3 = new HashMap<>(), mapb4 = new HashMap<>(), mapb5 = new HashMap<>(), mapb6 = new HashMap<>();
        Map<String,Object> mapc1 = new HashMap<>(), mapc2 = new HashMap<>(), mapc3 = new HashMap<>(), mapc4 = new HashMap<>(), mapc5 = new HashMap<>(), mapc6 = new HashMap<>();
        Map<String,Object> mapd1 = new HashMap<>(), mapd2 = new HashMap<>(), mapd3 = new HashMap<>(), mapd4 = new HashMap<>(), mapd5 = new HashMap<>(), mapd6 = new HashMap<>();
        Map<String,Object> mape1 = new HashMap<>(), mape2 = new HashMap<>(), mape3 = new HashMap<>(), mape4 = new HashMap<>(), mape5 = new HashMap<>(), mape6 = new HashMap<>();
        Map<String,Object> mapf1 = new HashMap<>(), mapf2 = new HashMap<>(), mapf3 = new HashMap<>(), mapf4 = new HashMap<>(), mapf5 = new HashMap<>(), mapf6 = new HashMap<>();
        List<Map<String,Object>> listA1 = new ArrayList<>(),listA2 = new ArrayList<>(),listA3 = new ArrayList<>(),listA4 = new ArrayList<>(),listA5 = new ArrayList<>(),listA6 = new ArrayList<>();
        mapa1.put("num",totala1);
        mapa1.put("list",lista1);
        listA1.add(mapa1);
        mapa2.put("num",totala2);
        mapa2.put("list",lista2);
        listA1.add(mapa2);
        mapa3.put("num",totala3);
        mapa3.put("list",lista3);
        listA1.add(mapa3);
        mapa4.put("num",totala4);
        mapa4.put("list",lista4);
        listA1.add(mapa4);
        mapa5.put("num",totala5);
        mapa5.put("list",lista5);
        listA1.add(mapa5);
        mapa6.put("num",totala6);
        mapa6.put("list",lista6);
        listA1.add(mapa6);
        listA1.add(mapa);
        result.put("proceed",listA1); //进行
        mapb1.put("num",totalb1);
        mapb1.put("list",listb2);
        listA2.add(mapb1);
        mapb2.put("num",totalb2);
        mapb2.put("list",listb2);
        listA2.add(mapb2);
        mapb3.put("num",totalb3);
        mapb3.put("list",listb3);
        listA2.add(mapb3);
        mapb4.put("num",totalb4);
        mapb4.put("list",listb4);
        listA2.add(mapb4);
        mapb5.put("num",totalb5);
        mapb5.put("list",listb5);
        listA2.add(mapb5);
        mapb6.put("num",totalb6);
        mapb6.put("list",listb6);
        listA2.add(mapb6);
        listA2.add(mapb);
        result.put("operations",listA2); //运维
        mapc1.put("num",totalc1);
        mapc1.put("list",listc2);
        listA3.add(mapc1);
        mapc2.put("num",totalc2);
        mapc2.put("list",listc2);
        listA3.add(mapc2);
        mapc3.put("num",totalc3);
        mapc3.put("list",listc3);
        listA3.add(mapc3);
        mapc4.put("num",totalc4);
        mapc4.put("list",listc4);
        listA3.add(mapc4);
        mapc5.put("num",totalc5);
        mapc5.put("list",listc5);
        listA3.add(mapc5);
        mapc6.put("num",totalc6);
        mapc6.put("list",listc6);
        listA3.add(mapc6);
        listA3.add(mapc);
        result.put("complete",listA3); //完成
        mapd1.put("num",totald1);
        mapd1.put("list",listd2);
        listA4.add(mapd1);
        mapd2.put("num",totald2);
        mapd2.put("list",listd2);
        listA4.add(mapd2);
        mapd3.put("num",totald3);
        mapd3.put("list",listd3);
        listA4.add(mapd3);
        mapd4.put("num",totald4);
        mapd4.put("list",listd4);
        listA4.add(mapd4);
        mapd5.put("num",totald5);
        mapd5.put("list",listd5);
        listA4.add(mapd5);
        mapd6.put("num",totald6);
        mapd6.put("list",listd6);
        listA4.add(mapd6);
        listA4.add(mapd);
        result.put("suspended",listA4); //暂停
        mape1.put("num",totale1);
        mape1.put("list",liste2);
        listA5.add(mape1);
        mape2.put("num",totale2);
        mape2.put("list",liste2);
        listA5.add(mape2);
        mape3.put("num",totale3);
        mape3.put("list",liste3);
        listA5.add(mape3);
        mape4.put("num",totale4);
        mape4.put("list",liste4);
        listA5.add(mape4);
        mape5.put("num",totale5);
        mape5.put("list",liste5);
        listA5.add(mape5);
        mape6.put("num",totale6);
        mape6.put("list",liste6);
        listA5.add(mape6);
        listA5.add(mape);
        result.put("discontinue",listA5); //中止
        mapf1.put("num",totalf1);
        mapf1.put("list",listf2);
        listA6.add(mapf1);
        mapf2.put("num",totalf2);
        mapf2.put("list",listf2);
        listA6.add(mapf2);
        mapf3.put("num",totalf3);
        mapf3.put("list",listf3);
        listA6.add(mapf3);
        mapf4.put("num",totalf4);
        mapf4.put("list",listf4);
        listA6.add(mapf4);
        mapf5.put("num",totalf5);
        mapf5.put("list",listf5);
        listA6.add(mapf5);
        mapf6.put("num",totalf6);
        mapf6.put("list",listf6);
        listA6.add(mapf6);
        listA6.add(mapf);
        result.put("daily",listA6); //日常

        return result;
    }


    //详情
    @Override
    public Map<String, Object> detail(String projectCode) {
        Map<String,Object> result = new HashMap<>();
        ProjectInfor projectInfor = projectBoardDao.queryInforDetail(projectCode); //根据编号查询详情
        List<String> incomes = projectBoardDao.queryIncome(projectCode); //查询项目收入
        double income = 0;
        for(String str: incomes) {
            income += Double.parseDouble(str);
        }
        String materials = projectBoardDao.queryMaterials(projectCode); //查询项目的物品消耗
        String human = projectBoardDao.queryHuman(projectCode); //查询人力成本
        String expense = projectBoardDao.queryExpense(projectCode); //查询费用报销
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        String start = simpleDateFormat.format(calendar.getTime());
        Map<String,String> content = new HashMap<>();
        content.put("code",projectCode);
        content.put("start",start);
        WeeklyPlan weeklyPlan = projectBoardDao.queryWeeklyPlan(content); //查询当前周计划
        if(weeklyPlan == null) {
            result.put("weekly",""); //周计划
            result.put("risk",""); //风险提醒
        } else {
            result.put("risk",weeklyPlan.getRisk()); //风险提醒
            List<String> weeklyPlanDetails = projectBoardDao.queryWeeklyPlanDetail(weeklyPlan.getId()); //查询周计划详情
            StringBuilder stringBuilder = new StringBuilder();
            for(String string: weeklyPlanDetails) {
                stringBuilder.append(string);
                stringBuilder.append(";");
            }
            result.put("weekly",stringBuilder.toString()); //周计划
        }

        List<MileStonePlan> mileStonePlans = projectBoardDao.queryMileStonePlans(projectCode); //查询项目里程碑计划
        for(MileStonePlan mileStonePlan: mileStonePlans) {
            if(mileStonePlan.getStatus().equals("1")) {
                result.put("phase",mileStonePlan.getPhase());
                result.put("start",mileStonePlan.getStartDate());
                result.put("expected",mileStonePlan.getEndDate());
                try {
                    long date1 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlan.getStartDate()).getTime();
                    long date2 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlan.getEndDate()).getTime();
                    long date3 = new Date().getTime();
                    String schedule = new BigDecimal((double) (date3 - date1) / (double) (date2 - date1)*100).setScale(2,RoundingMode.UP).doubleValue() + "%";
                    result.put("schedule",schedule); //时间进度
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        result.put("infor",projectInfor); //项目基本信息
        result.put("income",income); //项目收入
        result.put("materials",materials); //物品消耗
        result.put("human",human); //物品消耗
        result.put("expense",expense); //物品消耗
        result.put("miles",mileStonePlans); //里程碑计划
        return result;
    }

    //看板信息（手机）
    @Override
    public List<Map<String, Object>> projectPhone() {
        List<Map<String,Object>> list = new ArrayList<>();
        List<ProjectVarious> typeList = projectBoardDao.queryProjectType(); //查询所有项目类型
        List<ProjectVarious> sourceList = projectBoardDao.queryProjectSource(); //查询所有项目来源
        List<ProjectInfor> projectInforList = projectBoardDao.queryProInforP(); //查询项目信息 （手机）
        for(ProjectInfor projectInfor: projectInforList) {
            Map<String,Object> map = new HashMap<>();
            //项目进本情况
            map.put("projectCode",projectInfor.getProjectCode());
            map.put("projectName",projectInfor.getProjectName());
            map.put("inDepartment",projectInfor.getInDepartment());
            map.put("projectStatus","进行");
            map.put("projectManager",projectInfor.getProjectManager());
            for(ProjectVarious source : sourceList) {
                if(projectInfor.getProjectSource().equals(source.getCode())) {
                    map.put("projectSource",source.getName());
                }
            }
            map.put("businessManager",projectInfor.getBusinessManager());
            for(ProjectVarious type: typeList) {
                if(projectInfor.getProjectType().equals(type.getCode())) {
                    map.put("projectType",type.getName());
                }
            }
            map.put("situation",projectInfor.getGeneralSituation());
            //收入支出
            ProjectIncome projectIncome = projectBoardDao.queryIncomeContent(projectInfor.getProjectCode()); //查询项目收入的内容
            if(projectIncome == null) {
                map.put("payAmount", "");
                map.put("payDate","");
                map.put("invoiceAmount","");
                map.put("invoiceProportion","");
                map.put("receivableAmount","");
                map.put("receivableProportion","");
                map.put("acceptanceAmount","");
                map.put("acceptanceProportion","");
            } else {
                map.put("payAmount", projectIncome.getPayAmount());
                map.put("payDate", projectIncome.getPayDate());
                map.put("invoiceAmount", projectIncome.getInvoiceAmount());
                map.put("invoiceProportion", projectIncome.getInvoiceProportion());
                map.put("receivableAmount", projectIncome.getReceivableAmount());
                map.put("receivableProportion", projectIncome.getReceivableProportion());
                map.put("acceptanceAmount", projectIncome.getAcceptanceAmount());
                map.put("acceptanceProportion", projectIncome.getAcceptanceProportion());
            }
            List<String> projectIncomeDetailList = projectBoardDao.queryIncomeDetail(projectInfor.getProjectCode()); //查询项目收入合同收入金额
            double income = 0;
            if(projectIncomeDetailList.size() == 0) {

            } else {
                for(String str: projectIncomeDetailList) {
                    income += Double.parseDouble(str);
                }
            }

            double consume = 0;
            ProjectMaterial projectMaterial = projectBoardDao.queryProjectMaterial(projectInfor.getProjectCode()); //查询物品消耗
            if(projectMaterial == null) {
                map.put("projectMaterial","");
            } else {
                map.put("projectMaterial", projectMaterial.getTotalAmountL());
                consume += Double.parseDouble(projectMaterial.getTotalAmountL());
            }
            ProjectHuman projectHuman = projectBoardDao.queryProjectHuman(projectInfor.getProjectCode()); //查询人力成本
            if(projectHuman == null) {
                map.put("projectHuman","");
            } else {
                map.put("projectHuman",projectHuman.getTotalAmountL());
                consume += Double.parseDouble(projectHuman.getTotalAmountL());
            }
            ProjectExpense projectExpense = projectBoardDao.queryProjectExpense(projectInfor.getProjectCode()); //查询费用报销
            if(projectExpense == null) {
                map.put("projectExpense","");
            } else {
                map.put("projectExpense",projectExpense.getTotalAmountL());
                consume += Double.parseDouble(projectExpense.getTotalAmountL());
            }
            if(income == 0) {
                map.put("grossProfit","");
                map.put("gorssProfitMargin","");
            } else {
                map.put("grossProfit",(income - consume)+"");
                String grossProfitMargin = new BigDecimal((income - consume) / income * 100).setScale(2,RoundingMode.UP) + "%";
                map.put("gorssProfitMargin",grossProfitMargin);
            }

            map.put("writeDate",projectInfor.getWriteDate()); //立项日期
            List<MileStonePlan> mileStonePlans = projectBoardDao.queryMileStonePlans(projectInfor.getProjectCode()); //查询该项目的所有里程碑计划
            boolean mileMarker = true;
            if(mileStonePlans.size() > 0) {
                for(MileStonePlan mileStonePlan: mileStonePlans) {
                    if(mileStonePlan.getStatus().equals("1")) {
                        mileStonePlan.setStatus("进行");
                        map.put("projectPhase",mileStonePlan.getPhase()); //项目阶段
                        map.put("startDate",mileStonePlan.getStartDate()); //启动日期
                        map.put("endDate",mileStonePlan.getEndDate()); //预计完成
                        try {
                            long date1 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlan.getStartDate()).getTime();
                            long date2 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlan.getEndDate()).getTime();
                            long date3 = new Date().getTime();
                            String schedule = new BigDecimal((double) (date3 - date1) / (double) (date2 - date1)*100).setScale(2,RoundingMode.UP).doubleValue() + "%";
                            map.put("mileSchedule",schedule);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        mileMarker = false;
                    } else if(mileStonePlan.getStatus().equals("2")) {
                        mileStonePlan.setStatus("延期");
                    } else if(mileStonePlan.getStatus().equals("3")) {
                        mileStonePlan.setStatus("暂停");
                    } else if(mileStonePlan.getStatus().equals("4")) {
                        mileStonePlan.setStatus("完成");
                    } else if(mileStonePlan.getStatus().equals("5")) {
                        mileStonePlan.setStatus("中止");
                    }
                }
                map.put("milePlan",mileStonePlans); //里程碑计划
            } else {
                map.put("milePlan",null); //里程碑计划
            }
            if(mileMarker) {
                map.put("projectPhase","");
                map.put("startDate","");
                map.put("endDate","");
                map.put("mileSchedule","");
            }


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            String start = simpleDateFormat.format(calendar.getTime());
            Map<String,String> content = new HashMap<>();
            content.put("code",projectInfor.getProjectCode());
            content.put("start",start);
            WeeklyPlan weeklyPlan = projectBoardDao.queryWeeklyPlan(content); //查询当前周计划
            if(weeklyPlan == null) {
                map.put("weeklyPlan",null);
                map.put("currentProgress",""); //当前进度
            } else {
                List<String> weeklyPlanDetails = projectBoardDao.queryWeeklyPlanDetail(weeklyPlan.getId()); //查询周计划详情
                map.put("weeklyPlan",weeklyPlanDetails);
                StringBuilder stringBuilder = new StringBuilder();
                for(String string: weeklyPlanDetails) {
                    stringBuilder.append(string);
                    stringBuilder.append(";");
                }
                map.put("currentProgress",stringBuilder.toString()); //当前进度
            }
            list.add(map);
        }

        return list;
    }
}
