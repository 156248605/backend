package com.elex.oa.saweb.context;

/**
 *@author hugo.zhao
 *@since 2018/5/5 12:38
*/
import com.elex.oa.org.model.ITenant;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.service.UserService;
import com.elex.oa.util.AppBeanUtil;
import com.elex.oa.util.WebAppUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
public class ContextUtil {
    private static ThreadLocal<IUser> curUserLocal = new ThreadLocal();

    public ContextUtil() {
    }

    public static IUser getCurrentUser() {
        IUser curUser = (IUser)curUserLocal.get();
        if(curUser != null) {
            return curUser;
        } else {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if(securityContext == null) {
                return null;
            } else {
                Authentication auth = securityContext.getAuthentication();
                return auth == null?null:(auth.getPrincipal() instanceof IUser?(IUser)auth.getPrincipal():null);
            }
        }
    }

    public static void setCurUser(IUser user) {
        curUserLocal.set(user);
    }

    public static void setCurUser(String account) {
        UserService userService = (UserService) AppBeanUtil.getBean(UserService.class);
        IUser user = userService.getByUsername(account);
        curUserLocal.set(user);
    }

    public static void cleanAll() {
        curUserLocal.remove();
    }

    public static ITenant getTenant() {
        IUser curUser = getCurrentUser();
        return curUser == null?null:curUser.getTenant();
    }

    public static String getCurrentUserId() {
        IUser iUser = getCurrentUser();
        System.out.println(iUser);
        return iUser == null?null:iUser.getUserId();
    }

    public static String getCurrentTenantId() {
        IUser curUser = getCurrentUser();
        return curUser == null?null:curUser.getTenant().getTenantId();
    }

    public static String getUserTmpDir() throws Exception {
        return WebAppUtil.getUploadPath() + "/" + getCurrentUser().getUsername() + "/tmp";
    }

    public static void clearCurLocal() {
        curUserLocal.remove();
    }

}
