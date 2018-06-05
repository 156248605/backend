package com.elex.oa.saweb.security.provider;
import java.util.Map;
import java.util.Set;

public interface ISecurityDataProvider {
    Set<String> getAnonymousUrls();

    Map<String, Set<String>> getTenantUrlSet();

    Set<String> getPublicUrls();

    void reloadSecurityDataCache();

    Map<String, Set<String>> getMenuGroupIdsMap();

    void reloadTenantMenuUrls();

    void reloadMenuGroupIdsMap();
}