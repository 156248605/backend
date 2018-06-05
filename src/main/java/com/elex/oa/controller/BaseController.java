package com.elex.oa.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
/**
 *@author hugo.zhao
 *@since 2018/5/10 15:23
*/
public abstract class BaseController extends GenericController {
    public BaseController() {
    }
    public Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        Cookie[] var4 = cookies;
        int var5 = cookies.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Cookie ck = var4[var6];
            if(ck.getName().equals(cookieName)) {
                return ck;
            }
        }

        return null;
    }
}
