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
        return (Integer.parseInt(idcard.substring(16,17)) % 2)==0?"女":"男";
    }

    /**
     *@Author:ShiYun;
     *@Description:获得年龄
     *@Date: 19:15 2018\5\16 0016
     */
    public static String getAge(String birthday) throws Exception {
        if(StringUtils.isBlank(birthday) || birthday.equals("null")){
            throw new Exception("出生日期有误！");
        }
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
     *@Description:根据身份证号码获得其所在的省份/城市/区县
     *@Date: 11:04 2018\9\18 0018
     */
    /*public static String getProvinceByIdcode(String idcode){
        String provinceCode = idcode.substring(0,2);
        String cityCode = idcode.substring(2,4);
        String countyCode = idcode.substring(4,6);
        String provinceName = "";
        String cityName = "";
        String countyName = "";
        switch (provinceCode){
            //北京市的身份证前六位对应关系==============================================================================
            case "11":{
                provinceName="北京市";
                switch (cityCode){
                    case "01":{
                        cityName="--";
                        switch (countyCode){
                            case "01":countyName="东城区";  break;
                            case "02":countyName="西城区";  break;
                            case "03":countyName="崇文区";  break;
                            case "04":countyName="宣武区";  break;
                            case "05":countyName="朝阳区";  break;
                            case "06":countyName="丰台区";  break;
                            case "07":countyName="石景山区";break;
                            case "08":countyName="海淀区";  break;
                            case "09":countyName="门头沟区";break;
                            case "10":countyName="燕山区";  break;
                            case "11":countyName="房山区";  break;
                            case "12":countyName="通州区";  break;
                            case "13":countyName="顺义区";  break;
                            case "14":countyName="昌平区";  break;
                            case "15":countyName="大兴区";  break;
                            case "16":countyName="怀柔区";  break;
                            case "17":countyName="平谷区";  break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    case "02":{
                        cityName="--";
                        switch (countyCode){
                            case "21":countyName="昌平县";  break;
                            case "22":countyName="顺义县";  break;
                            case "23":countyName="通县";    break;
                            case "24":countyName="大兴县";  break;
                            case "25":countyName="房山县";  break;
                            case "26":countyName="平谷县";  break;
                            case "27":countyName="怀柔县";  break;
                            case "28":countyName="密云县";  break;
                            case "29":countyName="延庆县";  break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //天津市的身份证前六位对应关系==============================================================================
            case "12":{
                provinceName = "天津市";
                switch (cityCode){
                    case "01":{
                        cityName="--";
                        switch (countyCode){
                            case "01":countyName="和平区";break;
                            case "02":countyName="河东区";break;
                            case "03":countyName="河西区";break;
                            case "04":countyName="南开区";break;
                            case "05":countyName="河北区";break;
                            case "06":countyName="红桥区";break;
                            case "07":countyName="塘沽区";break;
                            case "08":countyName="汉沽区";break;
                            case "09":countyName="大港区";break;
                            case "10":countyName="东丽区";break;
                            case "11":countyName="西青区";break;
                            case "12":countyName="津南区";break;
                            case "13":countyName="北辰区";break;
                            case "14":countyName="武清区";break;
                            case "15":countyName="宝坻区";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    case "02":{
                        cityName="--";
                        switch (countyCode){
                            case "21":countyName="宁河县";break;
                            case "22":countyName="武清县";break;
                            case "23":countyName="静海县";break;
                            case "24":countyName="宝坻县";break;
                            case "25":countyName="蓟县";  break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //河北省的身份证前六位对应关系==============================================================================
            case "13":{
                provinceName="河北省";
                switch (cityCode){
                    //河北省石家庄市的**********
                    case "01":{
                        cityName="石家庄市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="长安区";break;
                            case "03":countyName="桥东区";break;
                            case "04":countyName="桥西区";break;
                            case "05":countyName="新华区";break;
                            case "06":countyName="郊区";break;
                            case "07":countyName="井陉矿区";break;
                            case "08":countyName="裕华区";break;
                            case "21":countyName="井陉县";break;
                            case "22":countyName="获鹿县";break;
                            case "23":countyName="正定县";break;
                            case "24":countyName="栾城县";break;
                            case "25":countyName="行唐县";break;
                            case "26":countyName="灵寿县";break;
                            case "27":countyName="高邑县";break;
                            case "28":countyName="深泽县";break;
                            case "29":countyName="赞皇县";break;
                            case "30":countyName="无极限";break;
                            case "31":countyName="平山县";break;
                            case "32":countyName="元氏县";break;
                            case "33":countyName="赵县";break;
                            case "81":countyName="辛集县";break;
                            case "82":countyName="藁城市";break;
                            case "83":countyName="晋州市";break;
                            case "84":countyName="新乐市";break;
                            case "85":countyName="鹿泉市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省唐山市的**********
                    case "02":{
                        cityName="唐山市";
                        switch (countyCode){
                            case "01":countyName="市辖市";break;
                            case "02":countyName="路南区";break;
                            case "03":countyName="路北区";break;
                            case "04":countyName="古冶区";break;
                            case "05":countyName="开平区";break;
                            case "06":countyName="新区";break;
                            case "07":countyName="丰南区";break;
                            case "08":countyName="丰润区";break;
                            case "21":countyName="丰润县";break;
                            case "22":countyName="丰南县";break;
                            case "23":countyName="滦县";break;
                            case "24":countyName="滦南县";break;
                            case "25":countyName="乐亭县";break;
                            case "26":countyName="迁安县";break;
                            case "27":countyName="迁西县";break;
                            case "28":countyName="遵化县";break;
                            case "29":countyName="玉田县";break;
                            case "30":countyName="唐海县";break;
                            case "81":countyName="遵化市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省秦皇岛市的**********
                    case "03":{
                        cityName="秦皇岛市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="海港区";break;
                            case "03":countyName="山海关区";break;
                            case "04":countyName="北戴河区";break;
                            case "21":countyName="青龙满族自治县";break;
                            case "22":countyName="昌黎县";break;
                            case "23":countyName="抚宁县";break;
                            case "24":countyName="卢龙县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省邯郸市的**********
                    case "04":{
                        cityName="邯郸市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="邯山区";break;
                            case "03":countyName="丛台区";break;
                            case "04":countyName="复兴区";break;
                            case "05":countyName="郊区";break;
                            case "06":countyName="峰峰矿区";break;
                            case "21":countyName="邯郸县";break;
                            case "22":countyName="武安县";break;
                            case "23":countyName="临漳县";break;
                            case "24":countyName="成安县";break;
                            case "25":countyName="大名县";break;
                            case "26":countyName="涉县";break;
                            case "27":countyName="磁县";break;
                            case "28":countyName="肥乡县";break;
                            case "29":countyName="永年县";break;
                            case "30":countyName="邱县";break;
                            case "31":countyName="鸡泽县";break;
                            case "32":countyName="广平县";break;
                            case "33":countyName="馆陶县";break;
                            case "34":countyName="魏县";break;
                            case "35":countyName="曲周县";break;
                            case "81":countyName="武安市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省邢台市的**********
                    case "05":{
                        cityName="邢台市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="桥东区";break;
                            case "03":countyName="桥西区";break;
                            case "21":countyName="邢台县";break;
                            case "22":countyName="临城县";break;
                            case "23":countyName="内丘县";break;
                            case "24":countyName="柏乡县";break;
                            case "25":countyName="隆尧县";break;
                            case "26":countyName="任县";break;
                            case "27":countyName="南和县";break;
                            case "28":countyName="宁晋县";break;
                            case "29":countyName="巨鹿县";break;
                            case "30":countyName="新和县";break;
                            case "31":countyName="广宗县";break;
                            case "32":countyName="平乡县";break;
                            case "33":countyName="威县";break;
                            case "34":countyName="清河县";break;
                            case "35":countyName="临西县";break;
                            case "81":countyName="南宫市";break;
                            case "82":countyName="沙河市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省保定市的**********
                    case "06":{
                        cityName="保定市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="新市区";break;
                            case "03":countyName="北市区";break;
                            case "04":countyName="南市区";break;
                            case "21":countyName="满城县";break;
                            case "22":countyName="清苑县";break;
                            case "23":countyName="涞水县";break;
                            case "24":countyName="阜平县";break;
                            case "25":countyName="徐水县";break;
                            case "26":countyName="定兴县";break;
                            case "27":countyName="唐县";break;
                            case "28":countyName="高阳县";break;
                            case "29":countyName="容城县";break;
                            case "30":countyName="涞源县";break;
                            case "31":countyName="望都县";break;
                            case "32":countyName="安新县";break;
                            case "33":countyName="易县";break;
                            case "34":countyName="曲阳县";break;
                            case "35":countyName="蠡县";break;
                            case "36":countyName="顺平县";break;
                            case "37":countyName="博野县";break;
                            case "38":countyName="雄县";break;
                            case "81":countyName="涿州市";break;
                            case "82":countyName="定州市";break;
                            case "83":countyName="安国市";break;
                            case "84":countyName="高碑店市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省张家口市的**********
                    case "07":{
                        cityName="张家口市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="桥东区";break;
                            case "03":countyName="桥西区";break;
                            case "05":countyName="宣化区";break;
                            case "06":countyName="下花园区";break;
                            case "21":countyName="宣化县";break;
                            case "22":countyName="张北县";break;
                            case "23":countyName="康保县";break;
                            case "24":countyName="沽源县";break;
                            case "25":countyName="尚义县";break;
                            case "26":countyName="蔚县";break;
                            case "27":countyName="阳原县";break;
                            case "28":countyName="怀安县";break;
                            case "29":countyName="万全县";break;
                            case "30":countyName="怀来县";break;
                            case "31":countyName="涿鹿县";break;
                            case "32":countyName="赤城县";break;
                            case "33":countyName="崇礼县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省承德市的**********
                    case "08":{
                        cityName="承德市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="双桥区";break;
                            case "03":countyName="双滦区";break;
                            case "04":countyName="鹰手营子矿区";break;
                            case "21":countyName="承德县";break;
                            case "22":countyName="兴隆县";break;
                            case "23":countyName="平原县";break;
                            case "24":countyName="滦平县";break;
                            case "25":countyName="隆化县";break;
                            case "26":countyName="丰宁满族自治县";break;
                            case "27":countyName="宽城满族自治县";break;
                            case "28":countyName="围场满族蒙古族自治县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省沧州市的**********
                    case "09":{
                        cityName="沧州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="新华区";break;
                            case "03":countyName="运河区";break;
                            case "04":countyName="郊区";break;
                            case "21":countyName="沧县";break;
                            case "22":countyName="青县";break;
                            case "23":countyName="东光县";break;
                            case "24":countyName="海兴县";break;
                            case "25":countyName="盐山县";break;
                            case "26":countyName="肃宁县";break;
                            case "27":countyName="南皮县";break;
                            case "28":countyName="吴桥县";break;
                            case "29":countyName="献县";break;
                            case "30":countyName="孟村回族自治县";break;
                            case "81":countyName="泊头市";break;
                            case "82":countyName="任丘市";break;
                            case "83":countyName="黄骅市";break;
                            case "84":countyName="河间市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省廊坊市的**********
                    case "10":{
                        cityName="廊坊市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="安次区";break;
                            case "03":countyName="广阳区";break;
                            case "21":countyName="三河县";break;
                            case "22":countyName="固安县";break;
                            case "23":countyName="永清县";break;
                            case "24":countyName="香河县";break;
                            case "25":countyName="大城县";break;
                            case "26":countyName="文安县";break;
                            case "27":countyName="霸县";break;
                            case "28":countyName="大厂回族自治县";break;
                            case "29":countyName="献县";break;
                            case "81":countyName="霸州市";break;
                            case "82":countyName="三河市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省衡水市的**********
                    case "11":{
                        cityName="衡水市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="桃城区";break;
                            case "21":countyName="枣强县";break;
                            case "22":countyName="武邑县";break;
                            case "23":countyName="武强县";break;
                            case "24":countyName="饶阳县";break;
                            case "25":countyName="安平县";break;
                            case "26":countyName="故城县";break;
                            case "27":countyName="景县";break;
                            case "28":countyName="阜城县";break;
                            case "81":countyName="冀州市";break;
                            case "82":countyName="深州市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省邯郸地区的**********
                    case "21":{
                        cityName="邯郸地区";
                        switch (countyCode){
                            case "01":countyName="邯郸市";break;
                            case "21":countyName="大名县";break;
                            case "22":countyName="魏县";break;
                            case "23":countyName="曲周县";break;
                            case "24":countyName="邱县";break;
                            case "25":countyName="鸡泽县";break;
                            case "26":countyName="肥乡县";break;
                            case "27":countyName="广平县";break;
                            case "28":countyName="成安县";break;
                            case "29":countyName="临漳县";break;
                            case "30":countyName="磁县";break;
                            case "31":countyName="武安县";break;
                            case "32":countyName="涉县";break;
                            case "33":countyName="永年县";break;
                            case "34":countyName="邯郸县";break;
                            case "35":countyName="馆陶县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省邢台地区的**********
                    case "22":{
                        cityName="邢台地区";
                        switch (countyCode){
                            case "01":countyName="南宫市";break;
                            case "02":countyName="沙河市";break;
                            case "21":countyName="邢台县";break;
                            case "22":countyName="沙河县";break;
                            case "23":countyName="临城县";break;
                            case "24":countyName="内丘县";break;
                            case "25":countyName="柏乡县";break;
                            case "26":countyName="隆尧县";break;
                            case "27":countyName="任县";break;
                            case "28":countyName="南和县";break;
                            case "29":countyName="宁晋县";break;
                            case "30":countyName="南宫县";break;
                            case "31":countyName="巨鹿县";break;
                            case "32":countyName="新河县";break;
                            case "33":countyName="广宗县";break;
                            case "34":countyName="平乡县";break;
                            case "35":countyName="威县";break;
                            case "36":countyName="清河县";break;
                            case "37":countyName="临西县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省石家庄市的**********
                    case "23":{
                        cityName="石家庄市";
                        switch (countyCode){
                            case "01":countyName="辛集市";break;
                            case "02":countyName="藁城市";break;
                            case "21":countyName="束鹿县";break;
                            case "22":countyName="晋县";break;
                            case "23":countyName="深泽县";break;
                            case "24":countyName="无极县";break;
                            case "25":countyName="藁城县";break;
                            case "26":countyName="赵县";break;
                            case "27":countyName="栾城县";break;
                            case "28":countyName="正定县";break;
                            case "29":countyName="新乐县";break;
                            case "30":countyName="高邑县";break;
                            case "31":countyName="元氏县";break;
                            case "32":countyName="赞皇县";break;
                            case "33":countyName="井陉县";break;
                            case "34":countyName="获鹿县";break;
                            case "35":countyName="平山县";break;
                            case "36":countyName="灵寿县";break;
                            case "37":countyName="行唐县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省保定地区的**********
                    case "24":{
                        cityName="保定地区";
                        switch (countyCode){
                            case "01":countyName="定州市";break;
                            case "02":countyName="涿州市";break;
                            case "03":countyName="安国市";break;
                            case "04":countyName="高碑店市";break;
                            case "21":countyName="易县";break;
                            case "22":countyName="满城县";break;
                            case "23":countyName="徐水县";break;
                            case "24":countyName="涞源县";break;
                            case "25":countyName="定兴县";break;
                            case "26":countyName="完县";break;
                            case "27":countyName="唐县";break;
                            case "28":countyName="望都县";break;
                            case "29":countyName="涞水县";break;
                            case "30":countyName="涿县";break;
                            case "31":countyName="清苑县";break;
                            case "32":countyName="高阳县";break;
                            case "33":countyName="安新县";break;
                            case "34":countyName="雄县";break;
                            case "35":countyName="容城县";break;
                            case "36":countyName="新城县";break;
                            case "37":countyName="曲阳县";break;
                            case "38":countyName="阜平县";break;
                            case "39":countyName="定县";break;
                            case "40":countyName="安国县";break;
                            case "41":countyName="博野县";break;
                            case "42":countyName="蠡县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省张家口地区的**********
                    case "25":{
                        cityName="张家口地区";
                        switch (countyCode){
                            case "01":countyName="张家口市";break;
                            case "21":countyName="张北市";break;
                            case "22":countyName="康保县";break;
                            case "23":countyName="沽源县";break;
                            case "24":countyName="尚义县";break;
                            case "25":countyName="蔚县";break;
                            case "26":countyName="阳原县";break;
                            case "27":countyName="怀安县";break;
                            case "28":countyName="万全县";break;
                            case "29":countyName="怀来县";break;
                            case "30":countyName="涿鹿县";break;
                            case "31":countyName="宣化县";break;
                            case "32":countyName="赤城县";break;
                            case "33":countyName="崇礼县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省承德地区的**********
                    case "26":{
                        cityName="承德地区";
                        switch (countyCode){
                            case "01":countyName="承德市";break;
                            case "21":countyName="青龙县";break;
                            case "22":countyName="宽城满族自治县";break;
                            case "23":countyName="兴隆县";break;
                            case "24":countyName="平原县";break;
                            case "25":countyName="承德县";break;
                            case "26":countyName="滦平县";break;
                            case "27":countyName="丰宁满族自治县";break;
                            case "28":countyName="隆化县";break;
                            case "29":countyName="围场满族蒙古族自治县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省廊坊地区的**********
                    case "28":{
                        cityName="廊坊地区";
                        switch (countyCode){
                            case "01":countyName="廊坊市";break;
                            case "21":countyName="三河县";break;
                            case "22":countyName="大厂回族自治县";break;
                            case "23":countyName="香河县";break;
                            case "25":countyName="永清县";break;
                            case "26":countyName="固安县";break;
                            case "27":countyName="霸县";break;
                            case "28":countyName="文安县";break;
                            case "29":countyName="大城县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省沧州地区的**********
                    case "29":{
                        cityName="沧州地区";
                        switch (countyCode){
                            case "01":countyName="沧州市";break;
                            case "02":countyName="泊头市";break;
                            case "03":countyName="任丘市";break;
                            case "04":countyName="黄骅市";break;
                            case "05":countyName="河间市";break;
                            case "21":countyName="沧县";break;
                            case "22":countyName="河间县";break;
                            case "23":countyName="肃宁县";break;
                            case "24":countyName="献县";break;
                            case "25":countyName="交河县";break;
                            case "26":countyName="吴桥县";break;
                            case "27":countyName="东光县";break;
                            case "28":countyName="南皮县";break;
                            case "29":countyName="盐山县";break;
                            case "30":countyName="黄骅县";break;
                            case "31":countyName="孟村回族自治县";break;
                            case "32":countyName="青县";break;
                            case "33":countyName="任丘县";break;
                            case "34":countyName="海兴县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省衡水地区的**********
                    case "30":{
                        cityName="衡水地区";
                        switch (countyCode){
                            case "01":countyName="衡水市";break;
                            case "02":countyName="冀州市";break;
                            case "21":countyName="衡水县";break;
                            case "22":countyName="冀县";break;
                            case "23":countyName="枣强县";break;
                            case "24":countyName="武邑县";break;
                            case "25":countyName="深县";break;
                            case "26":countyName="武强县";break;
                            case "27":countyName="饶阳县";break;
                            case "28":countyName="安平县";break;
                            case "29":countyName="故城县";break;
                            case "30":countyName="景县";break;
                            case "31":countyName="阜城县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //河北省武安市的**********
                    case "90":{
                        cityName="--";
                        if (countyCode.equals("01")) {
                            countyName="武安市";
                        } else {
                            countyName="(身份证号码5-6位有误)";
                        }
                    }break;
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //河北省的身份证前六位对应关系==============================================================================
            case "14":{
                provinceName="山西省";
                switch (cityCode){
                    //山西省太原市的**********
                    case "01":{
                        cityName="太原市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="南城区";break;
                            case "03":countyName="北城区";break;
                            case "04":countyName="河西区";break;
                            case "05":countyName="小店区";break;
                            case "06":countyName="迎泽区";break;
                            case "07":countyName="杏花岭区";break;
                            case "08":countyName="尖草坪区";break;
                            case "09":countyName="万柏林区";break;
                            case "10":countyName="晋源区";break;
                            case "11":countyName="古交工矿区";break;
                            case "12":countyName="南郊区";break;
                            case "13":countyName="北郊区";break;
                            case "21":countyName="清徐县";break;
                            case "22":countyName="阳曲县";break;
                            case "23":countyName="娄烦县";break;
                            case "81":countyName="古交市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省大同市的**********
                    case "02":{
                        cityName="大同市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="城区";break;
                            case "03":countyName="矿区";break;
                            case "11":countyName="南郊区";break;
                            case "12":countyName="新荣区";break;
                            case "21":countyName="阳高区";break;
                            case "22":countyName="天镇县";break;
                            case "23":countyName="广灵县";break;
                            case "24":countyName="灵丘县";break;
                            case "25":countyName="浑源县";break;
                            case "26":countyName="左云县";break;
                            case "27":countyName="大同县";break;
                            case "30":countyName="左云县";break;
                            case "32":countyName="大同县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省阳泉市的**********
                    case "03":{
                        cityName="阳泉市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="城区";break;
                            case "03":countyName="矿区";break;
                            case "11":countyName="郊区";break;
                            case "21":countyName="平定县";break;
                            case "22":countyName="盂县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省长治市的**********
                    case "04":{
                        cityName="长治市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="城区";break;
                            case "11":countyName="郊区";break;
                            case "21":countyName="长治县";break;
                            case "23":countyName="襄垣县";break;
                            case "24":countyName="屯留县";break;
                            case "25":countyName="平顺县";break;
                            case "26":countyName="黎城县";break;
                            case "27":countyName="壶关县";break;
                            case "28":countyName="长子县";break;
                            case "29":countyName="武乡县";break;
                            case "30":countyName="沁县";break;
                            case "31":countyName="沁源县";break;
                            case "81":countyName="潞城市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省晋城市的**********
                    case "05":{
                        cityName="晋城市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="城区";break;
                            case "11":countyName="郊区";break;
                            case "21":countyName="沁水县";break;
                            case "22":countyName="阳城县";break;
                            case "23":countyName="高平县";break;
                            case "24":countyName="陵川县";break;
                            case "25":countyName="泽州县";break;
                            case "81":countyName="高平市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省朔州市的**********
                    case "06":{
                        cityName="朔州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="朔城区";break;
                            case "03":countyName="平鲁区";break;
                            case "21":countyName="山阴县";break;
                            case "22":countyName="应县";break;
                            case "23":countyName="左玉县";break;
                            case "24":countyName="怀仁县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省晋中市的**********
                    case "07":{
                        cityName="晋中市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="榆次区";break;
                            case "21":countyName="榆社县";break;
                            case "22":countyName="左权县";break;
                            case "23":countyName="和顺县";break;
                            case "24":countyName="昔阳县";break;
                            case "25":countyName="寿阳县";break;
                            case "26":countyName="太谷县";break;
                            case "27":countyName="祁县";break;
                            case "28":countyName="平遥县";break;
                            case "29":countyName="灵石县";break;
                            case "81":countyName="介休县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省运城市的**********
                    case "08":{
                        cityName="运城市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="盐湖区";break;
                            case "21":countyName="临猗县";break;
                            case "22":countyName="万荣县";break;
                            case "23":countyName="闻喜县";break;
                            case "24":countyName="稷山县";break;
                            case "25":countyName="新绛县";break;
                            case "26":countyName="绛县";break;
                            case "27":countyName="垣曲县";break;
                            case "28":countyName="夏县";break;
                            case "29":countyName="平陆县";break;
                            case "30":countyName="芮城县";break;
                            case "81":countyName="永济市";break;
                            case "82":countyName="河津市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省忻州市的**********
                    case "09":{
                        cityName="忻州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="忻府区";break;
                            case "21":countyName="定襄县";break;
                            case "22":countyName="五台县";break;
                            case "23":countyName="代县";break;
                            case "24":countyName="繁峙县";break;
                            case "25":countyName="宁武县";break;
                            case "26":countyName="静乐县";break;
                            case "27":countyName="神池县";break;
                            case "28":countyName="五寨县";break;
                            case "29":countyName="岢岚县";break;
                            case "30":countyName="河曲县";break;
                            case "31":countyName="保德县";break;
                            case "32":countyName="偏关县";break;
                            case "81":countyName="原平市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省临汾市的**********
                    case "10":{
                        cityName="临汾市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="尧都区";break;
                            case "21":countyName="曲沃县";break;
                            case "22":countyName="翼城县";break;
                            case "23":countyName="襄汾县";break;
                            case "24":countyName="洪洞县";break;
                            case "25":countyName="古县";break;
                            case "26":countyName="安泽县";break;
                            case "27":countyName="浮山县";break;
                            case "28":countyName="吉县";break;
                            case "29":countyName="乡宁县";break;
                            case "30":countyName="大宁县";break;
                            case "31":countyName="隰县";break;
                            case "32":countyName="永和县";break;
                            case "33":countyName="蒲县";break;
                            case "34":countyName="汾西县";break;
                            case "81":countyName="侯马市";break;
                            case "82":countyName="霍州市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省吕梁市的**********
                    case "11":{
                        cityName="吕梁市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="离石区";break;
                            case "21":countyName="文水县";break;
                            case "22":countyName="交城县";break;
                            case "23":countyName="兴县";break;
                            case "24":countyName="临县";break;
                            case "25":countyName="柳林县";break;
                            case "26":countyName="石楼县";break;
                            case "27":countyName="岚县";break;
                            case "28":countyName="方山县";break;
                            case "29":countyName="中阳县";break;
                            case "30":countyName="交口县";break;
                            case "81":countyName="孝义市";break;
                            case "82":countyName="汾阳市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省雁北地区的**********
                    case "21":{
                        cityName="雁北地区";
                        switch (countyCode){
                            case "21":countyName="阳高县";break;
                            case "22":countyName="天镇县";break;
                            case "23":countyName="广灵县";break;
                            case "24":countyName="灵丘县";break;
                            case "25":countyName="浑源县";break;
                            case "26":countyName="应县";break;
                            case "27":countyName="山阴县";break;
                            case "28":countyName="朔县";break;
                            case "29":countyName="平鲁县";break;
                            case "30":countyName="左云县";break;
                            case "31":countyName="右玉县";break;
                            case "32":countyName="大同县";break;
                            case "33":countyName="怀仁县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省忻州地区的**********
                    case "22":{
                        cityName="忻州地区";
                        switch (countyCode){
                            case "01":countyName="忻州市";break;
                            case "02":countyName="原平市";break;
                            case "22":countyName="定襄县";break;
                            case "23":countyName="五台县";break;
                            case "25":countyName="代县";break;
                            case "26":countyName="繁峙县";break;
                            case "27":countyName="宁武县";break;
                            case "28":countyName="静乐县";break;
                            case "29":countyName="神池县";break;
                            case "30":countyName="五寨县";break;
                            case "31":countyName="岢岚县";break;
                            case "32":countyName="河曲县";break;
                            case "33":countyName="保德县";break;
                            case "34":countyName="偏关县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省吕梁地区的**********
                    case "23":{
                        cityName="吕梁地区";
                        switch (countyCode){
                            case "01":countyName="孝义市";break;
                            case "02":countyName="离石市";break;
                            case "03":countyName="汾阳市";break;
                            case "21":countyName="汾阳县";break;
                            case "22":countyName="文水县";break;
                            case "23":countyName="交城县";break;
                            case "25":countyName="兴县";break;
                            case "26":countyName="临县";break;
                            case "27":countyName="柳林县";break;
                            case "28":countyName="石楼县";break;
                            case "29":countyName="岚县";break;
                            case "30":countyName="方山县";break;
                            case "32":countyName="中阳县";break;
                            case "33":countyName="交口县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省晋中地区的**********
                    case "24":{
                        cityName="晋中地区";
                        switch (countyCode){
                            case "01":countyName="榆次市";break;
                            case "02":countyName="介休市";break;
                            case "21":countyName="榆社县";break;
                            case "22":countyName="左权县";break;
                            case "23":countyName="和顺县";break;
                            case "24":countyName="昔阳县";break;
                            case "27":countyName="寿阳县";break;
                            case "29":countyName="太谷县";break;
                            case "30":countyName="祁县";break;
                            case "31":countyName="平遥县";break;
                            case "33":countyName="灵石县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省临汾地区的**********
                    case "26":{
                        cityName="临汾地区";
                        switch (countyCode){
                            case "01":countyName="临汾市";break;
                            case "02":countyName="侯马市";break;
                            case "03":countyName="霍州市";break;
                            case "21":countyName="曲沃县";break;
                            case "22":countyName="翼城县";break;
                            case "23":countyName="襄汾县";break;
                            case "25":countyName="洪洞县";break;
                            case "27":countyName="古县";break;
                            case "28":countyName="安泽县";break;
                            case "29":countyName="浮山县";break;
                            case "30":countyName="吉县";break;
                            case "31":countyName="乡宁县";break;
                            case "32":countyName="蒲县";break;
                            case "33":countyName="大宁县";break;
                            case "34":countyName="永和县";break;
                            case "35":countyName="隰县";break;
                            case "36":countyName="汾西县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省运城地区的**********
                    case "27":{
                        cityName="运城地区";
                        switch (countyCode){
                            case "01":countyName="运城市";break;
                            case "02":countyName="永济市";break;
                            case "03":countyName="河津市";break;
                            case "23":countyName="芮城县";break;
                            case "24":countyName="临猗县";break;
                            case "25":countyName="万荣县";break;
                            case "26":countyName="新绛县";break;
                            case "27":countyName="稷山县";break;
                            case "29":countyName="闻喜县";break;
                            case "30":countyName="夏县";break;
                            case "31":countyName="绛县";break;
                            case "32":countyName="平陆县";break;
                            case "33":countyName="垣曲县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //山西省古交市的**********
                    case "90":{
                        cityName="--";
                        if (countyCode.equals("01")) {
                            countyName="古交市";
                        } else {
                            countyName="(身份证号码5-6位有误)";
                        }
                    }break;
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //内蒙古自治区的身份证前六位对应关系==============================================================================
            case "15":{
                provinceName="内蒙古自治区";
                switch (cityCode){
                    //内蒙古自治区--呼和浩特市**********
                    case "01":{
                        cityName="呼和浩特市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="新城区";break;
                            case "03":countyName="回民区";break;
                            case "04":countyName="玉泉区";break;
                            case "05":countyName="郊区";break;
                            case "21":countyName="土默特左旗";break;
                            case "22":countyName="托克托县";break;
                            case "23":countyName="和林格尔县";break;
                            case "24":countyName="清水河县";break;
                            case "25":countyName="武川县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--包头市**********
                    case "02":{
                        cityName="包头市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="东河区";break;
                            case "03":countyName="昆都仑区";break;
                            case "04":countyName="青山区";break;
                            case "05":countyName="石拐矿区";break;
                            case "06":countyName="白云矿区";break;
                            case "07":countyName="郊区";break;
                            case "21":countyName="土默特右旗";break;
                            case "22":countyName="固阳县";break;
                            case "23":countyName="达尔罕茂明安联合旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--乌海市**********
                    case "03":{
                        cityName="乌海市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="海勃湾区";break;
                            case "03":countyName="海南区";break;
                            case "04":countyName="乌达区";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--赤峰市**********
                    case "04":{
                        cityName="赤峰市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="红山区";break;
                            case "03":countyName="元宝山区";break;
                            case "04":countyName="松山区";break;
                            case "21":countyName="阿鲁科尔沁旗";break;
                            case "22":countyName="巴林左旗";break;
                            case "23":countyName="巴林右旗";break;
                            case "24":countyName="林西县";break;
                            case "25":countyName="克尔克腾旗";break;
                            case "26":countyName="翁牛特旗";break;
                            case "28":countyName="喀喇沁旗";break;
                            case "29":countyName="宁城县";break;
                            case "30":countyName="敖汉旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--鄂尔多斯市**********
                    case "05":{
                        cityName="通辽市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="科尔沁区";break;
                            case "21":countyName="科尔沁左翼中旗";break;
                            case "22":countyName="科尔沁左翼后旗";break;
                            case "23":countyName="开鲁县";break;
                            case "24":countyName="库伦旗";break;
                            case "25":countyName="奈曼旗";break;
                            case "26":countyName="扎鲁特旗";break;
                            case "81":countyName="霍林郭勒市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--鄂尔多斯市**********
                    case "06":{
                        cityName="鄂尔多斯市";
                        switch (countyCode){
                            case "01":countyName="鄂尔多斯市";break;
                            case "02":countyName="东胜区";break;
                            case "21":countyName="达拉特旗";break;
                            case "22":countyName="准格尔旗";break;
                            case "23":countyName="鄂托克前旗";break;
                            case "24":countyName="鄂托克旗";break;
                            case "25":countyName="杭锦旗";break;
                            case "26":countyName="乌审旗";break;
                            case "27":countyName="伊金霍洛旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--呼伦贝尔市**********
                    case "07":{
                        cityName="呼伦贝尔市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="海拉尔区";break;
                            case "21":countyName="阿荣旗";break;
                            case "22":countyName="莫力达瓦达斡尔族自治旗";break;
                            case "23":countyName="鄂伦春自治旗";break;
                            case "24":countyName="鄂温克族自治旗";break;
                            case "25":countyName="陈巴尔虎旗";break;
                            case "26":countyName="新巴尔虎左旗";break;
                            case "27":countyName="新巴尔虎右旗";break;
                            case "81":countyName="满洲里市";break;
                            case "82":countyName="牙克石市";break;
                            case "83":countyName="扎兰屯市";break;
                            case "84":countyName="额尔古纳市";break;
                            case "85":countyName="根河市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--巴彦淖尔市**********
                    case "08":{
                        cityName="巴彦淖尔市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="临河区";break;
                            case "21":countyName="五原县";break;
                            case "22":countyName="磴口县";break;
                            case "23":countyName="乌拉特前旗";break;
                            case "24":countyName="乌拉特中旗";break;
                            case "25":countyName="乌拉特后旗";break;
                            case "26":countyName="杭锦后旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--乌兰察布市**********
                    case "09":{
                        cityName="乌兰察布市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="集宁区";break;
                            case "21":countyName="卓资县";break;
                            case "22":countyName="化德县";break;
                            case "23":countyName="商都县";break;
                            case "24":countyName="兴和县";break;
                            case "25":countyName="凉城县";break;
                            case "26":countyName="察哈尔右翼前旗";break;
                            case "27":countyName="察哈尔右翼中旗";break;
                            case "28":countyName="察哈尔右翼后旗";break;
                            case "29":countyName="四子王旗";break;
                            case "81":countyName="丰镇市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--呼伦贝尔盟**********
                    case "21":{
                        cityName="呼伦贝尔盟";
                        switch (countyCode){
                            case "01":countyName="海拉尔市";break;
                            case "02":countyName="满洲里市";break;
                            case "03":countyName="扎兰屯市";break;
                            case "04":countyName="牙克石市";break;
                            case "05":countyName="根河市";break;
                            case "06":countyName="额尔古纳市";break;
                            case "21":countyName="布特哈旗";break;
                            case "22":countyName="阿荣旗";break;
                            case "23":countyName="莫力达瓦达斡尔族自治旗";break;
                            case "24":countyName="喜桂图旗";break;
                            case "25":countyName="额尔古纳右旗";break;
                            case "26":countyName="额尔古纳左旗";break;
                            case "27":countyName="鄂伦春自治旗";break;
                            case "28":countyName="鄂温克族自治旗";break;
                            case "29":countyName="新巴尔虎右旗";break;
                            case "30":countyName="新巴尔虎左旗";break;
                            case "31":countyName="陈巴尔虎旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--兴安盟**********
                    case "22":{
                        cityName="兴安盟";
                        switch (countyCode){
                            case "01":countyName="乌兰浩特市";break;
                            case "02":countyName="阿尔山市";break;
                            case "21":countyName="科尔沁右翼前旗";break;
                            case "22":countyName="科尔沁右翼中旗";break;
                            case "23":countyName="扎赉特旗";break;
                            case "24":countyName="突泉县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--哲里木盟**********
                    case "23":{
                        cityName="哲里木盟";
                        switch (countyCode){
                            case "01":countyName="通辽市";break;
                            case "02":countyName="霍林郭勒市";break;
                            case "21":countyName="通辽县";break;
                            case "22":countyName="科尔沁右翼中旗";break;
                            case "23":countyName="科尔沁右翼后旗";break;
                            case "24":countyName="开鲁县";break;
                            case "25":countyName="库伦旗";break;
                            case "26":countyName="奈曼旗";break;
                            case "27":countyName="扎鲁特旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--锡林郭勒盟**********
                    case "25":{
                        cityName="锡林郭勒盟";
                        switch (countyCode){
                            case "01":countyName="二连浩特市";break;
                            case "02":countyName="锡林浩特市";break;
                            case "22":countyName="阿巴嘎旗";break;
                            case "23":countyName="苏尼特左旗";break;
                            case "24":countyName="苏尼特右旗";break;
                            case "25":countyName="东乌珠穆沁旗";break;
                            case "26":countyName="西乌珠穆沁旗";break;
                            case "27":countyName="太仆寺旗";break;
                            case "28":countyName="镶黄旗";break;
                            case "29":countyName="正镶白旗";break;
                            case "30":countyName="正蓝旗";break;
                            case "31":countyName="多伦县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--乌兰察布盟**********
                    case "26":{
                        cityName="乌兰察布盟";
                        switch (countyCode){
                            case "01":countyName="集宁市";break;
                            case "02":countyName="丰镇市";break;
                            case "21":countyName="武川县";break;
                            case "22":countyName="和林格尔县";break;
                            case "23":countyName="清水河县";break;
                            case "24":countyName="卓资县";break;
                            case "25":countyName="化德县";break;
                            case "26":countyName="商都县";break;
                            case "27":countyName="兴和县";break;
                            case "28":countyName="丰镇县";break;
                            case "29":countyName="凉城县";break;
                            case "30":countyName="察哈尔右翼前旗";break;
                            case "31":countyName="察哈尔右翼中旗";break;
                            case "32":countyName="察哈尔右翼后旗";break;
                            case "33":countyName="达尔罕茂明安联合旗";break;
                            case "34":countyName="四子王旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--伊克昭盟**********
                    case "27":{
                        cityName="伊克昭盟";
                        switch (countyCode){
                            case "01":countyName="东胜市";break;
                            case "21":countyName="东胜县";break;
                            case "22":countyName="达拉特旗";break;
                            case "23":countyName="准格尔旗";break;
                            case "24":countyName="鄂托克前旗";break;
                            case "25":countyName="鄂托克旗";break;
                            case "26":countyName="杭锦旗";break;
                            case "27":countyName="乌审旗";break;
                            case "28":countyName="伊金霍洛旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--巴彦淖尔盟**********
                    case "28":{
                        cityName="巴彦淖尔盟";
                        switch (countyCode){
                            case "01":countyName="临河市";break;
                            case "21":countyName="临河县";break;
                            case "22":countyName="五原县";break;
                            case "23":countyName="磴口市";break;
                            case "24":countyName="乌拉特前旗";break;
                            case "25":countyName="乌拉特中旗";break;
                            case "26":countyName="乌拉特后旗";break;
                            case "27":countyName="杭锦后旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //内蒙古自治区--阿拉善盟**********
                    case "29":{
                        cityName="阿拉善盟";
                        switch (countyCode){
                            case "21":countyName="阿拉善左旗";break;
                            case "22":countyName="阿拉善右旗";break;
                            case "23":countyName="额济纳旗";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //辽宁省的身份证前六位对应关系==============================================================================
            case "21":{
                provinceName="辽宁省";
                switch (cityCode){
                    //辽宁省--沈阳市**********
                    case "01":{
                        cityName="沈阳市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="和平区";break;
                            case "03":countyName="沈河区";break;
                            case "04":countyName="大东区";break;
                            case "05":countyName="皇姑区";break;
                            case "06":countyName="铁西区";break;
                            case "11":countyName="苏家屯区";break;
                            case "12":countyName="东陵区";break;
                            case "13":countyName="沈北新区";break;
                            case "14":countyName="于洪区";break;
                            case "20":countyName="市区";break;
                            case "21":countyName="新民县";break;
                            case "22":countyName="辽中县";break;
                            case "23":countyName="康平县";break;
                            case "24":countyName="法库县";break;
                            case "81":countyName="新民市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--大连市**********
                    case "02":{
                        cityName="大连市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="中山区";break;
                            case "03":countyName="西岗区";break;
                            case "04":countyName="沙河口区";break;
                            case "11":countyName="甘井子区";break;
                            case "12":countyName="旅顺口区";break;
                            case "13":countyName="金州区";break;
                            case "19":countyName="瓦房店市";break;
                            case "20":countyName="市区";break;
                            case "21":countyName="金县";break;
                            case "22":countyName="新金县";break;
                            case "23":countyName="复县";break;
                            case "24":countyName="长海县";break;
                            case "25":countyName="庄河县";break;
                            case "81":countyName="瓦房店市";break;
                            case "82":countyName="普兰店市";break;
                            case "83":countyName="庄河市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--鞍山市**********
                    case "03":{
                        cityName="鞍山市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="铁东区";break;
                            case "03":countyName="铁西区";break;
                            case "04":countyName="立山区";break;
                            case "11":countyName="千山区";break;
                            case "19":countyName="开发区";break;
                            case "21":countyName="台安县";break;
                            case "23":countyName="岫岩满族自治县";break;
                            case "81":countyName="海城市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--抚顺市**********
                    case "04":{
                        cityName="抚顺市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="新抚区";break;
                            case "03":countyName="东洲区";break;
                            case "04":countyName="望花区";break;
                            case "11":countyName="顺城区";break;
                            case "21":countyName="抚顺县";break;
                            case "22":countyName="新宾满族自治县";break;
                            case "23":countyName="青原满族自治县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--本溪市**********
                    case "05":{
                        cityName="本溪市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="平山区";break;
                            case "03":countyName="溪湖区";break;
                            case "04":countyName="明山区";break;
                            case "05":countyName="南芬区";break;
                            case "11":countyName="南芬区";break;
                            case "21":countyName="本溪满族自治县";break;
                            case "22":countyName="桓仁满族自治县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--丹东市**********
                    case "06":{
                        cityName="丹东市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="元宝区";break;
                            case "03":countyName="振兴区";break;
                            case "04":countyName="振安区";break;
                            case "20":countyName="市区";break;
                            case "21":countyName="凤城满族自治县";break;
                            case "22":countyName="岫岩满族自治县";break;
                            case "23":countyName="东沟县";break;
                            case "24":countyName="宽甸满族自治县";break;
                            case "81":countyName="东港市";break;
                            case "82":countyName="凤城市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--锦州市**********
                    case "07":{
                        cityName="锦州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="古塔区";break;
                            case "03":countyName="凌河区";break;
                            case "04":countyName="南票区";break;
                            case "05":countyName="葫芦岛区";break;
                            case "11":countyName="太和区";break;
                            case "19":countyName="锦西市";break;
                            case "20":countyName="市区";break;
                            case "21":countyName="锦西县";break;
                            case "22":countyName="兴城县";break;
                            case "23":countyName="绥中县";break;
                            case "24":countyName="锦县";break;
                            case "25":countyName="北镇满族自治县";break;
                            case "26":countyName="黑山县";break;
                            case "27":countyName="义县";break;
                            case "81":countyName="凌海市";break;
                            case "82":countyName="北镇市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--营口市**********
                    case "08":{
                        cityName="营口市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="站前区";break;
                            case "03":countyName="西市区";break;
                            case "04":countyName="鲅鱼圈区";break;
                            case "11":countyName="老边区";break;
                            case "21":countyName="营口县";break;
                            case "24":countyName="盖县";break;
                            case "81":countyName="盖州市";break;
                            case "82":countyName="大石桥市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--阜新市**********
                    case "09":{
                        cityName="阜新市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="海州区";break;
                            case "03":countyName="新邱区";break;
                            case "04":countyName="太平区";break;
                            case "05":countyName="清河门区";break;
                            case "11":countyName="细河区";break;
                            case "21":countyName="阜新蒙古族自治县";break;
                            case "22":countyName="彰武县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--辽阳市**********
                    case "10":{
                        cityName="辽阳市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="白塔区";break;
                            case "03":countyName="文圣区";break;
                            case "04":countyName="宏伟区";break;
                            case "05":countyName="弓长岭区";break;
                            case "11":countyName="太子河区";break;
                            case "21":countyName="辽阳县";break;
                            case "22":countyName="灯塔县";break;
                            case "81":countyName="灯塔市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--盘锦市**********
                    case "11":{
                        cityName="盘锦市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="双台子区";break;
                            case "03":countyName="兴隆台区";break;
                            case "11":countyName="郊区";break;
                            case "21":countyName="大洼县";break;
                            case "22":countyName="盘山县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--铁岭市**********
                    case "12":{
                        cityName="铁岭市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="银州区";break;
                            case "03":countyName="铁法区";break;
                            case "11":countyName="清河区";break;
                            case "21":countyName="铁岭县";break;
                            case "22":countyName="开原县";break;
                            case "23":countyName="西丰县";break;
                            case "24":countyName="昌图县";break;
                            case "25":countyName="康平县";break;
                            case "26":countyName="法库县";break;
                            case "81":countyName="调兵山市";break;
                            case "82":countyName="开原市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--朝阳市**********
                    case "13":{
                        cityName="朝阳市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="双塔区";break;
                            case "03":countyName="龙城区";break;
                            case "21":countyName="朝阳县";break;
                            case "22":countyName="建平县";break;
                            case "23":countyName="凌源市";break;
                            case "24":countyName="喀喇沁左翼蒙古族自治县";break;
                            case "25":countyName="建昌县";break;
                            case "26":countyName="北票县";break;
                            case "81":countyName="北票市";break;
                            case "82":countyName="凌源市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--葫芦岛市**********
                    case "14":{
                        cityName="葫芦岛市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="连山区";break;
                            case "03":countyName="龙港区";break;
                            case "04":countyName="南票区";break;
                            case "21":countyName="绥中县";break;
                            case "22":countyName="建昌县";break;
                            case "81":countyName="兴城市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--铁岭市**********
                    case "21":{
                        cityName="铁岭市";
                        switch (countyCode){
                            case "01":countyName="铁岭市";break;
                            case "02":countyName="铁法市";break;
                            case "21":countyName="铁岭县";break;
                            case "22":countyName="开原县";break;
                            case "23":countyName="西丰县";break;
                            case "24":countyName="昌图县";break;
                            case "25":countyName="康平县";break;
                            case "26":countyName="法库县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--朝阳市**********
                    case "22":{
                        cityName="朝阳市";
                        switch (countyCode){
                            case "25":countyName="建昌县";break;
                            case "26":countyName="北票县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //辽宁省--其它市********************************
                    case "90":{
                        cityName="--";
                        switch (countyCode){
                            case "01":countyName="瓦房店市";break;
                            case "02":countyName="海城市";break;
                            case "03":countyName="锦西市";break;
                            case "04":countyName="兴城市";break;
                            case "05":countyName="铁法市";break;
                            case "06":countyName="北票市";break;
                            case "07":countyName="开原市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //吉林省的身份证前六位对应关系==============================================================================
            case "22":{
                provinceName="吉林省";
                switch (cityCode){
                    //吉林省--长春市**********
                    case "01":{
                        cityName="长春市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="南关区";break;
                            case "03":countyName="宽城区";break;
                            case "04":countyName="朝阳区";break;
                            case "05":countyName="二道区";break;
                            case "06":countyName="绿园区";break;
                            case "11":countyName="郊区";break;
                            case "12":countyName="双阳区";break;
                            case "20":countyName="市区";break;
                            case "21":countyName="榆树县";break;
                            case "22":countyName="农安县";break;
                            case "23":countyName="九台县";break;
                            case "24":countyName="德惠县";break;
                            case "25":countyName="双阳县";break;
                            case "81":countyName="九台市";break;
                            case "82":countyName="榆树市";break;
                            case "83":countyName="德惠市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--吉林市**********
                    case "02":{
                        cityName="吉林市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="昌邑区";break;
                            case "03":countyName="龙潭区";break;
                            case "04":countyName="船营区";break;
                            case "11":countyName="丰满区";break;
                            case "20":countyName="市区";break;
                            case "21":countyName="永吉县";break;
                            case "22":countyName="舒兰县";break;
                            case "23":countyName="磐石县";break;
                            case "24":countyName="蛟河县";break;
                            case "25":countyName="桦甸县";break;
                            case "81":countyName="蛟河市";break;
                            case "82":countyName="桦甸市";break;
                            case "83":countyName="舒兰市";break;
                            case "84":countyName="磐石市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--四平市**********
                    case "03":{
                        cityName="四平市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="铁西区";break;
                            case "03":countyName="铁东区";break;
                            case "19":countyName="公主岭市";break;
                            case "21":countyName="怀德县";break;
                            case "22":countyName="梨树县";break;
                            case "23":countyName="伊通满族自治县";break;
                            case "24":countyName="双辽县";break;
                            case "81":countyName="公主岭市";break;
                            case "82":countyName="双辽市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--辽源市**********
                    case "04":{
                        cityName="辽源市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="龙山区";break;
                            case "03":countyName="西安区";break;
                            case "21":countyName="东丰县";break;
                            case "22":countyName="东辽县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--通化市**********
                    case "05":{
                        cityName="通化市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="东昌区";break;
                            case "03":countyName="二道江区";break;
                            case "19":countyName="梅河口市";break;
                            case "21":countyName="通化县";break;
                            case "22":countyName="集安县";break;
                            case "23":countyName="辉南县";break;
                            case "24":countyName="柳河县";break;
                            case "81":countyName="梅河口市";break;
                            case "82":countyName="集安市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--白山市**********
                    case "06":{
                        cityName="白山市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="八道江区";break;
                            case "03":countyName="三岔子区";break;
                            case "04":countyName="临江区";break;
                            case "21":countyName="抚松县";break;
                            case "22":countyName="靖宇县";break;
                            case "23":countyName="长白朝鲜族自治县";break;
                            case "25":countyName="江源县";break;
                            case "81":countyName="临江市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--松原市**********
                    case "07":{
                        cityName="松原市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="宁江区";break;
                            case "21":countyName="前郭尔罗斯蒙古族自治县";break;
                            case "22":countyName="长岭县";break;
                            case "23":countyName="乾安县";break;
                            case "24":countyName="扶余县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--白城市**********
                    case "08":{
                        cityName="白城市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="洮北区";break;
                            case "21":countyName="镇赉县";break;
                            case "22":countyName="通榆县";break;
                            case "81":countyName="洮南县";break;
                            case "82":countyName="大安市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--其它市1********************************
                    case "21":{
                        cityName="--";
                        switch (countyCode){
                            case "01":countyName="四平市";break;
                            case "02":countyName="辽源市";break;
                            case "21":countyName="怀德县";break;
                            case "22":countyName="梨树县";break;
                            case "23":countyName="伊通满族自治县";break;
                            case "24":countyName="东丰县";break;
                            case "25":countyName="双辽县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--其它市2********************************
                    case "22":{
                        cityName="--";
                        switch (countyCode){
                            case "01":countyName="通化市";break;
                            case "02":countyName="浑江市";break;
                            case "21":countyName="海龙县";break;
                            case "22":countyName="通化县";break;
                            case "23":countyName="柳河县";break;
                            case "24":countyName="辉南县";break;
                            case "25":countyName="集安县";break;
                            case "26":countyName="抚松县";break;
                            case "27":countyName="靖宇县";break;
                            case "28":countyName="长白朝鲜族自治县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--白城地区**********
                    case "23":{
                        cityName="白城地区";
                        switch (countyCode){
                            case "01":countyName="白城市";break;
                            case "02":countyName="洮南市";break;
                            case "03":countyName="扶余市";break;
                            case "04":countyName="大安市";break;
                            case "21":countyName="扶余县";break;
                            case "22":countyName="洮安县";break;
                            case "23":countyName="长岭县";break;
                            case "24":countyName="前郭尔罗斯蒙古族自治县";break;
                            case "25":countyName="大安县";break;
                            case "26":countyName="镇赉县";break;
                            case "27":countyName="通榆县";break;
                            case "28":countyName="乾安县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--延边朝鲜族自治州**********
                    case "24":{
                        cityName="延边朝鲜族自治州";
                        switch (countyCode){
                            case "01":countyName="延吉市";break;
                            case "02":countyName="图们市";break;
                            case "03":countyName="郭化市";break;
                            case "04":countyName="珲春市";break;
                            case "05":countyName="龙井市";break;
                            case "06":countyName="和龙市";break;
                            case "21":countyName="龙井县";break;
                            case "22":countyName="郭化县";break;
                            case "23":countyName="和龙县";break;
                            case "24":countyName="汪清县";break;
                            case "25":countyName="珲春县";break;
                            case "26":countyName="安图县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //吉林省--其它市3********************************
                    case "90":{
                        cityName="--";
                        switch (countyCode){
                            case "01":countyName="公主岭市";break;
                            case "02":countyName="梅河口市";break;
                            case "03":countyName="集安市";break;
                            case "04":countyName="桦甸市";break;
                            case "05":countyName="九台市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //黑龙江省的身份证前六位对应关系==============================================================================
            case "23":{
                provinceName="黑龙江省";
                switch (cityCode){
                    //黑龙江省--哈尔滨市************************
                    case "01":{
                        cityName="哈尔滨市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="道里区";break;
                            case "03":countyName="南岗区";break;
                            case "04":countyName="道外区";break;
                            case "05":countyName="太平区";break;
                            case "06":countyName="香坊区";break;
                            case "07":countyName="动力区";break;
                            case "08":countyName="平房区";break;
                            case "09":countyName="松北区";break;
                            case "10":countyName="香坊区";break;
                            case "11":countyName="呼兰区";break;
                            case "12":countyName="阿城区";break;
                            case "19":countyName="阿城市";break;
                            case "21":countyName="呼兰县";break;
                            case "22":countyName="阿城县";break;
                            case "23":countyName="依兰县";break;
                            case "24":countyName="方正县";break;
                            case "25":countyName="宾县";break;
                            case "26":countyName="巴彦县";break;
                            case "27":countyName="木兰县";break;
                            case "28":countyName="通河县";break;
                            case "29":countyName="延寿县";break;
                            case "81":countyName="阿城市";break;
                            case "82":countyName="双城市";break;
                            case "83":countyName="尚志市";break;
                            case "84":countyName="五常市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--齐齐哈尔市************************
                    case "02":{
                        cityName="齐齐哈尔市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="龙沙区";break;
                            case "03":countyName="建华区";break;
                            case "04":countyName="铁锋区";break;
                            case "05":countyName="昂昂溪区";break;
                            case "06":countyName="富拉尔基区";break;
                            case "07":countyName="碾子山区";break;
                            case "08":countyName="梅里斯达翰尔族区";break;
                            case "21":countyName="龙江县";break;
                            case "22":countyName="讷河县";break;
                            case "23":countyName="依安县";break;
                            case "24":countyName="泰来县";break;
                            case "25":countyName="甘南县";break;
                            case "26":countyName="杜尔伯特蒙古族自治县";break;
                            case "27":countyName="富裕县";break;
                            case "28":countyName="林甸县";break;
                            case "29":countyName="克山县";break;
                            case "30":countyName="克东县";break;
                            case "31":countyName="拜泉县";break;
                            case "81":countyName="讷河市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--鸡西市************************
                    case "03":{
                        cityName="鸡西市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="鸡冠区";break;
                            case "03":countyName="恒山区";break;
                            case "04":countyName="滴道区";break;
                            case "05":countyName="梨树区";break;
                            case "06":countyName="城子河区";break;
                            case "07":countyName="麻山区";break;
                            case "21":countyName="鸡东县";break;
                            case "81":countyName="虎林市";break;
                            case "82":countyName="密山市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--鹤岗市************************
                    case "04":{
                        cityName="鹤岗市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="向阳区";break;
                            case "03":countyName="工农区";break;
                            case "04":countyName="南山区";break;
                            case "05":countyName="兴安区";break;
                            case "06":countyName="东山区";break;
                            case "07":countyName="兴山区";break;
                            case "21":countyName="萝北县";break;
                            case "22":countyName="绥滨县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--双鸭山市************************
                    case "05":{
                        cityName="双鸭山市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="尖山区";break;
                            case "03":countyName="岭东区";break;
                            case "04":countyName="岭西区";break;
                            case "05":countyName="四方台区";break;
                            case "06":countyName="宝山区";break;
                            case "21":countyName="集贤县";break;
                            case "22":countyName="友谊县";break;
                            case "23":countyName="宝清县";break;
                            case "24":countyName="饶河县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--大庆市************************
                    case "06":{
                        cityName="大庆市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="萨尔图区";break;
                            case "03":countyName="龙凤区";break;
                            case "04":countyName="让胡路区";break;
                            case "05":countyName="红岗区";break;
                            case "06":countyName="大同区";break;
                            case "21":countyName="肇州县";break;
                            case "22":countyName="肇源县";break;
                            case "23":countyName="林甸县";break;
                            case "24":countyName="杜尔伯特蒙古族自治县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--伊春市************************
                    case "07":{
                        cityName="伊春市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="伊春区";break;
                            case "03":countyName="南岔区";break;
                            case "04":countyName="友好区";break;
                            case "05":countyName="西林区";break;
                            case "06":countyName="翠峦区";break;
                            case "07":countyName="新青区";break;
                            case "08":countyName="美溪区";break;
                            case "09":countyName="金山屯区";break;
                            case "10":countyName="五营区";break;
                            case "11":countyName="乌马河区";break;
                            case "12":countyName="汤旺河区";break;
                            case "13":countyName="带岭区";break;
                            case "14":countyName="乌伊岭区";break;
                            case "15":countyName="红星区";break;
                            case "16":countyName="上甘岭区";break;
                            case "21":countyName="铁力县";break;
                            case "22":countyName="嘉荫县";break;
                            case "81":countyName="铁力市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--佳木斯市************************
                    case "08":{
                        cityName="佳木斯市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="永红区";break;
                            case "03":countyName="向阳区";break;
                            case "04":countyName="前进区";break;
                            case "05":countyName="东风区";break;
                            case "11":countyName="郊区";break;
                            case "21":countyName="富锦县";break;
                            case "22":countyName="桦南县";break;
                            case "23":countyName="依兰县";break;
                            case "25":countyName="集贤县";break;
                            case "26":countyName="桦川县";break;
                            case "27":countyName="宝清县";break;
                            case "28":countyName="汤原县";break;
                            case "29":countyName="绥滨县";break;
                            case "30":countyName="萝北县";break;
                            case "31":countyName="同江县";break;
                            case "32":countyName="饶河县";break;
                            case "33":countyName="抚远县";break;
                            case "34":countyName="友谊县";break;
                            case "81":countyName="同江市";break;
                            case "82":countyName="富锦市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--七台河市************************
                    case "09":{
                        cityName="七台河市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="新兴区";break;
                            case "03":countyName="桃山区";break;
                            case "04":countyName="茄子河区";break;
                            case "21":countyName="勃利县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--牡丹江市************************
                    case "10":{
                        cityName="牡丹江市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="东安区";break;
                            case "03":countyName="阳明区";break;
                            case "04":countyName="爱民区";break;
                            case "05":countyName="西安区";break;
                            case "11":countyName="郊区";break;
                            case "20":countyName="绥芬河市";break;
                            case "21":countyName="宁安县";break;
                            case "22":countyName="海林县";break;
                            case "23":countyName="穆棱县";break;
                            case "24":countyName="东宁县";break;
                            case "25":countyName="林口县";break;
                            case "26":countyName="密山县";break;
                            case "27":countyName="虎林县";break;
                            case "81":countyName="绥芬河市";break;
                            case "82":countyName="密山市";break;
                            case "83":countyName="海林市";break;
                            case "84":countyName="宁安市";break;
                            case "85":countyName="穆棱市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--黑河市************************
                    case "11":{
                        cityName="黑河市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="爱辉区";break;
                            case "21":countyName="嫩江县";break;
                            case "23":countyName="逊克县";break;
                            case "24":countyName="孙吴县";break;
                            case "81":countyName="北安市";break;
                            case "82":countyName="五大连池市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--绥化市************************
                    case "12":{
                        cityName="绥化市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="北林区";break;
                            case "21":countyName="望奎县";break;
                            case "22":countyName="兰西县";break;
                            case "23":countyName="青冈县";break;
                            case "24":countyName="庆安县";break;
                            case "25":countyName="明水县";break;
                            case "26":countyName="绥棱县";break;
                            case "81":countyName="安达市";break;
                            case "82":countyName="肇东市";break;
                            case "83":countyName="海伦市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--松花江地区************************
                    case "21":{
                        cityName="松花江地区";
                        switch (countyCode){
                            case "01":countyName="双城市";break;
                            case "02":countyName="尚志市";break;
                            case "03":countyName="五常市";break;
                            case "21":countyName="阿城市";break;
                            case "22":countyName="宾县";break;
                            case "23":countyName="呼兰县";break;
                            case "24":countyName="双城市";break;
                            case "25":countyName="五常市";break;
                            case "26":countyName="巴彦县";break;
                            case "27":countyName="木兰县";break;
                            case "28":countyName="通河县";break;
                            case "29":countyName="尚志市";break;
                            case "30":countyName="方正县";break;
                            case "31":countyName="延寿县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--绥化地区************************
                    case "23":{
                        cityName="绥化地区";
                        switch (countyCode){
                            case "01":countyName="绥化市";break;
                            case "02":countyName="安达市";break;
                            case "03":countyName="肇东市";break;
                            case "04":countyName="海伦市";break;
                            case "21":countyName="海伦县";break;
                            case "22":countyName="肇东县";break;
                            case "23":countyName="绥化县";break;
                            case "24":countyName="望奎县";break;
                            case "25":countyName="兰西县";break;
                            case "26":countyName="青冈县";break;
                            case "27":countyName="安达县";break;
                            case "28":countyName="肇源县";break;
                            case "29":countyName="肇州县";break;
                            case "30":countyName="庆安县";break;
                            case "31":countyName="明水县";break;
                            case "32":countyName="绥棱县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--佳木斯地区************************
                    case "24":{
                        cityName="佳木斯地区";
                        switch (countyCode){
                            case "01":countyName="佳木斯市";break;
                            case "21":countyName="富锦县";break;
                            case "22":countyName="桦南县";break;
                            case "23":countyName="依兰县";break;
                            case "24":countyName="勃利县";break;
                            case "25":countyName="集贤县";break;
                            case "26":countyName="桦川县";break;
                            case "27":countyName="宝清县";break;
                            case "28":countyName="汤原县";break;
                            case "29":countyName="绥滨县";break;
                            case "30":countyName="萝北县";break;
                            case "31":countyName="同江县";break;
                            case "32":countyName="饶河县";break;
                            case "33":countyName="抚远县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--牡丹江地区************************
                    case "25":{
                        cityName="牡丹江地区";
                        switch (countyCode){
                            case "01":countyName="牡丹江市";break;
                            case "02":countyName="绥芬河市";break;
                            case "21":countyName="宁安县";break;
                            case "22":countyName="海林县";break;
                            case "23":countyName="穆棱县";break;
                            case "24":countyName="东宁县";break;
                            case "25":countyName="林口县";break;
                            case "26":countyName="鸡东县";break;
                            case "27":countyName="密山县";break;
                            case "28":countyName="虎林县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--黑河地区************************
                    case "26":{
                        cityName="黑河地区";
                        switch (countyCode){
                            case "01":countyName="黑河市";break;
                            case "02":countyName="北安市";break;
                            case "03":countyName="五大连池市";break;
                            case "21":countyName="北安县";break;
                            case "22":countyName="嫩江县";break;
                            case "23":countyName="德都县";break;
                            case "25":countyName="逊克县";break;
                            case "26":countyName="孙吴县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--大兴安岭地区************************
                    case "27":{
                        cityName="大兴安岭地区";
                        switch (countyCode){
                            case "01":countyName="加格达奇区";break;
                            case "21":countyName="呼玛县";break;
                            case "22":countyName="塔河县";break;
                            case "23":countyName="漠河县";break;
                            case "24":countyName="漠河县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //黑龙江省--其它市********************************
                    case "90":{
                        cityName="--";
                        switch (countyCode){
                            case "01":countyName="绥芬河市";break;
                            case "02":countyName="阿城市";break;
                            case "03":countyName="同江市";break;
                            case "04":countyName="富锦市";break;
                            case "05":countyName="铁力市";break;
                            case "06":countyName="密山市";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //上海市的身份证前六位对应关系==============================================================================
            case "31":{
                provinceName="上海市";
                switch (cityCode){
                    case "01":{
                        cityName="--";
                        switch (countyCode){
                            case "01":countyName="黄浦区";break;
                            case "02":countyName="南京区";break;
                            case "03":countyName="卢湾区";break;
                            case "04":countyName="徐汇区";break;
                            case "05":countyName="长宁区";break;
                            case "06":countyName="静安区";break;
                            case "07":countyName="普陀区";break;
                            case "08":countyName="闸北区";break;
                            case "09":countyName="虹口区";break;
                            case "10":countyName="杨浦区";break;
                            case "11":countyName="吴淞区";break;
                            case "12":countyName="闵行区";break;
                            case "13":countyName="宝山区";break;
                            case "14":countyName="嘉定区";break;
                            case "15":countyName="浦东新区";break;
                            case "16":countyName="金山区";break;
                            case "17":countyName="松江区";break;
                            case "18":countyName="青浦区";break;
                            case "19":countyName="南汇区";break;
                            case "20":countyName="奉贤区";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    case "02":{
                        cityName="--";
                        switch (countyCode){
                            case "21":countyName="上海县";break;
                            case "22":countyName="嘉定县";break;
                            case "23":countyName="宝山县";break;
                            case "24":countyName="川沙县";break;
                            case "25":countyName="南汇县";break;
                            case "26":countyName="奉贤县";break;
                            case "27":countyName="松江县";break;
                            case "28":countyName="金山县";break;
                            case "29":countyName="青浦县";break;
                            case "30":countyName="崇明县";break;
                            default:countyName="(身份证号码5-6位有误)";break;
                        }
                    }break;
                    default:{
                        cityName="身份证号码3-4位有误";
                        countyName="--";
                    }break;
                }
            }break;
            //江苏省的身份证前六位对应关系==============================================================================
            case "32":{
                provinceName="江苏省";
                switch (cityCode){
                    //江苏省--南京市************************
                    case "01":{
                        cityName="南京市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="玄武区";break;
                            case "03":countyName="白下区";break;
                            case "04":countyName="秦淮区";break;
                            case "05":countyName="建邺区";break;
                            case "06":countyName="鼓楼区";break;
                            case "07":countyName="下关区";break;
                            case "11":countyName="浦口区";break;
                            case "12":countyName="大厂区";break;
                            case "13":countyName="栖霞区";break;
                            case "14":countyName="雨花台区";break;
                            case "15":countyName="江宁区";break;
                            case "16":countyName="六合区";break;
                            case "20":countyName="市区";break;
                            case "21":countyName="江宁县";break;
                            case "22":countyName="江浦县";break;
                            case "23":countyName="六合县";break;
                            case "24":countyName="溧水县";break;
                            case "25":countyName="高淳县";break;
                            case "26":countyName="江都县";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--无锡市************************
                    case "02":{
                        cityName="无锡市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="崇安区";break;
                            case "03":countyName="南长区";break;
                            case "04":countyName="北塘区";break;
                            case "05":countyName="锡山区";break;
                            case "06":countyName="惠山区";break;
                            case "11":countyName="郊区";break;
                            case "12":countyName="马山区";break;
                            case "19":countyName="江阴县";break;
                            case "21":countyName="江阴县";break;
                            case "22":countyName="无锡县";break;
                            case "23":countyName="宜兴县";break;
                            case "81":countyName="江阴市";break;
                            case "82":countyName="宜兴市";break;
                            case "83":countyName="锡山市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--徐州市************************
                    case "03":{
                        cityName="徐州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="鼓楼区";break;
                            case "03":countyName="云龙区";break;
                            case "04":countyName="九里区";break;
                            case "05":countyName="贾汪区";break;
                            case "11":countyName="泉山区";break;
                            case "21":countyName="丰县";break;
                            case "22":countyName="沛县";break;
                            case "23":countyName="铜山县";break;
                            case "24":countyName="睢宁县";break;
                            case "25":countyName="邳县";break;
                            case "26":countyName="新沂县";break;
                            case "81":countyName="新沂市";break;
                            case "82":countyName="邳州市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--常州市************************
                    case "04":{
                        cityName="常州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="天宁区";break;
                            case "04":countyName="钟楼区";break;
                            case "05":countyName="戚墅堰区";break;
                            case "11":countyName="郊区";break;
                            case "12":countyName="武进区";break;
                            case "19":countyName="武进市";break;
                            case "21":countyName="武进县";break;
                            case "22":countyName="金坛县";break;
                            case "23":countyName="溧阳县";break;
                            case "81":countyName="溧阳市";break;
                            case "82":countyName="金坛市";break;
                            case "83":countyName="武进市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--苏州市************************
                    case "05":{
                        cityName="苏州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="沧浪区";break;
                            case "03":countyName="平江区";break;
                            case "04":countyName="金阊区";break;
                            case "05":countyName="虎丘区";break;
                            case "06":countyName="吴中区";break;
                            case "07":countyName="相城区";break;
                            case "11":countyName="郊区";break;
                            case "20":countyName="常熟市";break;
                            case "21":countyName="沙洲县";break;
                            case "22":countyName="太仓县";break;
                            case "23":countyName="昆山县";break;
                            case "24":countyName="吴县";break;
                            case "25":countyName="吴江县";break;
                            case "81":countyName="常熟市";break;
                            case "82":countyName="张家港市";break;
                            case "83":countyName="昆山市";break;
                            case "84":countyName="吴江市";break;
                            case "85":countyName="太仓市";break;
                            case "86":countyName="吴县市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--南通市************************
                    case "06":{
                        cityName="南通市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="崇川区";break;
                            case "03":countyName="港闸区";break;
                            case "04":countyName="海安县";break;
                            case "05":countyName="如皋县";break;
                            case "06":countyName="如东县";break;
                            case "07":countyName="南通县";break;
                            case "11":countyName="海门县";break;
                            case "20":countyName="启东县";break;
                            case "21":countyName="启东市";break;
                            case "22":countyName="如皋市";break;
                            case "23":countyName="通州市";break;
                            case "24":countyName="海门市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--连云港市************************
                    case "07":{
                        cityName="连云港市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="连云区";break;
                            case "03":countyName="云台区";break;
                            case "04":countyName="新浦区";break;
                            case "05":countyName="海州区";break;
                            case "06":countyName="赣榆县";break;
                            case "07":countyName="东海县";break;
                            case "11":countyName="灌云县";break;
                            case "20":countyName="灌南县";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--淮安市************************
                    case "08":{
                        cityName="淮安市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="清河区";break;
                            case "03":countyName="楚州区";break;
                            case "04":countyName="淮阴区";break;
                            case "11":countyName="清浦区";break;
                            case "19":countyName="宿迁市";break;
                            case "21":countyName="淮阴县";break;
                            case "22":countyName="灌南县";break;
                            case "23":countyName="沭阳县";break;
                            case "24":countyName="宿迁县";break;
                            case "25":countyName="泗阳县";break;
                            case "26":countyName="涟水县";break;
                            case "27":countyName="泗洪县";break;
                            case "28":countyName="淮安县";break;
                            case "29":countyName="洪泽县";break;
                            case "30":countyName="盱眙县";break;
                            case "31":countyName="金湖县";break;
                            case "81":countyName="宿迁市";break;
                            case "82":countyName="淮安市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--盐城市************************
                    case "09":{
                        cityName="盐城市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="城区";break;
                            case "03":countyName="盐都区";break;
                            case "11":countyName="郊区";break;
                            case "19":countyName="东台市";break;
                            case "21":countyName="响水县";break;
                            case "22":countyName="滨海县";break;
                            case "23":countyName="阜宁县";break;
                            case "24":countyName="射阳县";break;
                            case "25":countyName="建湖县";break;
                            case "26":countyName="大丰县";break;
                            case "27":countyName="东台县";break;
                            case "28":countyName="盐都县";break;
                            case "81":countyName="东台市";break;
                            case "82":countyName="大丰市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--扬州市************************
                    case "10":{
                        cityName="扬州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="广陵区";break;
                            case "03":countyName="邗江区";break;
                            case "11":countyName="郊区";break;
                            case "19":countyName="仪征区";break;
                            case "20":countyName="泰州市";break;
                            case "21":countyName="兴化县";break;
                            case "22":countyName="高邮县";break;
                            case "23":countyName="宝应县";break;
                            case "24":countyName="靖江县";break;
                            case "25":countyName="泰兴县";break;
                            case "26":countyName="江都县";break;
                            case "27":countyName="邗江县";break;
                            case "28":countyName="泰县";break;
                            case "29":countyName="仪征县";break;
                            case "81":countyName="仪征市";break;
                            case "82":countyName="泰州市";break;
                            case "83":countyName="兴化市";break;
                            case "84":countyName="高邮市";break;
                            case "85":countyName="泰兴市";break;
                            case "86":countyName="靖江市";break;
                            case "87":countyName="江都市";break;
                            case "88":countyName="江都市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--镇江市************************
                    case "11":{
                        cityName="镇江市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="京口区";break;
                            case "11":countyName="润州区";break;
                            case "12":countyName="丹徒区";break;
                            case "19":countyName="丹阳市";break;
                            case "21":countyName="丹徒县";break;
                            case "22":countyName="丹阳县";break;
                            case "23":countyName="句容县";break;
                            case "24":countyName="扬中县";break;
                            case "81":countyName="丹阳市";break;
                            case "82":countyName="扬中市";break;
                            case "83":countyName="句容市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--泰州市************************
                    case "12":{
                        cityName="泰州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="海陵区";break;
                            case "03":countyName="高港区";break;
                            case "81":countyName="兴化市";break;
                            case "82":countyName="靖江市";break;
                            case "83":countyName="泰兴市";break;
                            case "84":countyName="姜堰市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江苏省--宿迁市************************
                    case "13":{
                        cityName="宿迁市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="宿城区";break;
                            case "11":countyName="宿豫区";break;
                            case "21":countyName="宿豫县";break;
                            case "22":countyName="沭阳县";break;
                            case "23":countyName="泗阳县";break;
                            case "24":countyName="泗洪县";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //浙江省的身份证前六位对应关系==============================================================================
            case "33":{
                provinceName="浙江省";
                switch (cityCode){
                    //浙江省--杭州市************************
                    case "01":{
                        cityName="杭州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="上城区";break;
                            case "03":countyName="下城区";break;
                            case "04":countyName="江干区";break;
                            case "05":countyName="拱墅区";break;
                            case "06":countyName="西湖区";break;
                            case "07":countyName="滨江区";break;
                            case "08":countyName="滨江区";break;
                            case "09":countyName="萧山区";break;
                            case "10":countyName="余杭区";break;
                            case "20":countyName="市区";break;
                            case "21":countyName="萧山县";break;
                            case "22":countyName="桐庐县";break;
                            case "23":countyName="富阳县";break;
                            case "24":countyName="临安县";break;
                            case "25":countyName="余杭县";break;
                            case "26":countyName="建德县";break;
                            case "27":countyName="淳安县";break;
                            case "81":countyName="萧山市";break;
                            case "82":countyName="建德市";break;
                            case "83":countyName="富阳市";break;
                            case "84":countyName="余杭市";break;
                            case "85":countyName="临安市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--宁波市************************
                    case "02":{
                        cityName="宁波市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="镇明区";break;
                            case "03":countyName="海曙区";break;
                            case "04":countyName="江东区";break;
                            case "05":countyName="江北区";break;
                            case "06":countyName="北仑区";break;
                            case "07":countyName="鄞州区";break;
                            case "11":countyName="镇海区";break;
                            case "12":countyName="鄞州区";break;
                            case "19":countyName="余姚市";break;
                            case "21":countyName="镇海县";break;
                            case "22":countyName="慈溪县";break;
                            case "23":countyName="余姚县";break;
                            case "24":countyName="奉化县";break;
                            case "25":countyName="象山县";break;
                            case "26":countyName="宁海县";break;
                            case "27":countyName="鄞县";break;
                            case "81":countyName="余姚市";break;
                            case "82":countyName="慈溪市";break;
                            case "83":countyName="奉化市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--温州市************************
                    case "03":{
                        cityName="温州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="鹿城区";break;
                            case "03":countyName="龙湾区";break;
                            case "04":countyName="瓯海区";break;
                            case "21":countyName="瓯海县";break;
                            case "22":countyName="洞头县";break;
                            case "23":countyName="乐清县";break;
                            case "24":countyName="永嘉县";break;
                            case "25":countyName="瑞安县";break;
                            case "26":countyName="平阳县";break;
                            case "27":countyName="苍南县";break;
                            case "28":countyName="文成县";break;
                            case "29":countyName="泰顺县";break;
                            case "81":countyName="瑞安市";break;
                            case "82":countyName="乐清市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--嘉兴市************************
                    case "04":{
                        cityName="嘉兴市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="秀城区";break;
                            case "11":countyName="郊区";break;
                            case "19":countyName="海宁市";break;
                            case "21":countyName="嘉善市";break;
                            case "22":countyName="平湖县";break;
                            case "23":countyName="海宁县";break;
                            case "24":countyName="海盐县";break;
                            case "25":countyName="桐乡县";break;
                            case "81":countyName="海宁市";break;
                            case "82":countyName="平湖市";break;
                            case "83":countyName="桐乡市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--湖州市************************
                    case "05":{
                        cityName="湖州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="吴兴区";break;
                            case "03":countyName="南浔区";break;
                            case "11":countyName="郊区";break;
                            case "21":countyName="德清县";break;
                            case "22":countyName="长兴县";break;
                            case "23":countyName="安吉县";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--绍兴市************************
                    case "06":{
                        cityName="绍兴市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="越城区";break;
                            case "21":countyName="绍兴县";break;
                            case "22":countyName="上虞县";break;
                            case "23":countyName="嵊县";break;
                            case "24":countyName="新昌县";break;
                            case "25":countyName="诸暨县";break;
                            case "81":countyName="诸暨市";break;
                            case "82":countyName="上虞市";break;
                            case "83":countyName="嵊州市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--金华市************************
                    case "07":{
                        cityName="金华市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="婺城区";break;
                            case "03":countyName="金东区";break;
                            case "19":countyName="兰溪市";break;
                            case "21":countyName="金华县";break;
                            case "22":countyName="永康县";break;
                            case "23":countyName="武义县";break;
                            case "24":countyName="东阳县";break;
                            case "25":countyName="义乌县";break;
                            case "26":countyName="浦江县";break;
                            case "27":countyName="磐安县";break;
                            case "81":countyName="兰溪市";break;
                            case "82":countyName="义乌市";break;
                            case "83":countyName="东阳市";break;
                            case "84":countyName="永康市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--衢州市************************
                    case "08":{
                        cityName="衢州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="柯城区";break;
                            case "03":countyName="衢江区";break;
                            case "21":countyName="衢县";break;
                            case "22":countyName="常山县";break;
                            case "23":countyName="江山县";break;
                            case "24":countyName="开化县";break;
                            case "25":countyName="龙游县";break;
                            case "81":countyName="江山市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--丹山市************************
                    case "09":{
                        cityName="丹山市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="定海区";break;
                            case "03":countyName="普陀区";break;
                            case "21":countyName="岱山县";break;
                            case "22":countyName="嵊泗县";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--台州市************************
                    case "10":{
                        cityName="台州市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="淑江区";break;
                            case "03":countyName="黄岩区";break;
                            case "04":countyName="路桥区";break;
                            case "21":countyName="玉环县";break;
                            case "22":countyName="三门县";break;
                            case "23":countyName="天台县";break;
                            case "24":countyName="仙居县";break;
                            case "81":countyName="温岭市";break;
                            case "82":countyName="临海市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--丽水市************************
                    case "11":{
                        cityName="丽水市";
                        switch (countyCode){
                            case "01":countyName="市辖区";break;
                            case "02":countyName="莲都区";break;
                            case "21":countyName="青田县";break;
                            case "22":countyName="缙云县";break;
                            case "23":countyName="遂昌县";break;
                            case "24":countyName="松阳县";break;
                            case "25":countyName="云和县";break;
                            case "26":countyName="庆元县";break;
                            case "27":countyName="景宁畲族自治县";break;
                            case "81":countyName="龙泉市";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--丽水地区************************
                    case "25":{
                        cityName="丽水地区";
                        switch (countyCode){
                            case "01":countyName="丽水市";break;
                            case "02":countyName="龙泉市";break;
                            case "21":countyName="丽水县";break;
                            case "22":countyName="青田县";break;
                            case "23":countyName="云和县";break;
                            case "24":countyName="龙泉县";break;
                            case "25":countyName="庆元县";break;
                            case "26":countyName="缙云县";break;
                            case "27":countyName="遂昌县";break;
                            case "28":countyName="松阳县";break;
                            case "29":countyName="景宁畲族自治县";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--台州地区************************
                    case "26":{
                        cityName="台州地区";
                        switch (countyCode){
                            case "01":countyName="椒江市";break;
                            case "02":countyName="临海市";break;
                            case "03":countyName="黄岩市";break;
                            case "21":countyName="临海县";break;
                            case "22":countyName="黄岩县";break;
                            case "23":countyName="温岭县";break;
                            case "24":countyName="仙居县";break;
                            case "25":countyName="天台县";break;
                            case "26":countyName="三门县";break;
                            case "27":countyName="玉环县";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--舟山地区************************
                    case "27":{
                        cityName="舟山地区";
                        switch (countyCode){
                            case "21":countyName="定海县";break;
                            case "22":countyName="普陀县";break;
                            case "23":countyName="岱山县";break;
                            case "24":countyName="嵊泗县";break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //浙江省--其它市************************
                    case "90":{
                        cityName="--";
                        switch (countyCode){
                            case "01":{countyName="余姚市";}break;
                            case "02":{countyName="海宁市";}break;
                            case "03":{countyName="兰溪市";}break;
                            case "04":{countyName="瑞安市";}break;
                            case "05":{countyName="萧山市";}break;
                            case "06":{countyName="江山市";}break;
                            case "07":{countyName="义乌市";}break;
                            case "08":{countyName="东阳市";}break;
                            case "09":{countyName="慈溪市";}break;
                            case "10":{countyName="奉化市";}break;
                            case "11":{countyName="诸暨市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //安徽省的身份证前六位对应关系==============================================================================
            case "34":{
                provinceName="安徽省";
                switch (cityCode){
                    //安徽省--合肥市************************
                    case "01":{
                        cityName="合肥市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="瑶海区";}break;
                            case "03":{countyName="庐阳区";}break;
                            case "04":{countyName="蜀山区";}break;
                            case "11":{countyName="包河区";}break;
                            case "21":{countyName="长丰县";}break;
                            case "22":{countyName="肥西县";}break;
                            case "23":{countyName="肥东县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--芜湖市************************
                    case "02":{
                        cityName="芜湖市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="镜湖区";}break;
                            case "03":{countyName="弋江区";}break;
                            case "04":{countyName="新抚区";}break;
                            case "05":{countyName="裕溪口去";}break;
                            case "06":{countyName="四褐山区";}break;
                            case "07":{countyName="鸠江区";}break;
                            case "08":{countyName="三山区";}break;
                            case "11":{countyName="郊区";}break;
                            case "20":{countyName="市区";}break;
                            case "21":{countyName="芜湖县";}break;
                            case "22":{countyName="繁昌县";}break;
                            case "23":{countyName="南陵县";}break;
                            case "24":{countyName="青阳县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--蚌阜市************************
                    case "03":{
                        cityName="蚌阜市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="龙子湖区";}break;
                            case "03":{countyName="蚌山区";}break;
                            case "04":{countyName="禹会区";}break;
                            case "11":{countyName="淮上区";}break;
                            case "21":{countyName="怀远区";}break;
                            case "22":{countyName="五河县";}break;
                            case "23":{countyName="固镇县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--淮南市************************
                    case "04":{
                        cityName="淮南市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="大通区";}break;
                            case "03":{countyName="田家庵区";}break;
                            case "04":{countyName="谢家集区";}break;
                            case "05":{countyName="八公山区";}break;
                            case "06":{countyName="潘集区";}break;
                            case "21":{countyName="凤台区";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--马鞍山市************************
                    case "05":{
                        cityName="马鞍山市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="金家庄区";}break;
                            case "03":{countyName="花山区";}break;
                            case "04":{countyName="雨山区";}break;
                            case "05":{countyName="向山区";}break;
                            case "21":{countyName="当涂县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--淮北市************************
                    case "06":{
                        cityName="淮北市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="杜集区";}break;
                            case "03":{countyName="相山区";}break;
                            case "04":{countyName="烈山区";}break;
                            case "21":{countyName="濉溪县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--铜陵市************************
                    case "07":{
                        cityName="铜陵市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="铜官山区";}break;
                            case "03":{countyName="狮子山区";}break;
                            case "11":{countyName="郊区";}break;
                            case "21":{countyName="铜陵县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--安庆市************************
                    case "08":{
                        cityName="安庆市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="迎江区";}break;
                            case "03":{countyName="大观区";}break;
                            case "11":{countyName="郊区";}break;
                            case "21":{countyName="桐城县";}break;
                            case "22":{countyName="怀宁县";}break;
                            case "23":{countyName="枞阳县";}break;
                            case "24":{countyName="潜山县";}break;
                            case "25":{countyName="太湖县";}break;
                            case "26":{countyName="宿松县";}break;
                            case "27":{countyName="望江县";}break;
                            case "28":{countyName="岳西县";}break;
                            case "81":{countyName="桐城市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--黄山市************************
                    case "10":{
                        cityName="黄山市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="屯溪区";}break;
                            case "03":{countyName="黄山区";}break;
                            case "04":{countyName="徽州区";}break;
                            case "21":{countyName="歙县";}break;
                            case "22":{countyName="休宁县";}break;
                            case "23":{countyName="黟县";}break;
                            case "24":{countyName="祁门县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--滁州市************************
                    case "11":{
                        cityName="滁州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="琅琊区";}break;
                            case "03":{countyName="南焦区";}break;
                            case "21":{countyName="天长县";}break;
                            case "22":{countyName="来安县";}break;
                            case "24":{countyName="全椒县";}break;
                            case "25":{countyName="定远县";}break;
                            case "26":{countyName="凤阳县";}break;
                            case "27":{countyName="嘉山县";}break;
                            case "81":{countyName="天长市";}break;
                            case "82":{countyName="明光市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--阜阳市************************
                    case "12":{
                        cityName="阜阳市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="颍州区";}break;
                            case "03":{countyName="颍东区";}break;
                            case "04":{countyName="颍泉区";}break;
                            case "21":{countyName="临泉县";}break;
                            case "22":{countyName="太和县";}break;
                            case "23":{countyName="涡阳县";}break;
                            case "24":{countyName="蒙城县";}break;
                            case "25":{countyName="阜南县";}break;
                            case "86":{countyName="颍上县";}break;
                            case "87":{countyName="利辛县";}break;
                            case "81":{countyName="亳州市";}break;
                            case "82":{countyName="界首市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--宿州市************************
                    case "13":{
                        cityName="宿州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="墉桥区";}break;
                            case "21":{countyName="砀山县";}break;
                            case "22":{countyName="萧县";}break;
                            case "23":{countyName="灵璧县";}break;
                            case "24":{countyName="泗县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--巢湖市************************
                    case "14":{
                        cityName="巢湖市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="居巢区";}break;
                            case "21":{countyName="庐江县";}break;
                            case "22":{countyName="无为县";}break;
                            case "23":{countyName="含山县";}break;
                            case "24":{countyName="和县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--六安市************************
                    case "15":{
                        cityName="六安市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="金安区";}break;
                            case "03":{countyName="裕安区";}break;
                            case "21":{countyName="寿县";}break;
                            case "22":{countyName="霍邱县";}break;
                            case "23":{countyName="舒城县";}break;
                            case "24":{countyName="金寨县";}break;
                            case "25":{countyName="霍山县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--亳州市************************
                    case "16":{
                        cityName="亳州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="谯城区";}break;
                            case "21":{countyName="涡阳县";}break;
                            case "22":{countyName="蒙城县";}break;
                            case "23":{countyName="利辛县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--池州市************************
                    case "17":{
                        cityName="池州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="贵池区";}break;
                            case "21":{countyName="东至县";}break;
                            case "22":{countyName="石台县";}break;
                            case "23":{countyName="青阳县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--宣城市************************
                    case "18":{
                        cityName="宣城市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="宣州区";}break;
                            case "21":{countyName="郎溪县";}break;
                            case "22":{countyName="广德县";}break;
                            case "23":{countyName="泾县";}break;
                            case "24":{countyName="绩溪县";}break;
                            case "25":{countyName="旌德县";}break;
                            case "81":{countyName="宁国市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--阜阳地区************************
                    case "21":{
                        cityName="阜阳地区";
                        switch (countyCode){
                            case "01":{countyName="阜阳市";}break;
                            case "21":{countyName="阜阳县";}break;
                            case "22":{countyName="临泉县";}break;
                            case "23":{countyName="太和县";}break;
                            case "24":{countyName="涡阳县";}break;
                            case "25":{countyName="蒙城县";}break;
                            case "26":{countyName="亳县";}break;
                            case "27":{countyName="阜南县";}break;
                            case "28":{countyName="颍上县";}break;
                            case "29":{countyName="界首县";}break;
                            case "30":{countyName="利辛县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--宿县地区************************
                    case "22":{
                        cityName="宿县地区";
                        switch (countyCode){
                            case "01":{countyName="宿州市";}break;
                            case "21":{countyName="砀山县";}break;
                            case "22":{countyName="萧县";}break;
                            case "23":{countyName="宿县";}break;
                            case "24":{countyName="灵璧县";}break;
                            case "25":{countyName="泗县";}break;
                            case "26":{countyName="怀远县";}break;
                            case "27":{countyName="五河县";}break;
                            case "28":{countyName="固镇县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--滁县地区************************
                    case "23":{
                        cityName="滁县地区";
                        switch (countyCode){
                            case "01":{countyName="滁州市";}break;
                            case "21":{countyName="天长县";}break;
                            case "22":{countyName="来安县";}break;
                            case "23":{countyName="滁县";}break;
                            case "24":{countyName="全椒县";}break;
                            case "25":{countyName="定远县";}break;
                            case "26":{countyName="凤阳县";}break;
                            case "27":{countyName="嘉山县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--六安地区************************
                    case "24":{
                        cityName="六安地区";
                        switch (countyCode){
                            case "01":{countyName="六安市";}break;
                            case "21":{countyName="六安县";}break;
                            case "22":{countyName="寿县";}break;
                            case "23":{countyName="霍邱县";}break;
                            case "24":{countyName="肥西县";}break;
                            case "25":{countyName="舒城县";}break;
                            case "26":{countyName="金寨县";}break;
                            case "27":{countyName="霍山县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--宣城地区************************
                    case "25":{
                        cityName="宣城地区";
                        switch (countyCode){
                            case "01":{countyName="宣州市";}break;
                            case "02":{countyName="宁国市";}break;
                            case "21":{countyName="宣州县";}break;
                            case "22":{countyName="郎溪县";}break;
                            case "23":{countyName="广德县";}break;
                            case "24":{countyName="宁国县";}break;
                            case "25":{countyName="当涂县";}break;
                            case "26":{countyName="繁昌县";}break;
                            case "27":{countyName="南陵县";}break;
                            case "28":{countyName="青阳县";}break;
                            case "29":{countyName="泾县";}break;
                            case "30":{countyName="旌德县";}break;
                            case "31":{countyName="绩溪县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--巢湖地区************************
                    case "26":{
                        cityName="巢湖地区";
                        switch (countyCode){
                            case "01":{countyName="巢湖市";}break;
                            case "21":{countyName="肥东县";}break;
                            case "22":{countyName="庐江县";}break;
                            case "23":{countyName="无为县";}break;
                            case "24":{countyName="巢县";}break;
                            case "25":{countyName="含山县";}break;
                            case "26":{countyName="和县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--徽州地区************************
                    case "27":{
                        cityName="徽州地区";
                        switch (countyCode){
                            case "01":{countyName="屯溪市";}break;
                            case "21":{countyName="绩溪县";}break;
                            case "22":{countyName="旌德县";}break;
                            case "23":{countyName="歙县";}break;
                            case "24":{countyName="休宁县";}break;
                            case "25":{countyName="黟县";}break;
                            case "26":{countyName="祁门县";}break;
                            case "27":{countyName="太平县";}break;
                            case "28":{countyName="石台县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--安庆地区************************
                    case "28":{
                        cityName="安庆地区";
                        switch (countyCode){
                            case "21":{countyName="怀宁县";}break;
                            case "22":{countyName="桐城县";}break;
                            case "23":{countyName="枞阳县";}break;
                            case "24":{countyName="潜山县";}break;
                            case "25":{countyName="太湖县";}break;
                            case "26":{countyName="宿松县";}break;
                            case "27":{countyName="望江县";}break;
                            case "28":{countyName="岳西县";}break;
                            case "29":{countyName="东至县";}break;
                            case "30":{countyName="贵池县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //安徽省--池州地区************************
                    case "29":{
                        cityName="池州地区";
                        switch (countyCode){
                            case "01":{countyName="贵池市";}break;
                            case "21":{countyName="东至县";}break;
                            case "22":{countyName="石台县";}break;
                            case "23":{countyName="青阳县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //福建省的身份证前六位对应关系==============================================================================
            case "35":{
                provinceName="福建省";
                switch (cityCode){
                    //福建省--福州市************************
                    case "01":{
                        cityName="福州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="鼓楼区";}break;
                            case "03":{countyName="台江区";}break;
                            case "04":{countyName="仓山区";}break;
                            case "05":{countyName="马尾区";}break;
                            case "11":{countyName="晋安区";}break;
                            case "20":{countyName="市区";}break;
                            case "21":{countyName="闽侯区";}break;
                            case "22":{countyName="连江县";}break;
                            case "23":{countyName="罗源县";}break;
                            case "24":{countyName="闽清县";}break;
                            case "25":{countyName="永泰县";}break;
                            case "26":{countyName="长乐县";}break;
                            case "27":{countyName="福清县";}break;
                            case "28":{countyName="平潭县";}break;
                            case "81":{countyName="福清市";}break;
                            case "82":{countyName="长乐市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--厦门市************************
                    case "02":{
                        cityName="厦门市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="鼓浪屿区";}break;
                            case "03":{countyName="思明区";}break;
                            case "04":{countyName="开元区";}break;
                            case "05":{countyName="海沧区";}break;
                            case "06":{countyName="湖里区";}break;
                            case "11":{countyName="集美区";}break;
                            case "12":{countyName="同安区";}break;
                            case "13":{countyName="翔安区";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--莆田市************************
                    case "03":{
                        cityName="莆田市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="城厢区";}break;
                            case "03":{countyName="涵江区";}break;
                            case "04":{countyName="荔城区";}break;
                            case "05":{countyName="秀屿区";}break;
                            case "21":{countyName="莆田县";}break;
                            case "22":{countyName="仙游县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--三明市************************
                    case "04":{
                        cityName="三明市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="梅列区";}break;
                            case "03":{countyName="三元区";}break;
                            case "04":{countyName="永安市";}break;
                            case "20":{countyName="永安市";}break;
                            case "21":{countyName="明溪县";}break;
                            case "22":{countyName="永安县";}break;
                            case "23":{countyName="清流县";}break;
                            case "24":{countyName="宁化县";}break;
                            case "25":{countyName="大田县";}break;
                            case "26":{countyName="龙溪县";}break;
                            case "27":{countyName="沙县";}break;
                            case "28":{countyName="将乐县";}break;
                            case "29":{countyName="泰宁县";}break;
                            case "30":{countyName="建宁县";}break;
                            case "81":{countyName="永安市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--泉州市************************
                    case "05":{
                        cityName="泉州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="鲤城区";}break;
                            case "03":{countyName="丰泽区";}break;
                            case "04":{countyName="洛江区";}break;
                            case "05":{countyName="泉港区";}break;
                            case "21":{countyName="惠安县";}break;
                            case "22":{countyName="晋江县";}break;
                            case "23":{countyName="南安县";}break;
                            case "24":{countyName="安溪县";}break;
                            case "25":{countyName="永春县";}break;
                            case "26":{countyName="德化县";}break;
                            case "27":{countyName="金门县";}break;
                            case "81":{countyName="石狮市";}break;
                            case "82":{countyName="晋江市";}break;
                            case "83":{countyName="南安市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--漳州市************************
                    case "06":{
                        cityName="漳州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="芗城县";}break;
                            case "03":{countyName="龙文区";}break;
                            case "21":{countyName="龙海县";}break;
                            case "22":{countyName="云霄县";}break;
                            case "23":{countyName="漳浦县";}break;
                            case "24":{countyName="诏安县";}break;
                            case "25":{countyName="长泰县";}break;
                            case "26":{countyName="东山县";}break;
                            case "27":{countyName="南靖县";}break;
                            case "28":{countyName="平和县";}break;
                            case "29":{countyName="华安县";}break;
                            case "81":{countyName="龙海市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--南平市************************
                    case "07":{
                        cityName="南平市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="延平区";}break;
                            case "21":{countyName="顺昌县";}break;
                            case "22":{countyName="浦城县";}break;
                            case "23":{countyName="光泽县";}break;
                            case "24":{countyName="松溪县";}break;
                            case "25":{countyName="政和县";}break;
                            case "81":{countyName="邵武县";}break;
                            case "82":{countyName="武夷山市";}break;
                            case "83":{countyName="建瓯市";}break;
                            case "84":{countyName="建阳市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--龙岩市************************
                    case "08":{
                        cityName="龙岩市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="新罗区";}break;
                            case "21":{countyName="长汀县";}break;
                            case "22":{countyName="永定县";}break;
                            case "23":{countyName="上杭县";}break;
                            case "24":{countyName="武平县";}break;
                            case "25":{countyName="连城县";}break;
                            case "81":{countyName="漳平市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--宁德市************************
                    case "09":{
                        cityName="宁德市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="蕉城区";}break;
                            case "21":{countyName="霞浦县";}break;
                            case "22":{countyName="古田县";}break;
                            case "23":{countyName="屏南县";}break;
                            case "24":{countyName="寿宁县";}break;
                            case "25":{countyName="周宁县";}break;
                            case "26":{countyName="拓荣县";}break;
                            case "81":{countyName="福安市";}break;
                            case "82":{countyName="福鼎市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--南平地区************************
                    case "21":{
                        cityName="南平地区";
                        switch (countyCode){
                            case "01":{countyName="南平市";}break;
                            case "02":{countyName="邵武市";}break;
                            case "03":{countyName="武夷山市";}break;
                            case "04":{countyName="建瓯市";}break;
                            case "21":{countyName="顺昌县";}break;
                            case "22":{countyName="建阳县";}break;
                            case "23":{countyName="建瓯县";}break;
                            case "24":{countyName="浦城县";}break;
                            case "25":{countyName="邵武县";}break;
                            case "26":{countyName="崇安县";}break;
                            case "27":{countyName="光泽县";}break;
                            case "28":{countyName="松溪县";}break;
                            case "29":{countyName="政和县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--宁德地区************************
                    case "22":{
                        cityName="宁德地区";
                        switch (countyCode){
                            case "01":{countyName="宁德市";}break;
                            case "02":{countyName="福安市";}break;
                            case "03":{countyName="福鼎市";}break;
                            case "21":{countyName="宁德县";}break;
                            case "22":{countyName="连江县";}break;
                            case "23":{countyName="罗源县";}break;
                            case "24":{countyName="福鼎县";}break;
                            case "25":{countyName="霞浦县";}break;
                            case "26":{countyName="福安县";}break;
                            case "27":{countyName="古田县";}break;
                            case "28":{countyName="屏南县";}break;
                            case "29":{countyName="寿宁县";}break;
                            case "30":{countyName="周宁县";}break;
                            case "31":{countyName="拓荣县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--龙岩地区************************
                    case "26":{
                        cityName="龙岩地区";
                        switch (countyCode){
                            case "01":{countyName="龙岩市";}break;
                            case "02":{countyName="漳平市";}break;
                            case "22":{countyName="长汀县";}break;
                            case "23":{countyName="永定县";}break;
                            case "24":{countyName="上杭县";}break;
                            case "25":{countyName="武平县";}break;
                            case "26":{countyName="漳平县";}break;
                            case "27":{countyName="连城县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--三明市************************
                    case "27":{
                        cityName="三明市";
                        switch (countyCode){
                            case "01":{countyName="三明市";}break;
                            case "21":{countyName="明溪县";}break;
                            case "22":{countyName="永安县";}break;
                            case "23":{countyName="清流县";}break;
                            case "24":{countyName="宁化县";}break;
                            case "25":{countyName="大田县";}break;
                            case "26":{countyName="龙溪县";}break;
                            case "27":{countyName="沙县";}break;
                            case "28":{countyName="将乐县";}break;
                            case "29":{countyName="泰宁县";}break;
                            case "30":{countyName="建宁县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //福建省--其它市************************
                    case "90":{
                        cityName="--";
                        switch (countyCode){
                            case "01":{countyName="永安市";}break;
                            case "02":{countyName="石狮市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //江西省的身份证前六位对应关系==============================================================================
            case "36":{
                provinceName="江西省";
                switch (cityCode){
                    //江西省--南昌市************************
                    case "01":{
                        cityName="南昌市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="东湖区";}break;
                            case "03":{countyName="西湖区";}break;
                            case "04":{countyName="青云谱区";}break;
                            case "05":{countyName="湾里区";}break;
                            case "11":{countyName="郊区";}break;
                            case "21":{countyName="南昌县";}break;
                            case "22":{countyName="新建县";}break;
                            case "23":{countyName="安义县";}break;
                            case "24":{countyName="进贤县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--景德镇市************************
                    case "02":{
                        cityName="景德镇市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="昌江区";}break;
                            case "03":{countyName="珠山区";}break;
                            case "11":{countyName="鹅湖区";}break;
                            case "12":{countyName="蛟潭区";}break;
                            case "21":{countyName="乐平县";}break;
                            case "22":{countyName="浮梁县";}break;
                            case "81":{countyName="乐平市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--萍乡市************************
                    case "03":{
                        cityName="萍乡市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="安源区";}break;
                            case "11":{countyName="上栗区";}break;
                            case "12":{countyName="泸溪县";}break;
                            case "13":{countyName="湘东区";}break;
                            case "21":{countyName="莲花县";}break;
                            case "22":{countyName="上栗县";}break;
                            case "23":{countyName="泸溪县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--九江市************************
                    case "04":{
                        cityName="九江市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="庐山区";}break;
                            case "03":{countyName="浔阳区";}break;
                            case "21":{countyName="九江县";}break;
                            case "22":{countyName="瑞昌县";}break;
                            case "23":{countyName="武宁县";}break;
                            case "24":{countyName="修水县";}break;
                            case "25":{countyName="永修县";}break;
                            case "26":{countyName="德安县";}break;
                            case "27":{countyName="星子县";}break;
                            case "28":{countyName="都昌县";}break;
                            case "29":{countyName="湖口县";}break;
                            case "30":{countyName="彭泽县";}break;
                            case "81":{countyName="瑞昌市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--新余市************************
                    case "05":{
                        cityName="新余市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="渝水区";}break;
                            case "21":{countyName="分宜县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--鹰潭市************************
                    case "06":{
                        cityName="鹰潭市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="月湖区";}break;
                            case "21":{countyName="贵溪县";}break;
                            case "22":{countyName="余江县";}break;
                            case "81":{countyName="贵溪市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--赣州市************************
                    case "07":{
                        cityName="赣州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="章贡区";}break;
                            case "21":{countyName="赣县";}break;
                            case "22":{countyName="信丰县";}break;
                            case "23":{countyName="大余县";}break;
                            case "24":{countyName="上犹县";}break;
                            case "25":{countyName="崇义县";}break;
                            case "26":{countyName="安远县";}break;
                            case "27":{countyName="龙南县";}break;
                            case "28":{countyName="定南县";}break;
                            case "29":{countyName="全南县";}break;
                            case "30":{countyName="宁都县";}break;
                            case "31":{countyName="于都县";}break;
                            case "32":{countyName="兴国县";}break;
                            case "33":{countyName="会昌县";}break;
                            case "34":{countyName="寻乌县";}break;
                            case "35":{countyName="石城县";}break;
                            case "81":{countyName="瑞金市";}break;
                            case "82":{countyName="南康市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--吉安市************************
                    case "08":{
                        cityName="吉安市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="吉州区";}break;
                            case "03":{countyName="青原区";}break;
                            case "21":{countyName="吉安县";}break;
                            case "22":{countyName="吉水县";}break;
                            case "23":{countyName="峡江县";}break;
                            case "24":{countyName="新干县";}break;
                            case "25":{countyName="永丰县";}break;
                            case "26":{countyName="泰和县";}break;
                            case "27":{countyName="遂川县";}break;
                            case "28":{countyName="万安县";}break;
                            case "29":{countyName="安福县";}break;
                            case "30":{countyName="永新县";}break;
                            case "81":{countyName="井冈山市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--宜春市************************
                    case "09":{
                        cityName="宜春市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="袁州区";}break;
                            case "21":{countyName="奉新县";}break;
                            case "22":{countyName="万载县";}break;
                            case "23":{countyName="上高县";}break;
                            case "24":{countyName="宜丰县";}break;
                            case "25":{countyName="靖安县";}break;
                            case "26":{countyName="铜鼓县";}break;
                            case "81":{countyName="丰城市";}break;
                            case "82":{countyName="樟树市";}break;
                            case "83":{countyName="高安市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--抚州市************************
                    case "10":{
                        cityName="抚州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="临川区";}break;
                            case "21":{countyName="南城县";}break;
                            case "22":{countyName="黎川县";}break;
                            case "23":{countyName="南丰县";}break;
                            case "24":{countyName="崇仁县";}break;
                            case "25":{countyName="乐安县";}break;
                            case "26":{countyName="宜黄县";}break;
                            case "27":{countyName="金溪县";}break;
                            case "28":{countyName="资溪县";}break;
                            case "29":{countyName="东乡县";}break;
                            case "30":{countyName="广昌县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--上饶市************************
                    case "11":{
                        cityName="上饶市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="信州区";}break;
                            case "21":{countyName="上饶县";}break;
                            case "22":{countyName="广丰县";}break;
                            case "23":{countyName="玉山县";}break;
                            case "24":{countyName="铅山县";}break;
                            case "25":{countyName="横峰县";}break;
                            case "26":{countyName="弋阳县";}break;
                            case "27":{countyName="余干县";}break;
                            case "28":{countyName="鄱阳县";}break;
                            case "29":{countyName="万年县";}break;
                            case "30":{countyName="婺源县";}break;
                            case "81":{countyName="德兴市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--赣州地区************************
                    case "21":{
                        cityName="赣州地区";
                        switch (countyCode){
                            case "01":{countyName="赣州市";}break;
                            case "02":{countyName="瑞金市";}break;
                            case "03":{countyName="南康市";}break;
                            case "21":{countyName="赣县";}break;
                            case "22":{countyName="南康市";}break;
                            case "23":{countyName="信丰市";}break;
                            case "24":{countyName="大余县";}break;
                            case "25":{countyName="上犹县";}break;
                            case "26":{countyName="崇义县";}break;
                            case "27":{countyName="安远县";}break;
                            case "28":{countyName="龙南县";}break;
                            case "29":{countyName="定南县";}break;
                            case "30":{countyName="全南县";}break;
                            case "31":{countyName="宁都县";}break;
                            case "32":{countyName="于都县";}break;
                            case "33":{countyName="兴国县";}break;
                            case "34":{countyName="瑞金市";}break;
                            case "35":{countyName="会昌县";}break;
                            case "36":{countyName="寻乌县";}break;
                            case "37":{countyName="石城县";}break;
                            case "38":{countyName="广昌县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--宜春地区************************
                    case "22":{
                        cityName="宜春地区";
                        switch (countyCode){
                            case "01":{countyName="宜春市";}break;
                            case "02":{countyName="丰城市";}break;
                            case "03":{countyName="樟树市";}break;
                            case "04":{countyName="高安市";}break;
                            case "21":{countyName="丰城县";}break;
                            case "22":{countyName="高安县";}break;
                            case "23":{countyName="清江县";}break;
                            case "24":{countyName="新余县";}break;
                            case "25":{countyName="宜春县";}break;
                            case "26":{countyName="奉新县";}break;
                            case "27":{countyName="万载县";}break;
                            case "28":{countyName="上高县";}break;
                            case "29":{countyName="宜丰县";}break;
                            case "30":{countyName="分宜县";}break;
                            case "31":{countyName="安义县";}break;
                            case "32":{countyName="靖安县";}break;
                            case "33":{countyName="铜鼓县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--上饶地区************************
                    case "23":{
                        cityName="上饶地区";
                        switch (countyCode){
                            case "01":{countyName="上饶市";}break;
                            case "02":{countyName="德兴市";}break;
                            case "21":{countyName="上饶县";}break;
                            case "22":{countyName="广丰县";}break;
                            case "23":{countyName="玉山县";}break;
                            case "24":{countyName="铅山县";}break;
                            case "25":{countyName="横峰县";}break;
                            case "26":{countyName="弋阳县";}break;
                            case "27":{countyName="贵溪县";}break;
                            case "28":{countyName="余江县";}break;
                            case "29":{countyName="余干县";}break;
                            case "30":{countyName="波阳县";}break;
                            case "31":{countyName="万年县";}break;
                            case "32":{countyName="乐平县";}break;
                            case "33":{countyName="德兴县";}break;
                            case "34":{countyName="婺源县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--吉安地区************************
                    case "24":{
                        cityName="吉安地区";
                        switch (countyCode){
                            case "01":{countyName="吉安市";}break;
                            case "02":{countyName="井冈山市";}break;
                            case "21":{countyName="吉安县";}break;
                            case "22":{countyName="吉水县";}break;
                            case "23":{countyName="峡江县";}break;
                            case "24":{countyName="新干县";}break;
                            case "25":{countyName="永丰县";}break;
                            case "26":{countyName="泰和县";}break;
                            case "27":{countyName="遂川县";}break;
                            case "28":{countyName="万安县";}break;
                            case "29":{countyName="安福县";}break;
                            case "30":{countyName="永新县";}break;
                            case "31":{countyName="莲花县";}break;
                            case "32":{countyName="宁冈县";}break;
                            case "33":{countyName="井冈山县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--抚州地区************************
                    case "25":{
                        cityName="抚州地区";
                        switch (countyCode){
                            case "01":{countyName="临川市";}break;
                            case "02":{countyName="临川市";}break;
                            case "21":{countyName="临川县";}break;
                            case "22":{countyName="南城县";}break;
                            case "23":{countyName="黎川县";}break;
                            case "24":{countyName="南丰县";}break;
                            case "25":{countyName="崇仁县";}break;
                            case "26":{countyName="乐安县";}break;
                            case "27":{countyName="宜黄县";}break;
                            case "28":{countyName="金溪县";}break;
                            case "29":{countyName="资溪县";}break;
                            case "30":{countyName="进贤县";}break;
                            case "31":{countyName="东乡县";}break;
                            case "32":{countyName="广昌县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //江西省--九江地区************************
                    case "26":{
                        cityName="九江地区";
                        switch (countyCode){
                            case "21":{countyName="九江县";}break;
                            case "22":{countyName="瑞昌县";}break;
                            case "23":{countyName="武宁县";}break;
                            case "24":{countyName="修水县";}break;
                            case "25":{countyName="永修县";}break;
                            case "26":{countyName="德安县";}break;
                            case "27":{countyName="星子县";}break;
                            case "28":{countyName="都昌县";}break;
                            case "29":{countyName="湖口县";}break;
                            case "30":{countyName="彭泽县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //山东省的身份证前六位对应关系==============================================================================
            case "37":{
                provinceName="山东省";
                switch (cityCode){
                    //山东省--济南市************************
                    case "01":{
                        cityName="济南市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="历下区";}break;
                            case "03":{countyName="市中区";}break;
                            case "04":{countyName="槐荫区";}break;
                            case "05":{countyName="天桥区";}break;
                            case "11":{countyName="郊区";}break;
                            case "12":{countyName="历城区";}break;
                            case "13":{countyName="长清区";}break;
                            case "20":{countyName="市区";}break;
                            case "21":{countyName="历城县";}break;
                            case "22":{countyName="章丘县";}break;
                            case "23":{countyName="长清县";}break;
                            case "24":{countyName="平阴县";}break;
                            case "25":{countyName="济阳县";}break;
                            case "26":{countyName="商河县";}break;
                            case "81":{countyName="章丘县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--青岛市************************
                    case "02":{
                        cityName="青岛市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="市南区";}break;
                            case "03":{countyName="市北区";}break;
                            case "04":{countyName="台东区";}break;
                            case "05":{countyName="四方区";}break;
                            case "06":{countyName="沧口区";}break;
                            case "11":{countyName="黄岛区";}break;
                            case "12":{countyName="崂山区";}break;
                            case "13":{countyName="李沧区";}break;
                            case "14":{countyName="城阳区";}break;
                            case "20":{countyName="市区";}break;
                            case "21":{countyName="崂山县";}break;
                            case "22":{countyName="即墨县";}break;
                            case "23":{countyName="胶南县";}break;
                            case "24":{countyName="胶县";}break;
                            case "25":{countyName="莱西县";}break;
                            case "26":{countyName="平度县";}break;
                            case "81":{countyName="胶州市";}break;
                            case "82":{countyName="即墨市";}break;
                            case "83":{countyName="平度市";}break;
                            case "84":{countyName="胶南市";}break;
                            case "85":{countyName="莱西市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--淄博市************************
                    case "03":{
                        cityName="淄博市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="淄川区";}break;
                            case "03":{countyName="张店区";}break;
                            case "04":{countyName="博山区";}break;
                            case "05":{countyName="临淄区";}break;
                            case "06":{countyName="周村区";}break;
                            case "21":{countyName="桓台县";}break;
                            case "22":{countyName="高青县";}break;
                            case "23":{countyName="沂源县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--枣庄市************************
                    case "04":{
                        cityName="枣庄市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="市中区";}break;
                            case "03":{countyName="薛城区";}break;
                            case "04":{countyName="峄城区";}break;
                            case "05":{countyName="台儿庄区";}break;
                            case "06":{countyName="山亭区";}break;
                            case "20":{countyName="市区";}break;
                            case "21":{countyName="藤县";}break;
                            case "81":{countyName="滕州市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--东营市************************
                    case "05":{
                        cityName="东营市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="东营区";}break;
                            case "03":{countyName="河口区";}break;
                            case "21":{countyName="垦利区";}break;
                            case "22":{countyName="利津县";}break;
                            case "23":{countyName="广饶县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--烟台市************************
                    case "06":{
                        cityName="烟台市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="芝罘区";}break;
                            case "11":{countyName="福山区";}break;
                            case "12":{countyName="牟平区";}break;
                            case "13":{countyName="莱山区";}break;
                            case "19":{countyName="龙口市";}break;
                            case "20":{countyName="威海市";}break;
                            case "22":{countyName="蓬莱县";}break;
                            case "23":{countyName="黄县";}break;
                            case "24":{countyName="招远县";}break;
                            case "25":{countyName="掖县";}break;
                            case "27":{countyName="莱阳县";}break;
                            case "28":{countyName="栖霞县";}break;
                            case "29":{countyName="海阳县";}break;
                            case "31":{countyName="牟平县";}break;
                            case "32":{countyName="文登县";}break;
                            case "33":{countyName="容城县";}break;
                            case "34":{countyName="长岛县";}break;
                            case "81":{countyName="龙口市";}break;
                            case "82":{countyName="莱阳市";}break;
                            case "83":{countyName="莱州市";}break;
                            case "84":{countyName="蓬莱市";}break;
                            case "85":{countyName="招远市";}break;
                            case "86":{countyName="栖霞市";}break;
                            case "87":{countyName="海阳市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--潍坊市************************
                    case "07":{
                        cityName="潍坊市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="潍城区";}break;
                            case "03":{countyName="寒亭区";}break;
                            case "04":{countyName="坊子区";}break;
                            case "05":{countyName="奎文区";}break;
                            case "19":{countyName="青州市";}break;
                            case "21":{countyName="益都县";}break;
                            case "22":{countyName="安丘县";}break;
                            case "23":{countyName="寿光县";}break;
                            case "24":{countyName="临朐县";}break;
                            case "25":{countyName="昌乐县";}break;
                            case "26":{countyName="昌邑县";}break;
                            case "27":{countyName="高密县";}break;
                            case "28":{countyName="诸城县";}break;
                            case "29":{countyName="五莲县";}break;
                            case "81":{countyName="青州市";}break;
                            case "82":{countyName="诸城市";}break;
                            case "83":{countyName="寿光市";}break;
                            case "84":{countyName="安丘市";}break;
                            case "85":{countyName="高密市";}break;
                            case "86":{countyName="昌邑市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--济宁市************************
                    case "08":{
                        cityName="济宁市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="市中区";}break;
                            case "11":{countyName="任城区";}break;
                            case "19":{countyName="曲阜市";}break;
                            case "22":{countyName="衮州县";}break;
                            case "23":{countyName="曲阜县";}break;
                            case "25":{countyName="邹县";}break;
                            case "26":{countyName="微山县";}break;
                            case "27":{countyName="鱼台县";}break;
                            case "28":{countyName="金乡县";}break;
                            case "29":{countyName="嘉祥县";}break;
                            case "30":{countyName="汶上县";}break;
                            case "31":{countyName="泗水县";}break;
                            case "32":{countyName="梁山县";}break;
                            case "81":{countyName="曲阜市";}break;
                            case "82":{countyName="衮州市";}break;
                            case "83":{countyName="邹城市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--泰安市************************
                    case "09":{
                        cityName="泰安市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="泰山区";}break;
                            case "03":{countyName="岱岳区";}break;
                            case "11":{countyName="郊区";}break;
                            case "19":{countyName="莱芜市";}break;
                            case "20":{countyName="新泰市";}break;
                            case "21":{countyName="宁阳县";}break;
                            case "22":{countyName="肥城县";}break;
                            case "23":{countyName="东平县";}break;
                            case "81":{countyName="莱芜市";}break;
                            case "82":{countyName="新泰市";}break;
                            case "83":{countyName="肥城市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--威海市************************
                    case "10":{
                        cityName="威海市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="环翠区";}break;
                            case "21":{countyName="乳山县";}break;
                            case "81":{countyName="文登市";}break;
                            case "82":{countyName="荣成市";}break;
                            case "83":{countyName="乳山市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--日照市************************
                    case "11":{
                        cityName="日照市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="东港区";}break;
                            case "03":{countyName="岚山区";}break;
                            case "21":{countyName="五莲县";}break;
                            case "22":{countyName="莒县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--莱芜市************************
                    case "12":{
                        cityName="莱芜市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="莱城区";}break;
                            case "03":{countyName="钢城区";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--临沂市************************
                    case "13":{
                        cityName="临沂市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="兰山区";}break;
                            case "11":{countyName="罗庄区";}break;
                            case "12":{countyName="河东区";}break;
                            case "21":{countyName="沂南县";}break;
                            case "22":{countyName="郯城县";}break;
                            case "23":{countyName="沂水县";}break;
                            case "24":{countyName="苍山县";}break;
                            case "25":{countyName="费县";}break;
                            case "26":{countyName="平邑县";}break;
                            case "27":{countyName="莒南县";}break;
                            case "28":{countyName="蒙阴县";}break;
                            case "29":{countyName="临沭县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--德州市************************
                    case "14":{
                        cityName="德州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="德城区";}break;
                            case "21":{countyName="陵县";}break;
                            case "22":{countyName="宁津县";}break;
                            case "23":{countyName="庆云县";}break;
                            case "24":{countyName="临邑县";}break;
                            case "25":{countyName="齐河县";}break;
                            case "26":{countyName="平原县";}break;
                            case "27":{countyName="夏津县";}break;
                            case "28":{countyName="武城县";}break;
                            case "81":{countyName="乐陵市";}break;
                            case "82":{countyName="禹城市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--聊城市************************
                    case "15":{
                        cityName="聊城市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="东昌府区";}break;
                            case "21":{countyName="阳谷县";}break;
                            case "22":{countyName="莘县";}break;
                            case "23":{countyName="茌平县";}break;
                            case "24":{countyName="东阿县";}break;
                            case "25":{countyName="冠县";}break;
                            case "26":{countyName="高唐县";}break;
                            case "81":{countyName="临清市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--滨州市************************
                    case "16":{
                        cityName="滨州市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="滨城区";}break;
                            case "21":{countyName="惠民县";}break;
                            case "22":{countyName="阳信县";}break;
                            case "23":{countyName="无棣县";}break;
                            case "24":{countyName="沾化县";}break;
                            case "25":{countyName="博兴县";}break;
                            case "26":{countyName="邹平县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--菏泽市************************
                    case "17":{
                        cityName="菏泽市";
                        switch (countyCode){
                            case "01":{countyName="市辖区";}break;
                            case "02":{countyName="牡丹区";}break;
                            case "21":{countyName="曹县";}break;
                            case "22":{countyName="单县";}break;
                            case "23":{countyName="成武县";}break;
                            case "24":{countyName="巨野县";}break;
                            case "25":{countyName="郓城县";}break;
                            case "26":{countyName="鄄城县";}break;
                            case "27":{countyName="定陶县";}break;
                            case "28":{countyName="东明县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--烟台地区************************
                    case "21":{
                        cityName="烟台地区";
                        switch (countyCode){
                            case "01":{countyName="烟台市";}break;
                            case "02":{countyName="威海市";}break;
                            case "21":{countyName="福山县";}break;
                            case "22":{countyName="蓬莱县";}break;
                            case "24":{countyName="招远县";}break;
                            case "25":{countyName="掖县";}break;
                            case "26":{countyName="莱西县";}break;
                            case "27":{countyName="莱阳县";}break;
                            case "28":{countyName="栖霞县";}break;
                            case "29":{countyName="海阳县";}break;
                            case "30":{countyName="乳山县";}break;
                            case "31":{countyName="牟平县";}break;
                            case "32":{countyName="文登县";}break;
                            case "33":{countyName="容城县";}break;
                            case "34":{countyName="长岛县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--潍坊地区************************
                    case "22":{
                        cityName="潍坊地区";
                        switch (countyCode){
                            case "01":{countyName="潍坊市";}break;
                            case "21":{countyName="益都县";}break;
                            case "22":{countyName="安丘县";}break;
                            case "23":{countyName="寿光县";}break;
                            case "24":{countyName="临朐县";}break;
                            case "25":{countyName="昌乐县";}break;
                            case "26":{countyName="昌邑县";}break;
                            case "27":{countyName="高密县";}break;
                            case "28":{countyName="诸城县";}break;
                            case "30":{countyName="平度县";}break;
                            case "31":{countyName="潍县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--滨州地区************************
                    case "23":{
                        cityName="滨州地区";
                        switch (countyCode){
                            case "01":{countyName="滨州市";}break;
                            case "21":{countyName="惠民县";}break;
                            case "22":{countyName="滨县";}break;
                            case "23":{countyName="阳信县";}break;
                            case "24":{countyName="无棣县";}break;
                            case "25":{countyName="沾化县";}break;
                            case "26":{countyName="利津县";}break;
                            case "27":{countyName="广饶县";}break;
                            case "28":{countyName="博兴县";}break;
                            case "29":{countyName="桓台县";}break;
                            case "30":{countyName="邹平县";}break;
                            case "31":{countyName="高青县";}break;
                            case "32":{countyName="垦利县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--德州地区************************
                    case "24":{
                        cityName="德州地区";
                        switch (countyCode){
                            case "01":{countyName="德州市";}break;
                            case "02":{countyName="乐陵市";}break;
                            case "03":{countyName="禹城市";}break;
                            case "21":{countyName="陵县";}break;
                            case "22":{countyName="平原县";}break;
                            case "23":{countyName="夏津县";}break;
                            case "24":{countyName="武城县";}break;
                            case "25":{countyName="齐河县";}break;
                            case "26":{countyName="禹城县";}break;
                            case "27":{countyName="乐陵县";}break;
                            case "28":{countyName="临邑县";}break;
                            case "29":{countyName="商河县";}break;
                            case "30":{countyName="济阳县";}break;
                            case "31":{countyName="宁津县";}break;
                            case "32":{countyName="庆云县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--聊城市************************
                    case "25":{
                        cityName="聊城市";
                        switch (countyCode){
                            case "01":{countyName="聊城市";}break;
                            case "02":{countyName="东昌府区";}break;
                            case "21":{countyName="阳谷县";}break;
                            case "22":{countyName="莘县";}break;
                            case "23":{countyName="茌平县";}break;
                            case "24":{countyName="东阿县";}break;
                            case "25":{countyName="冠县";}break;
                            case "26":{countyName="高唐县";}break;
                            case "27":{countyName="高唐县";}break;
                            case "28":{countyName="临清县";}break;
                            case "81":{countyName="临清市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--泰安地区************************
                    case "26":{
                        cityName="泰安地区";
                        switch (countyCode){
                            case "01":{countyName="泰安市";}break;
                            case "02":{countyName="莱芜市";}break;
                            case "03":{countyName="新泰市";}break;
                            case "22":{countyName="莱芜县";}break;
                            case "23":{countyName="新泰县";}break;
                            case "24":{countyName="宁阳县";}break;
                            case "25":{countyName="肥城县";}break;
                            case "26":{countyName="东平县";}break;
                            case "27":{countyName="平阴县";}break;
                            case "28":{countyName="新汶县";}break;
                            case "30":{countyName="泗水县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--济宁地区************************
                    case "27":{
                        cityName="济宁地区";
                        switch (countyCode){
                            case "01":{countyName="济宁市";}break;
                            case "22":{countyName="衮州县";}break;
                            case "23":{countyName="曲阜县";}break;
                            case "24":{countyName="泗水县";}break;
                            case "25":{countyName="邹县";}break;
                            case "26":{countyName="微山县";}break;
                            case "27":{countyName="鱼台县";}break;
                            case "28":{countyName="金乡县";}break;
                            case "29":{countyName="嘉祥县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--临沂地区************************
                    case "28":{
                        cityName="临沂地区";
                        switch (countyCode){
                            case "01":{countyName="临沂市";}break;
                            case "02":{countyName="日照市";}break;
                            case "21":{countyName="临沂县";}break;
                            case "22":{countyName="郯城县";}break;
                            case "23":{countyName="苍山县";}break;
                            case "24":{countyName="莒南县";}break;
                            case "25":{countyName="日照县";}break;
                            case "26":{countyName="莒县";}break;
                            case "27":{countyName="沂水县";}break;
                            case "28":{countyName="沂源县";}break;
                            case "29":{countyName="蒙阴县";}break;
                            case "30":{countyName="平邑县";}break;
                            case "31":{countyName="费县";}break;
                            case "32":{countyName="沂南县";}break;
                            case "33":{countyName="临沭县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--菏泽地区************************
                    case "29":{
                        cityName="菏泽地区";
                        switch (countyCode){
                            case "01":{countyName="菏泽市";}break;
                            case "21":{countyName="菏泽县";}break;
                            case "22":{countyName="曹县";}break;
                            case "23":{countyName="定陶县";}break;
                            case "24":{countyName="成武县";}break;
                            case "25":{countyName="单县";}break;
                            case "26":{countyName="巨野县";}break;
                            case "27":{countyName="梁山县";}break;
                            case "28":{countyName="郓城县";}break;
                            case "29":{countyName="鄄城县";}break;
                            case "30":{countyName="东明县";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //山东省--其它市************************
                    case "90":{
                        cityName="--";
                        switch (countyCode){
                            case "01":{countyName="青州市";}break;
                            case "02":{countyName="龙口市";}break;
                            case "03":{countyName="曲阜市";}break;
                            case "04":{countyName="莱芜市";}break;
                            case "05":{countyName="新泰市";}break;
                            case "06":{countyName="胶州市";}break;
                            case "07":{countyName="诸城市";}break;
                            case "08":{countyName="莱阳市";}break;
                            case "09":{countyName="莱州市";}break;
                            case "10":{countyName="滕州市";}break;
                            case "11":{countyName="文登市";}break;
                            case "12":{countyName="荣成市";}break;
                            default:countyName="身份证号码5-6位有误";break;
                        }
                    }break;
                    //********************************
                    default:{
                        cityName="(身份证号码3-4位有误)";
                        countyName="--";
                    }break;
                }
            }break;
            //河南省的身份证前六位对应关系==============================================================================
            case "41":{
                provinceName="河南省";

            }break;
            //湖北省的身份证前六位对应关系==============================================================================
            case "42":{provinceName="湖北省";}break;
            //湖南省的身份证前六位对应关系==============================================================================
            case "43":{provinceName="湖南省";}break;
            //广东省的身份证前六位对应关系==============================================================================
            case "44":{provinceName="广东省";}break;
            //广西壮族自治区的身份证前六位对应关系==============================================================================
            case "45":{provinceName="广西壮族自治区";}break;
            //海南省的身份证前六位对应关系==============================================================================
            case "46":{provinceName="海南省";}break;
            //重庆市的身份证前六位对应关系==============================================================================
            case "50":{provinceName="重庆市";}break;
            //四川省的身份证前六位对应关系==============================================================================
            case "51":{provinceName="四川省";}break;
            //贵州省的身份证前六位对应关系==============================================================================
            case "52":{provinceName="贵州省";}break;
            //云南省的身份证前六位对应关系==============================================================================
            case "53":{provinceName="云南省";}break;
            //西藏自治区的身份证前六位对应关系==============================================================================
            case "54":{provinceName="西藏自治区";}break;
            //陕西省的身份证前六位对应关系==============================================================================
            case "61":{provinceName="陕西省";}break;
            //甘肃省的身份证前六位对应关系==============================================================================
            case "62":{provinceName="甘肃省";}break;
            //青海省的身份证前六位对应关系==============================================================================
            case "63":{provinceName="青海省";}break;
            //宁夏回族自治区的身份证前六位对应关系==============================================================================
            case "64":{provinceName="宁夏回族自治区";}break;
            //新疆维吾尔自治区的身份证前六位对应关系==============================================================================
            case "65":{provinceName="新疆维吾尔自治区";}break;
            //台湾省的身份证前六位对应关系==============================================================================
            case "71":{provinceName="台湾省";}break;
            //香港特别行政区的身份证前六位对应关系==============================================================================
            case "81":{provinceName="香港特别行政区";}break;
            //澳门特别行政区的身份证前六位对应关系==============================================================================
            case "82":{provinceName="澳门特别行政区";}break;
            default:{
                provinceName = "(身份证号码1-2位有误)";
                cityName = "--";
                countyName = "--";
            }break;
        }
        return provinceName+"/"+cityName+"/"+countyName;
    }*/

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
