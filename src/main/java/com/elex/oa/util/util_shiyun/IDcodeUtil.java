package com.elex.oa.util.util_shiyun;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:通过身份证号码获得一些信息
 * @Date:Created in  18:52 2018\5\12 0012
 * @Modify By:
 */
public class IDcodeUtil {
    public IDcodeUtil() {
    }

    /**
     *@Author:ShiYun;
     *@Description:获得转正日期
     *@Date: 17:45 2018\5\15 0015
     */
    public static String getZhuanzhengdate(String entrydate){
        String[] split = entrydate.split("/");
        if (Integer.parseInt(split[1]) + 2<10) {
            split[1] = Integer.parseInt(split[1]) + 2 + "";
        }else if (Integer.parseInt(split[1]) + 2>=10){
            split[1] = "0" + Integer.parseInt(split[1]) + 2 + "";
        }
        return split[0] + "/" + split[1] + "/" + split[2];
    }

    /**
     *@Author:ShiYun;
     *@Description:获得星座
     *@Date: 18:59 2018\5\12 0012
     */
    public static String getConstellation(String idcard){
        String ret = "";
        int month=Integer.parseInt(idcard.substring(10,12));
        int days=Integer.parseInt(idcard.substring(12,14));
        if((month==1&&days>=20)||(month==2&&days<=18))
        {
            ret="水瓶座";
        }
        else if((month==2&&days>=19)||(month==3&&days<=20))
        {
            ret="双鱼座";
        }
        else if((month==3&&days>=21)||(month==4&&days<=19))
        {
            ret="白羊座";
        }
        else if((month==4&&days>=20)||(month==5&&days<=20))
        {
            ret="金牛座";
        }
        else if((month==5&&days>=21)||(month==6&&days<=21))
        {
            ret="双子座";
        }
        else if((month==6&&days>=22)||(month==7&&days<=22))
        {
            ret="巨蟹座";
        }
        else if((month==7&&days>=23)||(month==8&&days<=22))
        {
            ret="狮子座";
        }
        else if((month==8&&days>=23)||(month==9&&days<=22))
        {
            ret="处女座";
        }
        else if((month==9&&days>=23)||(month==10&&days<=23))
        {
            ret="天秤座";
        }
        else if((month==10&&days>=24)||(month==11&&days<=22))
        {
            ret="天蝎座";
        }
        else if((month==11&&days>=23)||(month==12&&days<=21))
        {
            ret="射手座";
        }
        else if((month==12&&days>=21)||(month==1&&days<=19))
        {
            ret="摩羯座";
        }
        return ret;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得出生日期（"yyyy/MM/dd"）
     *@Date: 19:00 2018\5\12 0012
     */
    public static String getBirthday(String idcard){
        String birthday = idcard.substring(6,10) + "/" + idcard.substring(10,12) + "/" + idcard.substring(12,14);
        return birthday;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得属相
     *@Date: 19:08 2018\5\12 0012
     */
    public static String getChinesecs(String idcard){
        Integer year = Integer.parseInt(idcard.substring(6,10));
        String zodia[]={"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};
        int i=(year-4)%12;
        String value=zodia[i];
        return value;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得性别
     *@Date: 20:01 2018\5\12 0012
     */
    public static String getSex(String idcard) {
        Integer l = Integer.parseInt(idcard.substring(16,17));
        int i = l % 2;
        String sex = "";
        if(i==0){
            sex = "女";
        }else if(i==1){
            sex = "男";
        }
        return sex;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得年龄
     *@Date: 19:15 2018\5\16 0016
     */
    public static String getAge(String birthday) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date=new Date();
        java.util.Date mydate= myFormatter.parse(birthday);
        long day=(date.getTime()-mydate.getTime())/(24*60*60*1000) + 1;
        String year = day/365 + "";
        return year;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据年龄获得出生日期
     *@Date: 16:30 2018\5\22 0022
     */
    public static HashMap<String,String> getBirdayByAge(String age) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = new Date();
        long l = date1.getTime() - (365*Long.parseLong(age)  + Long.parseLong(age)/4)*(24*60*60*1000);
        long l2 = date1.getTime() - (365*(Long.parseLong(age)+1) - 1 + Long.parseLong(age)/4)*(24*60*60*1000);
        Date sdate = new Date(l);
        Date edate = new Date(l2);
        String format = myFormatter.format(sdate);
        String format2 = myFormatter.format(edate);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sbir",format2);
        hashMap.put("ebir",format);
        return hashMap;
    };

    /**
     *@Author:ShiYun;
     *@Description:获得工龄
     *@Date: 19:20 2018\5\16 0016
     */
    public static String getWorkingage(String firstworkingtime) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date=new Date();
        java.util.Date mydate= myFormatter.parse(firstworkingtime);
        long day=(date.getTime()-mydate.getTime())/(24*60*60*1000) + 1;
        String year = day/365 + "";
        return year;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据工龄获得首次工作时间
     *@Date: 16:43 2018\5\22 0022
     */
    public static HashMap<String,String> getFwtByWorkingage(String workingage){
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = new Date();
        long l = date1.getTime() - (365*Long.parseLong(workingage)  + Long.parseLong(workingage)/4)*(24*60*60*1000);
        long l2 = date1.getTime() - (365*(Long.parseLong(workingage)+1) - 1 + Long.parseLong(workingage)/4)*(24*60*60*1000);
        Date sdate = new Date(l);
        Date edate = new Date(l2);
        String format = myFormatter.format(sdate);
        String format2 = myFormatter.format(edate);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sfwt",format2);
        hashMap.put("efwt",format);
        return hashMap;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得司龄
     *@Date: 15:11 2018\5\17 0017
     */
    public static String getCompanyAge(String entrydate) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date=new Date();
        java.util.Date mydate= myFormatter.parse(entrydate);
        long day=(date.getTime()-mydate.getTime())/(24*60*60*1000) + 1;
        String year = day/365 + "";
        String month = (day - Long.parseLong(year) * 365) / 30 + "";
        return year + "年" + month + "月";
    }

    /**
     *@Author:ShiYun;
     *@Description:将数组转换成字符串
     *@Date: 19:56 2018\5\16 0016
     */
    public static String getArrayToString(List<String> strs,String s){
        String str = "";
        for(int i=0;i<strs.size();i++){
            str = str + strs.get(i);
            if(i<strs.size()-1){
                str = str + s;
            }
        }
        return str;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得合同年限
     *@Date: 10:06 2018\5\28 0028
     */
    public static String getContractage(String startdate,String enddate) throws ParseException {
        String str = "";
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Date sdate = myFormatter.parse(startdate);
        Date edate = myFormatter.parse(enddate);
        long day = (edate.getTime() - sdate.getTime()) / (24 * 60 * 60 * 1000) + 1;
        long year = day / 365;
        if(year>=1){
            str = str + year + "年";
        }
        long month = (day - year * 365) / 30;
        if(month>=1){
            str = str + month + "月";
        }
        return str;
    }

    /**
     *@Author:ShiYun;
     *@Description:将后台年限转换成String(Integer)类型
     *@Date: 13:56 2018\5\28 0028
     */
    public static String getContractage(String contractage){
        String str1 = "";
        String year = "";
        if (contractage!=null && !"".equals(contractage)) {
            if(contractage.indexOf("年")!=-1 && contractage.indexOf("月")!=-1){
                str1 = contractage.substring(0,contractage.indexOf("年"));
                year = Integer.parseInt(str1) + 1 + "";
            }
            if(contractage.indexOf("年")!=-1 && contractage.indexOf("月")==-1){
                str1 = contractage.substring(0,contractage.indexOf("年"));
                System.out.println(contractage.indexOf("年"));
                year = Integer.parseInt(str1) + "";
            }
            if(contractage.indexOf("年")==-1 && contractage.indexOf("月")!=-1){
                year = 1 + "";
            }
            if(contractage.indexOf("年")==-1 && contractage.indexOf("月")==-1){
                year = contractage;
            }
        }else {
            return null;
        }
        return year;
    }
}
