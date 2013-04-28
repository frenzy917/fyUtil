package com.fy.common.util;

import java.util.Date;
import java.util.Calendar;
import java.math.BigDecimal;
import java.util.StringTokenizer;

/**
 * 字符串转换类
 * 
 */
public class Convert {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static int getint(String s) {
		int i = 0;
		if (s == null)
			return 0;
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
		return i;
	}

	public static byte getbyte(String s) {
		byte b = 0;
		if (s == null)
			return 0;
		try {
			b = Byte.parseByte(s);
		} catch (Exception e) {
			return 0;
		}
		return b;
	}

	public static short getshort(String s) {
		short i = 0;
		if (s == null)
			return 0;
		try {
			i = Short.parseShort(s);
		} catch (Exception e) {
			return 0;
		}
		return i;
	}

	public static long getlong(String s) {
		long l = 0;
		if (s == null)
			return 0;
		try {
			l = Long.parseLong(s);
		} catch (Exception e) {
			return 0;
		}
		return l;
	}

	public static float getfloat(String s) {
		float f = 0;
		if (s == null)
			return 0;
		try {
			f = Float.parseFloat(s);
		} catch (Exception e) {
			return 0;
		}
		return f;
	}

	public static double getdouble(String s) {
		double d = 0;
		if (s == null)
			return 0;
		try {
			d = Double.parseDouble(s);
		} catch (Exception e) {
			return 0;
		}
		return d;
	}

	public static double getPercent(String num) {
		return getPercent(num, 2);
	}

	public static double getPercent(String num, int scale) {
		BigDecimal bd = new BigDecimal(num);
		bd.multiply(new BigDecimal("100"));
		return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double getDouble(String num, int scale) {
		BigDecimal bd = new BigDecimal(num);
		return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static Date getDate(String s) {
		Date d = null;
		if (s == null)
			return null;

		try { // d=new java.text.SimpleDateFormat("yyyy-MM-dd").parse(s);
			if (s.length() > 10)
				d = new java.text.SimpleDateFormat(TIME_FORMAT).parse(s);
			else
				d = new java.text.SimpleDateFormat(DATE_FORMAT).parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	public static Date getDateTime(String s, String dateformat) {
		Date d = null;
		if (s == null)
			return null;

		try {
			d = new java.text.SimpleDateFormat(dateformat).parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	public static boolean getboolean(String s) {
		if (s == null)
			return false;
		if (s.trim().toLowerCase().equals("true"))
			return true;
		if (s.trim().equals("1"))
			return true;
		return false;
	}

	public static String getString(String s) {
		return s;
	}

	public static String getStringKey(String s) {
		return s;
	}

	public static BigDecimal getBigDecimal(String s) {
		return new BigDecimal(s);
	}

	public static char getchar(String s) {
		if (s.length() == 0)
			return ' ';
		char[] chs = s.toCharArray();
		return chs[0];
	}

	public static Date getDate(String s, int off) {
		Date d = getDate(s);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, off);
		return c.getTime();
	}

	public static Date now() {
		return Calendar.getInstance().getTime();
	}

	public static Boolean getBoolean(String s) {
		return new Boolean(getboolean(s));
	}

	public static Integer getInteger(String s) {
		return new Integer(getint(s));
	}

	public static Byte getByte(String s) {
		return new Byte(s);
	}

	public static Short getShort(String s) {
		return new Short(getshort(s));
	}

	public static Long getLong(String s) {
		return new Long(getlong(s));
	}

	public static Float getFloat(String s) {
		return new Float(getfloat(s));
	}

	public static Double getDouble(String s) {
		return new Double(getdouble(s));
	}

	/**
	 * replace [r] to [t] in [str]
	 */
	public static String replace(String str, String r, String t) {
		if (r == null)
			return str;
		if (str == null)
			return str;

		int p = str.indexOf(r);
		int last = 0;
		if (p == -1)
			return str;
		StringBuffer strb = new StringBuffer(str.length() << 1);

		while (p >= 0) {
			strb.append(str.substring(last, p));
			if (t != null)
				strb.append(t);
			last = p + r.length();
			p = str.indexOf(r, last);
		}
		strb.append(str.substring(last));
		return strb.toString();
	}

	public static String removeInvalidChar(String s, String invalid) {
		if (s == null)
			return null;
		StringBuffer strb = new StringBuffer(s.length());
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (invalid.indexOf(c) == -1)
				strb.append(c);
		}
		return strb.toString();
	}

	public static String[] split(String str, String s) {
		if (str == null)
			return null;

		if (s == null)
			return new String[] { str };

		StringTokenizer st = new StringTokenizer(str, s);
		String[] r = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			r[i++] = st.nextToken();
		}
		return r;

	}

	public static String removeTag(String html) {
		if (html == null)
			return null;
		StringBuffer strb = new StringBuffer(html.length());
		int begin = 0;
		int p = html.indexOf("<");
		while (p != -1 && p < html.length() - 1) {
			if (html.charAt(p + 1) < 128) {
				int p1 = html.indexOf(">", p + 1);
				int p2 = html.indexOf("<", p + 1);
				if (p1 != -1 && (p1 < p2 || p2 == -1)) {
					strb.append(html.substring(begin, p));
					begin = p1 + 1;
				}
				p = p2;
			} else
				p = html.indexOf("<", p + 1);

		}
		strb.append(html.substring(begin));
		String str = strb.toString();
		str = Convert.replace(str, "&nbsp;", " ");
		str = Convert.replace(str, "&amp;", "&");
		str = Convert.replace(str, "&lt;", "<");
		str = Convert.replace(str, "&gt;", ">");
		str = Convert.replace(str, "&brvbar;", "?");
		str = Convert.replace(str, "&quot;", "\"");
		str = Convert.replace(str, "&middot;", "·");
		str = Convert.replace(str, "&bull;", "·");
		return trim(str);
	}

	public static String trim(String str) {
		if (str == null)
			return null;
		int s = 0;
		for (s = 0; s < str.length(); s++) {
			char c = str.charAt(s);
			if (c != ' ' && c != '\r' && c != '\n' && c != '\t')
				break;
		}
		if (s == str.length())
			return "";
		int e = 0;
		for (e = str.length() - 1; e >= 0; e--) {
			char c = str.charAt(e);
			if (c != ' ' && c != '\r' && c != '\n' && c != '\t')
				break;
		}
		return str.substring(s, e + 1);
	}

	public static void main(String[] arv) {
		// System.out.println(replace("&nbsp;&nbsp;&nbsp;请订阅手机新东方英语全部&gt;&gt;","&gt;",">"));
		// System.out.println(replace("222","222","333"));
		// System.out.println(removeTag("<a>bbbb</a>"));
		System.out.println(trim("111\n"));

	}
}
