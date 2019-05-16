package com.elex.oa.service.reportForm.impl;

import com.elex.oa.dao.reportForm.MoneyDao;
import com.elex.oa.service.reportForm.MoneyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Service
public class MoneyImpl implements MoneyService {

    @Resource
    private MoneyDao costDao;

    @Override
    public List moneyForm(HttpServletRequest request) {
        List<HashMap<String, Object>> moneyList = costDao.getFormByProject(request.getParameter("projectId"), request.getParameter("formName"));
        return moneyList;
    }
}
