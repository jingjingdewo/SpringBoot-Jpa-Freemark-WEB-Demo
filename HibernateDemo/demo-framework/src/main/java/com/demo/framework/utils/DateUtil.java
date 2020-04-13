package com.demo.framework.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

	public static String YEAR = "yyyy";

	public static String MONTH = "MM";

	public static String DAY = "dd";

	public static String HOUR = "hh";

	public static String HOUR_24 = "HH";

	public static String MIMUTE = "mm";

	public static String SECOND = "ss";

	public static String MILLISECOND = "SS";

	public static String MD_FORMAT_NOSPACE = MONTH + DAY;

	public static String YM_FORMAT = YEAR + "-" + MONTH;

	public static String YM_FORMAT_NOSPACE = YEAR + MONTH;

	public static String YMD_FORMAT = YEAR + "-" + MONTH + "-" + DAY;

	public static String YMD_FORMAT_NOSPACE = YEAR + MONTH + DAY;

	public static String YMDHM_FORMAT = YEAR + "-" + MONTH + "-" + DAY + " " + HOUR_24 + ":" + MIMUTE;

	public static String YMDHM_FORMAT_NOSPACE = YEAR + MONTH + DAY + HOUR_24 + MIMUTE;

	public static String YMDHMS_FORMAT = YEAR + "-" + MONTH + "-" + DAY + " " + HOUR_24 + ":" + MIMUTE + ":" + SECOND;

	public static String YMDHMS_FORMAT_NOSPACE = YEAR + MONTH + DAY + HOUR_24 + MIMUTE + SECOND;

	public static String YMDHMS_FORMAT_SPACE = YEAR + MONTH + DAY + " " + HOUR_24 + ":" + MIMUTE + ":" + SECOND;

	public static String UTILTIME_FORMAT = YEAR + "-" + MONTH + "-" + DAY + " " + HOUR_24 + ":" + MIMUTE + ":" + SECOND
			+ ":" + MILLISECOND;

	public static String UTILTIME_FORMAT_NOSPACE = YEAR + MONTH + DAY + HOUR_24 + MIMUTE + SECOND + MILLISECOND;

	/* 中国科学院国家授时中心 */
	public static String BEIJING_TIME_URL = "http://www.time.ac.cn/stime.asp";

	public static String getDateByFormat(String format) {
		String dateStr = "";
		try {
			if (format != null) {
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat simFormat = new SimpleDateFormat(format, Locale.CHINA);
				dateStr = simFormat.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	public static String getDate(String format) {
		return getDateByFormat(format);
	}

	public static Date getCurrentTime() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 获取特定日期的指定格式日期字符串
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String getDateByFormat(String format, Date date) {
		String dateStr = "";
		try {
			if (format != null && date != null) {
				SimpleDateFormat simFormat = new SimpleDateFormat(format, Locale.CHINA);
				dateStr = simFormat.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 自定类型的时间字符串转换为Date类型
	 * 
	 * @param format
	 * @param time
	 * @return
	 */
	public static Date getDateByFormat(String format, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (StringUtils.isEmpty(time)) {
			return null;
		}
		try {
			Date date = sdf.parse(time);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前日期后日期
	 * 
	 * @param format
	 * @param type   YEAR,MONTH,DATE
	 * @param value
	 * @return
	 */
	public static String getDayAfter(String format, String type, Integer value) {
		String dateStr = "";
		try {
			if (format != null) {
				Calendar cal = Calendar.getInstance(Locale.CHINA);
				if (type.equals(DateUtil.YEAR)) {
					cal.add(Calendar.YEAR, +value);
				} else if (type.equals(DateUtil.MONTH)) {
					cal.add(Calendar.MONTH, +value);
				} else {
					cal.add(Calendar.DATE, +value);
				}
				long date = cal.getTimeInMillis();
				SimpleDateFormat simFormat = new SimpleDateFormat(format, Locale.CHINA);
				dateStr = simFormat.format(new Date(date));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 获取当前日期的前日期
	 * 
	 * @param format
	 * @param type   YEAR,MONTH,DATE
	 * @param value
	 * @return
	 */
	public static String getDayBefore(String format, String type, Integer value) {
		String dateStr = "";
		try {
			if (format != null) {
				Calendar cal = Calendar.getInstance(Locale.CHINA);
				if (type.equals(DateUtil.YEAR)) {
					cal.add(Calendar.YEAR, -value);
				} else if (type.equals(DateUtil.MONTH)) {
					cal.add(Calendar.MONTH, -value);
				} else {
					cal.add(Calendar.DATE, -value);
				}
				long date = cal.getTimeInMillis();
				SimpleDateFormat simFormat = new SimpleDateFormat(format, Locale.CHINA);
				dateStr = simFormat.format(new Date(date));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 获取某日期的前日期
	 * 
	 * @param format
	 * @param type   YEAR,MONTH,DATE
	 * @param value
	 * @return
	 */
	public static String getDayBefore(String date, String format, String type, Integer value) {
		String dateStr = "";
		try {
			if (format != null) {
				Calendar cal = Calendar.getInstance(Locale.CHINA);
				if (type.equals(DateUtil.YEAR)) {
					cal.add(Calendar.YEAR, -value);
				} else if (type.equals(DateUtil.MONTH)) {
					cal.add(Calendar.MONTH, -value);
				} else {
					cal.add(Calendar.DATE, -value);
				}
				long dateTime = cal.getTimeInMillis();
				SimpleDateFormat simFormat = new SimpleDateFormat(format, Locale.CHINA);
				dateStr = simFormat.format(new Date(dateTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 获取某日期的后日期
	 * 
	 * @param format
	 * @param type   Calendar.YEAR,Calendar.MONTH,Calendar.DATE
	 * @param value
	 * @return
	 */
	public static Date getDateAfter(Date date, int field, Integer value) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setTime(date);
		cal.add(field, value);
		return cal.getTime();
	}

	/**
	 * 获取当前年/月的第一个天
	 * 
	 * @param format
	 * @param type   YEAR,MONTH
	 * @return
	 */
	public static String getDayBegin(String format, String type) {
		String dateStr = "";
		try {
			if (format != null) {
				Calendar cal = Calendar.getInstance(Locale.CHINA);
				if (type.equals(DateUtil.YEAR)) {
					cal.set(Calendar.MONTH, 1);
					cal.set(Calendar.DATE, 1);
				} else if (type.equals(DateUtil.MONTH)) {
					cal.set(Calendar.DATE, 1);
				}
				long date = cal.getTimeInMillis();
				SimpleDateFormat simFormat = new SimpleDateFormat(format, Locale.CHINA);
				dateStr = simFormat.format(new Date(date));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * 判断当前时间是否在某个时间区间内
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return -1：区间前 1：区间后 0：区间中
	 */
	public static int judgeDateBetween(Date beginDate, Date endDate) {
		Date now = new Date();
		if (now.before(beginDate)) {
			return -1;
		} else if (now.after(endDate)) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
	public static Integer getCurrentYear() {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Integer year = calendar.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static Integer getCurrentMonth() {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Integer month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 获取当前日
	 * 
	 * @return
	 */
	public static Integer getCurrentDay() {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		Integer day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 获取指定时间的月份有几周
	 * 
	 * @param calendar
	 * @return
	 */
	public static Integer getWeekNumber(Calendar calendar) {
		double days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		double firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return (int) Math.ceil((firstDayOfWeek + days) / 7);
	}

	/**
	 * 获取指定日期前一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static Date getSpecifiedDayBefore(String format, String specifiedDay, int day) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		c.add(Calendar.DATE, -day);
		return c.getTime();
	}

	public static Date getSpecifiedDayBefore(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -day);
		return c.getTime();
	}

	/**
	 * 获取指定日期后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static Date getSpecifiedDayAfter(String format, String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		return c.getTime();
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long getTimeSubtractionResult(Date startDate, Date endDate) {
		if (startDate != null && endDate != null) {
			return ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24) + 1;
		}
		return 0L;
	}

	/**
	 * 获取2个日期的天数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int getDaySpan(String begin, String end, String format) {
		Date c1 = getDateByFormat(format, begin);
		Date c2 = getDateByFormat(format, end);
		double timeLong = c2.getTime() - c1.getTime();
		int dayNum = (int) (((timeLong / 1000) / 3600) / 24);
		return dayNum;
	}

	/**
	 * 获取与当前日期相隔天数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int getDaySpan(Date data) {
		double timeLong = System.currentTimeMillis() - data.getTime();
		int dayNum = (int) (((timeLong / 1000) / 3600) / 24);
		return dayNum;
	}

	/**
	 * 验证字符串是否为符合日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static boolean checkDateString(String date, String format) {
		SimpleDateFormat dFormat = new SimpleDateFormat(format);
		dFormat.setLenient(false);
		try {
			dFormat.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean checkDateFormat(String date) {
		String eL = "^((//d{2}(([02468][048])|([13579][26]))[//-/////s]?((((0?[13578])|(1[02]))[//-/////s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[//-/////s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[//-/////s]?((0?[1-9])|([1-2][0-9])))))|(//d{2}(([02468][1235679])|([13579][01345789]))[//-/////s]?((((0?[13578])|(1[02]))[//-/////s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[//-/////s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[//-/////s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(//s(((0?[0-9])|([1][0-9])|([2][0-3]))//:([0-5]?[0-9])((//s)|(//:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(date);
		boolean b = m.matches();
		return b;
	}

	public static Date getDayBeginOrEnd(Date date, boolean begin) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (begin) {
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
		} else {
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 23);
			c.set(Calendar.SECOND, 59);
		}
		return c.getTime();
	}

	public static Date getDayBeginOrEnd(String format, String time, boolean begin) {
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.getDateByFormat(format, time));
		if (begin) {
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
		} else {
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 23);
			c.set(Calendar.SECOND, 59);
		}
		return c.getTime();
	}

	/**
	 * 
	 * @Title: getDateOfBeijingTime
	 * @Description: 从中国网络授时中心获取北京时间
	 * @return
	 */
	public static Date getDateOfBeijingTime() {
		Date date = null;
		try {
//			URL url = new URL(BEIJING_TIME_URL);
			URL url = new URL("http://www.baidu.com");
			URLConnection uc = url.openConnection();
			uc.connect();
			long timestamp = uc.getDate();
			date = new Date(timestamp);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Bean \"" + DateUtil.class.getName() + "\" get Beijing Time error！");
		}
		return date;
	}

//	/**
//	 *
//	 * @Title: modifyLocalTimeByBeijinTime
//	 * @Description: 修改本地时间为标准北京时间-windows/linux系统
//	 */
//	public static void modifyLocalTimeByBeijinTime() {
//		String cmd, dateStr;
//		Date beijing = getDateOfBeijingTime();
//		try {
//			if (OSInfoUtil.isWindows()) {
//				cmd = "cmd /c";
//				dateStr = getDateByFormat(YMDHMS_FORMAT, beijing);
//				Runtime.getRuntime().exec(cmd + " time " + dateStr.substring(11));
//				Runtime.getRuntime().exec(cmd + " date " + dateStr.substring(0, 10));
//			} else if (OSInfoUtil.isLinux()) {
//				dateStr = getDateByFormat(YMDHMS_FORMAT_SPACE, beijing);
//				cmd = "  date -s " + dateStr.substring(0, 8);
//				Runtime.getRuntime().exec(cmd);
//				cmd = "  date -s " + dateStr.substring(9);
//				Runtime.getRuntime().exec(cmd);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * @param month    从1开始
	 * @param timeType null:不处理时分秒 false:零点 true:23点
	 * @return
	 */
	public static Date getMonthFistDay(int year, int month, Boolean endTime) {
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.MONTH, month - 1);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		if (endTime != null) {
			if (endTime) {
				cale.set(Calendar.HOUR_OF_DAY, 23);
				cale.set(Calendar.MINUTE, 59);
				cale.set(Calendar.SECOND, 59);
				cale.set(Calendar.MILLISECOND, 999);
			} else {
				cale.set(Calendar.HOUR_OF_DAY, 0);
				cale.set(Calendar.MINUTE, 0);
				cale.set(Calendar.SECOND, 0);
				cale.set(Calendar.MILLISECOND, 0);
			}
		}
		return cale.getTime();
	}

	/**
	 * 
	 * @param month   从1开始
	 * @param endTime
	 * @return
	 */
	public static Date getMonthLastDay(int year, int month, Boolean endTime) {
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.MONTH, month);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		if (endTime != null) {
			if (endTime) {
				cale.set(Calendar.HOUR_OF_DAY, 23);
				cale.set(Calendar.MINUTE, 59);
				cale.set(Calendar.SECOND, 59);
				cale.set(Calendar.MILLISECOND, 999);
			} else {
				cale.set(Calendar.HOUR_OF_DAY, 0);
				cale.set(Calendar.MINUTE, 0);
				cale.set(Calendar.SECOND, 0);
				cale.set(Calendar.MILLISECOND, 0);
			}
		}
		return cale.getTime();
	}

	public static Date getDate(String date, boolean begin) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(DateUtil.getDateByFormat(YMD_FORMAT, date));
		if (!begin) {
			cale.set(Calendar.HOUR_OF_DAY, 23);
			cale.set(Calendar.MINUTE, 59);
			cale.set(Calendar.SECOND, 59);
			cale.set(Calendar.MILLISECOND, 999);
		}
		return cale.getTime();
	}

	public static void main(String[] args) {
		getDateOfBeijingTime();
	}

}
