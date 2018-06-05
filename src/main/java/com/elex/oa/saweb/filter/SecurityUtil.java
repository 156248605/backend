package com.elex.oa.saweb.filter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.elex.oa.entity.OsUser;
import com.elex.oa.entity.SysAccount;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.service.ISysAccountService;
import com.elex.oa.service.ISysMenuService;
import com.elex.oa.util.*;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
/**
 *@author hugo.zhao
 *@since 2018/5/10 15:46
*/
public class SecurityUtil {
    private static String rememberPrivateKey = "rememberPrivateKey";

    public SecurityUtil() {
    }

    public static Authentication login(HttpServletRequest request, String userName, String pwd, boolean isIgnorePwd) {
        AuthenticationManager authenticationManager = (AuthenticationManager) WebAppUtil.getBean("authenticationManager");
        CustomPwdEncoder.setIngore(isIgnorePwd);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, pwd);
        authRequest.setDetails(new WebAuthenticationDetails(request));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = authenticationManager.authenticate(authRequest);
        securityContext.setAuthentication(auth);
        return auth;
    }

    public static Boolean hasPermission(String menuKey) {
        OsUser user = (OsUser) ContextUtil.getCurrentUser();
        if(user.isSuperAdmin()) {
            return Boolean.valueOf(true);
        } else {
           // SysMenuQueryDao dao = (SysMenuQueryDao) AppBeanUtil.getBean(SysMenuQueryDao.class);
            ISysMenuService sysMenuService = (ISysMenuService)AppBeanUtil.getBean(ISysMenuService.class);
            List groups = sysMenuService.getGroupsByKey(menuKey);
            Collection authors = user.getAuthorities();
            Iterator var5 = authors.iterator();

            String str;
            do {
                if(!var5.hasNext()) {
                    return Boolean.valueOf(false);
                }

                GrantedAuthority auth = (GrantedAuthority)var5.next();
                str = auth.getAuthority();
            } while(!groups.contains(str));

            return Boolean.valueOf(true);
        }
    }

    public static void writeRememberMeCookie(HttpServletRequest request, HttpServletResponse response, String username, String password) throws DecoderException {
        String rememberMe = RequestUtil.getString(request, "rememberMe", "0");
        if("1".equals(rememberMe)) {
            String enPassword = EncryptUtil.hexToBase64(password);
            long tokenValiditySeconds = 1209600L;
            long tokenExpiryTime = System.currentTimeMillis() + tokenValiditySeconds * 1000L;
            String signatureValue = makeTokenSignature(tokenExpiryTime, username, enPassword);
            String tokenValue = username + ":" + tokenExpiryTime + ":" + signatureValue;
            String tokenValueBase64 = new String(Base64.encodeBase64(tokenValue.getBytes()));
            CookieUitl.addCookie("SPRING_SECURITY_REMEMBER_ME_COOKIE", tokenValueBase64, 31536000, true, request, response);
        }
    }

    public static void writeRememberMeCookie(HttpServletRequest request, HttpServletResponse response, String username) throws DecoderException {
        //SysAccountManager sysAccountManager = (SysAccountManager)AppBeanUtil.getBean(SysAccountManager.class);
        ISysAccountService sysAccountService = (ISysAccountService)AppBeanUtil.getBean(ISysAccountService.class);
        SysAccount sysAccount = sysAccountService.getByName(username);
        String enPassword = sysAccount.getPwd();
        long tokenValiditySeconds = 1209600L;
        long tokenExpiryTime = System.currentTimeMillis() + tokenValiditySeconds * 1000L;
        String signatureValue = makeTokenSignature(tokenExpiryTime, username, enPassword);
        String tokenValue = username + ":" + tokenExpiryTime + ":" + signatureValue;
        String tokenValueBase64 = new String(Base64.encodeBase64(tokenValue.getBytes()));
        CookieUitl.addCookie("SPRING_SECURITY_REMEMBER_ME_COOKIE", tokenValueBase64, 31536000, true, request, response);
    }

    private static String makeTokenSignature(long tokenExpiryTime, String username, String password) {
        String data = username + ":" + tokenExpiryTime + ":" + password + ":" + rememberPrivateKey;

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var7) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(data.getBytes())));
    }
}
