package com.elex.oa.saweb.security.filter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.elex.oa.saweb.security.metadata.SecurityFilterMetadataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;


public class PermissionFilter extends AbstractSecurityInterceptor implements Filter {
    private static final Log logger = LogFactory.getLog(PermissionFilter.class);
    private SecurityFilterMetadataSource securityFilterMetadataSource;

    public PermissionFilter() {
    }

    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityFilterMetadataSource;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        Collection auths = auth.getAuthorities();
        Iterator var7 = auths.iterator();

        while(var7.hasNext()) {
            GrantedAuthority gath = (GrantedAuthority)var7.next();
            logger.info("--------------name:" + gath.getAuthority());
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public SecurityFilterMetadataSource getSecurityFilterMetadataSource() {
        return this.securityFilterMetadataSource;
    }

    public void setSecurityFilterMetadataSource(SecurityFilterMetadataSource securityFilterMetadataSource) {
        this.securityFilterMetadataSource = securityFilterMetadataSource;
    }
}