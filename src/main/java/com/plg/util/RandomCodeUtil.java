package com.plg.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 随机码工具类
 */
public class RandomCodeUtil {
	private final static String ZERO = "0";
	private static final Integer MIN = 10000;
	private static final Integer MAX = 90000;
	private static Integer sequence = MIN;
	private static long  currentTimestamp = 0;
	private static StringBuffer sb = new StringBuffer();
	
	public synchronized static long createSerialSequence(){
		long time =  (System.currentTimeMillis()/1000);
		
		 if(time == currentTimestamp) {
			 if(sequence >= MAX) {
				 while(time <= currentTimestamp){
					 time = (System.currentTimeMillis()/1000);
				 }
				 sequence = MIN;
			 }
			 else {
				 sequence++;
			 }
		 }
		 else {
			 sequence = MIN;
			 currentTimestamp = time;
		 }
		
		String date = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
		 
		String str = sb.append(date).append(sequence).toString();
		sb.delete(0, sb.length());
		return Long.parseLong(str);
		 
	}
	
	/**
	 * 根据日期生成流水号
	 * @param format 日期格式 例如yyyyMMddHHmmss
	 * @param randomCount 后缀随机数的位数
	 * @return
	 */
	public static String createSerial(int randomCount) {  
    	String str = DateUtil.dateToString(new Date(), "yyyyMMddHHmmss");
    	
    	if(randomCount > 0){
    		str += RandomStringUtils.randomNumeric(randomCount);  
    	}
    	
    	return str;
    }
	
	/**
	 * 获取原始uuid字符串 包含-
	 * @return
	 */
	public static String getOriginalUUID(){
		UUID uuid = UUID.randomUUID();  
        return uuid.toString();  
	}
	
	/**
	 * 获取uuid字符串  不包含-
	 * @return
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp;
	}
	
	/**
	 * 获取数字型随机数 2位数以上最高位至少是1
	 * @param length
	 * @return
	 */
	public static long getRandomNumeric(int length) {
		if(length < 1){
			return 0;
		}
		String v = null;
		if(length == 1){
			v = RandomStringUtils.randomNumeric(length);
		}
		else {
			String t = RandomStringUtils.randomNumeric(1);
			
			if(ZERO.equals(t)){
				t = "1";
			}
			
			v =  t + RandomStringUtils.randomNumeric(length - 1);
		}
		
		return Long.parseLong(v);
	}
	
	/**
	 * 获取字符型随机数 最高位至少是0
	 * @param length
	 * @return
	 */
	public static String getRandomNumericString(int length) {
		if(length < 1){
			return "";
		}
		return RandomStringUtils.randomNumeric(length);
	}

	/**
	 * 生成字母和数字的随机码 字母区分大小写
	 * @param length
	 * @return
	 */
	public static String getRandomAlphanumeric(int length) {
		if(length < 1){
			return "";
		}
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	/**
	 * 生成字母的随机码 字母区分大小写
	 * @param length
	 * @return
	 */
	public static String randomAlphabetic(int length) {
		if(length < 1){
			return "";
		}
		return RandomStringUtils.randomAlphabetic(length);
	}
	
}
