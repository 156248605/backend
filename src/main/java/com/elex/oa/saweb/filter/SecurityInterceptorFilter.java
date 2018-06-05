package com.elex.oa.saweb.filter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elex.oa.org.model.IUser;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.saweb.security.provider.ISecurityDataProvider;
import com.elex.oa.service.ISysAccountService;
import com.elex.oa.util.StringUtil;
import com.elex.oa.util.WebAppUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 *@author hugo.zhao
 *@since 2018/5/14 16:26
*/
/*@Component*/
public class SecurityInterceptorFilter extends OncePerRequestFilter {
    private PathMatcher matcher = new AntPathMatcher();
    @Resource
    private ISecurityDataProvider securityDataProvider;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
   // SysAccountManager sysAccountManager;
    ISysAccountService sysAccountManager;

    public SecurityInterceptorFilter() {
    }

    public void setSecurityDataProvider(ISecurityDataProvider securityDataProvider) {
        this.securityDataProvider = securityDataProvider;
    }

    protected String getReqPath(HttpServletRequest request) {
        String url = request.getRequestURI();
        if(StringUtils.hasLength(request.getContextPath())) {
            String contextPath = request.getContextPath();
            int index = url.indexOf(contextPath);
            if(index != -1) {
                url = url.substring(index + contextPath.length());
            }
        }

        return url;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String username = request.getParameter("acc");
        ContextUtil.cleanAll();
        String url = this.getReqPath(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if("anonymousUser".equals(auth.getPrincipal().toString())) {
            Iterator user1 = this.securityDataProvider.getAnonymousUrls().iterator();

            String isSuperUser1;
            do {
                if(!user1.hasNext()) {
                    throw new AccessDeniedException("当前请求不允许匿名访问!");
                }

                isSuperUser1 = (String)user1.next();
            } while(!this.matcher.match(isSuperUser1, url));

            chain.doFilter(request, response);
        } else {
            IUser user = ContextUtil.getCurrentUser();
            boolean isSuperUser = false;
          /*  if(WebAppUtil.isSaasMgrUser() && user.isSuperAdmin()) {
                isSuperUser = true;
            }*/
            if(user.isSuperAdmin()) {
                isSuperUser = true;
            }
            if(isSuperUser) {
                chain.doFilter(request, response);
            } else {
                if(user.isSuperAdmin()) {
                    String groupIdSet = ContextUtil.getTenant().getInstType();
                    if(StringUtil.isEmpty(groupIdSet) || ((Set)this.securityDataProvider.getTenantUrlSet().get(groupIdSet)).contains(url)) {
                        chain.doFilter(request, response);
                        return;
                    }
                }

                Iterator groupIdSet1 = this.securityDataProvider.getPublicUrls().iterator();

                while(groupIdSet1.hasNext()) {
                    String isIncludeGroupId = (String)groupIdSet1.next();
                    if(this.matcher.match(isIncludeGroupId, url)) {
                        chain.doFilter(request, response);
                        return;
                    }
                }

                if(!this.securityDataProvider.getMenuGroupIdsMap().containsKey(url)) {
                    chain.doFilter(request, response);
                } else {
                    Set groupIdSet2 = (Set)this.securityDataProvider.getMenuGroupIdsMap().get(url);
                    boolean isIncludeGroupId1 = false;
                    Iterator var10 = auth.getAuthorities().iterator();

                    while(var10.hasNext()) {
                        GrantedAuthority au = (GrantedAuthority)var10.next();
                        if(groupIdSet2 != null && groupIdSet2.contains(au.getAuthority())) {
                            isIncludeGroupId1 = true;
                            break;
                        }
                    }

                    if(!isIncludeGroupId1) {
                        throw new AccessDeniedException("Access is denied! Url:" + url + " User:" + SecurityContextHolder.getContext().getAuthentication().getName());
                    } else {
                        chain.doFilter(request, response);
                    }
                }
            }
        }
    }

    public void afterPropertiesSet() throws ServletException {
        this.securityDataProvider.reloadSecurityDataCache();
    }
}
