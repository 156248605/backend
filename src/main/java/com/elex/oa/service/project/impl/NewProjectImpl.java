package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.NewProjectDao;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.project.NProjectQuery;
import com.elex.oa.entity.project.NewProject;
import com.elex.oa.entity.project.ProjectCode;
import com.elex.oa.mongo.project.NewProjectMongo;
import com.elex.oa.service.project.NewProjcetService;
import com.elex.oa.service.service_shiyun.IDeptService;
import com.elex.oa.util.project.ProjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewProjectImpl implements NewProjcetService {
    @Resource
    private NewProjectMongo newProjectMongo;

    @Resource
    private NewProjectDao newProjectDao;

    @Resource
    private IDeptService iDeptService;


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

    //根据人名获取项目编号
    @Override
    public String obtainCodeName(String name) {
        String departmentMark = iDeptService.queryByTruename(name);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("ELEX-PRJ-");
        stringBuilder.append(departmentMark);

        ProjectCode projectCode = newProjectMongo.queryProjectCode(stringBuilder.toString()); //查询某部门对应的项目编号
        String mark = ProjectUtils.codeValidate(projectCode.getCode()); //验证编码是否为当前月的
        if(mark.equals("1")) {
            newProjectMongo.updateProjectCode(projectCode); //更新项目编号
            stringBuilder.append("-");
            stringBuilder.append(projectCode.getCode());
        } else {
            newProjectMongo.modifyProjectCode(projectCode.getId(),mark); //修改编码
            stringBuilder.append("-");
            stringBuilder.append(mark);
        }
        return stringBuilder.toString();
    }
}
