package com.caizhixiang.springboot.ftp;

import com.caizhixiang.springboot.config.Constant;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Description: 日期工具类
 * @author: zhangfeibiao
 * @date: 2018年6月21日 上午11:36:56
 * @version: V1.0
 *
 */
@Slf4j
public final class DateUtil {

	public static final Date currentTime() {
		return new Date();
	}

	public static int calculateSecondsBetween(Date startDate, Date endDate) {
		BigDecimal milsDecimal = BigDecimal.valueOf(endDate.getTime() - startDate.getTime());
		return (int) Math.ceil(milsDecimal.divide(BigDecimal.valueOf(1000), 10, RoundingMode.CEILING).doubleValue());
	}

	public static String formatDate(Date date, String pattern) {
		if (date != null) {
			try {
				DateFormat sdf = new SimpleDateFormat(pattern);
				return sdf.format(date);
			} catch (Exception e) {
				return date.toString();
			}
		}

		return "";
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @param
	 * @return
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 * 
	 * @param
	 * @return
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * Date类型转换成String
	 *
	 * @param date
	 * @param pattern
	 * @return 日期文字描述
	 */
	public static final String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		DateFormat formatter = getDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * Calendar类型转换成String
	 *
	 * @param date
	 * @param pattern
	 * @return 日期文字描述
	 */
	public static final String format(Calendar date, String pattern) {
		return format(date.getTime(), pattern);
	}

	/**
	 * String类型转换成Date
	 *
	 * @param text
	 * @param pattern
	 * @return 日期文字描述
	 * @throws Exception
	 */
	public static final Date parseDate(String text, String pattern) {

		Date date = new Date();

		if (Strings.isNullOrEmpty(text)) {

			return null;
		}
		DateFormat formatter = getDateFormat(pattern);
		try {
			date = formatter.parse(text);
		} catch (ParseException e) {
			log.error("String类型转换成Date错误,参数：{}, exception:{}", text, pattern);
		}

		return date;
	}

	/**
	 * String类型转换成Calendar
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static final Calendar parseCalendar(String text, String pattern) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(text, pattern));
		return calendar;
	}

	/**
	 * 获取年月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public static final Date getFirstDayOfMonth(int year, int month) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取年月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public static final Date getLastDayOfMonth(int year, int month) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}

	/**
	 * 根据出生日期计算出年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static final Integer calculateAgeByBirthday(Date birthday) throws NumberFormatException {
		if (birthday == null) {
			return 0;
		}
		int birthdayYear = Integer.parseInt(format(birthday, Constant.FORMAT_YYYYMMDD).split("-")[0]);
		int birthdayMonth = Integer.parseInt(format(birthday, Constant.FORMAT_YYYYMMDD).split("-")[1]);
		int nowYear = Integer.parseInt(format(currentTime(), Constant.FORMAT_YYYYMMDD).split("-")[0]);
		int nowMonth = Integer.parseInt(format(currentTime(), Constant.FORMAT_YYYYMMDD).split("-")[1]);
		int age = nowYear - birthdayYear;
		if (nowMonth < birthdayMonth) {
			age--;
		}
		return age;
	}

	/**
	 * 时间加减
	 * 
	 * @param source
	 * @param year
	 * @param month
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static final Date dateAdd(Date source, int year, int month, int date, int hour, int minute, int second) {
		if (source == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(source);
		c.add(Calendar.YEAR, year);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DATE, date);
		c.add(Calendar.HOUR, hour);
		c.add(Calendar.MINUTE, minute);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}

	/**
	 * 获取正确的DateFormat对象
	 *
	 * @param pattern
	 * @return
	 */
	private static final DateFormat getDateFormat(String pattern) {
		DateFormat df = new SimpleDateFormat(pattern, Locale.CHINA);
		return df;
	}

	/**
	 * 时间戳转化为Date
	 * 
	 * @Title timeStampToDate @Description TODO @param timeStamp @param @param
	 *        ParseException @return Date @throws
	 */
	public static final Date timeStampToDate(Long timeStamp) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_YYYYMMDD_HHMISS);
		String d = format.format(timeStamp);
		Date date = format.parse(d);
		return date;
	}

	// year, month, day都是自然日期
	public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 是否是相同一天
	 * 
	 * @Title isSameDate
	 * @Description TODO
	 * @param date1
	 * @param date2
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}

	/**
	 * 判断是否是时间类型
	 * 
	 * @Title isDate @Description TODO @param date @param pattern @throws
	 */
	public static boolean isDate(String date, String pattern) {
		try {
			DateFormat sdf = new SimpleDateFormat(pattern);
			sdf.parse(date);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
