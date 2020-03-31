package com.plg.util;

public class StringUtil {
	
	/**
	 * 字符串脱敏 用*替换
	 * @param str
	 * @param toLeft 距离右侧字符数
	 * @param size 脱敏字符数
	 * @return
	 */
	public static String removeSensitiveInfo(String str, int toRight, int size) {
		String resultStr = "";
		
		if(str.length() > size) {
			int lastSize = str.length() - toRight;
			String firstStr = str.substring(0, lastSize);
			String lastStr = str.substring(lastSize);
			
			char[] c = firstStr.toCharArray();
			int j = 0;
			for(int i = c.length - 1; i >= 0; i--) {
				j++;

				if(size < j) {
					break;
				}
				
				c[i] = '*';
				
			}
			resultStr = String.copyValueOf(c) + lastStr;
		}
		else {
			resultStr = str;
		}
		
		return resultStr;
	}
	
	/**
	 * 左补字符串
	 * @param str
	 * @param c
	 * @param count
	 * @return
	 */
	public static String appendStringFromLeft(String str, String c, int count) {
		StringBuilder sb = new StringBuilder();
		
		if(count > 0) {
			for(int i = 0; i < count; i++) {
				sb.append(c);
			}
		}
		
		sb.append(str);

		return sb.toString();
	}
	
}
