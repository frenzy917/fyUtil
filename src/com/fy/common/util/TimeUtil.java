package com.fy.common.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	public static Date getNextMonthBeginTime(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();

	}
	public static Date getNextDate(Date time, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(time); // 设置当前日期
		c.add(Calendar.DATE, days); // 日期加days
		Date date = c.getTime(); // 结果
		return date;
	}

	public static Date getPrevDate(Date time, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(time); // 设置当前日期
		c.add(Calendar.DATE, -days); // 日期加days
		Date date = c.getTime(); // 结果
		return date;
	}

	public static void wait(int minute) {
		try {
			Thread.sleep(minute * 1000 * 60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitSecond(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitMSecond(int msecond) {
		try {
			Thread.sleep(msecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] argv) throws Exception {
		System.out.println(getNextMonthBeginTime(new Date()));
	}
}
