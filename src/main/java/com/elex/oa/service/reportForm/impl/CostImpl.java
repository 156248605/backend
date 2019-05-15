package com.elex.oa.service.reportForm.impl;

import com.elex.oa.dao.reportForm.CostDao;
import com.elex.oa.entity.Page;
import com.elex.oa.service.reportForm.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Service
public class CostImpl implements CostService {

    @Resource
    private CostDao costDao;

    @Override
    public List serveForm(HttpServletRequest request) {
        List<HashMap<String, Object>> serveList = costDao.getServeByProject(request.getParameter("projectId"), request.getParameter("formName"));
        return serveList;
    }
}
