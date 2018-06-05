package com.elex.oa.org.service.impl;
import com.elex.oa.core.constants.MBoolean;
import com.elex.oa.dao.IOsGroupDao;
import com.elex.oa.entity.OsGroup;
import com.elex.oa.org.model.GroupType;
import com.elex.oa.org.model.IGroup;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

/**
 *@author hugo.zhao
 *@since 2018/4/25 16:18
*/
@Service
public class GroupServiceImpl  implements GroupService{

    @Resource
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
    public Map<String, List<IGroup>> getGroupsMapByUserId(String userId) {
        HashMap groupMap = new HashMap();
        HashMap<String,String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("relTypeId", "1");
        List groupList = this.osGroupDao.getByUserIdRelTypeId(params);
        Iterator var4 = groupList.iterator();

        while(var4.hasNext()) {
            OsGroup group = (OsGroup)var4.next();
            String groupType = group.getType();
            List list = (List)groupMap.get(groupType);
            if(groupMap.containsKey(groupType)) {
                list = (List)groupMap.get(groupType);
                list.add(group);
            } else {
                ArrayList list1 = new ArrayList();
                list1.add(group);
                groupMap.put(groupType, list1);
            }
        }

        return groupMap;
    }

    @Override
    public Map<String, List<String>> getGroupIdsMapByUserId(String userId) {
        HashMap groupMap = new HashMap();
        HashMap<String,String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("relTypeId", "1");
        List groupList = this.osGroupDao.getByUserIdRelTypeId(params);
        Iterator var4 = groupList.iterator();
        while(var4.hasNext()) {
            OsGroup group = (OsGroup)var4.next();
            String groupType = group.getType();
            List list = (List)groupMap.get(groupType);
            if(groupMap.containsKey(groupType)) {
                list = (List)groupMap.get(groupType);
                list.add(group.getGroupId());
            } else {
                ArrayList list1 = new ArrayList();
                list1.add(group.getGroupId());
                groupMap.put(groupType, list1);
            }
        }
        return groupMap;
    }

    @Override
    public List<IGroup> getGroupsByUserId(String userId) {
        HashMap<String,String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("relTypeId", "1");
        List groupList = this.osGroupDao.getByUserIdRelTypeId(params);
        return groupList;
    }

    @Override
    public List<String> getGroupsIdByUserId(String userId) {
        HashMap<String,String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("relTypeId", "1");
        List groupList = this.osGroupDao.getByUserIdRelTypeId(params);
        ArrayList groupIds = new ArrayList();
        Iterator var4 = groupList.iterator();
        while(var4.hasNext()) {
            OsGroup group = (OsGroup)var4.next();
            groupIds.add(group.getGroupId());
        }
        return groupIds;
    }

    @Override
    public IGroup getMainByUserId(String userId) {
        OsGroup group = this.getMainDeps(userId);
        return group;
    }

    @Override
    public String getFullDepNames(String userId) {
        return this.getMainDepFullNames(userId);
    }

    @Override
    public IGroup getById(String groupType, String groupId) {
        return this.osGroupDao.selectByPrimaryKey(groupId);
    }

    @Override
    public IGroup getById(String groupId) {
        OsGroup org = this.osGroupDao.selectByPrimaryKey(groupId);
        return org;
    }

    @Override
    public IGroup getByCode(String var1, String var2) {
        return null;
    }

    @Override
    public List<GroupType> getGroupTypes() {
        GroupType orgType = new GroupType("org", "行政组织");
        GroupType roleType = new GroupType("role", "角色");
        ArrayList list = new ArrayList();
        list.add(orgType);
        list.add(roleType);
        return list;
    }
    @Override
    public OsGroup getMainDeps(String userId){
        HashMap params = new HashMap();
        params.put("userId", userId);
        params.put("dimId", "1");
        params.put("relTypeId", "1");
        params.put("isMain", MBoolean.YES.name());
        List groups = this.osGroupDao.getByDimIdUserIdRelTypeIdIsMain(params);
        return groups.size() > 0?(OsGroup)groups.get(0):null;
    }
    @Override
    public String getMainDepFullNames(String userId) {
        OsGroup mainDep = this.getMainDeps(userId);
        if(mainDep != null && !StringUtils.isNotEmpty(mainDep.getPath())) {
            String[] mainDepIds = mainDep.getPath().split("[.]");
            StringBuffer depBuf = new StringBuffer();
            String[] var5 = mainDepIds;
            int var6 = mainDepIds.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String depId = var5[var7];
                if(!"0".equals(depId)) {
                    //OsGroup group = (OsGroup)this.get(depId);
                    OsGroup group = osGroupDao.selectByPrimaryKey(depId);
                    if(group != null) {
                        depBuf.append(group.getName()).append("/");
                    }
                }
            }

            return depBuf.toString();
        } else {
            return "";
        }
    }
    public OsGroup getByLevel(String groupId, Integer level) {
        OsGroup group = (OsGroup)this.getById(groupId);
        return this.getByLevel(group, level);
    }

    public OsGroup getByLevel(OsGroup group, Integer level) {
        if(group.getRankLevel().equals(level)) {
            return group;
        } else {
            String path = group.getPath();
            path = StringUtil.trimPrefix(path, "0.");
            path = StringUtil.trimSuffix(path, ".");
            String[] aryPath = path.split("[.]");
            int len = aryPath.length - 2;
            if(len < 0) {
                return null;
            } else {
                for(int i = len; i >= 0; --i) {
                    String id = aryPath[i];
                    group = (OsGroup)this.getById(id);
                    if(group.getRankLevel().equals(level)) {
                        return group;
                    }
                }

                return null;
            }
        }
    }

    @Override
    public List<OsGroup> getByDimIdAndUserId(String dimId, String userId) {
        Map<String,String> params = new HashMap();
        params.put("userId", userId);
        params.put("dimId", dimId);
        return this.osGroupDao.getByDimIdUserId(params);
    }

    @Override
    public List<OsGroup> getBelongGroups(String userId) {
        Map<String,String> params = new HashMap<>();
        params.put("userId",userId);
        params.put("relTypeId","1");
        return this.osGroupDao.getByUserIdRelTypeId(params);
    }

    @Override
    public List<OsGroup> getBelongDeps(String userId) {
        Map<String,String> params = new HashMap<>();
        params.put("userId",userId);
        params.put("dimId","1");
        params.put("relTypeId","1");
        return this.osGroupDao.getByDimIdUserIdRelTypeId(params);
    }

}
