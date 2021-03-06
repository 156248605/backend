package com.elex.oa.util.hr_util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:通过身份证号码获得一些信息
 * @Date:Created in  18:52 2018\5\12 0012
 * @Modify By:
 */
@Service
public class IDcodeUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public IDcodeUtil() {
    }

    /**
     *@Author:ShiYun;
     *@Description:获得转正日期
     *@Date: 17:45 2018\5\15 0015
     */
    public String getZhuanzhengdate(String entrydate) {
        entrydate = entrydate.replace('-','/');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date entryDate = null;
        try {
            entryDate = sdf.parse(entrydate);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("时间格式不对！");
            return null;
        }
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
    public String getConstellation(String idcard){
        if(StringUtils.isBlank(idcard) || "null".equals(idcard))return null;
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
    public String getBirthday(String idcard) {
        if(StringUtils.isBlank(idcard) || "null".equals(idcard))return null;
        Integer month = Integer.valueOf(idcard.substring(10,12));
        if(month<=0 || month>12){
            logger.error("身份证的号码输入不符合标准，出生月必须在[1,12]之间！");
            return null;
        }
        Integer year = Integer.valueOf(idcard.substring(6,10));
        Integer maxDayOfMonth = getMaxDayOfMonth(year, month);
        Integer day_of_month = Integer.valueOf(idcard.substring(12,14));
        if(day_of_month<=0 || day_of_month>maxDayOfMonth){
            logger.error("身份证的号码输入不符合标准，出生日必须在[1,"+maxDayOfMonth+"]之间！");
            return null;
        }
        String birthday = idcard.substring(6,10) + "/" + idcard.substring(10,12) + "/" + idcard.substring(12,14);
        String stringAge = getAge(birthday);
        if(StringUtils.isBlank(stringAge)){
            logger.error("年龄不能为0岁");
            return null;
        }
        Integer age = Integer.valueOf(getAge(birthday).substring(0,getAge(birthday).length()-1));
        if(age>65 || age<18) {
            logger.error("身份证的号码输入不符合标准，年龄必须18-65周岁之间");
            return null;
        }
        return birthday;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据年和月获得当前月的最大天数
     *@Date: 15:08 2018\9\10 0010
     */
    public Integer getMaxDayOfMonth(Integer year,Integer month){
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
    public String getChinesecs(String idcard){
        if(StringUtils.isBlank(idcard) || "null".equals(idcard))return null;
        int checkYear = Integer.parseInt(idcard.substring(6, 10));
        if(checkYear<=1899 || checkYear>=2050){
            logger.error("输入的身份证号码错误（年龄必须在1900到2049之间）！");
            return null;
        }
        String s = null;
        try {
            s = CalendarUtil.solarToLunar(idcard.substring(6, 14)).split(":")[1];
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("日期转换错误！");
            return null;
        }
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
    public String getSex(String idcard) {
        if(StringUtils.isBlank(idcard.trim()) || "null".equals(idcard))return null;
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
    public String getAge(String birthday){
        if(StringUtils.isBlank(birthday))return null;
        DateCompute curDateCompute = new DateCompute();
        DateCompute entryDateCompute = new DateCompute(birthday);
        DateCompute respDC = curDateCompute.getDateComputeByAnother(entryDateCompute);
        String year = respDC.getYear().intValue()<=0?"":respDC.getYear() + "岁";
        return year;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据时间推算出据现在是多长时间
     *@Date: 17:17 2018\9\10 0010
     */
    private Calendar getCalendarByDate(String birthday,String newday)throws Exception{
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
    public HashMap<String,String> getBirdayByAge(String age) {
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
    public String getWorkingage(String firstworkingtime) {
        DateCompute curDateCompute = new DateCompute();
        DateCompute entryDateCompute = new DateCompute(firstworkingtime);
        DateCompute respDC = curDateCompute.getDateComputeByAnother(entryDateCompute);
        if(null==respDC)return null;
        String year = respDC.getYear().intValue()<=0?"":respDC.getYear() + "年";
        String month = respDC.getMonth().intValue()<=0?"":respDC.getMonth() + "月";
        return year + month;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据工龄获得首次工作时间
     *@Date: 16:43 2018\5\22 0022
     */
    public HashMap<String,String> getFwtByWorkingage(String workingage) throws ParseException {
        return getBirdayByAge(workingage);
    }

    /**
     *@Author:ShiYun;
     *@Description:获得司龄
     *@Date: 15:11 2018\5\17 0017
     */
    public String getCompanyAge(String entrydate) {
        DateCompute curDateCompute = new DateCompute();
        DateCompute entryDateCompute = new DateCompute(entrydate);
        DateCompute respDC = curDateCompute.getDateComputeByAnother(entryDateCompute);
        String year = respDC.getYear().intValue()<=0?"":respDC.getYear() + "年";
        String month = respDC.getMonth().intValue()<=0?"":respDC.getMonth() + "月";
        return year + month;
    }

    /**
     *@Author:ShiYun;
     *@Description:将数组转换成字符串
     *@Date: 19:56 2018\5\16 0016
     */
    public String getArrayToString(List<String> strs,String s){
        if(null==strs)return null;
        String str = "";
        for(int i=0;i<strs.size();i++){
            str = str + strs.get(i);
            if(i<strs.size()-1){
                str = str + s;
            }
        }
        return str;
    }
    public String getListIntegerToString(List<Integer> strs,String s){
        if(null==strs)return null;
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
    public String getContractage(String startdate,String enddate){
        DateCompute dateComputeStart = new DateCompute(startdate);
        DateCompute dateComputeEnd = new DateCompute(enddate);
        DateCompute respDateCompute = dateComputeStart.getDateComputeByAnother(dateComputeEnd);
        if(null==respDateCompute)return null;
        String respStr = "";
        if(null != respDateCompute.getYear()){
            respStr = respStr + respDateCompute.getYear() + "年";
        }
        if(null != respDateCompute.getMonth()){
            respStr = respStr + respDateCompute.getMonth() + "月";
        }
        if(null != respDateCompute.getDay()){
            respStr = respStr + respDateCompute.getDay() + "天";
        }
        return respStr;
    }

    /**
     *@Author:ShiYun;
     *@Description:将后台年限转换成String(Integer)类型
     *@Date: 13:56 2018\5\28 0028
     */
    public String getContractage(String contractage){
        return contractage;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据时间获得当月的头尾日期
     *@Date: 15:50 2018\8\2 0002
     */
    public Map getFirstAndLastDate(Date date){
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
    public Date getMaxDate(Date date) throws ParseException {
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
    public Integer getDaysByDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getMaxDayOfMonth(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据时间获得天
     *@Date: 11:43 2018\8\3 0003
     */
    public Integer getMonthByDate(String str){
        String[] split = str.split("/|-");
        Integer day = Integer.parseInt(split[2].substring(0,2));
        return day;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据身份证号码获得其所在的省份/城市/区县(因为会报Java代码太长错误，所以要分割方法)
     *@Date: 11:04 2018\9\18 0018
     */
    public String getProvinceByIdcode(String idcode){
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
    public String getIdcode1and2ByProvincename(String proviceName){
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

    public List<String> getStringToListString(String post_list, String s) {
        if(null==post_list)return null;
        String[] strArr = post_list.split(s);
        if(null==strArr || strArr.length==0)return null;
        List<String> strList = new ArrayList<>();
        for (String str:strArr
             ) {
            strList.add(str);
        }
        return strList;
    }

    //时间计算辅助类
    private class DateCompute{
        private Integer year;
        private Integer month;
        private Integer day;
        private String strDay="";

        public DateCompute() {
            Calendar curCal = Calendar.getInstance();
            this.year = curCal.get(Calendar.YEAR);
            this.month = curCal.get(Calendar.MONTH);
            this.day = curCal.get(Calendar.DAY_OF_MONTH);
        }

        public DateCompute(Integer year, Integer month, Integer day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public DateCompute(String strDay) {
            this.strDay = strDay;
            setByStringDate(strDay);
        }

        public String getStrDay() {
            return strDay;
        }

        public void setStrDay(String strDay) {
            this.strDay = strDay;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Integer getMonth() {
            return month;
        }

        public void setMonth(Integer month) {
            this.month = month;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public DateCompute getDateComputeByAnother(DateCompute dateCompute){
            if(null==dateCompute||null==dateCompute.getYear()||null==dateCompute.getMonth()||null==dateCompute.getDay())return null;//空值不做处理
            DateCompute bigDC = null;
            DateCompute smallDC = null;
            Integer companParam = this.compareToAnother(dateCompute);
            if(companParam==0)return new DateCompute(0,0,0);
            if(companParam==-1){
                bigDC = dateCompute;
                smallDC = this;
            }
            if(companParam==1){
                bigDC = this;
                smallDC = dateCompute;
            }
            DateCompute respDateCompute = computeDateByTowObjs(bigDC, smallDC);
            return respDateCompute;
        }

        private void setByStringDate(String date){
            if(StringUtils.isBlank(date))return;
            try {
                //设置时间格式
                SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy/MM/dd");
                //获得出生日期
                Date mydate= myFormatter.parse(date);
                Calendar birCal = Calendar.getInstance();
                birCal.setTime(mydate);
                this.setYear(birCal.get(Calendar.YEAR));
                this.setMonth(birCal.get(Calendar.MONTH));
                this.setDay(birCal.get(Calendar.DAY_OF_MONTH));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        private DateCompute computeDateByTowObjs(DateCompute bigDC,DateCompute smallDC){
            if(null==bigDC||null==bigDC.getYear()||null==bigDC.getMonth()||null==bigDC.getDay()||null==smallDC||null==smallDC.getYear()||null==smallDC.getMonth()||null==smallDC.getDay())return null;
            DateCompute respDateCompute = new DateCompute();

            int bigDC_year = bigDC.getYear();
            int bigDC_month = bigDC.getMonth();
            int bigDC_day = bigDC.getDay();
            int smallDC_year = smallDC.getYear();
            int smallDC_month = smallDC.getMonth();
            int smallDC_day = smallDC.getDay();

            //先计算天数
            if(bigDC_day>=smallDC_day){
                respDateCompute.setDay(bigDC_day-smallDC_day);
            }else {
                respDateCompute.setDay(bigDC_day+30-smallDC_day);
                bigDC_month -=1;
            }
            //再计算月数
            if(bigDC_month>=smallDC_month){
                respDateCompute.setMonth(bigDC_month-smallDC_month);
            }else {
                respDateCompute.setMonth(bigDC_month+12-smallDC_month);
                bigDC_year -=1;
            }
            respDateCompute.setYear(bigDC_year-smallDC_year);
            return respDateCompute;
        }

        private Integer compareToAnother(DateCompute dateCompute){
            Integer year = dateCompute.getYear();
            Integer month = dateCompute.getMonth();
            Integer day = dateCompute.getDay();
            int absYear = this.year - year;
            int absMonth = this.month - month;
            int absDay = this.day - day;
            //先比年
            if(absYear>0){
                return 1;
            }else if(absYear<0){
                return -1;
            }else {
                //再比月
                if(absMonth>0){
                    return 1;
                }else if(absMonth<0){
                    return -1;
                }else {
                    //再比天
                    if(absDay>0){
                        return 1;
                    }else if(absDay<0){
                        return -1;
                    }else {
                        return 0;
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "DateCompute{" +
                    "year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    '}';
        }
    }
}
