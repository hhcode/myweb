package com.huang.util;

import java.util.Map;

/**
 *
 * @author huang
 * @since 2016年12月30日 下午10:06:50
 */
public class CheckUtil {
	/**
	 * 判断paraMap中参数是否为null
	 * 
	 * @param paraMap
	 * @param paraNames
	 * @return
	 */
	public static boolean checkParaNotNull(Map<String, Object> paraMap, String... paraNames) {
		for (int i = 0; i < paraNames.length; i++) {
			String paraName = paraNames[i];
			if(paraName == null){
				continue ;
			}
			Object value = paraMap.get(paraName);
			if (value == null) {
				return false;
			}
			if(value instanceof String){
				String valStr = (String)value;
				if("".equals(valStr.trim()) || "null".equals(valStr)){
					return false;
				}
			}
		}
		return true;
	}
}
