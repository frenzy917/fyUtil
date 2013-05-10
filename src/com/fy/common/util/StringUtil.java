package com.fy.common.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	/*
	 * 分离算法 算法: 1. 读取到"时，判断是否是新子段的第一个，即t是否为空,如果不为空，表明该"是个子串的一部分，
	 * 将"添加到子串中，并读取下一个字符；否则，尝试将该文本子串找出来。具体是：查找下一个", ，
	 * 如果找不到，判断本字符是否最后是一个字符，如果不是就判断最后一个字符是否是",如果是，
	 * 说明剩下的部分是一个""文本子串。读取完文本子串后，移动字符位置索引到文本串的下一个。 2.
	 * 读取到,将之前的子串记录到list中，并将子串清空，并移动字符位置索引 3. 其他时候，将该字符记录到子串中，并移动字符位置索引 4.
	 * 读取完毕后，将最后一个字串记录到list中（最后一个字串都是不好记录到list中）
	 * 
	 * 注：只有读取到,或者"***”,时，才会主动将字串记录到list，并清空子串
	 */
	public static String[] split(String str, char s) {
		if (str == null)
			return null;

		List l = new ArrayList();
		String t = "";
		int len = str.length();

		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (c == '\"') {// 判断是否进入字符串
				if (t.isEmpty()) {
					int pos = str.indexOf("\",", i + 1);
					if (pos == -1) {
						if (i + 1 == len) {// 最后一个字符串"
							t = "\"";
							break;
						} else {
							char c1 = str.charAt(len - 1);
							if (c1 == '\"') {
								t = str.substring(i + 1, len - 1);
								break;
							} else {
								t += c;
							}
						}
					} else {
						t = str.substring(i + 1, pos);
						l.add(t);
						t = "";
						i = pos + 1;// 因为i++，所以本来应该+2，就只能+1
					}
				} else
					t += c;
			} else if (c == s) {
				// 将之前内容加到list
				l.add(t);
				t = "";
			} else
				t += c;
		}
		l.add(t);

		String e[] = new String[l.size()];
		for (int i = 0; i < l.size(); i++) {
			String a = ((String) l.get(i)).trim();
			e[i] = a;
		}
		return e;

	}

	/**
	 * 截取字符串 从第一个出现开始字符截取,到第一个开始字符后面的第一个结束字符
	 * 如果开始字符不存在则从第一个字符开始
	 * @param begin
	 * @param end
	 * @param content
	 * @return
	 */
	public static String subString(String begin, String end, String content) {
		if (content == null || content.trim().length() == 0) {
			return content;
		}
		if ((begin == null || begin.trim().length() == 0) && (end == null || end.trim().length() == 0)) {
			return content;
		}

		if (!(begin == null || begin.trim().length() == 0)) {
			int index_s = content.indexOf(begin);
			if (index_s > -1) {
				index_s = index_s + begin.length();
				content = content.substring(index_s);
			} 
		}

		if (!(end == null || end.trim().length() == 0)) {
			int index_e = content.indexOf(end);
			if (index_e > -1) {
				return content.substring(0, index_e);
			} else {
				return content;
			}
		} 
		return content;
	}

	public static void main(String[] argv) throws Exception {
		// ,1,2,3""",5","6",,,"10,"
		// String
		// ss[]=split("\"0,\"8,\",1,2,3\"\"\",4\",\"5\",,,\"8,\",\"10aaaaa\"",',');
		// for(int i=0;i<ss.length;i++)
		// System.out.println(""+i+":"+ss[i]);
		
		System.out.println(subString("1","2","213"));

	}
}
