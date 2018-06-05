package com.elex.oa.saweb.filter;
import com.elex.oa.org.model.IUser;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.util.*;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class TokenSsoFilter implements Filter {
    public TokenSsoFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String token = CookieUitl.getValueByName("token", req);
        IUser user = ContextUtil.getCurrentUser();
        if(user != null) {
            chain.doFilter(request, response);
        } else {
            if(StringUtil.isNotEmpty(token)) {
                JSONObject userJson = this.getJson();
                String url = "http://121.201.110.228:8012/security-client/rest?method=securityService.checkState";
                String jsonParams = "{\"token\":" + token + "}";

                try {
                    String e = HttpClientUtil.postJson(url, jsonParams);
                    JSONObject obj = JSONObject.parseObject(e);
                    if("0".equals(obj.getString("returncode"))) {
                        JSONObject data = obj.getJSONObject("data");
                        String code = data.getString("code");
                        String account = userJson.getString(code) + "@redxun.cn";
                        SecurityUtil.login(req, account, "", true);
                        chain.doFilter(request, response);
                    } else {
                        response.getWriter().println("查询服务出错");
                    }
                } catch (Exception var15) {
                    var15.printStackTrace();
                }
            } else {
                chain.doFilter(request, response);
            }

        }
    }

    private JSONObject getJson() {
        String path = WebAppUtil.getClassPath();
        path = path + "config/usermap.json";
        String json = FileUtil.readFile(path);
        JSONObject jsonObj = JSONObject.parseObject(json);
        return jsonObj;
    }

    public void destroy() {
    }
}
