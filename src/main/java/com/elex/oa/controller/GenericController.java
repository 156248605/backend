package com.elex.oa.controller;
import com.elex.oa.saweb.context.ContextUtil;
import com.elex.oa.util.WebAppUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 *@author hugo.zhao
 *@since 2018/4/27 16:30
*/
public class GenericController {
    @Autowired
    MessageSource messageSource;

    protected String getErrorMsg(BindingResult bindResult) {
        List objErrorList = bindResult.getAllErrors();
        StringBuffer sb = new StringBuffer();

        String message;
        for(Iterator var4 = objErrorList.iterator(); var4.hasNext(); sb.append(message).append("\n")) {
            ObjectError err = (ObjectError)var4.next();
            message = null;
            if(err instanceof FieldError) {
                FieldError fieldErr = (FieldError)err;
                String msgKey = fieldErr.getObjectName() + "." + fieldErr.getField();
                String fielName = this.getMessage(msgKey, new Object[0]);
                String rejectVal = fieldErr.getRejectedValue() == null?"":fieldErr.getRejectedValue().toString();
                message = this.getMessage("reject.valueError", new Object[]{fielName, rejectVal, fieldErr.getDefaultMessage()});
            } else {
                message = "对象:" + err.getObjectName() + "，属性：" + (err.getCodes() == null?"":err.getCodes()[0]);
                message = message + err.getDefaultMessage();
            }
        }

        return sb.toString();
    }

    protected String getMessage(String msgKey, String defaultMsg) {
        return this.getMessage(msgKey, new Object[0], defaultMsg);
    }

    protected String getMessage(String msgKey, Object[] args) {
       // Locale locale = RequestContextUtils.getLocale(HttpServletContext.getRequest());
        Locale locale = new Locale("zh","CN");
        return this.messageSource.getMessage(msgKey, args, locale);
    }

    protected String getMessage(String msgKey, Object[] args, String defaultMsg) {
       // Locale locale = RequestContextUtils.getLocale(HttpServletContext.getRequest());
        Locale locale = new Locale("zh","CN");
        return this.messageSource.getMessage(msgKey, args, defaultMsg, locale);
    }

    protected String getCurTenantId(HttpServletRequest request) {
        String instId = request.getParameter("tenantId");
        return StringUtils.isNotEmpty(instId) && !"null".equals("tenantId") && WebAppUtil.getOrgMgrDomain().equals(ContextUtil.getTenant().getDomain())?instId:ContextUtil.getCurrentTenantId();
    }
}
