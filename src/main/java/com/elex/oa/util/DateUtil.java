package com.elex.oa.util;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *@author hugo.zhao
 *@since 2018/4/12 19:49
*/
public class DateUtil {
    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_TIME = "HH:mm:ss";
    public static final String DATE_FORMAT_MON = "yyyy-MM";
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

    public DateUtil() {
    }

    public static Date parseDate(String dateString) {
        Date date = null;
        if(dateString.indexOf("T") == -1) {
            date = parseDate(dateString, "");
        } else {
            dateString = dateString.replace("T", " ");
            date = parseDate(dateString, "yyyy-MM-dd HH:mm:ss");
        }

        return date;
    }

    public static Date parseDate(String dateString, String format) {
        if(StringUtil.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date rtn = null;

        try {
            rtn = sdf.parse(dateString);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return rtn;
    }

    public static String formatDate(Date date, String format) {
        if(BeanUtil.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String formatDate(Date date) {
        return formatDate(date, "");
    }

    public static Date setStartDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        return cal.getTime();
    }

    public static Date setEndDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        return cal.getTime();
    }

    public static int getCurDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(5);
    }

    public static int getCurWeekOfYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(3);
    }

    public static int getWeekOfYear(Date time) {
        Calendar cal = Calendar.getInstance();
        return cal.get(3);
    }

    public static int getCurMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(2);
    }

    public static int getCurYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(1);
    }

    public static int getDay(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        return cal.get(5);
    }

    public static int getMonth(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        return cal.get(2);
    }

    public static int getYear(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        return cal.get(1);
    }

    public static Date add(Date time, Integer dateUnit, Integer amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(dateUnit.intValue(), amount.intValue());
        return cal.getTime();
    }

    public static void main(String[] args) {
        System.out.println(parseDate("2018-9-30"));
    }
}
