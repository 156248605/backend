package com.elex.oa.org.service.impl;

import com.elex.oa.dao.IOsGroupDao;
import com.elex.oa.entity.OsGroup;
import com.elex.oa.org.model.GroupType;
import com.elex.oa.org.model.IGroup;
import com.elex.oa.org.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *@author hugo.zhao
 *@since 2018/4/25 16:18
*/
@Service
public class GroupServiceImpl  implements GroupService{

    @Autowired
    private IOsGroupDao osGroupDao;

    @Override
    public List<IGroup> getByTypeAndUserId(String groupType, String userId) {
        Map<String,String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("relTypeId", "1");
        List groupList = this.osGroupDao.getByUserIdRelTypeId(map);
        ArrayList rtnList = new ArrayList();
        Iterator var5 = groupList.iterator();
        while(var5.hasNext()) {
            OsGroup group = (OsGroup)var5.next();
            if(group.getType().equals(groupType)) {
                rtnList.add(group);
            }
        }
        return rtnList;
    }

    @Override
    public Map<String, List<IGroup>> getGroupsMapByUserId(String var1) {
        return null;
    }

    @Override
    public Map<String, List<String>> getGroupIdsMapByUserId(String var1) {
        return null;
    }

    @Override
    public List<IGroup> getGroupsByUserId(String var1) {
        return null;
    }

    @Override
    public List<String> getGroupsIdByUserId(String var1) {
        return null;
    }

    @Override
    public IGroup getMainByUserId(String var1) {
        return null;
    }

    @Override
    public String getFullDepNames(String var1) {
        return null;
    }

    @Override
    public IGroup getById(String var1, String var2) {
        return null;
    }

    @Override
    public IGroup getById(String var1) {
        return null;
    }

    @Override
    public IGroup getByCode(String var1, String var2) {
        return null;
    }

    @Override
    public List<GroupType> getGroupTypes() {
        return null;
    }
}
