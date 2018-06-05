package com.elex.oa.saweb.security.filter;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elex.oa.util.CookieUitl;
import com.elex.oa.util.StringUtil;
import com.elex.oa.util.WebAppUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class MultipleAuthenticationLoginEntry implements AuthenticationEntryPoint {
    private String defaultLoginUrl = "/login";
    private String partnerLoginUrl = "/partner/login";

    public MultipleAuthenticationLoginEntry() {
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setCharacterEncoding("UTF-8");
            response.addHeader("exceptionaction", "timeout");
        } else {
            String ctxPath = request.getContextPath();
            String instType = CookieUitl.getValueByName("instType", request);
            if(StringUtil.isNotEmpty(instType) && !"PLATFORM".equals(instType)) {
                response.sendRedirect(ctxPath + this.partnerLoginUrl);
            } else {
                String loginAction = CookieUitl.getValueByName("loginAction", request);
                Map actionPageMap = (Map) WebAppUtil.getBean("actionPageMap");
                if(StringUtil.isNotEmpty(loginAction) && actionPageMap.containsKey(loginAction)) {
                    String redirectUrl = (String)actionPageMap.get(loginAction);
                    response.sendRedirect(request.getContextPath() + redirectUrl);
                } else {
                    response.sendRedirect(ctxPath + this.defaultLoginUrl);
                }
            }
        }
    }

    public void setDefaultLoginUrl(String defaultLoginUrl) {
        this.defaultLoginUrl = defaultLoginUrl;
    }

    public void setPartnerLoginUrl(String partnerLoginUrl) {
        this.partnerLoginUrl = partnerLoginUrl;
    }
}
