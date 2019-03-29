package com.elex.oa.service.reportForm.impl;

import com.elex.oa.dao.reportForm.OvertimeDao;
import com.elex.oa.entity.Page;
import com.elex.oa.service.reportForm.OvertimeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OvertimeImpl implements OvertimeService {

    @Resource
    private OvertimeDao overtimeDao;

    @Override
    public PageInfo overtimeForm(HttpServletRequest request, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        String name = request.getParameter("name");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String projectId = request.getParameter("projectId");
        String category = request.getParameter("category");
        String department = request.getParameter("department");
        List overtimeList = overtimeDao.getOvertimeForm(name,startTime,endTime,projectId,category,department);
        return new PageInfo<>(overtimeList);
    }
}
