package com.elex.oa.service.reportForm.impl;

import com.elex.oa.dao.reportForm.OvertimeDao;
import com.elex.oa.entity.Page;
import com.elex.oa.service.reportForm.OvertimeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Service
public class OvertimeImpl implements OvertimeService {

    @Resource
    private OvertimeDao overtimeDao;

    @Override
    public PageInfo overtimeForm(HttpServletRequest request, Page page) throws ParseException {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        String name = request.getParameter("name");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String projectId = request.getParameter("projectId");
        String category = request.getParameter("category");
        String department = request.getParameter("department");
        List<HashMap<String,Object>> overtimeList = overtimeDao.getOvertimeForm(name,startTime,endTime,projectId,category,department);
        for (int i = 0;i < overtimeList.size();i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            overtimeList.get(i).put("F_SJHJ",Math.abs(sdf.parse(overtimeList.get(i).get("F_JBJSSJ").toString()).getTime() - sdf.parse(overtimeList.get(i).get("F_JBKSSJ").toString()).getTime())/3600000);
        }
        return new PageInfo<>(overtimeList);
    }
}
