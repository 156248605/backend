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
                    if(infor.getProjectStatus().equals("") || infor.getProjectStatus() == null) {
                        break;
                    }
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
