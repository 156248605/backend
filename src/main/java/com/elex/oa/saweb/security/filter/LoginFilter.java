package com.elex.oa.saweb.security.filter;


import com.elex.oa.dao.IOsUserDao;
import com.elex.oa.entity.OsUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elex.oa.org.model.IUser;
import com.elex.oa.org.service.UserService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Resource
    private UserService userService;

    public LoginFilter() {
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String username = this.obtainUsername(request);
            String password = this.obtainPassword(request);
            username = username.trim();
            IUser iUser = this.userService.getByUsername(username);
            if(iUser != null && iUser.getPwd().equals(password)) {
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                return this.getAuthenticationManager().authenticate(authRequest);
            } else {
                throw new AuthenticationServiceException("用户名或者密码错误！");
            }
        }
    }
}
