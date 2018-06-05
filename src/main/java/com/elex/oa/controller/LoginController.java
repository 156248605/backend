package com.elex.oa.controller;

import com.elex.oa.core.constants.MStatus;
import com.elex.oa.dao.IOsUserDao;
import com.elex.oa.entity.OsUser;
import com.elex.oa.json.JsonResult;
import com.elex.oa.org.service.UserService;
import com.elex.oa.org.service.impl.OsUserServiceImpl;
import com.elex.oa.saweb.filter.SecurityUtil;
import com.elex.oa.util.CookieUitl;
import com.elex.oa.util.EncryptUtil;
import com.elex.oa.util.RequestUtil;
import com.elex.oa.util.WebAppUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *@author hugo.zhao
 *@since 2018/5/10 15:20
*/
@Controller
@RequestMapping({"/"})
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping({"login"})
    @ResponseBody
    public JsonResult login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("acc");
        String password = request.getParameter("pd");
        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            if(username.indexOf("@") == -1) {
                username = username.trim() + "@" + WebAppUtil.getOrgMgrDomain();
            }
            String from = RequestUtil.getString(request, "from");
            CookieUitl.addCookie("loginAction", from, true, request, response);
            OsUser user = (OsUser)this.userService.getByUsername(username);
            if(user != null && user.getUsername().equals(username) && user.getPwd().equals(EncryptUtil.hexToBase64(password.trim()))) {
                if(user.getTenant() != null && MStatus.ENABLED.toString().equals(user.getTenant().getStatus())) {
                    String instType = user.getTenant().getInstType();
                    CookieUitl.addCookie("instType", instType, true, request, response);
                    SecurityUtil.login(request, username, password, false);
                    SecurityUtil.writeRememberMeCookie(request, response, username, password);
                   // this.logEntityManager.recordTheLog(request, response);
                   // this.osUserManager.virtualSetUserPhoto(user, request);
                    return new JsonResult(true, "Login Success", user);
                } else {
                    return new JsonResult(false, "企业机构已经被禁用！");
                }
            } else {
                return new JsonResult(false, "密码或用户名不正确！");
            }
        } else {
            return new JsonResult(false, "请输入用户与密码！");
        }
    }



}
