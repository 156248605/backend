package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.dao_shiyun.IDeptDao;
import com.elex.oa.dao.project.NewProjectDao;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.NProjectQuery;
import com.elex.oa.entity.project.NewProject;
import com.elex.oa.mongo.project.NewProjectMongo;
import com.elex.oa.service.project.NewProjcetService;
import com.elex.oa.util.util_per.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewProjectImpl implements NewProjcetService {
    @Resource
    private NewProjectMongo newProjectMongo;
    @Resource
    private IDeptDao iDeptDao;
    @Resource
    private NewProjectDao newProjectDao;



    @Override
    public Map<String, Object> newSubmit(NewProject newProject) {
        Map<String,Object> map = new HashMap<>();
        String projectCode = newProjectMongo.getProjectCode(); //项目编码
        if(projectCode.equals(newProject.getProjectCode())) {
            newProject.setWriteDate(TimeUtils.currentTime());
            int result = newProjectDao.insertNewProject(newProject); //保存新建的项目信息
            if(result == 1) {
                newProjectMongo.modifyProjectCode(projectCode);
            }
            NProjectQuery nProjectQuery = new NProjectQuery();
            nProjectQuery.setName(newProject.getProposer());
            PageHelper.startPage(1,10);
            List<NewProject> newProjectList = newProjectDao.listQuery(nProjectQuery);
            PageInfo<NewProject> newProjectPageInfo = new PageInfo<>(newProjectList);
            map.put("list",newProjectPageInfo.getList());
            map.put("total",newProjectPageInfo.getTotal());
            map.put("result","success");
            map.put("message","申请已提交，请耐心等待审批！");

        } else {
            map.put("result","failure");
            map.put("message","该编号已被占用，请换当前编号提交！");
            map.put("projectCode",projectCode);
        }
        return map;
    }

    //对某人已建项目进行列表查询
    @Override
    public PageInfo<NewProject> listQuery(Page page, NProjectQuery nProjectQuery) {
        List<String> dateList = JSONArray.parseArray(nProjectQuery.getDate3(),String.class);
        if(dateList.get(0).equals("")) {

        } else {
            nProjectQuery.setDate31(dateList.get(0));
            nProjectQuery.setDate32(dateList.get(1));
        }
        System.out.println(nProjectQuery.getSelect4());
        List<String> list4 = JSONArray.parseArray(nProjectQuery.getSelect4(),String.class);
        List<String> list5 = JSONArray.parseArray(nProjectQuery.getSelect5(),String.class);
        if(list4.size() > 0) {
            nProjectQuery.setList4(list4);
        }
        if(list5.size() > 0) {
            nProjectQuery.setList5(list5);
        }
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<NewProject> newProjectList = newProjectDao.listQuery(nProjectQuery);
        return new PageInfo<NewProject>(newProjectList);
    }

    //获取表头部分select中的内容
    @Override
    public List<NewProject> selectData(String proposer) {
        return newProjectDao.selectData(proposer);
    }
}
