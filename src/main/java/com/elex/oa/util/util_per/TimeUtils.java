package com.elex.oa.util.util_per;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils  {
    public  static  String currentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public  static  String currentMinutes() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date =simpleDateFormat.format(new Date());
        String minutes = date.substring(0,date.lastIndexOf(":"));
        return  minutes;
    }

    public static String hexString(){
        long nowDate=new Date().getTime();
        String sid=Integer.toHexString((int) nowDate);
        return sid;
    }
}
