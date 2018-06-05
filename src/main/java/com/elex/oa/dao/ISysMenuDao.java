package com.elex.oa.dao;

import com.elex.oa.entity.SysMenu;
import com.elex.oa.saweb.security.metadata.MenuGroupModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/5/10 18:03
*/
@Mapper
public interface ISysMenuDao {
    List<SysMenu> getGrantMenusByGroupId(Map<String,String> map);

    List<SysMenu> getGrantMenusBySysIdGroupId(Map<String,String> map);

    List<SysMenu> getGrantMenusBySysIdUserId(Map<String,String> map);

    List<SysMenu> getBySysIdIsBtnMenu(Map<String,String> map);

    List<String> getGroupsByKey(String key);
    List<MenuGroupModel> getMenuGroupUrlMap();

    List<SysMenu> getByInstType(String instType);

    List<SysMenu> getBySysId(String sysId);

    SysMenu getByUserMenuId(Map<String,String> map);

    //boolean isBoListExist(Map<String,String> map);

    //List getBoMenuByUserId(Map<String,String> map);
    List<SysMenu> getGrantMenusByTypeId(String typeId);

    List<SysMenu> getUrlMenuByTenantMgr(Map<String,String> map);

    List<SysMenu> getMenusByTenantUser(Map<String,String> map);

    List<SysMenu> getByTenantType(Map<String,String> map);

    //boolean isKeyExist(Map<String,String> map);



}
