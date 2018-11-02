package com.elex.oa.util.util_shiyun;

import org.apache.commons.lang.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public static String getZhuanzhengdate(String entrydate) throws ParseException {
        entrydate = entrydate.replace('-','/');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date entryDate = sdf.parse(entrydate);
        Calendar entryCal = Calendar.getInstance();
        entryCal.setTime(entryDate);
        entryCal.add(Calendar.MONTH,2);
        return sdf.format(entryCal.getTime());
    }

    /**
     *@Author:ShiYun;
     *@Description:根据身份证号码获得星座
     *@Date: 18:59 2018\5\12 0012
     */
    public static String getConstellation(String idcard){
        String ret = "";
        int month=Integer.parseInt(idcard.substring(10,12));
        int days=Integer.parseInt(idcard.substring(12,14));
        if((month==1&&days>=20)||(month==2&&days<=18)){ret="水瓶座";}
        else if((month==2&&days>=19)||(month==3&&days<=20)){ret="双鱼座";}
        else if((month==3&&days>=21)||(month==4&&days<=19)){ret="白羊座";}
        else if((month==4&&days>=20)||(month==5&&days<=20)){ret="金牛座";}
        else if((month==5&&days>=21)||(month==6&&days<=21)){ret="双子座";}
        else if((month==6&&days>=22)||(month==7&&days<=22)){ret="巨蟹座";}
        else if((month==7&&days>=23)||(month==8&&days<=22)){ret="狮子座";}
        else if((month==8&&days>=23)||(month==9&&days<=22)){ret="处女座";}
        else if((month==9&&days>=23)||(month==10&&days<=23)){ret="天秤座";}
        else if((month==10&&days>=24)||(month==11&&days<=22)){ret="天蝎座";}
        else if((month==11&&days>=23)||(month==12&&days<=21)){ret="射手座";}
        else if((month==12&&days>=21)||(month==1&&days<=19)){ret="摩羯座";}
        return ret;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得出生日期（"yyyy/MM/dd"）
     *@Date: 19:00 2018\5\12 0012
     */
    public static String getBirthday(String idcard) throws Exception {
        System.out.println(idcard+"==============================================================================================================");
        Integer month = Integer.valueOf(idcard.substring(10,12));
        if(month<=0 || month>12)throw new Exception("身份证的号码输入不符合标准，出生月必须在[1,12]之间！");
        Integer year = Integer.valueOf(idcard.substring(6,10));
        Integer maxDayOfMonth = IDcodeUtil.getMaxDayOfMonth(year, month);
        Integer day_of_month = Integer.valueOf(idcard.substring(12,14));
        if(day_of_month<=0 || day_of_month>maxDayOfMonth)throw new Exception("身份证的号码输入不符合标准，出生日必须在[1,"+maxDayOfMonth+"]之间！");
        String birthday = idcard.substring(6,10) + "/" + idcard.substring(10,12) + "/" + idcard.substring(12,14);
        Integer age = Integer.valueOf(IDcodeUtil.getAge(birthday));
        if(age>65 || age<18)throw new Exception("身份证的号码输入不符合标准，年龄必须18-65周岁之间");
        return birthday;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据年和月获得当前月的最大天数
     *@Date: 15:08 2018\9\10 0010
     */
    public static Integer getMaxDayOfMonth(Integer year,Integer month){
        if(",1,3,5,7,8,10,12,".indexOf(","+month+",")!=-1)return 31;
        if(",4,6,9,11,".indexOf(","+month+",")!=-1)return 30;
        if(year%4!=0 || (year%4==0 && year%100==0 && year%400!=0))return 28;
        return 29;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得属相
     *@Date: 19:08 2018\5\12 0012
     */
    public static String getChinesecs(String idcard) throws Exception {
        int checkYear = Integer.parseInt(idcard.substring(6, 10));
        if(checkYear<=1899 || checkYear>=2050){
            throw new Exception("输入的身份证号码错误（年龄必须在1900到2049之间）！");
        }
        String s = CalendarUtil.solarToLunar(idcard.substring(6, 14)).split(":")[1];
        Integer year = Integer.parseInt(s.substring(0,4));
        String zodia[]={"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};
        int i=(year-1900)%12;//注：1900年是鼠年
        String value=zodia[i];
        return value;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得性别
     *@Date: 20:01 2018\5\12 0012
     */
    public static String getSex(String idcard) {
        if(idcard==null && "".equals(idcard)){
            return "";
        }
        try {
            return (Integer.parseInt(idcard.substring(16,17)) % 2)==0?"女":"男";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:获得年龄
     *@Date: 19:15 2018\5\16 0016
     */
    public static String getAge(String birthday) throws Exception {
        if(StringUtils.isBlank(birthday) || birthday.equals("null")){
            //throw new Exception("出生日期有误！");
            return  "";
        }
        System.out.println(birthday+"==============================================================================");
        Calendar curCal = IDcodeUtil.getCalendarByDate(birthday,null);
        //定义年龄
        Integer ny = null;
        //获得年龄
        ny = curCal.get(Calendar.YEAR);
        return ny+"";
    }

    /**
     *@Author:ShiYun;
     *@Description:根据时间推算出据现在是多长时间
     *@Date: 17:17 2018\9\10 0010
     */
    private static Calendar getCalendarByDate(String birthday,String newday)throws Exception{
        //设置时间格式
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
        //获得当前时间
        Calendar curCal = Calendar.getInstance();
        if (newday!=null) {
            curCal.setTime(myFormatter.parse(newday));
        }
        curCal.set(Calendar.HOUR_OF_DAY, 0);
        curCal.set(Calendar.MINUTE, 0);
        curCal.set(Calendar.SECOND, 0);
        curCal.set(Calendar.MILLISECOND, 0);
        //获得出生日期
        System.out.println(birthday+"=====================================================================================");
        Date mydate= myFormatter.parse(birthday);
        Calendar birCal = Calendar.getInstance();
        birCal.setTime(mydate);
        //获得出生日期的年月日
        Integer by = birCal.get(Calendar.YEAR);
        Integer bm = birCal.get(Calendar.MONTH);
        Integer bd = birCal.get(Calendar.DAY_OF_MONTH);
        //在当前时间的基础上减去出生日期的年、月、日
        curCal.add(Calendar.DAY_OF_MONTH,-bd);
        curCal.add(Calendar.MONTH,-bm);
        curCal.add(Calendar.YEAR,-by);
        return curCal;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据年龄获得出生日期
     *@Date: 16:30 2018\5\22 0022
     */
    public static HashMap<String,String> getBirdayByAge(String age) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Calendar curCal = Calendar.getInstance();
        curCal.add(Calendar.YEAR,-Integer.parseInt(age));
        String format = myFormatter.format(curCal.getTime());
        curCal.add(Calendar.YEAR,-1);
        curCal.add(Calendar.DAY_OF_MONTH,1);
        String format2 = myFormatter.format(curCal.getTime());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sbir",format2);
        hashMap.put("ebir",format);
        return hashMap;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得工龄
     *@Date: 19:20 2018\5\16 0016
     */
    public static String getWorkingage(String firstworkingtime) throws Exception {
        System.out.println(firstworkingtime+"========================================================================");
        return  IDcodeUtil.getAge(firstworkingtime);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据工龄获得首次工作时间
     *@Date: 16:43 2018\5\22 0022
     */
    public static HashMap<String,String> getFwtByWorkingage(String workingage) throws ParseException {
        return IDcodeUtil.getBirdayByAge(workingage);
    }

    /**
     *@Author:ShiYun;
     *@Description:获得司龄
     *@Date: 15:11 2018\5\17 0017
     */
    public static String getCompanyAge(String entrydate) throws Exception {
        Calendar curCal = IDcodeUtil.getCalendarByDate(entrydate,null);
        return curCal.get(Calendar.YEAR) + "年" + curCal.get(Calendar.MONTH) + "月";
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
    public static String getContractage(String startdate,String enddate) throws Exception {
        Calendar cal = IDcodeUtil.getCalendarByDate(startdate, enddate);
        Integer year = cal.get(Calendar.YEAR);
        Integer month = cal.get(Calendar.MONTH)+1;
        return year+(year>0?"年":"")+month+(month>0?"月":"");
    }

    /**
     *@Author:ShiYun;
     *@Description:将后台年限转换成String(Integer)类型
     *@Date: 13:56 2018\5\28 0028
     */
    public static String getContractage(String contractage){
        /*String str1 = "";
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
        }*/
        return contractage;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据时间获得当月的头尾日期
     *@Date: 15:50 2018\8\2 0002
     */
    public static Map getFirstAndLastDate(Date date) throws ParseException{
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //获得某月的第一天
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),1,0,0,0);
        Date firstDate = cal.getTime();
        //获得某月的最后一天
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();
        Map map = new HashMap<String,Object>();
        map.put("firstDate", firstDate);
        map.put("lastDate", lastDate);
       return map;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据日期获得其最大时间(有问题已经不用这个方法了)
     *@Date: 9:29 2018\8\3 0003
     */
    public static Date getMaxDate(Date date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String format = simpleDateFormat.format(date);
        String[] split = format.split("/");
        String str = split[0] + "/" + split[1] + "/" + split[2].substring(0, 2) + " 23:59:59";
        Date parse = simpleDateFormat.parse(str);
        return parse;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据日期获得某月的天数
     *@Date: 11:24 2018\8\3 0003
     */
    public static Integer getDaysByDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return IDcodeUtil.getMaxDayOfMonth(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH));
    }

    /**
     *@Author:ShiYun;
     *@Description:根据时间获得天
     *@Date: 11:43 2018\8\3 0003
     */
    public static Integer getMonthByDate(String str){
        String[] split = str.split("/|-");
        Integer day = Integer.parseInt(split[2].substring(0,2));
        return day;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据身份证号码获得其所在的省份/城市/区县(因为会报Java代码太长错误，所以要分割方法)
     *@Date: 11:04 2018\9\18 0018
     */
    public static String getProvinceByIdcode(String idcode){
        String provinceCode = idcode.substring(0,2);
        String cityCode = idcode.substring(2,4);
        String countyCode = idcode.substring(4,6);
        String returnValue = GetProvinceMethod.getProvinceInformationsByCode(provinceCode, cityCode, countyCode);
        String[] strs = returnValue.split("/");
        String provinceName = strs[0];
        String cityName = strs[1];
        String countyName = strs[2];
        if(provinceName.equals("(身份证号码1-2位有误)")){
            try {
                throw new MyException(provinceName);
            } catch (MyException e) {
                e.printStackTrace();
            }
        }else if(cityName.equals("(身份证号码3-4位有误)")){
            try {
                throw new MyException(cityName);
            } catch (MyException e) {
                e.printStackTrace();
            }
        }else if(countyName.equals("(身份证号码5-6位有误)")){
            try {
                throw new MyException(countyName);
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
        return provinceName+cityName+countyName;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据其所在的身份获得其身份证号码的前两位
     *@Date: 11:24 2018\9\18 0018
     */
    public static String getIdcode1and2ByProvincename(String proviceName){
        String provinceCode = "";
        switch (proviceName){
            case "北京市":provinceCode="11";break;case "天津市":provinceCode="12";break;case "河北省":provinceCode="13";break;case "山西省":provinceCode="14";break;case "内蒙古自治区":provinceCode="15";break;
            case "辽宁省":provinceCode="21";break;case "吉林省":provinceCode="22";break;case "黑龙江省":provinceCode="23";break;case "上海市":provinceCode="31";break;case "江苏省":provinceCode="32";break;
            case "浙江省":provinceCode="33";break;case "安徽省":provinceCode="34";break;case "福建省":provinceCode="35";break;case "江西省":provinceCode="36";break;case "山东省":provinceCode="37";break;
            case "河南省":provinceCode="41";break;case "湖北省":provinceCode="42";break;case "湖南省":provinceCode="43";break;case "广东省":provinceCode="44";break;case "广西壮族自治区":provinceCode="45";break;
            case "海南省":provinceCode="46";break;case "重庆市":provinceCode="50";break;case "四川省":provinceCode="51";break;case "贵州省":provinceCode="52";break;case "云南省":provinceCode="53";break;
            case "西藏自治区":provinceCode="54";break;case "陕西省":provinceCode="61";break;case "甘肃省":provinceCode="62";break;case "青海省":provinceCode="63";break;case "宁夏回族自治区":provinceCode="64";break;
            case "新疆维吾尔自治区":provinceCode="65";break;case "台湾省":provinceCode="71";break;case "香港特别行政区":provinceCode="81";break;case "澳门特别行政区":provinceCode="82";break;
            default:provinceCode="--";break;
        }
        return provinceCode;
    }


}
