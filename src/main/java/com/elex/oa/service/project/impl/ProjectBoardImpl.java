package com.elex.oa.service.project.impl;

import com.elex.oa.controller.hr.PersonalInformationController;
import com.elex.oa.dao.project.OperationDao;
import com.elex.oa.dao.project.ProjectBoardDao;
import com.elex.oa.dao.project.ProjectSetDao;
import com.elex.oa.dao.project.WeeklyPlanDao;
import com.elex.oa.entity.hr_entity.Dept;
import com.elex.oa.entity.hr_entity.PerAndPostRs;
import com.elex.oa.entity.hr_entity.PersonalInformation;
import com.elex.oa.entity.project.*;
import com.elex.oa.service.project.ProjectBoardService;
import com.elex.oa.service.hr_service.IDeptService;
import com.elex.oa.service.hr_service.IPerandpostrsService;
import com.elex.oa.service.hr_service.IPersonalInformationService;
import com.elex.oa.service.hr_service.IPostService;
import com.elex.oa.util.util_per.SpellUtils;
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
        if(department.contains("股份")) {
            projectInfors = projectBoardDao.queryProjectInfor(); //查询项目详情信息
        } else {
            List<Dept> depts = iDeptService.queryByCompanyname(department);
            List<String> deptList = new ArrayList<>();
            deptList.add(department);
            for(Dept dept:depts) {
                deptList.add(dept.getDepname());
            }
            projectInfors = projectBoardDao.queryProjectInforByDepartment(deptList); //根据部门信息查询项目详情
        }
        int size1 = typeList.size() + 1;
        int size2 = statusList.size() + 1;
        int size3 = phaseList.size();
        int[][] number1 = new int[size1][size2];
        int[][] number2 = new int[size1][size3];
        int[][] number3 = new int[size1][4];

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
                                //String phase = weeklyPlanDao.queryPlanByCon(content); //条件查询当前周报中的阶段信息
                                WeeklyPlan weeklyPlan = weeklyPlanDao.queryPlanContent(content); //条件查询当前周报信息
                                if(weeklyPlan == null) {

                                } else {
                                    for(int l = 0; l < phaseList.size(); l ++) {
                                        if(weeklyPlan.getProjectPhase().equals(phaseList.get(l).getCode()+"")) {
                                            number2[k][l] ++;
                                            number2[size1 - 1][l] ++;
                                        }
                                    }
                                    if(weeklyPlan.getPunctuality().equals("t")) {
                                        number3[k][0] ++;
                                        number3[size1 - 1][0] ++;
                                    } else if(weeklyPlan.getPunctuality().equals("a")) {
                                        number3[k][1] ++;
                                        number3[size1 - 1][1] ++;
                                    } else if(weeklyPlan.getPunctuality().equals("y")) {
                                        number3[k][2] ++;
                                        number3[size1 - 1][2] ++;
                                    } else {
                                        number3[k][3] ++;
                                        number3[size1 - 1][3] ++;
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

        List<Object> week = new ArrayList<>();
        for(int r = 0; r < typeList.size(); r ++) {
            List<Map<String,String>> list = new ArrayList<>();
            for(int t = 0; t < 4; t ++) {
                Map<String,String> map = new HashMap<>();
                map.put("type",typeList.get(r).getCode()+"");
                if(t == 0) {
                    map.put("punctuality","t");
                } else if(t == 1) {
                    map.put("punctuality","a");
                } else if(t == 2) {
                    map.put("punctuality","y");
                } else if(t == 3) {
                    map.put("punctuality","w");
                }
                map.put("num",number3[r][t]+"");
                list.add(map);
            }
            week.add(list);
        }
        List<Map<String,String>> listW = new ArrayList<>();
        for(int h = 0; h < 4; h ++) {
            Map<String,String> mapY = new HashMap<>();
            mapY.put("type","Total");
            if(h == 0) {
                mapY.put("punctuality","t");
            } else if(h == 1) {
                mapY.put("punctuality","a");
            } else if(h == 2) {
                mapY.put("punctuality","y");
            } else if(h == 3) {
                mapY.put("punctuality","w");
            }
            mapY.put("num",number3[size1-1][h]+"");
            listW.add(mapY);
        }
        week.add(listW);
        result.put("week",week);
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
        Map<String,Object> condition = new HashMap<>();
        condition.put("status",status);
        condition.put("type",type);
        if(department.contains("股份")) {
            condition.put("department","all");
        } else {
            List<Dept> depts = iDeptService.queryByCompanyname(department);
            List<String> deptList = new ArrayList<>();
            deptList.add(department);
            for(Dept dept:depts) {
                deptList.add(dept.getDepname());
            }
            condition.put("department","part");
            condition.put("list",deptList);
        }
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
        if(department.contains("股份")) {
            content.put("department","all");
        } else {
            List<Dept> depts = iDeptService.queryByCompanyname(department);
            List<String> deptList = new ArrayList<>();
            deptList.add(department);
            for(Dept dept:depts) {
                deptList.add(dept.getDepname());
            }
            content.put("department","part");
            content.put("list",deptList);
        }
        PageHelper.startPage(pageNum,5);
        List<ProjectInfor> infors = projectBoardDao.queryInforPhase(content); //条件查询项目信息
        return new PageInfo(infors);
    }

    //查看是否延期的项目
    @Override
    public PageInfo projectWeek(Integer pageNum, String punctuality, String type, String department) {
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
        if(punctuality.equals("t")) {
            content.put("punctuality","ti");
        } else if(punctuality.equals("a")) {
            content.put("punctuality","an");
        } else if(punctuality.equals("y")) {
            content.put("punctuality","yan");
        } else if(punctuality.equals("w")) {
            content.put("punctuality","wu");
        }
        List<String> codes = projectBoardDao.queryWeekByContent(content); //查询本周周报相关的项目编号
        Map<String,Object> content1 =  new HashMap<>();
        content1.put("codes",codes);
        content1.put("type",type);
        if(department.contains("股份")) {
            content1.put("department","all");
        } else {
            List<Dept> depts = iDeptService.queryByCompanyname(department);
            List<String> deptList = new ArrayList<>();
            deptList.add(department);
            for(Dept dept:depts) {
                deptList.add(dept.getDepname());
            }
            content1.put("department","part");
            content1.put("list",deptList);
        }

        PageHelper.startPage(pageNum,5);
        List<ProjectInfor> infors = projectBoardDao.queryInforPhase(content1);

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
            result.put("materials",materials);
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
                result.put("grossProfit",new BigDecimal(molecular).setScale(2,BigDecimal.ROUND_UP).toString());
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
            staff.setPhoneticize(SpellUtils.phoneticize(onePersonalInformation.getUsername()));
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
        if(department.equals("全部") || department.equals("上海臻相") || department.equals("贵州中科") || department.equals("南京总部")) {

        } else {
            return null;
        }
        List<ProjectInfor> projectInfors;
        if(department.equals("全部")) {
            projectInfors = projectBoardDao.queryProjectInfor(); //查询所有项目详情信息
        } else {
            List<Dept> depts = iDeptService.queryAllCompany1and2();
            String company = "";
            for(Dept dept:depts) {
                if(dept.getDepname().contains(department)) {
                    company = dept.getDepname();
                }
            }
            List<Dept> depts1 = iDeptService.queryByCompanyname(company);
            List<String> deptList = new ArrayList<>();
            deptList.add(company);
            for(Dept dept1: depts1) {
                deptList.add(dept1.getDepname());
            }
            projectInfors = projectBoardDao.queryProjectInforByDepartment(deptList); //根据部门查询信息
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
        if(department.equals("全部") || department.equals("南京总部") || department.equals("上海臻相") || department.equals("贵州中科")) {

        } else {
            return null;
        }
        List<Map<String,String>> list = new ArrayList<>();
        List<ProjectInfor> projectInfors;
        if(department.equals("全部")) {
            projectInfors = projectBoardDao.queryProjectInfor(); //查询所有项目详情信息
        } else {
            List<Dept> depts = iDeptService.queryAllCompany1and2();
            String company = "";
            for(Dept dept:depts) {
                if(dept.getDepname().contains(department)) {
                    company = dept.getDepname();
                }
            }
            List<Dept> depts1 = iDeptService.queryByCompanyname(company);
            List<String> deptList = new ArrayList<>();
            deptList.add(company);
            for(Dept dept1: depts1) {
                deptList.add(dept1.getDepname());
            }
            projectInfors = projectBoardDao.queryProjectInforByDepartment(deptList); //根据部门查询信息
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
            map.put("projectCode",projectInfor.getProjectCode()); //项目编号
            map.put("projectName",projectInfor.getProjectName()); //项目名称
            map.put("inDepartment",projectInfor.getInDepartment()); //立项部门
            map.put("projectManager",projectInfor.getProjectManager()); //项目经理
            for(ProjectVarious st: statusList) {
                if(projectInfor.getProjectStatus().equals(st.getCode()+"")) {
                    map.put("projectStatus",st.getName()); //项目状态
                }
            }
            List<MileStonePlan> mileStonePlans = projectBoardDao.queryMileStonePlans(projectInfor.getProjectCode()); //查询该项目的所有里程碑计划,时间进度
            if(mileStonePlans.size() == 0 || mileStonePlans == null) {
                map.put("mileSchedule","");//时间进度
            } else {
                try {
                    long date1 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlans.get(0).getStartDate()).getTime();
                    long date2 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlans.get(mileStonePlans.size() - 1).getEndDate()).getTime();
                    long date3 = new Date().getTime();
                    double molecular = (date3 - date1) / (1000 * 24 * 3600) + 1;
                    double denominator = (date2 - date1) / (1000 * 24 * 3600) + 1;
                    Double schedule = new BigDecimal(molecular / denominator * 100).setScale(2,RoundingMode.UP).doubleValue();
                    if(schedule > 100) {
                        map.put("mileSchedule","100.00%");
                    } else {
                        map.put("mileSchedule",schedule+"%");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            ProjectIncome projectIncome = projectBoardDao.queryProjectIncome(projectInfor.getProjectCode()); //查询项目金额以及回款率
            if(projectIncome == null) {
                map.put("amount",""); //项目金额
                map.put("receivable",""); //回款率
            } else {
                map.put("amount",projectIncome.getAmount());
                map.put("receivable",projectIncome.getReceivableProportion());
            }
            SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
            String start = simpleDateFormat.format(calendar.getTime());
            Calendar calendar1 = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
            String end = simpleDateFormat.format(calendar.getTime());

            Map<String,String> content = new HashMap<>();
            content.put("code",projectInfor.getProjectCode());
            content.put("start",start);
            content.put("end",end);
            WeeklyPlan weeklyPlan = projectBoardDao.queryWeeklyPlan(content);
            if(weeklyPlan == null) {
                map.put("phase",""); //项目阶段
                map.put("fulfillment","");//实施进度
            } else {
                List<ProjectVarious> phaseList = projectSetDao.queryPhase();
                for(ProjectVarious phase: phaseList) {
                    if(weeklyPlan.getProjectPhase().equals(phase.getCode()+"")) {
                        map.put("phase",phase.getName());
                    }
                }
                map.put("fulfillment",weeklyPlan.getFulfillment());
            }
            list.add(map);
        }
        return list;
    }

    //查看进行中项目的类型及数量
    @Override
    public List<Object> queryProceed(String department) {
        List<ProjectVarious> typeList = projectSetDao.queryType();
        List<ProjectVarious> statusList = projectSetDao.queryStatus();
        List<ProjectVarious> phaseList = projectSetDao.queryPhase();
        int[][] number = new int[typeList.size() + 1][7];

        String proceed = "";
        for(ProjectVarious status:statusList) {
            if(status.getName().equals("进行")) {
                proceed = status.getCode()+"";
            }
        }

        List<ProjectInfor> projectInfors;
        if(department.equals("全部")) {
            projectInfors = projectBoardDao.queryInforProceed(proceed); //查询所有进行中的项目详情信息
        } else {
            List<Dept> depts = iDeptService.queryAllCompany1and2();
            String company = "";
            for(Dept dept:depts) {
                if(dept.getDepname().contains(department)) {
                    company = dept.getDepname();
                }
            }
            List<Dept> depts1 = iDeptService.queryByCompanyname(company);
            List<String> deptList = new ArrayList<>();
            deptList.add(company);
            for(Dept dept1: depts1) {
                deptList.add(dept1.getDepname());
            }
            Map<String,Object> content = new HashMap<>();
            content.put("proceed",proceed);
            content.put("list",deptList);
            projectInfors = projectBoardDao.queryProjectInforByDepartment1(content); //根据部门查询正在进行中的项目信息
        }

        String effect = "";
        for(ProjectVarious phase:phaseList) {
            if(phase.getName().equals("实施")) {
                effect = phase.getCode()+"";
                break;
            }
        }

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

        for(ProjectInfor projectInfor:projectInfors) {
           for(int i = 0; i < typeList.size(); i++) {
               if(projectInfor.getProjectType().equals(typeList.get(i).getCode()+"")) {
                   number[i][6] ++;
                   content.put("code",projectInfor.getProjectCode());
                   WeeklyPlan weeklyPlan = weeklyPlanDao.queryPlanContent(content); //条件查询当前周报信息
                   if(weeklyPlan == null) {

                   } else {
                       if(weeklyPlan.getProjectPhase().equals(effect)) {
                           number[i][4] ++;
                           number[typeList.size()][4] ++;
                           if(weeklyPlan.getPunctuality().equals("t")) {
                               number[i][0]++;
                               number[typeList.size()][0] ++;
                           } else if(weeklyPlan.getPunctuality().equals("a")) {
                               number[i][1] ++;
                               number[typeList.size()][1] ++;
                           } else if(weeklyPlan.getPunctuality().equals("y")) {
                               number[i][2] ++;
                               number[typeList.size()][2] ++;
                           } else {
                               number[i][3] ++;
                               number[typeList.size()][3] ++;
                           }
                       } else {
                           number[i][5] ++;
                           number[typeList.size()][5] ++;
                       }
                   }
               }
           }
        }

        List<Object> result = new ArrayList<>();
        for(int k = 0; k < typeList.size(); k ++) {

            Map<String,String> map = new HashMap<>();
            map.put("type",typeList.get(k).getName());
            map.put("advance",number[k][0]+"");
            map.put("normal",number[k][1]+"");
            map.put("delay",number[k][2]+"");
            map.put("noMilestone",number[k][3]+"");
            map.put("subtotal",number[k][4]+"");
            map.put("other",number[k][5]+"");
            map.put("combined",number[k][6]+"");
            result.add(map);
        }
        Map<String,String> tail = new HashMap<>();
        tail.put("type","合计");
        tail.put("advance",number[typeList.size()][0]+"");
        tail.put("normal",number[typeList.size()][1]+"");
        tail.put("delay",number[typeList.size()][2]+"");
        tail.put("noMilestone",number[typeList.size()][3]+"");
        tail.put("subtotal",number[typeList.size()][4]+"");
        tail.put("other",number[typeList.size()][5]+"");
        tail.put("combined",projectInfors.size()+"");
        result.add(tail);
        return result;
    }

    //点击进行中项目的数量查看相关详情（手机端）
    @Override
    public List<Map<String, String>> queryWeek(String department, String type, String punctuality) {
        List<ProjectVarious> typeList = projectSetDao.queryType();
        List<ProjectVarious> phaseList = projectSetDao.queryPhase();
        for(ProjectVarious ty:typeList) {
            if(ty.getName().equals(type)) {
                type = String.valueOf(ty.getCode());
            }
        }

        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar =  Calendar.getInstance(), calendar1 = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        calendar1.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
        String start = simpleDateFormat.format(calendar.getTime());
        String end = simpleDateFormat.format(calendar1.getTime());
        List<String> codes;
        if(punctuality.equals("提前") || punctuality.equals("按期") || punctuality.equals("延期") || punctuality.equals("无里程碑")) {
            Map<String,String> content = new HashMap<>();
            content.put("start",start);
            content.put("end",end);
            if(punctuality.equals("提前")) {
                content.put("punctuality","ti");
            } else if(punctuality.equals("按期")) {
                content.put("punctuality","an");
            } else if(punctuality.equals("延期")) {
                content.put("punctuality","yan");
            } else if(punctuality.equals("无里程碑")) {
                content.put("punctuality","wu");
            }
            codes = projectBoardDao.queryWeekByContent(content); //查询本周周报相关的项目编号
        } else if(punctuality.equals("小计") || punctuality.equals("其他")) {

            List<String> phases = new ArrayList<>();
            for(ProjectVarious phase: phaseList) {
                if(punctuality.equals("小计")) {
                    if(phase.getName().equals("实施")) {
                        phases.add(phase.getCode()+"");
                    }
                } else {
                    if(phase.getName().equals("实施")) {

                    } else {
                        phases.add(phase.getCode() + "");
                    }
                }
            }
            Map<String,Object> content1 = new HashMap<>();
            content1.put("start",start);
            content1.put("end",end);
            content1.put("list",phases);
            codes = projectBoardDao.queryPhaseByContent(content1); //根据本周周报阶段查询相关的项目编号
        } else {
            List<ProjectVarious> statusList = projectSetDao.queryStatus();
            String proceed = "";
            for(ProjectVarious status: statusList) {
                if(status.getName().equals("进行")) {
                    proceed = String.valueOf(status.getCode());
                }
            }
            codes = projectBoardDao.queryProceed(proceed); //查询进行中的项目编号
        }

        List<Dept> depts = iDeptService.queryAllCompany1and2();
        Map<String,Object> content2 = new HashMap<>();

        content2.put("type",type);
        content2.put("codes",codes);
        if(department.equals("全部")) {
            content2.put("unit","all");
        } else {
            content2.put("unit","subsidiary");
            for(Dept dept: depts) {
                if(dept.getDepname().contains(department)) {
                    List<Dept> depts1 = iDeptService.queryByCompanyname(dept.getDepname());
                    List<String> companys = new ArrayList<>();
                    companys.add(dept.getDepname());
                    for(Dept dept1:depts1) {
                        companys.add(dept1.getDepname());
                    }
                    content2.put("list",companys);
                }
            }
        }
        List<ProjectInfor> projectInfors = projectBoardDao.queryInforInProceed(content2); //查询项目详情信息
        List<Map<String,String>> result = new ArrayList<>();
        for(ProjectInfor infor: projectInfors) {
            Map<String,String> map = new HashMap<>();
            map.put("projectCode",infor.getProjectCode()); //项目编号
            map.put("projectName",infor.getProjectName()); //项目名称
            map.put("inDepartment",infor.getInDepartment()); //立项部门
            map.put("projectManager",infor.getProjectManager()); //项目经理
            map.put("projectStatus","进行"); //项目状态

            List<MileStonePlan> mileStonePlans = projectBoardDao.queryMileStonePlans(infor.getProjectCode()); //查询该项目的所有里程碑计划,时间进度
            if(mileStonePlans.size() == 0 || mileStonePlans == null) {
                map.put("mileSchedule","");//时间进度
            } else {
                try {
                    long date1 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlans.get(0).getStartDate()).getTime();
                    long date2 = new SimpleDateFormat("yyyy-MM-dd").parse(mileStonePlans.get(mileStonePlans.size() - 1).getEndDate()).getTime();
                    long date3 = new Date().getTime();
                    double molecular = (date3 - date1) / (1000 * 24 * 3600) + 1;
                    double denominator = (date2 - date1) / (1000 * 24 * 3600) + 1;
                    Double schedule = new BigDecimal(molecular / denominator * 100).setScale(2,RoundingMode.UP).doubleValue();
                    if(schedule > 100) {
                        map.put("mileSchedule","100.00%");
                    } else {
                        map.put("mileSchedule",schedule+"%");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            ProjectIncome projectIncome = projectBoardDao.queryProjectIncome(infor.getProjectCode()); //查询项目金额以及回款率
            if(projectIncome == null) {
                map.put("amount",""); //项目金额
                map.put("receivable",""); //回款率
            } else {
                map.put("amount",projectIncome.getAmount());
                map.put("receivable",projectIncome.getReceivableProportion());
            }

            Map<String,String> content3 = new HashMap<>();
            content3.put("code",infor.getProjectCode());
            content3.put("start",start);
            content3.put("end",end);
            WeeklyPlan weeklyPlan = projectBoardDao.queryWeeklyPlan(content3);
            if(weeklyPlan == null) {
                map.put("phase",""); //项目阶段
                map.put("fulfillment","");//实施进度
            } else {
                for(ProjectVarious phase: phaseList) {
                    if(weeklyPlan.getProjectPhase().equals(phase.getCode()+"")) {
                        map.put("phase",phase.getName());
                    }
                }
                map.put("fulfillment",weeklyPlan.getFulfillment());
            }
            result.add(map);
        }
        return result;
    }
}
