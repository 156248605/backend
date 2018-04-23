package com.elex.oa.util;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.codec.binary.Base64;
/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
public class StringUtil {
    private static String SPECIAL_REG_EX = "[`~!@#$%^&*()+=|{}\':;\',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"]";

    public StringUtil() {
    }

    public static String getArrayString(String[] arr, String split) {
        StringBuffer sb = new StringBuffer();
        String[] var3 = arr;
        int var4 = arr.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String a = var3[var5];
            sb.append(a).append(split);
        }

        if(sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String getParentPath(String fullPath) {
        String subString = fullPath.substring(0, fullPath.length() - 1);
        int index = subString.lastIndexOf(".");
        if(index != -1) {
            String newStr = subString.substring(0, index + 1);
            return newStr;
        } else {
            return subString;
        }
    }

    public static String getArrCharString(String path) {
        StringBuffer sb = new StringBuffer();
        String[] arr = path.split("[.]");

        for(int i = 0; i < arr.length; ++i) {
            if(!"0".equals(arr[i]) && !"".equals(arr)) {
                if(sb.length() > 0) {
                    sb.append(",");
                }

                sb.append("\'").append(arr[i]).append("\'");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = getArrCharString("1.2.3.4.");
        System.out.print(s);
    }

    public static String getCollectionString(Collection<String> cols, String splitChart) {
        StringBuffer sb = new StringBuffer();
        Iterator var3 = cols.iterator();

        while(var3.hasNext()) {
            String a = (String)var3.next();
            sb.append(a).append(splitChart);
        }

        if(sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static boolean isNumeric(String str) {
        int i = str.length();

        do {
            --i;
            if(i < 0) {
                return true;
            }
        } while(Character.isDigit(str.charAt(i)));

        return false;
    }

    public static int getAppearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);

        for(Matcher m = p.matcher(srcText); m.find(); ++count) {
            ;
        }

        return count;
    }

    public static String makeFirstLetterLowerCase(String newStr) {
        return toFirst(newStr, false);
    }

    public static String makeFirstLetterUpperCase(String newStr) {
        return toFirst(newStr, true);
    }

    public static Set<String> toSet(String includeSplitStr) {
        return toSet(includeSplitStr, ",");
    }

    public static Set<String> toSet(String includeSplitStr, String splitChars) {
        HashSet sets = new HashSet();
        String[] tmps = includeSplitStr.split(splitChars);
        String[] var4 = tmps;
        int var5 = tmps.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String tmp = var4[var6];
            sets.add(tmp);
        }

        return sets;
    }



    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();

        for(int i = 0; i < bGBK.length; ++i) {
            strBuf.append(Integer.toHexString(bGBK[i] & 255));
        }

        return strBuf.toString();
    }

    public static String encodeStr(String plainText) {
        byte[] b = plainText.getBytes();
        Base64 base64 = new Base64();
        b = base64.encode(b);
        String s = new String(b);
        return s;
    }

    public static String decodeStr(String encodeStr) {
        byte[] b = encodeStr.getBytes();
        Base64 base64 = new Base64();
        b = base64.decode(b);
        String s = new String(b);
        return s;
    }

    public static String convertToChineseNumeral(double amount) {
        char[] hunit = new char[]{'拾', '佰', '仟'};
        char[] vunit = new char[]{'万', '亿'};
        char[] digit = new char[]{'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
        long midVal = (long)(amount * 100.0D);
        String valStr = String.valueOf(midVal);
        String head = valStr.substring(0, valStr.length() - 2);
        String rail = valStr.substring(valStr.length() - 2);
        String prefix = "";
        String suffix = "";
        if(rail.equals("00")) {
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - 48] + "角" + digit[rail.charAt(1) - 48] + "分";
        }

        char[] chDig = head.toCharArray();
        char zero = 48;
        byte zeroSerNum = 0;

        for(int i = 0; i < chDig.length; ++i) {
            int idx = (chDig.length - i - 1) % 4;
            int vidx = (chDig.length - i - 1) / 4;
            if(chDig[i] == 48) {
                ++zeroSerNum;
                if(zero == 48) {
                    zero = digit[0];
                } else if(idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix = prefix + vunit[vidx - 1];
                    zero = 48;
                }
            } else {
                zeroSerNum = 0;
                if(zero != 48) {
                    prefix = prefix + zero;
                    zero = 48;
                }

                prefix = prefix + digit[chDig[i] - 48];
                if(idx > 0) {
                    prefix = prefix + hunit[idx - 1];
                }

                if(idx == 0 && vidx > 0) {
                    prefix = prefix + vunit[vidx - 1];
                }
            }
        }

        if(prefix.length() > 0) {
            prefix = prefix + '圆';
        }

        return prefix + suffix;
    }

    public static String format(String message, Object... args) {
        for(int i = 0; i < args.length; ++i) {
            message = message.replace("${" + i + "}", args[i].toString());
        }

        return message;
    }

    public static String format(String message, Map<String, Object> params) {
        String result = message;
        if(params != null && !params.isEmpty()) {
            Iterator keyIts = params.keySet().iterator();

            while(keyIts.hasNext()) {
                String key = (String)keyIts.next();
                Object value = params.get(key);
                if(value != null) {
                    result = result.replace("${" + key + "}", value.toString());
                }
            }

            return result;
        } else {
            return message;
        }
    }

    public static boolean isExist(String content, String beginStr, String endStr) {
        boolean isExist = true;
        String lowContent = content.toLowerCase();
        String lowBeginStr = beginStr.toLowerCase();
        String lowEndStr = endStr.toLowerCase();
        int beginIndex = lowContent.indexOf(lowBeginStr);
        int endIndex = lowContent.indexOf(lowEndStr);
        return beginIndex != -1 && endIndex != -1 && beginIndex < endIndex;
    }

    public static String trimPrefix(String content, String prefix) {
        if(isEmpty(prefix)) {
            return content;
        } else {
            String resultStr;
            for(resultStr = content; resultStr.startsWith(prefix); resultStr = resultStr.substring(prefix.length())) {
                ;
            }

            return resultStr;
        }
    }

    public static String trimSuffix(String content, String suffix) {
        if(isEmpty(suffix)) {
            return content;
        } else {
            String resultStr;
            for(resultStr = content; resultStr.endsWith(suffix); resultStr = resultStr.substring(0, resultStr.length() - suffix.length())) {
                ;
            }

            return resultStr;
        }
    }

    public static String trimSuffixOnce(String content, String suffix) {
        return isEmpty(suffix)?content:(!content.endsWith(suffix)?content:content.substring(0, content.length() - suffix.length()));
    }

    public static String trim(String content, String trimStr) {
        return trimSuffix(trimPrefix(content, trimStr), trimStr);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return str == null?true:str.trim().equals("");
    }

    public static boolean isZeroEmpty(String tmp) {
        boolean isEmpty = isEmpty(tmp);
        return isEmpty?true:"0".equals(tmp);
    }

    public static boolean isNotZeroEmpty(String tmp) {
        return !isZeroEmpty(tmp);
    }

    public static String toFirst(String str, boolean isUpper) {
        if(isEmpty(str)) {
            return "";
        } else {
            char first = str.charAt(0);
            String firstChar = new String(new char[]{first});
            firstChar = isUpper?firstChar.toUpperCase():firstChar.toLowerCase();
            return firstChar + str.substring(1);
        }
    }

    public static String replaceVariable(String content, String replace) {
        return replaceVariable(content, replace, "\\{(.*?)\\}");
    }

    public static String replaceVariable(String content, String replace, String regular) {
        Pattern regex = Pattern.compile(regular);
        String result = content;

        for(Matcher regexMatcher = regex.matcher(content); regexMatcher.find(); regexMatcher = regex.matcher(result)) {
            String toReplace = regexMatcher.group(0);
            result = result.replace(toReplace, replace);
        }

        return result;
    }

    public static String replaceVariableMap(String content, Map<String, Object> map) throws Exception {
        return replaceVariableMap(content, map, "\\{(.*?)\\}");
    }

    public static String replaceVariableMap(String template, Map<String, Object> map, String regular) throws Exception {
        Pattern regex = Pattern.compile(regular);
        Matcher regexMatcher = regex.matcher(template);

        while(regexMatcher.find()) {
            String key = regexMatcher.group(1);
            String toReplace = regexMatcher.group(0);
            String value = (String)map.get(key);
            if(value != null) {
                template = template.replace(toReplace, value);
            } else {
                template = template.replace(toReplace, "");
            }
        }

        return template;
    }

    public static String removeSpecial(String str) throws PatternSyntaxException {
        return removeByRegEx(str, SPECIAL_REG_EX);
    }

    public static String removeByRegEx(String str, String regEx) throws PatternSyntaxException {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static byte[] stringToBytes(String str) {
        byte[] digest = new byte[str.length() / 2];

        for(int i = 0; i < digest.length; ++i) {
            String byteString = str.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte)byteValue;
        }

        return digest;
    }

    public static String bytesToString(byte[] b) {
        StringBuffer hexString = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            String plainText = Integer.toHexString(255 & b[i]);
            if(plainText.length() < 2) {
                plainText = "0" + plainText;
            }

            hexString.append(plainText);
        }

        return hexString.toString();
    }

    public static String encodingString(String str, String from, String to) {
        String result;
        try {
            result = new String(str.getBytes(from), to);
        } catch (Exception var5) {
            result = str;
        }

        return result;
    }

    public static String convertDbFieldToField(String dbField, String symbol, boolean isIgnoreFirst) {
        String result = "";
        if(dbField.startsWith(symbol)) {
            dbField = dbField.substring(1);
        }

        if(dbField.endsWith(symbol)) {
            dbField = dbField.substring(0, dbField.length() - 1);
        }

        String[] arr = dbField.split(symbol);

        for(int i = 0; i < arr.length; ++i) {
            String str = arr[i];
            if(isIgnoreFirst && i != 0) {
                char oldChar = str.charAt(0);
                char newChar = (oldChar + "").toUpperCase().charAt(0);
                str = newChar + str.substring(1);
            }

            result = result + str;
        }

        return result;
    }

    public static String join(String[] vals, String separator) {
        if(BeanUtil.isEmpty(vals)) {
            return "";
        } else {
            String val = "";

            for(int i = 0; i < vals.length; ++i) {
                if(i == 0) {
                    val = val + vals[i];
                } else {
                    val = val + separator + vals[i];
                }
            }

            return val;
        }
    }

    public static String join(List<String> list, String joinSperator) {
        String rtnString = "";

        for(int i = 0; i < list.size(); ++i) {
            if(i == 0) {
                rtnString = (String)list.get(i);
            } else {
                rtnString = rtnString + joinSperator + (String)list.get(i);
            }
        }

        return rtnString;
    }

    public static String urlEncode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "utf-8");
    }

    public static String getRandomString(int length, boolean onlyNumber) {
        String base = "0123456789";
        if(!onlyNumber) {
            base = "abcdefghijklmnopqrstuvwxyz0123456789";
        }

        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }



    public static Set<String> getMacAddress() throws SocketException {
        HashSet set = new HashSet();
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while(e.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface)e.nextElement();
            byte[] mac = ni.getHardwareAddress();
            if(mac != null && mac.length > 0) {
                String str = getLocalMac(mac);
                set.add(str);
            }
        }

        return set;
    }

    private static String getLocalMac(byte[] mac) {
        StringBuffer sb = new StringBuffer("");

        for(int i = 0; i < mac.length; ++i) {
            if(i != 0) {
                sb.append("-");
            }

            int temp = mac[i] & 255;
            String str = Integer.toHexString(temp);
            if(str.length() == 1) {
                sb.append("0" + str);
            } else {
                sb.append(str);
            }
        }

        return sb.toString().toLowerCase();
    }


}
