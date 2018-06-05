package com.elex.oa.service.impl;
import com.elex.oa.core.constants.MBoolean;
import com.elex.oa.dao.ISysMenuDao;
import com.elex.oa.entity.SysMenu;
import com.elex.oa.saweb.security.metadata.MenuGroupModel;
import com.elex.oa.service.ISysMenuService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

/**
 *@author hugo.zhao
 *@since 2018/5/10 18:26
*/
@Service
public class SysMenuServiceImpl implements ISysMenuService {
    @Resource
    private ISysMenuDao sysMenuDao;

    @Override
    public List<SysMenu> getGrantMenusByGroupId(String groupId) {
        Map<String,String> params = new HashMap<>();
        params.put("groupId", groupId);
        return this.sysMenuDao.getGrantMenusByGroupId(params);
    }

    @Override
    public List<SysMenu> getGrantMenusBySysIdGroupId(String sysId, String groupId) {
        Map<String,String> params = new HashMap<>();
        params.put("sysId", sysId);
        params.put("groupId", groupId);
        return this.sysMenuDao.getGrantMenusBySysIdGroupId(params);
    }

    @Override
    public List<SysMenu> getGrantMenusBySysIdUserId(String sysId, String userId, String tenantId, String isBtnMenu) {
        Map<String,String> params = new HashMap<>();
        params.put("sysId", sysId);
        params.put("userId", userId);
        params.put("tenantId", tenantId);
        params.put("isBtnMenu", isBtnMenu);
        return  this.sysMenuDao.getGrantMenusBySysIdUserId(params);
    }

    @Override
    public List<SysMenu> getBySysIdIsBtnMenu(String sysId, String isBtnMenu) {
        Map<String,String> params = new HashMap<>();
        params.put("sysId", sysId);
        params.put("isBtnMenu", isBtnMenu);
        return this.sysMenuDao.getBySysIdIsBtnMenu(params);
    }

    @Override
    public List getGroupsByKey(String key) {
        return  this.sysMenuDao.getGroupsByKey(key);
    }

    @Override
    public List<MenuGroupModel> getMenuGroupUrlMap() {
        return this.sysMenuDao.getMenuGroupUrlMap();
    }

    @Override
    public List<SysMenu> getByInstType(String instType) {
        return this.sysMenuDao.getByInstType(instType);
    }

    @Override
    public List<SysMenu> getBySysId(String sysId) {
        return this.getBySysId(sysId);
    }

    @Override
    public SysMenu getByUserMenuId(String userId, String menuId) {
        Map<String,String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("menuId", menuId);
        return this.sysMenuDao.getByUserMenuId(params);
    }

    @Override
    public List<SysMenu> getGrantMenusByTypeId(String typeId) {
        return this.sysMenuDao.getGrantMenusByTypeId(typeId);
    }

    @Override
    public List<SysMenu> getUrlMenuByTenantMgr(String sysId, String tenantId, String isBtnMenu) {
        Map<String,String> params = new HashMap<>();
        params.put("sysId", sysId);
        params.put("tenantId", tenantId);
        params.put("isBtnMenu", isBtnMenu);
        return this.sysMenuDao.getUrlMenuByTenantMgr(params);
    }

    @Override
    public List<SysMenu> getMenusByTenantUser(String parentId, String userId) {
        Map<String,String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("parentId", parentId);
        params.put("isBtnMenu", MBoolean.NO.name());
        return this.sysMenuDao.getMenusByTenantUser(params);
    }

    @Override
    public List<SysMenu> getByTenantType(String sysId, String instType) {
        Map<String,String> params = new HashMap<>();
        params.put("sysId", sysId);
        params.put("instType", instType);
        return this.sysMenuDao.getByTenantType(params);
    }

    @Override
    public Map<String, Set<String>> getUrlGroupIdMap() {
        HashMap map = new HashMap();
        List list = this.sysMenuDao.getMenuGroupUrlMap();

        MenuGroupModel model;
        Object groupIdSet;
        for(Iterator var3 = list.iterator(); var3.hasNext(); ((Set)groupIdSet).add(model.getGroupKey())) {
            model = (MenuGroupModel)var3.next();
            groupIdSet = (Set)map.get(model.getUrl());
            if(groupIdSet == null) {
                groupIdSet = new HashSet();
                map.put(model.getUrl(), groupIdSet);
            }
        }

        return map;

    }
}
