package com.elex.oa.service;

import com.elex.oa.entity.SysMenu;
import com.elex.oa.saweb.security.metadata.MenuGroupModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *@author hugo.zhao
 *@since 2018/5/10 18:22
*/
public interface ISysMenuService {
    List<SysMenu> getGrantMenusByGroupId(String groupId);

    List<SysMenu> getGrantMenusBySysIdGroupId(String sysId, String groupId);

    List<SysMenu> getGrantMenusBySysIdUserId(String sysId, String userId, String tenantId, String isBtnMenu);

    List<SysMenu> getBySysIdIsBtnMenu(String sysId, String isBtnMenu);

     List getGroupsByKey(String key);
    List<MenuGroupModel> getMenuGroupUrlMap();

    List<SysMenu> getByInstType(String instType);

    List<SysMenu> getBySysId(String sysId);

    SysMenu getByUserMenuId(String userId, String menuId);

    //boolean isBoListExist(Map<String,String> map);

    //List getBoMenuByUserId(Map<String,String> map);
    List<SysMenu> getGrantMenusByTypeId(String typeId);

    List<SysMenu> getUrlMenuByTenantMgr(String sysId, String tenantId, String isBtnMenu);

    List<SysMenu> getMenusByTenantUser(String parentId, String userId);

    List<SysMenu> getByTenantType(String sysId, String instType);

    //boolean isKeyExist(Map<String,String> map);

    Map<String, Set<String>> getUrlGroupIdMap();



}
