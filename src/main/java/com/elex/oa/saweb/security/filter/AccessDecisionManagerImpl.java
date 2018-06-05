package com.elex.oa.saweb.security.filter;

import java.util.Collection;

import com.elex.oa.entity.SysAccount;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {
    public AccessDecisionManagerImpl() {
    }

    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(authentication == null) {
            throw new AccessDeniedException("没有登录系统");
        } else {
            Collection auths = authentication.getAuthorities();
            Object principal = authentication.getPrincipal();
            if(principal == null) {
                throw new AccessDeniedException("登录对象为空");
            } else {
                SysAccount user = (SysAccount)principal;
                if(user == null) {
                    throw new AccessDeniedException("对不起,你没有访问该页面的权限!");
                }
            }
        }
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}