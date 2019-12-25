package com.nolookblog.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description 日期工具类
 */

public class CustomerDateUtils extends DateUtils {

	/**
	 * 日期格式
	 */
	public static final String[] DATETIME_PATTERNS = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"
	};

	/**
	 * 当前时间日期加一天
	 *
	 * @param date
	 * @return
	 */
	public static Date addOneDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 1);

		return cal.getTime();
	}

	/**
	 * 获取当天的0点时间字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getTodayZeroDatetimeString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();

		return formatter.format(zero);
	}

	/**
	 * 获取当天的0点时间日期时间
	 *
	 * @return
	 */
	public static Date getTodayZeroDatetime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 获取昨天的23:59:59日期时间
	 *
	 * @return
	 */
	public static Date getYesterdayEndDatetime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new Date(cal.getTime().getTime() - 1);
	}

	/**
	 * 获取起止日期之间的日期
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getWithinDateTimeList(Date startDate, Date endDate) {
		List<Date> list = new ArrayList<>();

		long length = (endDate.getTime() - startDate.getTime()) / MILLIS_PER_DAY;
		for (long i = 0; i <= length; i++) {
			long time = startDate.getTime() + (MILLIS_PER_DAY * i);
			list.add(new Date(time));
		}

		return list;
	}

	/**
	 * 秒转换为日期时间
	 *
	 * @param seconds
	 * @return
	 */
	public static Date seconds2Date(Integer seconds) {
		return seconds == null ? new Date() : new Date(seconds * MILLIS_PER_SECOND);
	}

	/**
	 * 时间戳转换为日期时间
	 *
	 * @param millis
	 * @return
	 */
	public static Date millis2Date(Long millis) {
		return millis == null ? new Date() : new Date(millis);
	}

	/**
	 * 日期时间转换为时间戳
	 *
	 * @param date
	 * @return
	 */
	public static long date2Millis(Date date) {
		return date.getTime();
	}

	/**
	 * 获取本周周一日期字符串
	 *
	 * @return
	 */
	public static String getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		c.add(Calendar.DATE, -dayOfWeek + 1);

		return DateFormatUtils.format(c, DATETIME_PATTERNS[0]);
	}

	/**
	 * 获取本周周日日期字符串
	 *
	 * @return
	 */
	public static String getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		c.add(Calendar.DATE, -dayOfWeek + 7);

		return DateFormatUtils.format(c, DATETIME_PATTERNS[0]);
	}

	/**
	 * 获取本周周五日期字符串
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getFridayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		c.add(Calendar.DATE, -dayOfWeek + 5);

		return DateFormatUtils.format(c, DATETIME_PATTERNS[0]);
	}

	/**
	 * 获取上周周一日期字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getMondayOfLastWeek(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		// 一周
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return DateFormatUtils.format(cal, DATETIME_PATTERNS[0]);
	}

	/**
	 * 获取上周周日日期字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getSundayOfLastWeek(Date date) {
		Date a = DateUtils.addDays(date, -1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(a);
		cal.set(Calendar.DAY_OF_WEEK, 1);

		return DateFormatUtils.format(cal, DATETIME_PATTERNS[0]);
	}


//	/**
//	 * 获取过去的天数
//	 *
//	 * @param date
//	 * @return
//	 */
//	public static long pastDays(Date date) {
//		long t = System.currentTimeMillis() - date.getTime();
//		return t / (24 * 60 * 60 * 1000);
//	}

//	/**
//	 * 获取两个日期之间的天数
//	 *
//	 * @param before
//	 * @param after
//	 * @return
//	 */
//	public static double getDistanceOfTwoDate(Date before, Date after) {
//		long beforeTime = before.getTime();
//		long afterTime = after.getTime();
//		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
//	}

}
