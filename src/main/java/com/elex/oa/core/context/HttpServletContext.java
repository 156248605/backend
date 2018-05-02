package com.elex.oa.core.context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *@author hugo.zhao
 *@since 2018/4/27 16:35
*/


public class HttpServletContext {
    private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal();
    private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal();

    public HttpServletContext() {
    }

    public static void setContext(HttpServletRequest req, HttpServletResponse response) {
        requestLocal.set(req);
        responseLocal.set(response);
    }

    public static void cleanContext() {
        requestLocal.remove();
        responseLocal.remove();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest)requestLocal.get();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse)responseLocal.get();
    }
}
