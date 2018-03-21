package com.elex.oa.util;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 时间工具类
 * @author lill
 *
 */
public class TimeUtil {
	private static Logger logger= Logger.getLogger(TimeUtil.class);

	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获取当前时间戳
	 */
	public static String getCurrentTimeStr(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		date = date.replace("-", "").replace(":", "").replace(" ", "");
		return date;
	}
	
	/**
	 * 将字符串转为日期格式
	 * @param dateStr 待转化的字符串
	 * @param style 待转化日期格式  例"yyyy-MM-dd"
	 */
	public static Date strToDate(String dateStr,String style){
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error(e);
		}
		return date;
	}
	
	/**
	 * 将日期转化为字符串
	 * @param date 待转换日期
	 * @param style 转换格式
	 */
	public static String dateToStr(Date date,String style){
		SimpleDateFormat df = new SimpleDateFormat(style);//设置日期格式
		String dateStr = df.format(date);
		return dateStr;
	}
	
	/**
	 * 获取当前日期的零点时刻
	 */
	public static String getTodayZeroTime(){
		Calendar currentTime = Calendar.getInstance();
		currentTime.set(Calendar.HOUR_OF_DAY, 0);
		currentTime.set(Calendar.MINUTE, 0);
		currentTime.set(Calendar.SECOND, 0);
		Date d = currentTime.getTime();
		String s = dateToStr(d,"yyyy-MM-dd HH:mm:ss");
		return s;
	}

	/**
	 * 获取前一天的零点时刻
	 */
	public static String getYesterdayZeroTime(){
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime localDateTime1 = localDateTime.plusDays(-1);
		//年
		int year = localDateTime1.getYear();
		//月
		int month = localDateTime1.getMonthValue();
		//日
		int day = localDateTime1.getDayOfMonth();

		LocalDateTime localDateTime2 = LocalDateTime.of(year, month, day, 0, 0, 0, 0);
		String s = dtf.format(localDateTime2);
		return s;
	}
	
	/**
	 * 获取n个月之前的零点时刻
	 */
	public static String getMonthsBeforeZeroTime(int n){
		Calendar currentTime = Calendar.getInstance();
		currentTime.set(Calendar.MONTH, currentTime.get(Calendar.MONTH)-n);
		currentTime.set(Calendar.HOUR_OF_DAY, 0);
		currentTime.set(Calendar.MINUTE, 0);
		currentTime.set(Calendar.SECOND, 0);
		Date d = currentTime.getTime();
		String s = dateToStr(d,"yyyy-MM-dd HH:mm:ss");
		return s;
	}
	
	/**
	 * 判断当前时间是在凌晨1点之前还是之后
	 * @return 若在1点之前返回0；在1点之后则返回1
	 */
	public static String isOneAmBefore(){
		Calendar current = Calendar.getInstance();
		//小时
		int hour = current.get(Calendar.HOUR_OF_DAY);
		//分钟
		int minute = current.get(Calendar.MINUTE);
		//秒
		int second = current.get(Calendar.SECOND);
		String returnStr = "";
		if(hour>1){
			returnStr =  "1";
		}else if(hour==1){
			if(minute > 0 || second > 0){
				returnStr = "1";
			}
		}else{
			returnStr = "0";
		}
		return returnStr;
	}

	/**
	 * 生成最小值和最大之间的随机数
	 * @param min 最小值
	 * @param max 最大值
	 * @param digit 小数点后位数
	 * @return
	 */
	public static BigDecimal generateRandomNumber(final double min, final double max,int digit){
		BigDecimal bd = new BigDecimal(min + ((max - min) * new Random().nextDouble())).setScale(digit, BigDecimal.ROUND_HALF_UP);
		return bd;
	}

	/**
	 * 当前时间距第二天凌晨1点的毫秒数
	 */
	public static long getTimeInterval(){
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();

		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 1);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		long millis = c.getTimeInMillis() - now;

		return millis;
	}

	/**
	 * 生成随机登录时间
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static String randomStartDate(String beginDate,String  endDate ){

		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date start = format.parse(beginDate);//构造开始日期

			Date end = format.parse(endDate);//构造结束日期

			//getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。

			if(start.getTime() >= end.getTime()){

				return null;

			}

			long date = random(start.getTime(),end.getTime());

			Date d = new Date(date);

			String s = dateToStr(d,"yyyy-MM-dd HH:mm:ss");

			return s;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	private static long random(long begin,long end){

		long rtn = begin + (long)(Math.random() * (end - begin));

		//如果返回的是开始时间和结束时间，则递归调用本函数查找随机值

		if(rtn == begin || rtn == end){

			return random(begin,end);

		}

		return rtn;

	}

	/**
	 * 生成随机的登出时间
	 * @param beginDate 登录时间
	 * @return
	 */
	public static String randomEndDate(String beginDate,String  endDate ){

		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date start = format.parse(beginDate);//构造开始日期

			Date end = format.parse(endDate);//构造结束日期

			//getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。

			if(start.getTime() >= end.getTime()){

				return null;

			}

			long date = random1(start.getTime(),end.getTime());

			Date d = new Date(date);

			String s = dateToStr(d,"yyyy-MM-dd HH:mm:ss");

			return s;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	private static long random1(long begin,long end){

		long rtn = begin + 30*60*1000 +(long)(Math.random() * (end - begin));

		//如果返回的是开始时间和结束时间，则递归调用本函数查找随机值

		if(rtn == begin || rtn == end){

			return random(begin,end);

		}

		return rtn;

	}

	public static void main(String args[]){
		int num = (int)(Math.random()*50);
		System.out.print(num);
	}

}
