package com.elex.oa.util.project;

import com.elex.oa.entity.project.ProjectCode;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectUtils {

    public static String codeValidate(String original) { //验证编码是否为当前月的
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date()).substring(0,8);
        if(original.contains(date)) {
            return  "1";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date);
        stringBuilder.append("0002");
        return stringBuilder.toString();
    }

    public static String projectCode(ProjectCode projectCode) {
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] date = simpleDateFormat.format(new Date()).split("-");
        String[] oDate = original.split("-");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ELEX-PRJ-99-");
        if(date[0].equals(oDate[3])) {
            String oMonth = oDate[4].substring(0,2);
            if(date[1].equals(oMonth)) {
                stringBuilder.append(date[0]);
                stringBuilder.append("-");
                int code = Integer.parseInt(oDate[4])+1;
                String codeS = code+"";
                if(codeS.length() == 6){

                } else {
                    stringBuilder.append("0");
                }
                stringBuilder.append(codeS);
                return stringBuilder.toString();
            }
        }
        stringBuilder.append(date[0]);
        stringBuilder.append("-");
        stringBuilder.append(date[1]);
        stringBuilder.append("0001");*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        return "";
    }

    public static String lowerToCaptial(double money) {
        BigDecimal numberOfMoney = new BigDecimal(money);
        final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆","伍", "陆", "柒", "捌", "玖" };
        final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元","拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾","佰", "仟" };
        final String CN_FULL = "整";
        final String CN_NEGATIVE = "负";
        final int MONEY_PRECISION = 2;
        final String CN_ZEOR_FULL = "零元" + CN_FULL;
        StringBuffer sb = new StringBuffer();
        int signum = numberOfMoney.signum();
        if(signum == 0) {
            return CN_ZEOR_FULL;
        }
        long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0,4).abs().longValue();
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;

        if((scale <= 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if((scale > 0) && (scale % 10 <= 0)) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if(number <= 0) {
                break;
            }
            numUnit = (int) (number % 10);
            if(numUnit > 0) {
                if((numIndex == 9) && (zeroSize >=3)) {
                    sb.insert(0,CN_UPPER_MONETRAY_UNIT[6]);
                }
                if((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0,CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0,CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0,CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++ zeroSize;
                if(getZero) {

                } else {
                    sb.insert(0,CN_UPPER_NUMBER[numUnit]);
                }
                if(numIndex == 2) {
                    if(number > 0) {
                        sb.insert(0,CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if(((numIndex - 2) % 4 ==0) && (number % 1000 > 0)) {
                    sb.insert(0,CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            number = number / 10;
            ++ numIndex;
        }
        if(signum == -1) {
            sb.insert(0,CN_NEGATIVE);
        }
        if(scale <= 0) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }
}
