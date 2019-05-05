package com.elex.oa.service.reportForm.impl;

import com.elex.oa.dao.reportForm.PersonDetailDao;
import com.elex.oa.service.reportForm.PersonDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class PersonDetailImpl implements PersonDetailService {

    @Resource
    private PersonDetailDao personDetailDao;

    @Override
    public List userForm(HttpServletRequest request) {
        List<HashMap<String, Object>> nameList = null;
        if ( request.getParameter("employeeNumber") != null && !request.getParameter("employeeNumber").equals("") ) {
            nameList = personDetailDao.getUserIdByName("", request.getParameter("employeeNumber"));
        }else {
            nameList = personDetailDao.getUserIdByName(request.getParameter("name"),"");
        }

        return nameList;
    }

    @Override
    public List menuAndRole(HttpServletRequest request) {
        List<HashMap<String, Object>> roleList = personDetailDao.getGroupRoleByUserId(request.getParameter("userId"));
        List<HashMap<String, Object>> positionList = personDetailDao.getGroupPositionByUserId(request.getParameter("userId"));
        List roleAndMenu = new ArrayList();
        List positionAndMenu = new ArrayList();
        List grantSyses;
        List grantMenus;
        for (int i = 0; i < roleList.size(); i++) {
            grantSyses = personDetailDao.getGrantSubsByGroupId(roleList.get(i).get("roleId").toString());
            grantMenus = personDetailDao.getGrantMenusByGroupId(roleList.get(i).get("roleId").toString());
            roleAndMenu.add(roleList.get(i).get("roleName").toString());
            roleAndMenu.add(grantSyses);
            roleAndMenu.add(grantMenus);
        }
        for (int j = 0; j < positionList.size(); j++) {
            grantSyses = personDetailDao.getGrantSubsByGroupId(positionList.get(j).get("positionId").toString());
            grantMenus = personDetailDao.getGrantMenusByGroupId(positionList.get(j).get("positionId").toString());
            positionAndMenu.add(positionList.get(j).get("positionName").toString());
            positionAndMenu.add(grantSyses);
            positionAndMenu.add(grantMenus);
        }
        List allRights = new ArrayList();
        allRights.add(roleAndMenu);
        allRights.add(positionAndMenu);
        return allRights;
    }

}
