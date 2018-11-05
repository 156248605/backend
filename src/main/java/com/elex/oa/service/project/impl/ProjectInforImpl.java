package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.hr_shiyun.IDeptDao;
import com.elex.oa.dao.hr_shiyun.IPersonalInformationDao;
import com.elex.oa.dao.hr_shiyun.IUserDao;
import com.elex.oa.dao.project.ProjectInforDao;
import com.elex.oa.dao.project.ProjectSetDao;
import com.elex.oa.entity.hr_entity.User;
import com.elex.oa.entity.project.*;
import com.elex.oa.service.project.ProjectInforService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectInforImpl implements ProjectInforService {

    @Resource
    private ProjectInforDao projectInforDao;

    @Resource
    private ProjectSetDao projectSetDao;

    @Resource
    private IUserDao iUserDao;
    @Resource
    private IPersonalInformationDao iPersonalInformationDao;
    @Resource
    private IDeptDao iDeptDao;


    //查询已立项成功的项目，添加到项目信息中
    @Override
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
        List<ProjectVarious> statusList = projectSetDao.queryStatus();
        String code = "";
        for(ProjectVarious status:statusList) {
            if(status.getName().equals("进行")) {
                code = status.getCode()+"";
                break;
            }
        }
        for(ApprovalList approvalList: approvalLists) {
            approvalList.setWriteDate(approvalList.getWriteDate().substring(0,10));
            approvalList.setProjectStatus(code);
            User user = iUserDao.selectByTruename(approvalList.getProjectManager());
            Integer depid = iPersonalInformationDao.selectByUserid(user.getId()).getDepid();
            Integer principaluserid = iDeptDao.selectDeptByDepid(depid).getPrincipaluserid();
            User user1 = iUserDao.selectById(principaluserid);
            String truename = user1.getTruename();
            approvalList.setDepartmentManager(truename);
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
            if(projectInfor.getBusinessManager().equals(osUser.getFullName())) {
                projectInfor.setBusinessManagerCode(osUser.getUserId());
            }
            if(projectInfor.getProjectManager().equals(osUser.getFullName())) {
                projectInfor.setProjectManagerCode(osUser.getUserId());
            }
            if(projectInfor.getProjectMembers().contains(osUser.getFullName())) {
                stringBuilder1.append(osUser.getUserId());
                stringBuilder1.append(";");
            }
            if(projectInfor.getRelatedMembers().contains(osUser.getFullName())) {
                stringBuilder2.append(osUser.getUserId());
                stringBuilder2.append(";");
            }
        }
        projectInfor.setProjectMemberCode(stringBuilder1.toString());
        projectInfor.setRelatedMemberCode(stringBuilder2.toString());

        User user = iUserDao.selectByTruename(projectInfor.getProjectManager());
        Integer depid = iPersonalInformationDao.selectByUserid(user.getId()).getDepid();
        Integer principaluserid = iDeptDao.selectDeptByDepid(depid).getPrincipaluserid();
        User user1 = iUserDao.selectById(principaluserid);
        String truename = user1.getTruename();
        projectInfor.setDepartmentManager(truename);

        projectInforDao.amendInfor(projectInfor);
        return "1";
    }
}
