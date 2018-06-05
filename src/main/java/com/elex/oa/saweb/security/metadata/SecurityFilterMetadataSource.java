package com.elex.oa.saweb.security.metadata;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

import com.elex.oa.saweb.security.consts.SecurityConsts;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
public class SecurityFilterMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
    private HashSet<String> anonymousUrls = new HashSet();

    public SecurityFilterMetadataSource() {
    }

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HashSet configAttribute = new HashSet();
        FilterInvocation filterInvocation = (FilterInvocation)object;
        HttpServletRequest request = filterInvocation.getRequest();
        String url = this.getServletUrl(request);
        Iterator var6 = this.anonymousUrls.iterator();

        boolean isMatch;
        do {
            if(!var6.hasNext()) {
                configAttribute.add(new SecurityConfig("ROLE_PUBLIC"));
                return configAttribute;
            }

            String pUrl = (String)var6.next();
            AntPathMatcher matcher = new AntPathMatcher();
            isMatch = matcher.match(pUrl, url);
        } while(!isMatch);

        configAttribute.add(SecurityConsts.ROLE_ANONYMOUS);
        return configAttribute;
    }

    private String getServletUrl(HttpServletRequest req) {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        if(!"".equals(contextPath)) {
            int index = url.indexOf(contextPath);
            if(index != -1) {
                url = url.substring(index + contextPath.length());
            }
        }

        return url;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    public HashSet<String> getAnonymousUrls() {
        return this.anonymousUrls;
    }

    public void setAnonymousUrls(HashSet<String> anonymousUrls) {
        this.anonymousUrls = anonymousUrls;
    }

    public void afterPropertiesSet() throws Exception {
    }
}

