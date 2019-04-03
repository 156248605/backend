package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.dao.project.MileStoneDao;
import com.elex.oa.dao.project.ProjectInforDao;
import com.elex.oa.dao.project.ProjectSetDao;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.entity.project.*;
import com.elex.oa.mongo.project.ProjectRecordMongo;
import com.elex.oa.service.project.ProjectInforService;
import com.elex.oa.util.project.InforUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectInforImpl implements ProjectInforService {

    @Resource
    private ProjectInforDao projectInforDao;

    @Resource
    private ProjectSetDao projectSetDao;

    @Resource
    private IUserDao iUserDao;

    @Resource
    private ProjectRecordMongo projectRecordMongo;

    @Resource
    private MileStoneDao mileStoneDao;

    @Resource
    private ProjectAmendImpl projectAmendService;

    //查询已立项成功的项目，添加到项目信息中
    @Override
    @Transactional
    public void addInfor() {
        List<String> codes = projectInforDao.queryCodes(); //查询已建立项目详情信息的编号

        Map<String,Object> content = new HashMap<>();
        if(codes.size() > 0) {
            content.put("marker","1");
            content.put("codes",codes);
        } else {
            content.put("marker","0");
        }
        List<ApprovalList> approvalLists = projectInforDao.queryApproval(content); //查询已成功立项但没有建立项目详情的项目信息
        if(approvalLists.size() == 0) {
            return;
        }
        for(ApprovalList approvalList: approvalLists) {
            approvalList.setWriteDate(approvalList.getWriteDate().substring(0,10));
            if(StringUtils.isNotBlank(approvalList.getStartTime())) {
                approvalList.setStartTime(approvalList.getStartTime().substring(0, 10));
            }
            if(StringUtils.isNotBlank(approvalList.getEndTime())) {
                approvalList.setEndTime(approvalList.getEndTime().substring(0, 10));
            }
            List<MileStonePlan> mileStones = mileStoneDao.queryContentByRel(approvalList.getRelCode()); //查询关联里程碑计划
            if(mileStones == null || mileStones.size() == 0) {

            } else {
                for(MileStonePlan mileStonePlan: mileStones) {
                    mileStonePlan.setProjectCode(approvalList.getProjectCode());
                    mileStonePlan.setStartDate(approvalList.getStartTime().substring(0, 10));
                    mileStonePlan.setEndDate(approvalList.getEndTime().substring(0, 10));
                }
                mileStoneDao.addMileStonePlans(mileStones);
            }
        }
        projectInforDao.addInfor(approvalLists); //添加项目详情信息
    }

    //列表查询项目详情信息
    @Override
    public PageInfo queryList(OperationQuery operationQuery, Integer pageNum) {
        List<String> list4 = JSONArray.parseArray(operationQuery.getSelect4(),String.class);
        if(list4.size() > 0) {
            operationQuery.setList4(list4);
        }
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        PageHelper.startPage(pageNum,10);
        List<ProjectInfor> list = projectInforDao.queryList(operationQuery);
        return new PageInfo(list);
    }

    //修改项目信息
    @Override
    public String amendInfor(ProjectInfor projectInfor) {
        List<OsUser> osUsers = projectInforDao.queryOsUser(); //查询os_user表所有用户信息
        StringBuilder stringBuilder1 = new StringBuilder(), stringBuilder2 = new StringBuilder();
        for(OsUser osUser:osUsers) {
            if(StringUtils.isNotBlank(projectInfor.getBusinessManager())) {
                if(projectInfor.getBusinessManager().equals(osUser.getFullName())) {
                    projectInfor.setBusinessManagerCode(osUser.getUserId());
                }
            }
            if(StringUtils.isNotBlank(projectInfor.getProjectManager())) {
                if(projectInfor.getProjectManager().equals(osUser.getFullName())) {
                    projectInfor.setProjectManagerCode(osUser.getUserId());
                }
            }
            if(StringUtils.isNotBlank(projectInfor.getProjectMembers())) {
                if(projectInfor.getProjectMembers().contains(osUser.getFullName())) {
                    stringBuilder1.append(osUser.getUserId());
                    stringBuilder1.append(";");
                }
            }
            if(StringUtils.isNotBlank(projectInfor.getRelatedMembers())) {
                if(projectInfor.getRelatedMembers().contains(osUser.getFullName())) {
                    stringBuilder2.append(osUser.getUserId());
                    stringBuilder2.append(";");
                }
            }
        }
        projectInfor.setProjectMemberCode(stringBuilder1.toString());
        projectInfor.setRelatedMemberCode(stringBuilder2.toString());
        projectInforDao.amendInfor(projectInfor);
        return "1";
    }

    @Override
    public String businessManager() {
        List<OsUser> osUsers = projectInforDao.queryOsUser();
        List<ProjectInfor> infors = projectInforDao.queryBusiness();
        List<User> staffList = iUserDao.selectAllEmployeeON();
        for(ProjectInfor infor:infors){
            for(User staff:staffList){
                if(staff.getTruename().equals(infor.getBusinessManager())){
                    infor.setBusinessManagerId(staff.getEmployeenumber()+"");
                }
            }
            for(OsUser osUser:osUsers){
                if(osUser.getFullName().equals(infor.getBusinessManager())){
                    infor.setBusinessManagerCode(osUser.getUserId());
                }
            }
        }
        projectInforDao.updateBusinessId(infors);
        return "business";
    }

    @Override
    public String projectMembers() {
        List<ProjectInfor> infors = projectInforDao.queryProjectMembers();
        if(infors == null) {
            return "projectMembers failure";
        }
        if(infors.size() == 0) {
            return "projectMembers failure";
        }
        List<OsUser> osUsers = projectInforDao.queryOsUser();
        List<User> userList = iUserDao.selectAllEmployeeON();
        StringBuilder memberId, memberCode;
        for(ProjectInfor infor:infors){
            memberId = new StringBuilder();
            for(User staff:userList){
                if(infor.getProjectMembers().contains(staff.getTruename())){
                    memberId.append(staff.getEmployeenumber());
                    memberId.append(";");
                }
            }
            infor.setProjectMemberId(memberId.toString());
            memberCode = new StringBuilder();
            for(OsUser osUser:osUsers){
                if(infor.getProjectMembers().contains(osUser.getFullName())){
                    memberCode.append(osUser.getUserId());
                    memberCode.append(";");
                }
            }
            infor.setProjectMemberCode(memberCode.toString());
        }
        projectInforDao.updateProjectMembers(infors);
        return "projectMemberCode";
    }

    @Override
    public String relatedMembers() {
        List<ProjectInfor> infors = projectInforDao.queryRelatedMembers();
        if(infors == null) {
            return "relatedMembers failure";
        }
        if(infors.size() == 0) {
            return "relatedMembers failure";
        }
        List<OsUser> osUsers = projectInforDao.queryOsUser();
        List<User> staffList = iUserDao.selectAllEmployeeON();
        StringBuilder  memberId, memberCode;
        for(ProjectInfor infor:infors){
            memberId = new StringBuilder();
            for(User staff:staffList){
                if(infor.getRelatedMembers().contains(staff.getTruename())){
                    memberId.append(staff.getEmployeenumber());
                    memberId.append(";");
                }
            }
            infor.setRelatedMemberId(memberId.toString());
            memberCode = new StringBuilder();
            for(OsUser osUser:osUsers){
                if(infor.getRelatedMembers().contains(osUser.getFullName())){
                    memberCode.append(osUser.getUserId());
                    memberCode.append(";");
                }
            }
            infor.setRelatedMemberCode(memberCode.toString());
        }
        projectInforDao.updateRelatedMembers(infors);
        return "relatedMemberCode";
    }

    @Override
    public String projectManager() {
        List<OsUser> osUsers = projectInforDao.queryOsUser();
        List<ProjectInfor> infors = projectInforDao.queryProjects();
        List<User> staffList = iUserDao.selectAllEmployeeON();
        for(ProjectInfor projectInfor:infors){
            for(User staff:staffList){
                if(projectInfor.getProjectManager().equals(staff.getTruename())){
                    projectInfor.setProjectManagerId(staff.getEmployeenumber()+"");
                    for(OsUser osUser:osUsers){
                        if(osUser.getFullName().equals(projectInfor.getProjectManager())){
                            projectInfor.setProjectManagerCode(osUser.getUserId());
                        }
                    }
                    continue;
                }
            }
        }
        projectInforDao.updateProjects(infors);
        return "projectManagerCode";
    }

    @Override
    public String projectStatus() {
        List<ProjectVarious> statusList = projectSetDao.queryStatus();
        for(ProjectVarious various:statusList){
            projectInforDao.projectStatus(various);
        }
        return "projectStatus";
    }

    @Override
    public String projectSource() {
        List<ProjectVarious> sourceList = projectSetDao.querySource();
        for(ProjectVarious various:sourceList){
            projectInforDao.projectSource(various);
        }
        return "projectSource";
    }

    @Override
    public String projectType() {
        List<ProjectVarious> typeList = projectSetDao.queryType();
        for(ProjectVarious various:typeList){
            projectInforDao.projectType(various);
        }
        return "projectType";
    }

    //项目信息导入
    @Override
    public Map<String, String> importData(MultipartFile file) {
        Map<String,String> map = new HashMap<>();
        String fileName = file.getOriginalFilename();
        boolean marker = fileName.contains(".xls");
        boolean amend = false;
        if(marker) {
            try {
                List<ProjectInfor> list = InforUtils.obtainList(file);
                String lastId = projectInforDao.queryLastId(); //查询导入前最后一条的id
                int number = projectInforDao.importData(list);
                if(number == 0) {
                    map.put("result","undone");
                    map.put("message","因项目编号重复，无法导入！");
                } else if(number < list.size()) {
                    List<String> codeList = projectInforDao.queryCodeList(Integer.parseInt(lastId)); //查询导入的项目编号
                    List<String> unList = new ArrayList<>(); //未导入的项目编号信息
                    for(ProjectInfor infor: list) {
                        if(codeList.contains(infor.getProjectCode())) {

                        } else {
                            unList.add(infor.getProjectCode());
                        }
                    }
                    map.put("result", "unfinished");
                    map.put("message","总数："+list.size()+"，导入："+number+",原因：项目编号重复!");
                    /*map.put("details",unList.toString());*/
                    File filePath = new File("/usr/local/static/infor/");
                    if(filePath.exists()) {

                    } else {
                        filePath.mkdirs();
                    }
                    File unfile = new File("/usr/local/static/infor/", "unfinished.txt");
                    if(unfile.exists()) {

                    } else {
                        unfile.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(unfile);
                    fileWriter.flush();
                    fileWriter.close();
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(unfile));
                    for(String str : unList) {
                        bufferedWriter.write(str);
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    amend = true;
                } else {
                    map.put("result","success");
                    map.put("message","已全部导入！");
                    amend = true;
                }
                if(amend) {
                    this.businessManager();
                    this.projectMembers();
                    this.projectSource();
                    this.projectStatus();
                    this.projectType();
                    this.businessManager();
                    this.projectManager();
                    this.relatedMembers();
                }
                return map;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            map.put("result","failure");
            map.put("message","上传文件格式不对！");
            return map;
        }
        return null;
    }

    //信息导出时查询数据
    @Override
    public String queryExport(InforQuery inforQuery, HttpServletResponse response) {
        List<String> source = JSONArray.parseArray(inforQuery.getSourceSelect(), String.class);
        if(source.size() > 0) {
            inforQuery.setSourceList(source);
        }
        List<String> type = JSONArray.parseArray(inforQuery.getTypeSelect(), String.class);
        if(type.size() > 0) {
            inforQuery.setTypeList(type);
        }
        List<String> status = JSONArray.parseArray(inforQuery.getStatusSelect(), String.class);
        if(status.size() > 0) {
            inforQuery.setStatusList(status);
        }
        List<ProjectInfor> list = projectInforDao.queryExport(inforQuery);//查询导出的数据
        List<ProjectVarious> statusList = projectSetDao.queryStatus(); //项目状态
        List<ProjectVarious> sourceList = projectSetDao.querySource(); //项目来源
        List<ProjectVarious> typeList = projectSetDao.queryType(); //项目类型
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        String fileName = "项目信息" + date + ".xlsx";
        try {
            InforUtils.exportExcel(response, fileName, list, statusList, sourceList, typeList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "export";
    }


    //项目导入未导入的信息下载
    @Override
    public String importUnfinished(HttpServletResponse response) {
        String path = "/usr/local/static/infor/unfinished.txt";

        File file = new File(path);
        if(file.exists()) {
            try {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String val = "";

                String fileName = new String("未导入项目编号".getBytes(), "iso-8859-1");
                response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".txt");

                String enter = "\r\n";
                ServletOutputStream outSs = response.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(outSs);

                while ((val = br.readLine()) != null) {
                    bos.write(val.getBytes("UTF-8"));
                    bos.write(enter.getBytes());
                }

                bos.flush();
                bos.close();
                outSs.close();
                br.close();
                isr.close();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "start txt";
    }

    //获取项目信息列表
    @Override
    public PageInfo obtainList(InforQuery inforQuery, Integer pageNum) {
        List<String> sourceList = JSONArray.parseArray(inforQuery.getSourceSelect(), String.class);
        if(sourceList.size() > 0) {
            inforQuery.setSourceList(sourceList);
        }
        List<String> typeList = JSONArray.parseArray(inforQuery.getTypeSelect(), String.class);
        if(typeList.size() > 0) {
            inforQuery.setTypeList(typeList);
        }
        List<String> statusList = JSONArray.parseArray(inforQuery.getStatusSelect(), String.class);
        if(statusList.size() > 0) {
            inforQuery.setStatusList(statusList);
        }
        List<String> phaseList = JSONArray.parseArray(inforQuery.getPhaseSelect(), String.class);
        if(phaseList.size() > 0) {
            inforQuery.setPhaseList(phaseList);
        }
        PageHelper.startPage(pageNum, 10);
        List<ProjectInfor> list = projectInforDao.obtainList(inforQuery); //获取项目信息列表
        return new PageInfo(list);
    }

    //修改项目信息
    @Override
    public String amendPro(ProjectInfor projectInfor, String updateBy) {
        ProjectInfor infor = projectInforDao.queryInforByCode(projectInfor.getProjectCode()); //根据项目编号获取项目信息
        List<OsUser> osUsers = projectInforDao.queryOsUser(); //查询os_user表所有用户信息
        StringBuilder stringBuilder1 = new StringBuilder(), stringBuilder2 = new StringBuilder();
        for(OsUser osUser:osUsers) {
            if(StringUtils.isNotBlank(projectInfor.getProjectMembers())) {
                if(projectInfor.getProjectMembers().contains(osUser.getFullName())) {
                    stringBuilder1.append(osUser.getUserId());
                    stringBuilder1.append(";");
                }
            }
            if(StringUtils.isNotBlank(projectInfor.getRelatedMembers())) {
                if(projectInfor.getRelatedMembers().contains(osUser.getFullName())) {
                    stringBuilder2.append(osUser.getUserId());
                    stringBuilder2.append(";");
                }
            }
        }
        projectInfor.setProjectMemberCode(stringBuilder1.toString());
        projectInfor.setRelatedMemberCode(stringBuilder2.toString());
        projectInforDao.amendPro(projectInfor); //修改项目信息

        List<Map<String, String>> record = generateRecord(projectInfor, infor);
        if(record.size() > 0) {
            ProjectRecord projectRecord = new ProjectRecord();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = simpleDateFormat.format(new Date());
            projectRecord.setUpdateTime(date);
            projectRecord.setUpdateBy(updateBy);
            projectRecord.setProjectCode(projectInfor.getProjectCode());
            projectRecord.setRecord(record);
            projectRecordMongo.addRecord(projectRecord); //添加记录
        }
        return "success";
    }

    public int proDiff(ProjectInfor projectInforNew, String updateBy) {
        int i=0;
        ProjectInfor projectInfor1 = projectInforDao.queryInforByCode(projectInforNew.getProjectCode()); //根据项目编号获取项目信息
        List<OsUser> users = projectInforDao.queryOsUser(); //查询os_user表所有用户信息
        StringBuilder stringBuilder1 = new StringBuilder(),
        stringBuilder2 = new StringBuilder();
        for(OsUser osUser:users) {
            if(StringUtils.isNotBlank(projectInforNew.getProjectMembers())) {
                if(projectInforNew.getProjectMembers().contains(osUser.getFullName())) {
                    stringBuilder1.append(osUser.getUserId());
                    stringBuilder1.append(";");
                }
            }
            if(StringUtils.isNotBlank(projectInforNew.getRelatedMembers())) {
                if(projectInforNew.getRelatedMembers().contains(osUser.getFullName())) {
                    stringBuilder2.append(osUser.getUserId());
                    stringBuilder2.append(";");
                }
            }
        }
        projectInforNew.setProjectMemberCode(stringBuilder1.toString());
        projectInforNew.setRelatedMemberCode(stringBuilder2.toString());
        List<Map<String, String>> record = generateRecord(projectInforNew, projectInfor1);
        if(record.size() > 0) {
            ProjectRecord projectRecord = new ProjectRecord();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = simpleDateFormat.format(new Date());
            projectRecord.setUpdateTime(date);
            projectRecord.setUpdateBy(updateBy);
            projectRecord.setProjectCode(projectInforNew.getProjectCode());
            projectRecord.setRecord(record);
            ProjectAmend  projectAmend = new ProjectAmend();
            //projectAmend.setProject_json(JSONObject.toJSONString(projectInforNew));
            projectAmend.setRecord_json(JSONObject.toJSONString(projectRecord));
            this.projectAmendService.save(projectAmend);
            if(projectAmend.getId()!=null&&projectAmend.getId()>0){
                     projectInforNew.setAmendId(projectAmend.getId());
                     projectAmend.setProject_json(JSONObject.toJSONString(projectInforNew));
                     this.projectAmendService.update(projectAmend);
                     i=projectAmend.getId();
                }
        }
           return i;
    }

    private List<Map<String,String>> generateRecord(ProjectInfor projectInfor, ProjectInfor infor) {
        boolean marker = false;
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map;
        marker = columnValidate(projectInfor.getGeneralSituation(), infor.getGeneralSituation());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "项目概况");
            map.put("prefix", infor.getGeneralSituation());
            map.put("suffix", projectInfor.getGeneralSituation());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getProjectMembers(), infor.getProjectMembers());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "项目成员");
            map.put("prefix", infor.getProjectMembers());
            map.put("suffix", projectInfor.getProjectMembers());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getRelatedMembers(), infor.getRelatedMembers());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "相关成员");
            map.put("prefix", infor.getRelatedMembers());
            map.put("suffix", projectInfor.getRelatedMembers());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getPartyName(), infor.getPartyName());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "甲方名称");
            map.put("prefix", infor.getPartyName());
            map.put("suffix", projectInfor.getPartyName());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getPartyAddress(), infor.getPartyAddress());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "甲方地址");
            map.put("prefix", infor.getPartyAddress());
            map.put("suffix", projectInfor.getPartyAddress());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getPartyPhone(), infor.getPartyPhone());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "甲方电话");
            map.put("prefix", infor.getPartyPhone());
            map.put("suffix", projectInfor.getPartyPhone());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getPartyFax(), infor.getPartyFax());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "甲方传真");
            map.put("prefix", infor.getPartyFax());
            map.put("suffix", projectInfor.getPartyFax());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getHeadName(), infor.getHeadName());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "负责人姓名");
            map.put("prefix", infor.getHeadName());
            map.put("suffix", projectInfor.getHeadName());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getHeadPosition(), infor.getHeadPosition());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "负责人职务");
            map.put("prefix", infor.getHeadPosition());
            map.put("suffix", projectInfor.getHeadPosition());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getHeadMobile(), infor.getHeadMobile());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "负责人手机");
            map.put("prefix", infor.getHeadMobile());
            map.put("suffix", projectInfor.getHeadMobile());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getHeadEmail(), infor.getHeadEmail());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "负责人邮件");
            map.put("prefix", infor.getHeadEmail());
            map.put("suffix", projectInfor.getHeadEmail());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getContactName(), infor.getContactName());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "联系人姓名");
            map.put("prefix", infor.getContactName());
            map.put("suffix", projectInfor.getContactName());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getContactPosition(), infor.getContactPosition());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "联系人职务");
            map.put("prefix", infor.getContactPosition());
            map.put("suffix", projectInfor.getContactPosition());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getContactMobile(), infor.getContactMobile());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "联系人手机");
            map.put("prefix", infor.getContactMobile());
            map.put("suffix", projectInfor.getContactMobile());
            list.add(map);
        }
        marker = columnValidate(projectInfor.getContactEmail(), infor.getContactEmail());
        if(marker) {
            map = new HashMap<>();
            map.put("column", "联系人邮件");
            map.put("prefix", infor.getContactEmail());
            map.put("suffix", projectInfor.getContactEmail());
            list.add(map);
        }
        return list;
    }

    private boolean columnValidate(String a, String b) {
        if(StringUtils.isBlank(a) && StringUtils.isBlank(b)) {
            return false;
        }
        if(a.equals(b)) {
            return false;
        }
        return true;
    }

    //项目信息添加
    @Override
    @Transactional
    public String addPro(String id) {
        String instStatus = projectInforDao.queryInstStatus(id);
        if(instStatus.equals("SUCCESS_END")) {
            ApprovalList approvalList = projectInforDao.queryInforById(id); //查询项目信息
            if(StringUtils.isNotBlank(approvalList.getWriteDate())) {
                approvalList.setWriteDate(approvalList.getWriteDate().substring(0,10));
            }
            if(StringUtils.isNotBlank(approvalList.getStartTime())) {
                approvalList.setStartTime(approvalList.getStartTime().substring(0, 10));
            }
            if(StringUtils.isNotBlank(approvalList.getEndTime())) {
                approvalList.setEndTime(approvalList.getEndTime().substring(0, 10));
            }
            List<MileStonePlan> mileStones = mileStoneDao.queryContentByRel(id); //查询关联里程碑计划
            if(mileStones == null || mileStones.size() == 0) {

            } else {
                for(MileStonePlan mileStonePlan: mileStones) {
                    mileStonePlan.setProjectCode(approvalList.getProjectCode());
                    mileStonePlan.setStartDate(approvalList.getStartTime().substring(0, 10));
                    mileStonePlan.setEndDate(approvalList.getEndTime().substring(0, 10));
                }
                mileStoneDao.addMileStonePlans(mileStones);
            }
            projectInforDao.addProjectInfor(approvalList); //添加项目详情信息
        }
        return "RUNNING";
    }
}
