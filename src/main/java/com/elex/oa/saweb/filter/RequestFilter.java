package com.elex.oa.saweb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elex.oa.core.context.HttpServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;
@Component
public class RequestFilter implements Filter {
    private static final Log logger = LogFactory.getLog(RequestFilter.class);

    public RequestFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpServletContext.setContext((HttpServletRequest)request, resp);
        request.setAttribute("currentLocale", RequestContextUtils.getLocale((HttpServletRequest)request));
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}