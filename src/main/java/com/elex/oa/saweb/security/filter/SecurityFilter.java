package com.elex.oa.saweb.security.filter;
import com.elex.oa.json.JsonResult;
import com.elex.oa.util.StringUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*@Component*/
public class SecurityFilter implements Filter {
    private Pattern regex = Pattern.compile("<(\\S*?)[^>]*>.*?</\\1>|<[^>]+>|<\\S+|\'", 106);
/*    @Resource(
            name = "xssUrl"
    )
    RegMatchers xssMatchers;*/
 /*   @Resource(
            name = "csrfUrl"
    )
    RegMatchers crsfMatchers;*/
    @Resource
    RegMatchers tokenMatchers;
    public static String CRSF_TOKEN = "crsf_token";

    public SecurityFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        chain.doFilter(request, response);
    }

    void setToken(HttpServletRequest req, String url) {
        if(this.tokenMatchers.isContainUrl(url)) {
            HttpSession session = req.getSession();
            String uid = UUID.randomUUID().toString();
            session.setAttribute(CRSF_TOKEN, uid);
        }
    }

    private void handXss(HttpServletRequest req, ServletResponse response) throws ServletException, IOException {
        String reqWith = req.getHeader("x-requested-with");
        if(StringUtil.isEmpty(reqWith)) {
            req.getRequestDispatcher("/commons/xss.jsp").forward(req, response);
        } else {
            response.setContentType("text/html;charset=utf-8");
            JsonResult resultMessage = new JsonResult(false, "检测到XSS攻击，请检是否输入了HTML字符！");
            response.getWriter().print(resultMessage);
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

    private boolean checkXss(HttpServletRequest request) {
        Enumeration params = request.getParameterNames();

        while(params.hasMoreElements()) {
            String key = params.nextElement().toString();
            String[] vals = request.getParameterValues(key);
            String val = StringUtil.join(vals, "");
            if(!StringUtil.isEmpty(val)) {
                Matcher regexMatcher = this.regex.matcher(val);
                if(regexMatcher.find()) {
                    return true;
                }
            }
        }

        return false;
    }
}
