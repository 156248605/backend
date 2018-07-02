package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.ListingDao;
import com.elex.oa.dao.project.ProjectInforDao;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.OperationQuery;
import com.elex.oa.entity.project.ProjectInfor;
import com.elex.oa.entity.project.ProjectInforQuery;
import com.elex.oa.service.project.ProjectInforService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectInforImpl implements ProjectInforService {

    @Resource
    private ProjectInforDao projectInforDao;
    @Resource
    private ListingDao listingDao;

    //数据列表查询
    @Override
    public PageInfo<ProjectInfor> listQuery(Page page, ProjectInforQuery projectInforQuery) {
        List<String> date3 = JSONArray.parseArray(projectInforQuery.getDate3(),String.class);
        if(date3.get(0).equals("")) {

        } else {
            projectInforQuery.setDate3a(date3.get(0));
            projectInforQuery.setDate3b(date3.get(1));
        }
        List<String> date10 = JSONArray.parseArray(projectInforQuery.getDate10(),String.class);
        if(date10.get(0).equals("")) {

        } else {
            projectInforQuery.setDate10a(date10.get(0));
            projectInforQuery.setDate10b(date10.get(1));
        }
        List<String> date11 = JSONArray.parseArray(projectInforQuery.getDate11(),String.class);
        if(date11.get(0).equals("")) {

        } else {
            projectInforQuery.setDate11a(date11.get(0));
            projectInforQuery.setDate11b(date11.get(1));
        }
        List<String> list4 = JSONArray.parseArray(projectInforQuery.getSelect4(),String.class);
        List<String> list5 = JSONArray.parseArray(projectInforQuery.getSelect5(),String.class);
        List<String> list9 = JSONArray.parseArray(projectInforQuery.getSelect9(),String.class);
        if(list4.size() > 0) {
            projectInforQuery.setList4(list4);
        }
        if(list5.size() > 0) {
            projectInforQuery.setList5(list5);
        }
        if(list9.size() > 0) {
            projectInforQuery.setList9(list9);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ProjectInfor> list = projectInforDao.listQuery(projectInforQuery);
        return new PageInfo<>(list);
    }

    //根据姓名查询其新建时可用的项目编号
    @Override
    public List<String> queryCodeByName(String name) {
        List<String> list = new ArrayList<>();
        List<String> list1 = listingDao.queryCodeByName(name); //在已通过审批的项目中查询某人主管的项目编号
        List<String> list2 = projectInforDao.queryCodeByName(name); //在已有的项目详情中查询某人主管的项目编号
        for(String str: list1) {
            if(list2.contains(str)){

            } else {
                list.add(str);
            }
        }
        return list;
    }

    //根据编号查询某已通过审批的项目基本信息
    @Override
    public ApprovalList queryContentByCode(String projectCode) {
        return listingDao.queryContentByCode(projectCode);
    }

    //新建项目详情
    @Override
    @Transactional
    public Map<String, String> constructionDetails(ProjectInfor projectInfor) {
        Map<String,String> map = new HashMap<>();
        int result1 = projectInforDao.constructionDetails(projectInfor); //添加新的项目详情
        int result2 = listingDao.modifyAppListContent(projectInfor); //修改已审批清单中的信息
        if(result1 == 1 && result2 == 1) {
            map.put("result","success");
            map.put("message","新建成功！");
        } else{
            map.put("result","failure");
            map.put("message","新建失败！");
        }
        return map;
    }

    //修改项目详情
    @Override
    @Transactional
    public Map<String, String> modifyDetails(ProjectInfor projectInfor) {
        Map<String,String> map = new HashMap<>();
        int result1 = projectInforDao.modifyDetails(projectInfor); //修改项目详情
        int result2 = listingDao.modifyAppListContent(projectInfor); //修改已审批清单中的信息
        if(result1 == 1 && result2 == 1) {
            map.put("result","success");
            map.put("message","修改成功！");
        } else{
            map.put("result","failure");
            map.put("message","修改失败！");
        }
        return map;
    }

    //删除详情
    @Override
    public Map<String, String> deleteDetails(Integer id) {
        Map<String,String> map = new HashMap<>();
        int result1 = projectInforDao.deleteDetails(id);
        if(result1 == 1) {
            map.put("result","success");
            map.put("message","修改成功！");
        } else{
            map.put("result","failure");
            map.put("message","修改失败！");
        }
        return map;
    }

    //查询可新建详情的项目信息
    @Override
    public PageInfo<ApprovalList> queryProjectList(OperationQuery operationQuery, Page page) {
        List<String> list2 = projectInforDao.queryCodeByName(operationQuery.getName()); //在已有的项目详情中查询某人主管的项目编号
        operationQuery.setList4(list2);
        List<String> list6 = JSONArray.parseArray(operationQuery.getSelect6(),String.class);
        List<String> list8 = JSONArray.parseArray(operationQuery.getSelect8(),String.class);
        if(list6.size() > 0) {
            operationQuery.setList6(list6);
        }
        if(list8.size() > 0) {
            operationQuery.setList8(list8);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<ApprovalList> list = projectInforDao.queryProjectList(operationQuery);
        return new PageInfo<>(list);
    }
}
