package com.elex.oa.org.service;

/**
 *@author hugo.zhao
 *@since 2018/4/25 16:14
*/
import com.elex.oa.entity.OsGroup;
import com.elex.oa.org.model.GroupType;
import com.elex.oa.org.model.IGroup;

import java.util.List;
import java.util.Map;

public interface GroupService {
    List<IGroup> getByTypeAndUserId(String var1, String var2);

    Map<String, List<IGroup>> getGroupsMapByUserId(String var1);

    Map<String, List<String>> getGroupIdsMapByUserId(String var1);

    List<IGroup> getGroupsByUserId(String var1);

    List<String> getGroupsIdByUserId(String var1);

    IGroup getMainByUserId(String var1);

    String getFullDepNames(String var1);

    IGroup getById(String var1, String var2);

    IGroup getById(String var1);

    IGroup getByCode(String var1, String var2);

    List<GroupType> getGroupTypes();

    String getMainDepFullNames(String userId);

    OsGroup getMainDeps(String userId);

    OsGroup getByLevel(String groupId, Integer level);

    OsGroup getByLevel(OsGroup group, Integer level);

    List<OsGroup> getByDimIdAndUserId(String dimId, String userId);

    List<OsGroup> getBelongGroups(String userId);

    List<OsGroup> getBelongDeps(String userId);




}