package com.plg.util;


import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;



/**
 * 日期工具类
 */
public class DateUtil {
	
	public final static String START_TIME_OF_DAY = "00:00:00";
	public final static String END_TIME_OF_DAY = "23:59:59";
	
	public final static String FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 前端默认选不到秒
	 */
	public final static String FULL_FORMAT_NO_SECOND = "yyyy-MM-dd HH:mm";
	
	public final static String TIME_FORMAT = "HH:mm:ss";
	
	public final static String SIMPLE_TIME_FORMAT = "HH:mm";

	public final static String SIMPLE_FORMAT = "yyyy-MM-dd";
	
	public final static String SIMPLE_FORMAT_TWO = "dd/MM/yyyy";

	public final static String FORMAT = "yyyyMMddHHmmss";
	
	private static Calendar initCurrentDate()
	{
		Date date = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	public static int getCurrentYear()
	{
		Calendar c = initCurrentDate();
		return c.get(Calendar.YEAR);
	}
	public static int getCurrentMonth(){
		return initCurrentDate().get(Calendar.MONTH)+1;
	}
	public static int getCurrentDay(){
		return initCurrentDate().get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 取值范围0-23
	 */
	public static int getCurrentHour(){
		return initCurrentDate().get(Calendar.HOUR_OF_DAY);
	}
	public static int getCurrentMinute(){
		return initCurrentDate().get(Calendar.MINUTE);
	}
	public static int getCurrentSecond(){
		return initCurrentDate().get(Calendar.SECOND);
	}
	public static String getCurrentDate(String format){
		SimpleDateFormat parse = new SimpleDateFormat(format);
		return parse.format(new java.util.Date());
	}
	public static java.util.Date parseDate(String str, String parsePatterns) throws ParseException {
        if (str == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        
        SimpleDateFormat parser = null;
        ParsePosition pos = new ParsePosition(0);
                parser = new SimpleDateFormat(parsePatterns);
            pos.setIndex(0);
            java.util.Date date = parser.parse(str, pos);
            if (date != null && pos.getIndex() == str.length()) {
                return date;
            }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }
	
	
	public static String dateToString(java.util.Date date,String par)
	{
		if(date==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(par);
		return sdf.format(date);
	}
	/**
	 * 将日期字符串转换为长整型
	 * @param date 格式(yyyy-MM-dd)
	 * @return
	 */
	public static long strToDateLong(String date) {
		long l = 0L;
		try {
			Calendar ca = Calendar.getInstance();
			ca.setTime(java.sql.Date.valueOf(date));
			l=ca.getTimeInMillis();
		} catch (Exception e) {
			new Exception("日期字符串转长整形错误",e);
		}
		return l;
	}
	public static String longToStrDate(String format,long time){
		DateFormat df = new SimpleDateFormat(format);
		return df.format(new Date(time));
	}
	public static java.util.Date strToDate(String str, String parsePatterns) throws ParseException {
		return parseDate(str,parsePatterns);
	}
	

	/**
	 * 得到 n 天后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getAfterCurrentDay(int days) {
		Date result = null;
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.add(Calendar.DAY_OF_WEEK, +days);
			result = calendar.getTime();
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 得到当前日期的年月日时分秒
	 * 
	 * @return
	 */
	public static String getCurrentDateTimeString() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(FORMAT);
		return df.format(calendar.getTime());
	}

	/**
	 * 得到当前日期的年-月-日 时:分:秒
	 * 
	 * @return
	 */
	public static String getCurrentDateToString() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(FULL_FORMAT);
		return df.format(calendar.getTime());
	}

	/**
	 * 得到当前日期的年-月-日
	 * 
	 * @return
	 */
	public static String getCurrentDateSimpleToString() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT);
		return df.format(calendar.getTime());
	}

	/**
	 * 得到当前日期的年-月-日
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 得到当前日期的年-月-日
	 * 
	 * @return
	 */
	public static Timestamp getCurrentDateTimestamp() {
		Calendar calendar = Calendar.getInstance();
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * 得到当天到下周的日期
	 * 
	 * @return
	 */
	public static String getNextWeekToString() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_WEEK, +6);
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT);
		return df.format(calendar.getTime());
	}

	/**
	 * 得到明天的时间
	 * 
	 * @return
	 */
	public static String getNextDayToString(String day) {
		String result = "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT);
		try {
			calendar.setTime(df.parse(day));
			calendar.add(Calendar.DAY_OF_WEEK, +1);
			result = df.format(calendar.getTime());
		} catch (ParseException e) {
		}
		return result;
	}

	public static String getLastWeekToString() {
		String result = "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT);
		try {
			calendar.add(Calendar.WEEK_OF_MONTH, -1);// 周数减一，即上周
			result = df.format(calendar.getTime());
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 获取昨天日期
	 * 
	 * @param day
	 * @return
	 */
	public static String getYestarDayToString() {
		String result = "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT);
		try {
			calendar.add(Calendar.DATE, -1);
			result = df.format(calendar.getTime());
		} catch (Exception e) {
		}
		return result;
	}
	
	/**
	 * 获取昨天日期
	 * 
	 * @param day
	 * @return
	 */
	public static String getYestarDayToString(String format) {
		String result = "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			calendar.add(Calendar.DATE, -1);
			result = df.format(calendar.getTime());
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 字符串日期转Date类型
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToSimpleDate(String str) {
		if (StringUtils.isBlank(str))
			return null;
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 字符串日期转Date类型
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToFullDate(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(FULL_FORMAT);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 字符串日期转Date类型
	 * yyyy-MM-dd HH:mm
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToFullDateNoSecond(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(FULL_FORMAT_NO_SECOND);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Date类型转字符串日期
	 * 
	 * @param str
	 * @return
	 */
	public static String dateToSimpleString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT);
		return df.format(date);
	}

	/**
	 * Date类型转字符串日期
	 * 
	 * @param str
	 * @return
	 */
	public static String dateToFullString(Date date) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(FULL_FORMAT);
		return df.format(date);
	}
	
	/**
	 * Date类型转字符串日期
	 * 
	 * @param str
	 * @return
	 */
	public static String dateToFullStringNoSecond(Date date) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(FULL_FORMAT_NO_SECOND);
		return df.format(date);
	}
	
	
	
	public static int getWeekValue(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return w;
	}
	
	/**
	 * 根据日期得到星期几
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekValue(String date) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT);
		try {
			cal.setTime(df.parse(date));
		} catch (ParseException e) {
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return w;
	}

	/**
	 * 根据日期得到星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekChinese(String date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT);
		try {
			cal.setTime(df.parse(date));
		} catch (ParseException e) {
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 判断两个时间的大小 是否后面那个大于前面那个 格式 yyyy-MM-dd
	 * 
	 * @param startDate
	 * @param endDate
	 * @return true为是 false为否
	 */
	public static boolean judgeDate(String startStr, String endStr) {
		Date startDate = stringToSimpleDate(startStr);
		Date endDate = stringToSimpleDate(endStr);
		if (startDate.before(endDate)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两个时间的大小 是否后面那个大于或等于前面那个 格式 yyyy-MM-dd
	 * 
	 * @param startDate
	 * @param endDate
	 * @return true为是 false为否
	 */
	public static boolean judgeDateEqual(String startStr, String endStr) {
		Date startDate = stringToSimpleDate(startStr);
		Date endDate = stringToSimpleDate(endStr);
		if (startDate.before(endDate) || startDate.compareTo(endDate) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 得到多少天的日期 正数为加 负数为减
	 * 
	 * @param str
	 * @param day
	 * @return
	 */
	public static String getNumAfterDate(String str, int day) {
		Date startDate = stringToSimpleDate(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_WEEK, day);
		return dateToSimpleString(calendar.getTime());
	}

	/**
	 * 将UNIXTIME转为普通的时间
	 * 
	 * @param unixTime
	 * @return
	 */
	public static String unixTimeToString(String unixTime) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Long.parseLong(unixTime));
		return dateToSimpleString(c.getTime());
	}

	/**
	 * 将普通的日间转为UNIXTIME
	 * 
	 * @param date
	 * @return
	 */
	public static String stringToUnixTime(String date) {
		Calendar c = Calendar.getInstance();
		Date date2 = stringToSimpleDate(date);
		c.setTime(date2);
		return c.getTimeInMillis() + "";

	}

	/**
	 * 将时间字符串转化成Timestamp时间戳（String的类型必须形如： yyyy-mm-dd hh:mm:ss 这样的格式，否则报错）
	 * 
	 * @param tsStr
	 * @return Timestamp
	 */
	public static Timestamp stringToTimestamp(String tsStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(tsStr);
		} catch (Exception e) {
		}
		return ts;
	}

	/**
	 * 时间戳转字符串
	 * 
	 * @param cc_time
	 * @return
	 */
	public static String getStrByTimestamp(Timestamp cc_time) {
		SimpleDateFormat sdf = new SimpleDateFormat(FULL_FORMAT);
		return sdf.format(cc_time);
	}

	 

	@SuppressWarnings("static-access")
	public static Time strToTime(String strDate) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
		}
		java.sql.Time time = new java.sql.Time(d.getTime());
		return time.valueOf(str);
	}

	public static Time getTime() {
		Date d = new Date();
		// SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		java.sql.Time time = new java.sql.Time(d.getTime());
		return time;
	}
	
	/**
	 * dateB - dateA  的日期天数
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static int getBetweenDayNumber(Date dateA, Date dateB) {
		long dayNumber = 0;
		long DAY = 24L * 60L * 60L * 1000L;
		try {
			dayNumber = (dateB.getTime() - dateA.getTime()) / DAY;
		} catch (Exception e) {
		}
		return (int) dayNumber;
	}
	
	/**
	 * 计算两个日期相差的天数 date2-date1
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	/**
	 * 日期增加天数
	 * @param date
	 * @param addDays
	 * @return
	 */
	public static Date dateAddDays(Date date,int addDays){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, addDays);
		return calendar.getTime();
	}
	
	/**
	 * 日期增加小时
	 * @param date
	 * @param addHours
	 * @return
	 */
	public static Date dateAddHours(Date date,int addHours){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, addHours);
		return calendar.getTime();
	}
	
	/**
	 * 日期增加分钟
	 * @param date
	 * @param addMinutes
	 * @return
	 */
	public static Date dateAddMinutes(Date date,int addMinutes){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, addMinutes);
		return calendar.getTime();
	}
	/**
	 * Long类型时间戳转为yyyy-mm-dd HH:ss:mm
	 * @param date Long类型时间戳
	 * @return
	 */
	public static String getStrByLong(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FULL_FORMAT);
		return sdf.format(date * 1000);
	}
	/**
	 * 字符串转为Long类型时间戳
	 * @param time
	 * @return
	 */
	public static Long getLongBystr(String time) {
		//SimpleDateFormat sdf = new SimpleDateFormat(FULL_FORMAT);
		SimpleDateFormat format=new SimpleDateFormat(FULL_FORMAT);
		Date date;
		Long timestamp = null;
		try {
			date = format.parse(time);
			timestamp=date.getTime()/1000;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestamp;
		
	}
	/**
	 * 字符串日期转Date类型
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToSimpleDateTwo(String str) {
		if (StringUtils.isBlank(str))
			return null;
		SimpleDateFormat df = new SimpleDateFormat(SIMPLE_FORMAT_TWO);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		String str = getCurrentDate("yyyyMMdd");
		System.out.println(str);
	}
}
