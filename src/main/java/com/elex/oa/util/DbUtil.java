package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2018/4/18 11:56
*/
public class DbUtil {
    public DbUtil() {
    }

    public static String getColumnPre() {
        String columnPre = WebAppUtil.getProperty("column_pre", "F_").toUpperCase();
        return columnPre;
    }

    public static String getTablePre() {
        String tablePre = WebAppUtil.getProperty("table_pre", "W_");
        return tablePre;
    }
}
