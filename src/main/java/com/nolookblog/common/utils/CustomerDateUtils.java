package com.nolookblog.common.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
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
	public static String[] parsePatterns = {
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
	 * 获取当天的0点时间
	 *
	 * @param date
	 * @return
	 */
	public static String getTodayZeroDatetime(Date date) {
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
	 * 获取起止日期之间的日期
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getWithinDateList(Date startDate, Date endDate) {
		List<Date> list = new ArrayList<>();

		long length = (endDate.getTime() - startDate.getTime()) / MILLIS_PER_DAY;
		for (long i = 0; i <= length; i++) {
			long time = startDate.getTime() + (MILLIS_PER_DAY * i);
			list.add(new Date(time));
		}

		return list;
	}


}
