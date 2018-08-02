package com.elex.oa.service.project.impl;

import com.elex.oa.controller.controller_shiyun.PersonalInformationController;
import com.elex.oa.dao.project.OperationDao;
import com.elex.oa.dao.project.ProjectBoardDao;
import com.elex.oa.dao.project.ProjectSetDao;
import com.elex.oa.dao.project.WeeklyPlanDao;
import com.elex.oa.entity.entity_shiyun.Dept;
import com.elex.oa.entity.entity_shiyun.PerAndPostRs;
import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import com.elex.oa.entity.project.*;
import com.elex.oa.service.project.ProjectBoardService;
import com.elex.oa.service.service_shiyun.IDeptService;
import com.elex.oa.service.service_shiyun.IPerandpostrsService;
import com.elex.oa.service.service_shiyun.IPersonalInformationService;
import com.elex.oa.service.service_shiyun.IPostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    @Resource
    private WeeklyPlanDao weeklyPlanDao;

    @Resource
    private OperationDao operationDao;

    @Resource
    private IPostService iPostService;

    @Autowired
    private IDeptService iDeptService;

    @Autowired
    private IPerandpostrsService iPerandpostrsService;

    @Autowired
    private IPersonalInformationService informationService;

    @Autowired
    private PersonalInformationController personalInformationController;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private ProjectSetDao projectSetDao;

    //列表总览
    public Map<String, Object> overviewOld( ) {
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


    //项目数量
    @Override
    public Map<String, Object> overview(String department) {
        Map<String,Object> result = new HashMap<>();
        List<ProjectVarious> typeList = projectSetDao.queryType();
        List<ProjectVarious> statusList = projectSetDao.queryStatus();
        List<ProjectVarious> phaseList = projectSetDao.queryPhase();
        List<ProjectInfor> projectInfors = new ArrayList<>();
        if(department.equals("博智")) {
            projectInfors = projectBoardDao.queryProjectInfor(); //查询项目详情信息
        } else {
            projectInfors = projectBoardDao.queryProjectInforByDepartment(department); //根据部门信息查询项目详情
        }
        int size1 = typeList.size() + 1;
        int size2 = statusList.size() + 1;
        int size3 = phaseList.size();
        int[][] number1 = new int[size1][size2];
        int[][] number2 = new int[size1][size3];


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        String start = simpleDateFormat.format(calendar.getTime());
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
        String end = simpleDateFormat.format(calendar1.getTime());
        Map<String,String> content = new HashMap<>();
        content.put("start",start);
        content.put("end",end);

        for(ProjectInfor infor: projectInfors) {
            for(int k = 0; k< typeList.size(); k++) {
                if(infor.getProjectType().equals(typeList.get(k).getCode()+"")) {
                    number1[k][size2 - 1] ++;
                    for(int j = 0; j < statusList.size(); j ++) {
                        if(infor.getProjectStatus().equals(statusList.get(j).getCode()+"")) {
                            number1[k][j] ++;
                            number1[size1 - 1][j] ++;
                            if(statusList.get(j).getName().equals("进行")) {
                                content.put("code",infor.getProjectCode());
                                String phase = weeklyPlanDao.queryPlanByCon(content); //条件查询当前周报中的阶段信息
                                if(phase == null || phase.equals("")) {

                                } else {
                                    for(int l = 0; l < phaseList.size(); l ++) {
                                        if(phase.equals(phaseList.get(l).getCode()+"")) {
                                            number2[k][l] ++;
                                            number2[size1 - 1][l] ++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        List<Object> right = new ArrayList<>();
        for(int n = 0; n < typeList.size(); n++) {
            List<Map<String,String>> list = new ArrayList<>();
            for(int m = 0; m < statusList.size(); m++) {
                Map<String,String> map = new HashMap<>();
                map.put("type",typeList.get(n).getCode()+"");
                map.put("status",statusList.get(m).getCode()+"");
                map.put("num",number1[n][m]+"");
                list.add(map);
            }
            Map<String,String> map1 = new HashMap<>();
            map1.put("type",typeList.get(n).getCode() + "");
            map1.put("status","Total");
            map1.put("num",number1[n][size2 - 1] + "");
            list.add(map1);
            right.add(list);
        }
        List<Map<String,String>> listR = new ArrayList<>();
        for(int t = 0; t < statusList.size(); t ++) {
            Map<String,String> map = new HashMap<>();
            map.put("type","Total");
            map.put("status",statusList.get(t).getCode()+"");
            map.put("num",number1[size1 - 1][t]+"");
            listR.add(map);
        }
        Map<String,String> mapT = new HashMap<>();
        mapT.put("type","Total");
        mapT.put("status","Total");
        mapT.put("num",projectInfors.size()+"");
        listR.add(mapT);
        right.add(listR);
        result.put("right",right);

        List<Object> left = new ArrayList<>();
        for(int u = 0; u < typeList.size(); u++ ) {
            List<Map<String,String>> list = new ArrayList<>();
            for(int i = 0; i < phaseList.size(); i++) {
                Map<String,String> map = new HashMap<>();
                map.put("type",typeList.get(u).getCode()+"");
                map.put("phase",phaseList.get(i).getCode()+"");
                map.put("num",number2[u][i]+"");
                list.add(map);
            }
            left.add(list);
        }
        List<Map<String,String>> listL = new ArrayList<>();
        for(int p = 0; p < phaseList.size(); p ++) {
            Map<String,String> mapP = new HashMap<>();
            mapP.put("type","Total");
            mapP.put("phase",phaseList.get(p).getCode()+"");
            mapP.put("num",number2[size1 - 1][p]+"");
            listL.add(mapP);
        }
        left.add(listL);
        result.put("left",left);
        return result;
    }

    //查看某一类型的项目
    @Override
    public PageInfo projectVarious(Integer pageNum, String status, String type, String department) {
        Map<String,String> conditions = new HashMap<>();
        conditions.put("status",status);
        conditions.put("type",type);
        conditions.put("department",department);
        PageHelper.startPage(pageNum,5);
        if(status.equals("0")) {
            List<ApprovalList> approvalLists = projectBoardDao.queryApprovalByCon(conditions); //条件查询已立项项目
            return new PageInfo(approvalLists);
        } else {
            List<ProjectInfor> inforList = projectBoardDao.queryInforByCon(conditions); //条件查询项目信息
            return new PageInfo(inforList);
        }
    }

    //查看某类型的项目
    @Override
    public PageInfo projectStatus(Integer pageNum, String status, String type, String department) {
        Map<String,String> condition = new HashMap<>();
        condition.put("status",status);
        condition.put("type",type);
        condition.put("department",department);
        PageHelper.startPage(pageNum, 5);
        List<ProjectInfor> infors = projectBoardDao.queryInforCon(condition); //条件查询项目信息
        return new PageInfo(infors);
    }

    //查看某阶段的项目
    @Override
    public PageInfo projectPhase(Integer pageNum, String phase, String type, String department) {
        List<String> codes = weeklyPlanDao.queryCodeByPhase(phase); //查询某个阶段的项目编号
        Map<String,Object> content =  new HashMap<>();
        content.put("codes",codes);
        content.put("type",type);
        content.put("department",department);
        System.out.println(content);
        PageHelper.startPage(pageNum,5);
        List<ProjectInfor> infors = projectBoardDao.queryInforPhase(content); //条件查询项目信息
        return new PageInfo(infors);
    }

    //详情
    @Override
    public Map<String, Object> detail(String projectCode) {
        Map<String,Object> result = new HashMap<>();
        List<ProjectVarious> typeList = projectSetDao.queryType();
        List<ProjectVarious> sourceList = projectSetDao.querySource();
        List<ProjectVarious> statusList = projectSetDao.queryStatus();
        List<ProjectVarious> phaseList = projectSetDao.queryPhase();
        ProjectInfor projectInfor = projectBoardDao.queryInforDetail(projectCode); //根据编号查询详情

        String materials = projectBoardDao.queryMaterials(projectCode); //查询项目的物品消耗
        if(materials == null) {
            result.put("materials","");
        } else {
            result.put("materials","");
        }
        String human = projectBoardDao.queryHuman(projectCode); //查询人力成本
        if(human == null) {
            result.put("human","");
        } else {
            result.put("human",human);
        }
        String expense = projectBoardDao.queryExpense(projectCode); //查询费用报销
        if(expense == null) {
            result.put("expense","");
        } else {
            result.put("expense",expense);
        }

        ProjectIncome projectIncome = operationDao.queryProjectIncome(projectCode); //根据项目编号查询项目收入
        if(projectIncome == null) {
            result.put("amount","");
            result.put("copies","");
            result.put("receivableAmount","");
            result.put("grossProfit","");
            result.put("grossProfitMargin","");
        } else {
            result.put("amount",projectIncome.getAmount());
            result.put("copies",projectIncome.getCopies());
            result.put("receivableAmount",projectIncome.getReceivableAmount());
            if(projectIncome.getAmount() == null || projectIncome.getAmount().equals("")) {
                result.put("grossProfit","");
                result.put("grossProfitMargin","");
            } else {
                double molecular = Double.parseDouble(projectIncome.getAmount());
                if(materials == null) {

                } else {
                    molecular -= Double.parseDouble(materials);
                }
                if(human == null) {

                } else {
                    molecular -= Double.parseDouble(human);
                }
                if(expense == null) {

                } else {
                    molecular -= Double.parseDouble(expense);
                }
                double denominator = Double.parseDouble(projectIncome.getAmount());
                String margin = new BigDecimal(molecular / denominator * 100).setScale(2,RoundingMode.UP).doubleValue() + "%";
                result.put("grossProfit",molecular+"");
                result.put("grossProfitMargin",margin);
            }
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        String start = simpleDateFormat.format(calendar.getTime());
        Calendar calendar1 = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
        String end = simpleDateFormat.format(calendar.getTime());

        Map<String,String> content = new HashMap<>();
        content.put("code",projectCode);
        content.put("start",start);
        content.put("end",end);
        WeeklyPlan weeklyPlan = projectBoardDao.queryWeeklyPlan(content);

        if(weeklyPlan ==  null) {
            result.put("startDate","");
            result.put("endDate","");
            result.put("timeSchudle","");
            result.put("fulfillment","");
            result.put("punctuality","");
            result.put("projectPhase","");
            result.put("completion","");
            result.put("nextPlan","");
            result.put("risk","");
        } else {
            result.put("startDate",weeklyPlan.getStartDate());
            result.put("endDate",weeklyPlan.getEndDate());
            result.put("timeSchedule",weeklyPlan.getTimeSchedule());
            result.put("fulfillment",weeklyPlan.getFulfillment());
            if(weeklyPlan.getPunctuality().equals("t")) {
                result.put("punctuality","提前");
            } else if(weeklyPlan.getPunctuality().equals("a")) {
                result.put("punctuality","按期");
            } else if(weeklyPlan.getPunctuality().equals("y")) {
                result.put("punctuality","延期");
            } else {
                result.put("punctuality","");
            }
            for(ProjectVarious phase: phaseList) {
                if(weeklyPlan.getProjectPhase().equals(phase.getCode()+"")) {
                    result.put("projectPhase",phase.getName());
                    break;
                }
            }
            result.put("completion",weeklyPlan.getCompletion());
            result.put("nextPlan",weeklyPlan.getNextPlan());
            result.put("risk",weeklyPlan.getRisk());
        }

        List<MileStonePlan> mileStonePlans = projectBoardDao.queryMileStonePlans(projectCode); //查询项目里程碑计划
        for(MileStonePlan mileStonePlan: mileStonePlans) {
            if(mileStonePlan.getStatus().equals("1")) {
                mileStonePlan.setStatus("进行");
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


        for(ProjectVarious type: typeList) {
            if(projectInfor.getProjectType().equals(type.getCode()+"")) {
                projectInfor.setProjectType(type.getName());
                break;
            }
        }
        for(ProjectVarious source: sourceList) {
            if(projectInfor.getProjectSource().equals(source.getCode()+"")) {
                projectInfor.setProjectSource(source.getName());
                break;
            }
        }
        for(ProjectVarious status: statusList) {
            if(projectInfor.getProjectStatus().equals(status.getCode()+"")) {
                projectInfor.setProjectStatus(status.getName());
                break;
            }
        }

        result.put("infor",projectInfor); //项目基本信息
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            String start = simpleDateFormat.format(calendar.getTime());
            Map<String,String> content = new HashMap<>();
            WeeklyPlan weeklyPlan = projectBoardDao.queryWeeklyPlan(content); //查询当前周计划
            if(weeklyPlan == null) {
                map.put("currentProgress",""); //当前进度
            } else {
                List<WeeklyPlanDetail> weeklyPlanDetails = projectBoardDao.queryWeeklyPlanDetail(weeklyPlan.getId()); //查询周计划详情
                map.put("weeklyPlan",weeklyPlanDetails);
                StringBuilder stringBuilder = new StringBuilder();
                for(WeeklyPlanDetail weeklyPlanDetail: weeklyPlanDetails) {
                    stringBuilder.append(weeklyPlanDetail.getIssues());
                    stringBuilder.append(";");
                }
                map.put("currentProgress",stringBuilder.toString()); //当前进度
            }
            list.add(map);
        }

        return list;
    }

    //看板详情详情（手机）
    @Override
    public Map<String, Object> projectDetail(String projectCode) {
        ProjectInfor projectInfor = projectBoardDao.queryInforDetail(projectCode);
        if(projectInfor == null) {
            return null;
        }
        List<ProjectVarious> typeList = projectBoardDao.queryProjectType(); //查询所有项目类型
        List<ProjectVarious> sourceList = projectBoardDao.queryProjectSource(); //查询所有项目来源
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
            map.put("payAmount", "");
            map.put("payDate", "");
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
        System.out.println();
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
        WeeklyPlan currentWeek = projectBoardDao.queryWeeklyPlan(content); //查询当前周计划
        if(currentWeek == null) {
            map.put("currentProgress",""); //当前进度
            map.put("planProgress",""); //计划进度
            map.put("weekNote",""); //备注
            map.put("weekProgross","");
        } else {
            List<WeeklyPlanDetail> weeklyPlanDetails = projectBoardDao.queryWeeklyPlanDetail(currentWeek.getId()); //查询周计划详情
            map.put("weekNote",currentWeek.getRisk()); //备注
            map.put("weekProgress",""); //事项完成进度
            StringBuilder stringBuilder = new StringBuilder();
            for(WeeklyPlanDetail weeklyPlanDetail: weeklyPlanDetails) {
                stringBuilder.append(weeklyPlanDetail.getIssues());
                stringBuilder.append(";");
            }
            map.put("currentProgress",stringBuilder.toString()); //当前进度
            Calendar calendarW = Calendar.getInstance();
            calendarW.setTime(new Date());
            int weekDay =  calendarW.get(Calendar.DAY_OF_WEEK);
            if(weekDay == 1 || weekDay == 2) {
                map.put("planProgress","0%");
            } else if(weekDay == 3) {
                map.put("planProgress","20%");
            } else if(weekDay == 4) {
                map.put("planProgress","40%");
            } else if(weekDay == 5) {
                map.put("planProgress","60%");
            } else if(weekDay == 6) {
                map.put("planProgress","80%");
            } else if(weekDay == 7) {
                map.put("planProgress","100%");
            }
        }

        Calendar calendar1 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 1 - dayOfWeek;
        calendar1.add(Calendar.DATE,offset - 7);
        String last = simpleDateFormat.format(calendar1.getTime());
        content.put("start",last);
        WeeklyPlan lastWeek = projectBoardDao.queryWeeklyPlan(content);//查询上周的周报情况
        if(lastWeek == null) {
            map.put("lastWeek","");
        } else {
            if(lastWeek.getCompletion() == null) {
                map.put("lastWeek","");
            } else {
                map.put("lastWeek",lastWeek.getCompletion());
            }
        }

        return map;
    }


    //数据库人员更新
    @Override
    public void informationUpdate() {
        mongoTemplate.dropCollection(Staff.class);
        List<PersonalInformation> personalInformationList = informationService.queryAllByNull();
        for(PersonalInformation per: personalInformationList) {
            PersonalInformation onePersonalInformation = null;
            try {
                onePersonalInformation = personalInformationController.getOnePersonalinformation(per.getId());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(onePersonalInformation == null) {
                continue;
            }
            Staff staff = new Staff();
            staff.setId(onePersonalInformation.getId());
            staff.setPhoneticize(onePersonalInformation.getUsername());
            staff.setEmployeeName(onePersonalInformation.getTruename());
            staff.setDeptId(onePersonalInformation.getDepid()+"");
            if(onePersonalInformation.getDepid() == null) {
                staff.setDeptName("");
            } else {
                Dept dept = iDeptService.queryOneDepByDepid(onePersonalInformation.getId());
                if(dept == null) {
                    staff.setDeptName("");
                } else {
                    staff.setDeptName(dept.getDepname());
                }
            }
            List<PerAndPostRs> perAndPostRsList = iPerandpostrsService.queryPerAndPostRsByPerid(onePersonalInformation.getId());
            List<Map<String,String>> postList =  new ArrayList<>();
            for(PerAndPostRs perAndPostRs: perAndPostRsList) {
                Map<String,String> postMap = new HashMap<>();
                postMap.put("postId",perAndPostRs.getPostid()+"");
                postMap.put("postName",iPostService.queryOneByPostid(perAndPostRs.getPostid()).getPostname());
                postList.add(postMap);
            }
            staff.setPost(postList);
            mongoTemplate.save(staff);
        }
    }

    //查询所有人员信息
    @Override
    public List<Staff> queryStaff() {
        List<Staff> staffList = mongoTemplate.findAll(Staff.class);
        return staffList;
    }

    //看板手机部门相关详情（手机）
    @Override
    public List<Map<String, String>> projectTotal(String department) {
        if(department.equals("上海臻相") || department.equals("贵州中科") || department.equals("南京总部")) {

        } else {
            return null;
        }
        List<ProjectInfor> projectInfors;
        if(department.equals("南京总部")) {
            projectInfors = projectBoardDao.queryInforMain(); //查询南京总部的项目详情信息
        } else {
            projectInfors = projectBoardDao.queryProjectInforByDepartment(department); //根据部门查询信息
        }
        List<ProjectVarious> typeList = projectSetDao.queryType();
        List<ProjectVarious> statusList = projectSetDao.queryStatus();
        String status = "";
        for(ProjectVarious st: statusList) {
            if(st.getName().equals("进行")) {
                status += st.getCode();
                break;
            }
        }
        int proceedT = 0, otherT = 0;
        int proceedN = 0, otherN = 0, subN = 0;
        List<Map<String,String>> result = new ArrayList<>();
        for(ProjectVarious type : typeList) {
            proceedN = otherN = subN = 0;
            for(ProjectInfor projectInfor:projectInfors) {
                if(projectInfor.getProjectType().equals(type.getCode()+"")) {
                    subN++;
                    if(projectInfor.getProjectStatus().equals(status)) {
                        proceedN ++; proceedT++;
                    } else {
                        otherN ++; otherT++;
                    }
                }
            }
            Map<String,String> map = new HashMap<>();
            map.put("type",type.getName());
            map.put("proceed",proceedN+"");
            map.put("other",otherN+"");
            map.put("sub",subN+"");
            result.add(map);
        }
        Map<String,String> map = new HashMap<>();
        map.put("type","合计");
        map.put("proceed",proceedT+"");
        map.put("other",otherT+"");
        map.put("sub",projectInfors.size()+"");
        result.add(map);
        return result;
    }

    //看板根据类型查询概况（手机）
    @Override
    public List<Map<String, String>> projectSurvey(String status, String type, String department) {
        // status: 进行， 其他， 小计
        // type: 产品销售等
        // department: 南京总部，上海臻相， 贵州中科
        if(status.equals("进行") || status.equals("其他") || status.equals("小计")) {

        } else {
            return null;
        }
        if(department.equals("南京总部") || department.equals("上海臻相") || department.equals("贵州中科")) {

        } else {
            return null;
        }
        List<Map<String,String>> list = new ArrayList<>();
        List<ProjectInfor> projectInfors;
        if(department.equals("南京总部")) {
            projectInfors = projectBoardDao.queryInforExclusive(); //查询南京总部的项目信息
        } else {
            projectInfors = projectBoardDao.queryProjectInforByDepartment(department);
        }

        List<ProjectVarious> typeList = projectSetDao.queryType();
        List<ProjectVarious> statusList = projectSetDao.queryStatus();

        boolean marker1 = false, marker2 = false;
        for(ProjectInfor projectInfor: projectInfors) {
            marker1 = marker2 = false;
            if(status.equals("进行")) {
                for(ProjectVarious sta: statusList) {
                    if(sta.getName().equals("进行")) {
                        if(projectInfor.getProjectStatus().equals(sta.getCode()+"")) {
                            marker1 = true;
                        }
                    }
                }
            } else if(status.equals("其他")) {
                for(ProjectVarious sta1: statusList) {
                    if(sta1.getName().equals("进行")) {
                        if(projectInfor.getProjectStatus().equals(sta1.getCode()+"")) {

                        } else {
                            marker1 = true;
                        }
                    }
                }
            } else if(status.equals("小计")) {
                marker1 = true;
            }
            if(type.equals("合计")) {
                marker2 = true;
            } else {
                for(ProjectVarious ty: typeList) {
                    if(ty.getName().equals(type)) {
                        if(projectInfor.getProjectType().equals(ty.getCode()+"")) {
                            marker2 = true;
                        } else {

                        }
                    }
                }
            }

            if(marker1 && marker2) {

            } else {
                continue;
            }

            Map<String,String> map = new HashMap<>();
            map.put("projectCode",projectInfor.getProjectCode());
            map.put("projectName",projectInfor.getProjectName());
            map.put("inDepartment",projectInfor.getInDepartment());
            map.put("projectManager",projectInfor.getProjectManager());
            for(ProjectVarious st: statusList) {
                if(projectInfor.getProjectStatus().equals(st.getCode()+"")) {
                    map.put("projectStatus",st.getName());
                }
            }
            List<MileStonePlan> mileStonePlans = projectBoardDao.queryMileStonePlans(projectInfor.getProjectCode()); //查询该项目的所有里程碑计划,时间进度
            boolean mileMarker = true;
            for(MileStonePlan mileStonePlan: mileStonePlans) {
                if(mileStonePlan.getStatus().equals("1")) {
                    map.put("projectPhase",mileStonePlan.getPhase());
                    map.put("progress","");
                    mileMarker = false;
                    try {
                        long date1 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlan.getStartDate()).getTime();
                        long date2 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlan.getEndDate()).getTime();
                        long date3 = new Date().getTime();
                        double molecular = (date3 - date1) / (1000 * 24 * 3600) + 1;
                        double denominator = (date2 - date1) / (1000 * 24 * 3600) + 1;
                        String schedule = new BigDecimal(molecular / denominator * 100).setScale(2,RoundingMode.UP).doubleValue() + "%";
                        map.put("mileSchedule",schedule);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(mileMarker) {
                map.put("mileSchedule",""); //时间进度
                map.put("projectPhase",""); //当前阶段
                map.put("progress","");
            }
            list.add(map);
        }
        return list;
    }
}
