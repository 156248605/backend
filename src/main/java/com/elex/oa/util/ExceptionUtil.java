package com.elex.oa.util;
import java.io.PrintWriter;
import java.io.StringWriter;
/**
 *@author hugo.zhao
 *@since 2018/4/19 16:22
*/
public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static String getExceptionMessage(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        return str;
    }
}
