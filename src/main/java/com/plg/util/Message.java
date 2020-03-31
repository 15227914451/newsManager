/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.plg.util;

import java.util.HashMap;
import java.util.Map;

public  class Message {
	private static Map<String,String> messageMap = new HashMap<String, String>();  
    static {
    	messageMap.put("S000001", "操作成功。");
    	messageMap.put("E000000", "系统异常。");
    	messageMap.put("E000001", "用户不存在。");
    	messageMap.put("E000002", "账号或者密码不正确。");
    	messageMap.put("E000003", "参数不合法。");
    	messageMap.put("E000005", "该手机号已注册。");
    }
    
    public static String getMessage(String code) {
    	return messageMap.get(code);
    }
	
	
}