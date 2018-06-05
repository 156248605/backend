package com.elex.oa.saweb.security.provider;

import com.elex.oa.entity.SysInstType;
import com.elex.oa.entity.SysMenu;
import com.elex.oa.service.ISysInstTypeService;
import com.elex.oa.service.ISysMenuService;
import com.elex.oa.util.StringUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
@Component
public class SecurityDataSourceProvider implements ISecurityDataProvider {

    @Resource
    private ISysInstTypeService sysInstTypeManager;
    @Resource
    private ISysMenuService sysMenuManager;
    private Set<String> anonymousUrls = null;
    private Set<String> publicUrls = null;
    private Map<String, Set<String>> menuGroupIdsMap = null;
    private Map<String, Set<String>> tenantUrlSet = null;


    public SecurityDataSourceProvider() {
        this.anonymousUrls = new HashSet<>();
        this.anonymousUrls.add("/login");
    }

    public void setSysMenuManager(ISysMenuService sysMenuManager) {
        this.sysMenuManager = sysMenuManager;
    }

    public void setAnonymousUrls(Set<String> anonymousUrls) {
        this.anonymousUrls = anonymousUrls;
    }

    public void setPublicUrls(Set<String> publicUrls) {
        this.publicUrls = publicUrls;
    }

    public Set<String> getAnonymousUrls() {
        return this.anonymousUrls;
    }

    public Map<String, Set<String>> getTenantUrlSet() {
        return this.tenantUrlSet;
    }

    public Set<String> getPublicUrls() {
        return this.publicUrls;
    }

    public void reloadMenuGroupIdsMap() {
        this.menuGroupIdsMap = this.sysMenuManager.getUrlGroupIdMap();
    }

    public void reloadSecurityDataCache() {
        this.reloadMenuGroupIdsMap();
        this.reloadTenantMenuUrls();
    }

    public Map<String, Set<String>> getMenuGroupIdsMap() {
        return this.menuGroupIdsMap;
    }

    public void reloadTenantMenuUrls() {
        this.tenantUrlSet = new HashMap();
        List instTypes = this.sysInstTypeManager.getValidAll();
        Iterator var2 = instTypes.iterator();

        while(var2.hasNext()) {
            SysInstType instType = (SysInstType)var2.next();
            String type = instType.getTypeCode();
            HashSet urlSet = new HashSet();
            List menus = this.sysMenuManager.getByInstType(type);
            Iterator var7 = menus.iterator();

            while(var7.hasNext()) {
                SysMenu menu = (SysMenu)var7.next();
                if(!StringUtil.isEmpty(menu.getUrl())) {
                    urlSet.add(menu.getUrl());
                }
            }

            this.tenantUrlSet.put(type, urlSet);
        }

    }

}
